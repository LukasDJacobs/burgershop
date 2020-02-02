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

    @Override
    public String getDisplayString() {
        String s = "";

        switch (mDrinkType) {
            case Cola:
                s = "Cola";
                break;

            case Water:
                s = "Wasser";
                break;
        }

        if (mIce)
            s += " mit ";
        else
            s += " ohne ";

        s += "Eis";

        return s;
    }

    public enum Type {
        Cola,
        Water,
    }
}
