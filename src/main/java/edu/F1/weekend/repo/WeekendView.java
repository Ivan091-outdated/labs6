package edu.F1.weekend.repo;

import lombok.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class WeekendView {

    Integer weekendId;

    String name;

    LocalDate raceDate;

    String winnerName;
}
