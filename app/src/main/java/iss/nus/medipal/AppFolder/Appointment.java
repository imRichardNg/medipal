package iss.nus.medipal.AppFolder;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Appointment implements Parcelable {

    private int id;
    private String location;
    private Date appointmentDT;
    private String description;


    public Appointment() {
    }

    public Appointment(int id) {
        this(id, null, null, null);
    }

    public Appointment(String location,Date appointmentDT, String description) {
        this(-1, location, appointmentDT, description);
    }

    public Appointment(int id,String location, Date appointmentDT, String description) {
        this.id = id;
        this.location = location;
        this.appointmentDT = appointmentDT;
        this.description = description;
    }

    protected Appointment(Parcel in) {
        id = in.readInt();
        location = in.readString();
        appointmentDT = new Date(in.readLong());
        description = in.readString();
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {

        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getAppointmentDT() {
        return appointmentDT;
    }

    public void setAppointmentDT(Date appointmentDT) {
        this.appointmentDT = appointmentDT;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "location=" + location +
                ", appointmentDT=" + appointmentDT +
                ", description=" + description +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(location);
        dest.writeLong(appointmentDT.getTime());
        dest.writeString(description);
    }
}
