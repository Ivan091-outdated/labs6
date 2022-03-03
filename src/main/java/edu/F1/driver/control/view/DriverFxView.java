package edu.F1.driver.control.view;

import edu.F1.driver.control.view.TeamFxView;
import javafx.beans.property.*;


public class DriverFxView {

    private final IntegerProperty driverId = new SimpleIntegerProperty();

    private final StringProperty name = new SimpleStringProperty();

    private final IntegerProperty number = new SimpleIntegerProperty();

    private final ObjectProperty<TeamFxView> teamFxView = new SimpleObjectProperty<>(new TeamFxView());

    private final IntegerProperty points = new SimpleIntegerProperty();

    public ObjectProperty<TeamFxView> teamFxViewProperty() {
        return teamFxView;
    }

    public IntegerProperty driverIdProperty() {
        return driverId;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public IntegerProperty pointsProperty() {
        return points;
    }

    public TeamFxView getTeamFxView() {
        return this.teamFxView.get();
    }

    public void setTeamFxView(TeamFxView teamFxView) {
        this.teamFxView.set(teamFxView);
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

    public int getPoints() {
        return points.get();
    }

    public void setPoints(int points) {
        this.points.set(points);
    }
}
