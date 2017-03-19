package iss.nus.medipal.AppFolder;

/**
 * Created by nus on 12/3/17.
 */

public class BloodPressure extends Measurement {
    private int systolic;
    private int diastolic;

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }
}
