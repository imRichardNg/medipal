package iss.nus.medipal.AppFolder;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ROBBINM on 12/3/2017.
 */

public class Medicine implements Parcelable {


    private int medicineId;
    private String medicineName;
    private String medicineDescription;
    private int categoryID;
    private int reminderID;
    private boolean isRemind;
    private int quantity;
    private int dosage;
    private int consumedQuantity;
    private int thereshold;
    private int expireFactor;
    private Date issuedDate;


    public Medicine() {

    }

    public Medicine(int medicineId) {
        this(medicineId, null, null, true,-1,-1,-1,-1,-1,-1,-1,null);
    }

    public Medicine(String medicineName, String medicineDescription, boolean isRemind, int categoryID, int reminderID, int quantity, int dosage, int consumedQuantity,int  thereshold,int expireFactor , Date issuedDate) {
        this(-1,medicineName, medicineDescription, isRemind, categoryID, reminderID,quantity,dosage,consumedQuantity,thereshold,expireFactor,issuedDate);
    }

    public Medicine(int medicineId, String medicineName, String medicineDescription, boolean isRemind, int categoryID, int reminderID, int quantity, int dosage, int consumedQuantity,int  thereshold,int expireFactor , Date issuedDate ) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineDescription = medicineDescription;
        this.categoryID = categoryID;
        this.isRemind = isRemind;
        this.reminderID = reminderID;
        this.quantity = quantity;
        this.dosage = dosage;
        this.consumedQuantity = consumedQuantity;
        this.thereshold = thereshold;
        this.expireFactor = expireFactor;
        this.issuedDate = issuedDate;
    }

    public Medicine(Parcel in) {
        medicineId = in.readInt();
        medicineName = in.readString();
        medicineDescription = in.readString();
        categoryID = in.readInt();
        isRemind = (in.readInt() == 0) ? false : true;
        reminderID = in.readInt();
        quantity = in.readInt();
        dosage = in.readInt();
        consumedQuantity = in.readInt();
        thereshold = in.readInt();
        expireFactor = in.readInt();
        issuedDate = new Date(in.readLong());
    }

    public static final Creator<Medicine> CREATOR = new Creator<Medicine>() {
        @Override
        public Medicine createFromParcel(Parcel in) {
            return new Medicine(in);
        }

        @Override
        public Medicine[] newArray(int size) {
            return new Medicine[size];
        }
    };

    public Medicine(int medicineId, String medicineName, String medicineDescription, boolean isRemind, int categoryID, int reminderID, int quantity, int dosage, int consumedQuantity, int thereshold, int expireFactor, Date issuedDate, int i) {
    }


    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getReminderID() {
        return reminderID;
    }

    public void setReminderID(int reminderID) {
        this.reminderID = reminderID;
    }

    public boolean isRemind() {
        return isRemind;
    }

    public void setRemind(boolean remind) {
        isRemind = remind;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getConsumedQuantity() {
        return consumedQuantity;
    }

    public void setConsumedQuantity(int consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    public int getThereshold() {
        return thereshold;
    }

    public void setThereshold(int thereshold) {
        this.thereshold = thereshold;
    }

    public int getExpireFactor() {
        return expireFactor;
    }

    public void setExpireFactor(int expireFactor) {
        this.expireFactor = expireFactor;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(medicineId);
        dest.writeString(medicineName);
        dest.writeString(medicineDescription);
        dest.writeInt(categoryID);
        dest.writeInt(isRemind ? 1 : 0);
        dest.writeInt(reminderID);
        dest.writeInt(quantity);
        dest.writeInt(dosage);
        dest.writeInt(consumedQuantity);
        dest.writeInt(thereshold);
        dest.writeInt(expireFactor);
        dest.writeLong(issuedDate.getTime());
    }

      @Override
    public String toString() {
        return "Medicine{" +
                "medicineID=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                '}';
    }

}
