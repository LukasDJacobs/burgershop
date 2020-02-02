package de.tuc.burgershop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App extends Application {
    private ConfigurableApplicationContext mContext;
    private Parent mRootNode;
    private FXMLLoader mFxmlLoader;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        mContext = SpringApplication.run(App.class);
        mFxmlLoader = new FXMLLoader();
        mFxmlLoader.setControllerFactory(mContext::getBean);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FxWeaver fxWeaver = mContext.getBean(FxWeaver.class);
        mRootNode = fxWeaver.loadView(ManagerController.class);
        Scene scene = new Scene(mRootNode);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        mContext.stop();
        Platform.exit();
    }
}
