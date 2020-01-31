package de.tuc.burgershop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        mFxmlLoader.setLocation(getClass().getResource("/fxml/BurgershopView.fxml"));
        mRootNode = mFxmlLoader.load();

        stage.setTitle("Burgershop");
        Scene scene = new Scene(mRootNode, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        mContext.stop();
    }
}
