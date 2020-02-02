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
}
