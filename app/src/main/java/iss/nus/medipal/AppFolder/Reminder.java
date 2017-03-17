package iss.nus.medipal.AppFolder;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by richard on 17/3/17.
 */

public class Reminder implements Parcelable {
    private int id;
    private Date startDate;
    private int frequency;
    private int interval;

    public Reminder() {
    }

    public Reminder(int id) {
        this(id, null, -1, -1);
    }

    public Reminder(Date startDate, int frequency, int interval) {
        this(-1, startDate, frequency, interval);
    }

    public Reminder(int id, Date startDate, int frequency, int interval) {
        this.id = id;
        this.startDate = startDate;
        this.frequency = frequency;
        this.interval = interval;
    }

    protected Reminder(Parcel in) {
        id = in.readInt();
        startDate = new Date(in.readLong());
        frequency = in.readInt();
        interval = in.readInt();
    }

    public static final Creator<Reminder> CREATOR = new Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "startDate=" + startDate +
                ", frequency=" + frequency +
                ", interval=" + interval +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(startDate.getTime());
        dest.writeInt(frequency);
        dest.writeInt(interval);
    }
}
