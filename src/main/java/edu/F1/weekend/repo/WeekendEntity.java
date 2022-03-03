package edu.F1.weekend.repo;

import lombok.*;
import java.time.LocalDate;


@Data
public class WeekendEntity {

    Integer weekendId;

    String name;

    LocalDate raceDate;
}
