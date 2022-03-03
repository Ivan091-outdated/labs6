package edu.F1.driver.control;

import edu.F1.driver.repo.DriverEntity;
import edu.F1.driver.repo.DriverRepo;
import edu.F1.driver.service.DriverConverter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.*;
import javafx.util.converter.NumberStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;


@Component
public class DriverController {

    @FXML
    public TableColumn<DriverFxView, String> driverName;

    @FXML
    public TableColumn<DriverFxView, Number> driverNumber;

    @FXML
    public TableColumn<DriverFxView, Number> driverPoints;

    @FXML
    public TableColumn<DriverFxView, String> driverTeamName;

    @FXML
    private TableView<DriverFxView> driverTable;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private DriverConverter driverConverter;

    @FXML
    public void initialize() {
        driverName.setCellFactory(TextFieldTableCell.forTableColumn());
        driverName.setCellValueFactory(x -> x.getValue().nameProperty());
        driverNumber.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        driverNumber.setCellValueFactory(x -> x.getValue().numberProperty());
        driverPoints.setCellValueFactory(x -> x.getValue().pointsProperty());
        driverTeamName.setCellFactory(ComboBoxTableCell.forTableColumn("Scuderia Ferrari"));
        driverTeamName.setCellValueFactory(x -> x.getValue().teamNameProperty());
        var data = FXCollections.observableList(driverRepo.findAll().stream().map(x -> driverConverter.viewToFx(x)).collect(Collectors.toList()));

        driverName.setOnEditCommit(event -> {
            var d = event.getRowValue();
            var entity = driverConverter.fxToEntity(d);
            entity.setName(event.getNewValue());
            driverRepo.update(entity);
        });


        driverTable.setItems(data);
    }
}
