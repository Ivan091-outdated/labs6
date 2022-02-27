package edu.F1.weekend.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Repository
public final class WeekendRepo {

    @Autowired
    private BeanPropertyRowMapper<WeekendView> beanPropertyRowMapper;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<WeekendView> findAll() {
        var sql = """
                SELECT weekend.weekend_id,
                       weekend.name,
                       weekend.race_date,
                       driver.name AS winner_name
                FROM weekend
                LEFT JOIN driver_weekend_result ON weekend.weekend_id = driver_weekend_result.weekend_id
                LEFT JOIN driver ON driver_weekend_result.driver_id = driver.driver_id
                WHERE driver_weekend_result.position = 1
                ORDER BY weekend.race_date
                """;
        return jdbcTemplate.query(sql, beanPropertyRowMapper);
    }

    public void update(WeekendEntity weekendEntity) {
        var sql = """
                UPDATE weekend
                SET name = :name, race_date = :race_date
                WHERE weekend_id = :weekend_id
                """;
        var paramsMap = Map.<String, Object>of(
                "name", weekendEntity.getName(),
                "race_date", weekendEntity.getRaceDate(),
                "weekend_id", weekendEntity.getWeekendId()
        );
        jdbcTemplate.update(sql, paramsMap);
    }

    public void delete(Integer weekendId) {
        var sql = """
                DELETE FROM weekend
                WHERE weekend_id = :weekend_id
                """;
        var paramsMap = Map.<String, Object>of(
                "weekend_id", weekendId
        );
        jdbcTemplate.update(sql, paramsMap);
    }
}
