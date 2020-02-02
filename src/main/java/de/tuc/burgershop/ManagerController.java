package de.tuc.burgershop;

import de.tuc.burgershop.models.Item;
import de.tuc.burgershop.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Component
@FxmlView("ManagerView.fxml")
public class ManagerController implements Observer {

    private FxWeaver mWeaver;

    private OrdersService mOrdersService;

    @FXML private ListView<String> ordersList;
    private ObservableList<String> mOrdersStringList = FXCollections.observableArrayList();

    @Autowired
    public ManagerController(FxWeaver fxWeaver, OrdersService ordersService) {
        mWeaver = fxWeaver;
        mOrdersService = ordersService;
        mOrdersService.addObserver(this);
    }

    public void onOpenOrderView(ActionEvent actionEvent) {
        mWeaver.loadController(OrderController.class).show();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof List) {
            List l = (List) arg;
            if (!l.isEmpty() && l.get(0) instanceof Order) {
                updateOrders(l);
            } else if (l.isEmpty()) {
                mOrdersStringList.clear();
            }
        }
    }

    private void updateOrders(List<Order> orders) {
        ordersList.setItems(mOrdersStringList);
        mOrdersStringList.clear();
        for (Order order : orders) {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            StringBuilder s = new StringBuilder(format.format(order.getOrderPrice())).append(" |");
            for (Item item : order.getItems()) {
                s.append(" ").append(item.getDisplayString()).append(" |");
            }
            mOrdersStringList.add(s.toString());
        }
    }

    public void removeOldestOrder(ActionEvent actionEvent) {
        mOrdersService.removeOldestOrder();
    }
}
