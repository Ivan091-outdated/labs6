package edu.F1.driver.repo;

import lombok.Value;
import lombok.With;


@With
@Value
public class DriverView {

    Integer id;

    String name;

    Integer number;

    Integer teamId;

    String teamName;

    Integer points;
}
