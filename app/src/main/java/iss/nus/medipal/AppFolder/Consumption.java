package iss.nus.medipal.AppFolder;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Consumption implements Parcelable {

    private int id;
    private int medicineId;
    private int quantity;
    private Date consumedOn;

    public Consumption(){
    }

    public Consumption(int id) {
        this(id, -1, -1, null);
    }

    public Consumption(int medicineId, int quantity,Date consumedOn) {
        this(-1, medicineId, quantity, consumedOn);
    }

    public Consumption(int id, int medicineId, int quantity , Date consumedOn) {
        this.id = id;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.consumedOn = consumedOn;
    }

    protected Consumption(Parcel in) {
        id = in.readInt();
        medicineId = in.readInt();
        quantity = in.readInt();
        consumedOn = new Date(in.readLong());
   }

    public static final Creator<Consumption> CREATOR = new Creator<Consumption>() {

        @Override
        public Consumption createFromParcel(Parcel in) {
            return new Consumption(in);
        }

        @Override
        public Consumption[] newArray(int size) {
            return new Consumption[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicineId () { return medicineId; }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public Date getConsumedOn() {
        return consumedOn;
    }

    public void setConsumedOn(Date consumedOn) {
        this.consumedOn = consumedOn;
    }

    public int getQuantity () { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "medicineId=" + medicineId +
                ", quantity=" + quantity +
                ", consumedOn=" + consumedOn +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(medicineId);
        dest.writeInt(quantity);
        dest.writeLong(consumedOn.getTime());
    }
}
