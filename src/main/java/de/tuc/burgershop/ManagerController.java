package de.tuc.burgershop;

import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("ManagerView.fxml")
public class ManagerController {

    private FxWeaver mWeaver;

    public ManagerController(FxWeaver fxWeaver) {
        mWeaver = fxWeaver;
    }

    public void onOpenOrderView(ActionEvent actionEvent) {
        mWeaver.loadController(OrderController.class).show();
    }
}
