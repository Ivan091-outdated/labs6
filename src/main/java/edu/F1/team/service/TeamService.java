package edu.F1.team.service;

import edu.F1.driver.control.view.TeamFxView;
import edu.F1.team.repo.TeamRepo;
import edu.F1.team.repo.TeamView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public final class TeamService {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private Function<TeamView, TeamFxView> toTeamFxViewConverter;

    public List<TeamFxView> fetchTeamData() {
        return teamRepo.findAll().stream().map(toTeamFxViewConverter).collect(Collectors.toList());
    }
}
