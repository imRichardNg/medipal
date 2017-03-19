package iss.nus.medipal.AppFolder;

import java.util.Date;

/**
 * Created by nus on 12/3/17.
 */

public class Measurement {
    private int id;
    private Date measuredOn;




    public Date getMeasuredOn() {
        return measuredOn;
    }

    public void setMeasuredOn(Date measuredOn) {
        this.measuredOn = measuredOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
