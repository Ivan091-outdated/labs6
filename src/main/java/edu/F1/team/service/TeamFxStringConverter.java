package edu.F1.team.service;

import edu.F1.driver.control.view.TeamFxView;
import javafx.util.StringConverter;
import org.springframework.stereotype.Service;


@Service
public final class TeamFxStringConverter extends StringConverter<TeamFxView> {

    @Override
    public String toString(TeamFxView object) {
        if (object == null){
            return "";
        } else {
            return object.getTeamName();
        }
    }

    @Override
    public TeamFxView fromString(String string) {
        return null;
    }
}
