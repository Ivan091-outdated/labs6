package edu.F1.driver.control;

import edu.F1.driver.control.view.TeamFxView;
import edu.F1.driver.repo.DriverEntity;
import edu.F1.driver.repo.DriverRepo;
import edu.F1.team.service.TeamService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class DriverAddNewController {

    @FXML
    private TextField driverName;

    @FXML
    private TextField number;

    @FXML
    private ComboBox<TeamFxView> team;

    @Autowired
    private DriverController driverController;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private StringConverter<TeamFxView> teamFxViewStringConverter;

    @Autowired
    private TeamService teamService;

    @FXML
    public void initialize() {
        var list = teamService.fetchTeamData();
        team.setItems(FXCollections.observableList(list));
        team.setConverter(teamFxViewStringConverter);
    }

    @FXML
    public void submit(ActionEvent e) {
        var driverEntity = new DriverEntity();
        driverEntity.setName(driverName.getText());
        driverEntity.setNumber(Integer.valueOf(number.getText()));
        driverEntity.setTeamId(team.getSelectionModel().getSelectedItem().getTeamId());
        driverRepo.insert(driverEntity);
        var stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
        driverController.refillTable();
    }
}
