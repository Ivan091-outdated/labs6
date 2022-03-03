package edu.F1.team.service;

import edu.F1.driver.control.view.TeamFxView;
import edu.F1.team.repo.TeamView;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public final class TeamViewToFxConverter implements Function<TeamView, TeamFxView> {

    @Override
    public TeamFxView apply(TeamView teamView) {
        var teamFx = new TeamFxView();
        teamFx.setTeamId(teamView.getTeamId());
        teamFx.setTeamName(teamView.getName());
        return teamFx;
    }
}
