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

    @Override
    public String getDisplayString() {
        String s = "";
        switch (mPatty) {
            case Beef:
                s = "Rind";
                break;

            case Chicken:
                s = "Huhn";
                break;

            case Veggie:
                s = "Gemüse";
                break;
        }

        if (mDoubleCheese)
            s += "; Doppelt Käse";
        if (mDoublePatty)
            s+= "; Doppeltes Patty";

        return s;
    }

    public enum Patty {
        Beef,
        Chicken,
        Veggie
    }
}
