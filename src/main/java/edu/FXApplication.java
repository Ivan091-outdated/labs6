package edu;

import edu.F1.driver.control.DriverController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class FXApplication extends Application {

    @Autowired
    private ConfigurableApplicationContext appContext;

    @Autowired
    private FxWeaver fxWeaver;

    @Override
    public void init() {
        var appContext = new SpringApplicationBuilder(MainApplication.class)
                .run();
        appContext.getAutowireCapableBeanFactory()
                .autowireBean(this);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = fxWeaver.loadView(DriverController.class);
        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Alive JavaFX");
        primaryStage.setIconified(false);
        primaryStage.show();
    }

    @Override
    public void stop() {
        appContext.stop();
        Platform.exit();
    }
}
