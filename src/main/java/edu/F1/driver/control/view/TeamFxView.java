package edu.F1.driver.control.view;

import javafx.beans.property.*;


public class TeamFxView {

    private final IntegerProperty teamId = new SimpleIntegerProperty();

    private final StringProperty teamName = new SimpleStringProperty();

    public IntegerProperty teamIdProperty() {
        return teamId;
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public int getTeamId() {
        return teamId.get();
    }

    public void setTeamId(int teamId) {
        this.teamId.set(teamId);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }
}
