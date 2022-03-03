package edu.F1.driver.service;

import edu.F1.driver.control.view.DriverFxView;
import edu.F1.driver.repo.DriverView;
import org.springframework.stereotype.Service;
import java.util.function.Function;


@Service
public final class DriverViewToFxViewConverter implements Function<DriverView, DriverFxView> {

    @Override
    public DriverFxView apply(DriverView dv) {
        var dx = new DriverFxView();
        dx.setDriverId(dv.getDriverId());
        dx.setName(dv.getName());
        dx.getTeamFxView().setTeamId(dv.getTeamId());
        dx.getTeamFxView().setTeamName(dv.getTeamName());
        dx.setNumber(dv.getNumber());
        dx.setPoints(dv.getPoints());
        return dx;
    }
}
