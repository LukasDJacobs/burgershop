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

    private Order mOrder;

    @FXML VBox orderViewRoot;
    @FXML ComboBox pattyBox;
    @FXML ComboBox drinkBox;
    @FXML CheckBox doubleCheeseBox;
    @FXML CheckBox doublePattyBox;
    @FXML CheckBox iceBox;
    @FXML CheckBox friesBox;
    @FXML Label priceLabel;

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
        Order order = createOrder();
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

    public void setOrder(Order order) {
        mOrder = order;
    }

    public void setOrder() {
        mOrder = createOrder();
    }

    Order createOrder(Burger burger, Drink drink, boolean fries) {
        List<Item> items = new ArrayList<Item>();
        items.add(burger);
        items.add(drink);

        if (fries) {
            Fries f = new Fries();
            f.setPrice(1.5f);
            items.add(f);
        }

        Order order = new Order();
        order.setItems(items);

        return order;
    }

    private Order createOrder() {
        return createOrder(getBurger(), getDrink(), friesBox.isSelected());
    }

    private Burger getBurger() {
        Burger.Patty patty = null;
        switch (pattyBox.getValue().toString()) {
            case "Rind":
                patty = Burger.Patty.Beef;
                break;

            case "Huhn":
                patty = Burger.Patty.Chicken;
                break;

            case "Gemüse":
                patty = Burger.Patty.Veggie;
                break;
        }

        return getBurger(patty, doublePattyBox.isSelected(), doubleCheeseBox.isSelected());
    }

    Burger getBurger(Burger.Patty patty, boolean doublePatty, boolean doubleCheese) {
        Burger burger = new Burger();
        burger.setPatty(patty);
        burger.setDoubleCheese(doubleCheese);
        burger.setDoublePatty(doublePatty);

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
        Drink.Type type = null;
        switch (drinkBox.getValue().toString()) {
            case "Cola":
                type = Drink.Type.Cola;
                break;

            case "Wasser":
                type = Drink.Type.Water;
                break;
        }

        return getDrink(type, iceBox.isSelected());
    }

    Drink getDrink(Drink.Type type, boolean ice) {
        Drink drink = new Drink();
        drink.setDrinkType(type);
        drink.setIce(ice);

        switch (type) {
            case Cola:
                drink.setPrice(2.f);
                break;

            case Water:
                drink.setPrice(1.f);
                break;
        }

        return drink;
    }

    public void updatePrice(ActionEvent actionEvent) {
        setOrder();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        float price = mOrder.getOrderPrice();
        String priceString = formatter.format(price);

        priceLabel.setText(priceString);
    }
}
