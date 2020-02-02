package de.tuc.burgershop.models;

import java.util.List;

public class Order {
    private List<Item> mItems;

    public void setItems(List<Item> items) {
        mItems = items;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public float getOrderPrice() {
        float price = 0.f;

        if (mItems == null || mItems.isEmpty())
            return price;

        for(Item item : mItems) {
            price += item.getPrice();
        }

        return price;
    }
}
