package iss.nus.medipal.AppFolder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by richard on 4/3/17.
 */

public class ICEContact implements Parcelable {

    private int id;
    private String name;
    private String contactNo;
    private int contactType;
    private String description;
    private int sequence;

    public ICEContact() {
    }

    public ICEContact(int id) {
        this(id, null, null, -1, null, -1);
    }

    public ICEContact(String name, String contactNo, int contactType, String description) {
        this(-1, name, contactNo, contactType, description);
    }

    public ICEContact(int id, String name, String contactNo, int contactType, String description) {
        this(id, name, contactNo, contactType, description, -1);
    }

    public ICEContact(int id, String name, String contactNo, int contactType, String description, int sequence) {
        this.id = id;
        this.name = name;
        this.contactNo = contactNo;
        this.contactType = contactType;
        this.description = description;
        this.sequence = sequence;
    }

    public ICEContact(Parcel in) {
        id = in.readInt();
        name = in.readString();
        contactNo = in.readString();
        contactType = in.readInt();
        description = in.readString();
        sequence = in.readInt();
    }

    public static final Creator<ICEContact> CREATOR = new Creator<ICEContact>() {
        @Override
        public ICEContact createFromParcel(Parcel in) {
            return new ICEContact(in);
        }

        @Override
        public ICEContact[] newArray(int size) {
            return new ICEContact[size];
        }
    };

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public int getContactType() {
        return contactType;
    }

    public void setContactType(int contactType) {
        this.contactType = contactType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public ICEContact(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(contactNo);
        dest.writeInt(contactType);
        dest.writeString(description);
        dest.writeInt(sequence);
    }

    @Override
    public String toString() {
        return "ICEContact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", contactType=" + contactType +
                ", description='" + description + '\'' +
                ", sequence=" + sequence +
                '}';
    }
}
