package edu.F1.team.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Repository
public class TeamRepo {

    @Autowired
    private BeanPropertyRowMapper<TeamView> beanPropertyRowMapper;

    @Autowired
    private BeanPropertyRowMapper<TeamEntity> teamEntityRowMapper;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<TeamView> findAll() {
        var sql = """
                SELECT team.team_id,
                       team.name,
                       SUM(position_points.points) AS points
                FROM team
                         LEFT JOIN driver ON driver.team_id = team.team_id
                         LEFT JOIN driver_weekend_result ON driver.driver_id = driver_weekend_result.driver_id
                         LEFT JOIN position_points ON position_points.position = driver_weekend_result.position
                GROUP BY team.team_id
                ORDER BY points DESC
                """;
        return jdbcTemplate.query(sql, beanPropertyRowMapper);
    }

    public void update(TeamEntity teamEntity) {
        var sql = """
                UPDATE team
                SET name=:name
                WHERE team_id=:team_id
                """;
        var paramsMap = Map.<String, Object>of(
                "team_id", teamEntity.getTeamId(),
                "name", teamEntity.getName()
        );
        jdbcTemplate.update(sql, paramsMap);
    }

    public void delete(Integer teamId) {
        var sql = """
                DELETE FROM team
                WHERE team_id=:team_id
                """;
        var paramsMap = Map.<String, Object>of(
                "team_id", teamId);
        jdbcTemplate.update(sql, paramsMap);
    }
}
