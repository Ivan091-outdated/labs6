package edu.F1.driver.service;

import edu.F1.driver.control.DriverFxView;
import edu.F1.driver.repo.DriverEntity;
import edu.F1.driver.repo.DriverView;
import org.springframework.stereotype.Service;


@Service
public class DriverConverter {

    public DriverFxView viewToFx(DriverView dv) {
        var dx = new DriverFxView();
        dx.setDriverId(dv.getDriverId());
        dx.setName(dv.getName());
        dx.setTeamId(dv.getTeamId());
        dx.setTeamName(dv.getTeamName());
        dx.setNumber(dv.getNumber());
        dx.setPoints(dv.getPoints());
        return dx;
    }

    public DriverEntity fxToEntity(DriverFxView dx){
        var de = new DriverEntity();
        de.setDriverId(dx.getDriverId());
        de.setName(dx.getName());
        de.setNumber(dx.getNumber());
        de.setTeamId(dx.getTeamId());
        return de;
    }
}
