package edu.F1.team.repo;

import lombok.Value;
import lombok.With;


@Value
@With
public final class TeamView {

    Integer teamId;

    String name;

    Integer points;
}
