package edu.F1.driver.control;

import edu.F1.driver.repo.DriverRepo;
import edu.F1.driver.service.DriverConverter;
import edu.F1.team.repo.TeamRepo;
import edu.F1.team.repo.TeamView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyCode;
import javafx.util.converter.NumberStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.event.KeyEvent;
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
    private TeamRepo teamRepo;

    @Autowired
    private DriverConverter driverConverter;

    @FXML
    public void initialize() {
        driverName.setCellFactory(TextFieldTableCell.forTableColumn());
        driverName.setCellValueFactory(x -> x.getValue().nameProperty());

        driverNumber.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        driverNumber.setCellValueFactory(x -> x.getValue().numberProperty());

        driverPoints.setCellValueFactory(x -> x.getValue().pointsProperty());

        var teams = FXCollections.observableList(teamRepo.findAll().stream().map(TeamView::getName).toList());
        driverTeamName.setCellFactory(ComboBoxTableCell.forTableColumn(teams));
        driverTeamName.setCellValueFactory(x -> x.getValue().teamNameProperty());
        var data = FXCollections.observableList(driverRepo.findAll().stream().map(x -> driverConverter.viewToFx(x)).collect(Collectors.toList()));

        driverTable.setItems(data);

        driverTable.setOnKeyPressed(e -> {
            if (e.getCode().getCode() == KeyCode.DELETE.getCode()) {
                var selectedItem = driverTable.getSelectionModel().getSelectedItem();
                driverTable.getItems().remove(selectedItem);
                driverRepo.delete(selectedItem.getDriverId());
            }
        });
    }

    @FXML
    public void onEditDriverName(TableColumn.CellEditEvent<DriverFxView, String> event){
        var d = event.getRowValue();
        var entity = driverConverter.fxToEntity(d);
        entity.setName(event.getNewValue());
        driverRepo.update(entity);
    }

    @FXML
    public void onEditDriverNumber(TableColumn.CellEditEvent<DriverFxView, Number> event){
        var d = event.getRowValue();
        var entity = driverConverter.fxToEntity(d);
        entity.setNumber(event.getNewValue().intValue());
        driverRepo.update(entity);
    }

    @FXML
    public void onEditDriverTeamName(TableColumn.CellEditEvent<DriverFxView, String> event){
        var n = event.getNewValue();
        var team = teamRepo.findByName(n);
        var entity = driverConverter.fxToEntity(event.getRowValue());
        entity.setTeamId(team.getTeamId());
        driverRepo.update(entity);
    }
}
