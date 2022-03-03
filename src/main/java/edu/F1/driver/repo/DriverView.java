package edu.F1.driver.repo;

import lombok.*;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DriverView {

    Integer driverId;

    String name;

    Integer number;

    Integer teamId;

    String teamName;

    int points;
}
