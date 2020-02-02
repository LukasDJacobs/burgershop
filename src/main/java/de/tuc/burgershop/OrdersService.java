package de.tuc.burgershop;

import de.tuc.burgershop.models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Service
public class OrdersService extends Observable {

    private List<Order> mOrders;

    public OrdersService() {
        mOrders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return mOrders;
    }

    public void addOrder(Order order) {
        mOrders.add(order);
        notifyObservers(mOrders);
    }

    @Override
    public void notifyObservers(Object arg) {
        this.setChanged();
        super.notifyObservers(arg);
    }

    public void removeOldestOrder() {
        if (!mOrders.isEmpty())
            mOrders.remove(0);
        notifyObservers(mOrders);
    }
}
