package de.tuc.burgershop.models;

public class Burger extends Item {

    private Patty mPatty;
    private boolean mDoubleCheese;
    private boolean mDoublePatty;

    public Patty getPatty() {
        return mPatty;
    }

    public void setPatty(Patty patty) {
        mPatty = patty;
    }

    public boolean getDoubleCheese() {
        return mDoubleCheese;
    }

    public void setDoubleCheese(boolean doubleCheese) {
        mDoubleCheese = doubleCheese;
    }

    public boolean getDoublePatty() {
        return mDoublePatty;
    }

    public void setDoublePatty(boolean doublePatty) {
        mDoublePatty = doublePatty;
    }

    public enum Patty {
        Beef,
        Chicken,
        Veggie
    }
}
