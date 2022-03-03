package edu.F1.driver.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Repository
public class DriverRepo {

    @Autowired
    private BeanPropertyRowMapper<DriverView> driverViewRowMapper;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<DriverView> findAll() {
        var sql = """
                SELECT driver.driver_id,
                       driver.name,
                       driver.number,
                       driver.team_id,
                       team.name AS team_name,
                       COALESCE(SUM(position_points.points), 0) AS points
                FROM driver
                         LEFT JOIN team ON driver.team_id = team.team_id
                         LEFT JOIN driver_weekend_result ON driver.driver_id = driver_weekend_result.driver_id
                         LEFT JOIN position_points ON driver_weekend_result.position = position_points.position
                GROUP BY driver.driver_id
                ORDER BY points DESC
                """;
        return jdbcTemplate.query(sql, driverViewRowMapper);
    }

    public void update(DriverEntity driverEntity) {
        var sql = """
                UPDATE driver
                SET name=:name, number=:number, team_id=:team_id
                WHERE driver_id=:driver_id
                """;
        var paramsMap = Map.<String, Object>of(
                "driver_id", driverEntity.getDriverId(),
                "name", driverEntity.getName(),
                "number", driverEntity.getNumber(),
                "team_id", driverEntity.getTeamId());
        jdbcTemplate.update(sql, paramsMap);
    }

    public void delete(Integer driverId) {
        var sql = """
                DELETE FROM driver
                WHERE driver_id=:driver_id
                """;
        var paramsMap = Map.<String, Object>of(
                "driver_id", driverId);
        jdbcTemplate.update(sql, paramsMap);
    }

    public void insert(DriverEntity entity) {
        var sql = """
                INSERT INTO driver(name, number, team_id)
                VALUES (:name, :number, :team_id)
                """;
        var paramsMap = Map.<String, Object>of(
                "name", entity.getName(),
                "number", entity.getNumber(),
                "team_id", entity.getTeamId()
        );
        jdbcTemplate.update(sql, paramsMap);
    }
}
