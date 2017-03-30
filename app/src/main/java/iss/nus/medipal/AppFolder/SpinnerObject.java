package iss.nus.medipal.AppFolder;

public class SpinnerObject {
    private int id;
    private String strValue;

    public SpinnerObject(int id , String strValue ) {
        this.id = id;
        this.strValue = strValue;
    }

    public int getId () {
        return id;
    }

    public String getValue () {
        return strValue;
    }

    @Override
    public String toString () {
        return strValue;
    }

}
