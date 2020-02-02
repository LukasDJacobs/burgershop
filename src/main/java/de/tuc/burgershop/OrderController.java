package de.tuc.burgershop;

import de.tuc.burgershop.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("OrderView.fxml")
public class OrderController {

    private Stage mStage;

    private OrdersService mOrdersService;

    @FXML private VBox orderViewRoot;
    @FXML private ComboBox pattyBox;
    @FXML private ComboBox drinkBox;
    @FXML private CheckBox doubleCheeseBox;
    @FXML private CheckBox doublePattyBox;
    @FXML private CheckBox iceBox;
    @FXML private CheckBox friesBox;
    @FXML private Label priceLabel;

    @Autowired
    public OrderController(OrdersService ordersService) {
        mOrdersService = ordersService;
    }

    @FXML
    public void initialize() {
        mStage = new Stage();
        mStage.setScene(new Scene(orderViewRoot));
        setupAvailableItems();
    }

    public void show() {
        mStage.show();
        updatePrice( null);
    }

    public void onOrderPlaced(ActionEvent actionEvent) {
        Order order = getOrder();
        mOrdersService.addOrder(order);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bestellung aufgegeben");
        alert.setHeaderText("Ihre Bestellung wurde aufgegeben. Vielen Dank!");
        alert.showAndWait();
        resetOrder();
    }

    private void resetOrder() {
        pattyBox.setValue(pattyBox.getItems().get(0));
        drinkBox.setValue(drinkBox.getItems().get(0));
        doubleCheeseBox.setSelected(false);
        doublePattyBox.setSelected(false);
        iceBox.setSelected(false);
        friesBox.setSelected(false);
        updatePrice(null);
    }

    private void setupAvailableItems() {
        // In einer weniger prototypischen Implementierung würde das hier natürlich aus einer Datei oder Datenbank
        // gelesen. Aber für die Demonstration eines UseCases sollte es reichen...

        ObservableList<String> availablePatties = FXCollections.observableArrayList("Rind", "Huhn", "Gemüse");
        pattyBox.setItems(availablePatties);
        pattyBox.setValue(availablePatties.get(0));

        ObservableList<String> availableDrinks = FXCollections.observableArrayList("Cola", "Wasser");
        drinkBox.setItems(availableDrinks);
        drinkBox.setValue(availableDrinks.get(0));
    }

    private Order getOrder() {
        List<Item> items = new ArrayList<Item>();
        items.add(getBurger());
        items.add(getDrink());

        if (friesBox.isSelected()) {
            Fries fries = new Fries();
            fries.setPrice(1.5f);
            items.add(fries);
        }

        Order order = new Order();
        order.setItems(items);

        return order;
    }

    private Burger getBurger() {
        Burger burger = new Burger();

        switch (pattyBox.getValue().toString()) {
            case "Rind":
                burger.setPatty(Burger.Patty.Beef);
                break;

            case "Huhn":
                burger.setPatty(Burger.Patty.Chicken);
                break;

            case "Gemüse":
                burger.setPatty(Burger.Patty.Veggie);
                break;
        }

        burger.setDoubleCheese(doubleCheeseBox.isSelected());
        burger.setDoublePatty(doublePattyBox.isSelected());

        // Preis wird hier auch sehr "prototypisch" berechnet, das würde normalerweise etwas ausgefeilter erfolgen,
        // mit Daten die aus einer Datei oder Datenbank kommen o.Ä.
        float price = 3.00f;
        if (burger.getDoubleCheese())
            price += .75f;
        if (burger.getDoublePatty())
            price += 1.50f;
        burger.setPrice(price);

        return burger;
    }

    private Drink getDrink() {
        Drink drink = new Drink();

        switch (drinkBox.getValue().toString()) {
            case "Cola":
                drink.setDrinkType(Drink.Type.Cola);
                drink.setPrice(2.f);
                break;

            case "Wasser":
                drink.setDrinkType(Drink.Type.Water);
                drink.setPrice(1.f);
                break;
        }

        drink.setIce(iceBox.isSelected());

        return drink;
    }

    public void updatePrice(ActionEvent actionEvent) {
        Order order = getOrder();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        float price = order.getOrderPrice();
        String priceString = formatter.format(price);

        priceLabel.setText(priceString);
    }
}
