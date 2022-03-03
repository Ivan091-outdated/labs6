package edu.F1.driver.control;

import edu.F1.driver.control.view.DriverFxView;
import edu.F1.driver.control.view.TeamFxView;
import edu.F1.driver.repo.*;
import edu.F1.driver.service.DriverViewToFxViewConverter;
import edu.F1.team.repo.TeamRepo;
import edu.F1.team.repo.TeamView;
import edu.F1.team.service.TeamService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Controller
public class DriverController {

    @FXML
    public TableColumn<DriverFxView, String> driverName;

    @FXML
    public TableColumn<DriverFxView, Number> driverNumber;

    @FXML
    public TableColumn<DriverFxView, Number> driverPoints;

    @FXML
    public TableColumn<DriverFxView, TeamFxView> driverTeamName;

    @FXML
    private TableView<DriverFxView> driverTable;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private Function<DriverView, DriverFxView> toFxViewConverter;

    @Autowired
    private Function<DriverFxView, DriverEntity> toEntityConverter;

    @Autowired
    private StringConverter<TeamFxView> teamFxViewStringConverter;

    @Autowired
    private TeamService teamService;

    @Autowired
    private FxWeaver fxWeaver;

    private ObservableList<DriverFxView> driverData;

    private ObservableList<TeamFxView> teamNames;

    @FXML
    public void initialize() {
        driverData = FXCollections.observableList(fetchData());
        teamNames = FXCollections.observableList(teamService.fetchTeamData());

        driverName.setCellFactory(TextFieldTableCell.forTableColumn());
        driverName.setCellValueFactory(x -> x.getValue().nameProperty());

        driverNumber.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        driverNumber.setCellValueFactory(x -> x.getValue().numberProperty());

        driverPoints.setCellValueFactory(x -> x.getValue().pointsProperty());

        driverTeamName.setCellFactory(ComboBoxTableCell.forTableColumn(teamFxViewStringConverter, teamNames));
        driverTeamName.setCellValueFactory(x -> x.getValue().teamFxViewProperty());
        driverTable.setItems(driverData);
    }

    @FXML
    public void onEditDriverName(TableColumn.CellEditEvent<DriverFxView, String> event){
        var driver = event.getRowValue();
        var entity = toEntityConverter.apply(driver);
        entity.setName(event.getNewValue());
        driverRepo.update(entity);
    }

    @FXML
    public void onEditDriverNumber(TableColumn.CellEditEvent<DriverFxView, Number> event){
        var d = event.getRowValue();
        var entity = toEntityConverter.apply(d);
        entity.setNumber(event.getNewValue().intValue());
        driverRepo.update(entity);
    }

    @FXML
    public void onEditDriverTeamName(TableColumn.CellEditEvent<DriverFxView, TeamFxView> event){
        var newTeamId = event.getNewValue();
        var entity = toEntityConverter.apply(event.getRowValue());
        entity.setTeamId(newTeamId.getTeamId());
        driverRepo.update(entity);
    }

    @FXML
    public void onDelete(KeyEvent e) {
        if (e.getCode() == KeyCode.DELETE) {
            var selectedItem = driverTable.getSelectionModel().getSelectedItem();
            driverTable.getItems().remove(selectedItem);
            driverRepo.delete(selectedItem.getDriverId());
        }
    }

    @FXML
    public void refillTable(){
        driverData.setAll(fetchData());
        teamNames.setAll(teamService.fetchTeamData());
    }

    @FXML
    public void add(){
        Parent parent = fxWeaver.loadView(DriverAddNewController.class);
        var stage = new Stage();
        var scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    private List<DriverFxView> fetchData(){
        return driverRepo.findAll().stream().map(toFxViewConverter).collect(Collectors.toList());
    }
}
