package edu.F1.team.repo;

import lombok.Value;
import lombok.With;


@Value
@With
public class TeamView {

    Integer teamId;

    String name;

    Integer points;
}
