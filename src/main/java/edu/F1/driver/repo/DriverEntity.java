package edu.F1.driver.repo;

import lombok.*;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DriverEntity {

    Integer driverId;

    String name;

    Integer number;

    Integer teamId;
}
