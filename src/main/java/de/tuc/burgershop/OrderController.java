package de.tuc.burgershop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("OrderView.fxml")
public class OrderController {

    private Stage mStage;

    @FXML
    private VBox orderViewRoot;

    @FXML
    public void initialize() {
        mStage = new Stage();
        mStage.setScene(new Scene(orderViewRoot));
    }

    public void show() {
        mStage.show();
    }

    public void onOrderPlaced(ActionEvent actionEvent) {

    }
}
