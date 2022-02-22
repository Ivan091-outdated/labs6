package edu.F1.team.repo;

import lombok.Value;
import lombok.With;


@Value
@With
public final class TeamEntity {

    Integer teamId;

    String name;
}
