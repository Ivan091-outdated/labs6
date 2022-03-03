package edu.F1.driver.service;

import edu.F1.driver.control.view.DriverFxView;
import edu.F1.driver.repo.DriverEntity;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public final class DriverFxViewToEntityConverter implements Function<DriverFxView, DriverEntity> {

    @Override
    public DriverEntity apply(DriverFxView dx) {
        var de = new DriverEntity();
        de.setDriverId(dx.getDriverId());
        de.setName(dx.getName());
        de.setNumber(dx.getNumber());
        de.setTeamId(dx.getTeamFxView().getTeamId());
        return de;
    }
}
