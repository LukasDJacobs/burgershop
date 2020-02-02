package de.tuc.burgershop.models;

public class Drink extends Item {

    private Type mDrinkType;
    private boolean mIce;

    public Type getDrinkType() {
        return mDrinkType;
    }

    public void setDrinkType(Type type) {
        mDrinkType = type;
    }

    public boolean getIce() {
        return mIce;
    }

    public void setIce(boolean ice) {
        mIce = ice;
    }

    public enum Type {
        Cola,
        Water,
    }
}
