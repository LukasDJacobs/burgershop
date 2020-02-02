package de.tuc.burgershop.models;

public abstract class Item {
    private float mPrice;

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public abstract String getDisplayString();
}
