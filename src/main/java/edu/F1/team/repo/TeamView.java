package edu.F1.team.repo;

import lombok.*;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeamView {

    Integer teamId;

    String name;

    Integer points;
}
