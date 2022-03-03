package edu.F1.driver.control;

import javafx.beans.property.*;


public class DriverFxView {

    private final IntegerProperty driverId = new SimpleIntegerProperty();

    private final StringProperty name = new SimpleStringProperty();

    private final IntegerProperty number = new SimpleIntegerProperty();

    private final IntegerProperty teamId = new SimpleIntegerProperty();

    private final StringProperty teamName = new SimpleStringProperty();

    private final IntegerProperty points = new SimpleIntegerProperty();

    public IntegerProperty driverIdProperty() {
        return driverId;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public IntegerProperty teamIdProperty() {
        return teamId;
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public IntegerProperty pointsProperty() {
        return points;
    }

    public int getDriverId() {
        return driverId.get();
    }

    public void setDriverId(int driverId) {
        this.driverId.set(driverId);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
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

    public int getPoints() {
        return points.get();
    }

    public void setPoints(int points) {
        this.points.set(points);
    }
}
