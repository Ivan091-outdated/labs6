package edu.F1.config;

import edu.F1.driver.repo.DriverView;
import edu.F1.team.repo.TeamEntity;
import edu.F1.team.repo.TeamView;
import edu.F1.weekend.repo.WeekendView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


@Configuration
public class Repo {

    @Bean
    public BeanPropertyRowMapper<DriverView> driverViewRowMapper() {
        return new BeanPropertyRowMapper<>(DriverView.class);
    }

    @Bean
    public BeanPropertyRowMapper<TeamView> teamViewRowMapper() {
        return new BeanPropertyRowMapper<>(TeamView.class);
    }

    @Bean
    public BeanPropertyRowMapper<TeamEntity> teamEntityRowMapper() {
        return new BeanPropertyRowMapper<>(TeamEntity.class);
    }

    @Bean
    public BeanPropertyRowMapper<WeekendView> weekendViewRowMapper() {
        return new BeanPropertyRowMapper<>(WeekendView.class);
    }
}
