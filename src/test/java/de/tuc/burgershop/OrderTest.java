package de.tuc.burgershop;

import de.tuc.burgershop.models.*;
import de.tuc.burgershop.models.Burger.Patty;
import de.tuc.burgershop.models.Drink.Type;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    private OrderController mController;

    public OrderTest() {
        mController = new OrderController(null);
    }

    @Test
    public void testNull() {
        float actual = new Order().getOrderPrice();
        assertEquals(0.f, actual,4.f);
    }

    @Test
    public void testEmpty() {
        List<Item> items = new ArrayList<>();
        Order order = new Order();
        order.setItems(items);
        assertEquals(0.f, order.getOrderPrice(),0.001f);
    }

    @Test
    public void testStandardMenue() {
        Burger burger = mController.getBurger(Patty.Beef, false, false);
        Drink drink = mController.getDrink(Type.Cola, true);
        Order order = mController.createOrder(burger, drink, false);

        assertEquals(5.f, order.getOrderPrice(),0.001f);
    }

    @Test
    public void testMitPommes() {
        Burger burger = mController.getBurger(Patty.Beef, false, false);
        Drink drink = mController.getDrink(Type.Cola, true);
        Order order = mController.createOrder(burger, drink, true);

        assertEquals(6.5f, order.getOrderPrice(),0.01f);
    }

    @Test
    public void testGeunderBurger() {
        Burger burger = mController.getBurger(Patty.Veggie, false, false);
        Drink drink = mController.getDrink(Type.Water, false);
        Order order = mController.createOrder(burger, drink, false);

        assertEquals(4.f, order.getOrderPrice(),0.01f);
    }

    @Test
    public void testTeuersteMenue() {
        Burger burger = mController.getBurger(Patty.Chicken, true, true);
        Drink drink = mController.getDrink(Type.Cola, true);
        Order order = mController.createOrder(burger, drink, true);

        assertEquals(8.75f, order.getOrderPrice(), 0.001f);
    }

    @Test
    public void testDoppeltKaese() {
        Burger burger = mController.getBurger(Patty.Beef, false, true);
        Drink drink = mController.getDrink(Type.Cola, true);
        Order order = mController.createOrder(burger, drink, false);

        assertEquals(5.75f, order.getOrderPrice(),0.001f);
    }

    @Test
    public void testDoppeltFleisch() {
        Burger burger = mController.getBurger(Patty.Beef, true, false);
        Drink drink = mController.getDrink(Type.Cola, true);
        Order order = mController.createOrder(burger, drink, false);

        assertEquals(6.5f, order.getOrderPrice(),0.001f);
    }

}
