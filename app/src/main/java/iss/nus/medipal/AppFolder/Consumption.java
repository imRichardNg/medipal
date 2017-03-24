package iss.nus.medipal.AppFolder;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Member;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consumption implements Parcelable {

    private int id;
    private int medicineId;
    private int quantity;
    private Date consumedOn;
    private Medicine medicine;
    private Category category;

    public static final String DATE_FORMAT = "d-MMM-yyyy";
    public static final String TIME_FORMAT = "d-MMM-yyyy H:mm";
    private static DateFormat tf = new SimpleDateFormat(TIME_FORMAT);

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

    public Consumption(Medicine medicine, Category category, int quantity, Date consumedOn){
        this.medicine = medicine;
        this.category = category;
        this.quantity = quantity;
        this.consumedOn = consumedOn;
    }

    public Consumption(int id,Medicine medicine,Category category, int quantity, Date consumedOn){
        this.id = id;
        this.medicine = medicine;
        this.category = category;
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

    public void setMedicine(Medicine m) {
        this.medicine = m;
    }

    public Medicine getMedicine () {
        return medicine;
    }

    public void setCategory(Category c) {
        this.category = c;
    }

    public Category getCategory () {
        return category;
    }

    @Override
    public String toString () {
        return ("Consumption: " + id + " - " + medicine.getMedicineName()+ "\r\n( " + category.getCategory() +" )"
                + "\r\nQuantity " + quantity +  "\r\nConsumedOn: " + tf.format(consumedOn));
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
