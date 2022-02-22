package edu.F1.driver.repo;

import lombok.*;


@Value
@With
public class DriverEntity {

    Integer id;

    String name;

    Integer number;

    Integer teamId;
}
