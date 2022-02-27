package edu.F1.weekend.repo;

import lombok.Value;
import lombok.With;
import java.time.LocalDate;


@Value
@With
public class WeekendEntity {

    Integer weekendId;

    String name;

    LocalDate raceDate;
}
