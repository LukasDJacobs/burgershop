package de.tuc.burgershop;

import de.tuc.burgershop.models.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("OrderView.fxml")
public class OrderController {

    private Stage mStage;

    private OrdersService mOrdersService;

    @FXML
    private VBox orderViewRoot;

    @Autowired
    public OrderController(OrdersService ordersService) {
        mOrdersService = ordersService;
    }

    @FXML
    public void initialize() {
        mStage = new Stage();
        mStage.setScene(new Scene(orderViewRoot));
    }

    public void show() {
        mStage.show();
    }

    public void onOrderPlaced(ActionEvent actionEvent) {
        mOrdersService.addOrder(new Order());
    }
}
