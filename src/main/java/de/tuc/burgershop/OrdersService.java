package de.tuc.burgershop;

import de.tuc.burgershop.models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    private List<Order> mOrders;

    public OrdersService() {
        mOrders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return mOrders;
    }

    public void addOrder(Order order) {
        mOrders.add(order);
    }

}
