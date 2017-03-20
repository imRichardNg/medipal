package iss.nus.medipal.AppFolder;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ROBBINM on 12/3/2017.
 */

public class Category implements Parcelable {

    private int categoryID;
    private String category;
    private String code;
    private String categoryDescription;
    private boolean isReminder;


    public Category() {
    }

    public Category(int categoryID) {
        this(categoryID, null, null, null, true);
    }

    public Category(String category, String code, String categoryDescription, boolean isReminder) {
        this(-1,category,code,categoryDescription,isReminder);
    }

    public Category(int categoryID, String category, String code, String categoryDescription, boolean isReminder) {
        this.categoryID = categoryID;
        this.category = category;
        this.code = code;
        this.categoryDescription = categoryDescription;
        this.isReminder = isReminder;
    }

    public Category(Parcel in) {
        categoryID = in.readInt();
        category = in.readString();
        code = in.readString();
        categoryDescription = in.readString();
       // isReminder = in.readbo;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public boolean isReminder() {
        return isReminder;
    }

    public void setReminder(boolean reminder) {
        isReminder = reminder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryID);
        dest.writeString(code);
        dest.writeString(categoryDescription);
        dest.writeString(category);
      //  dest.writeInt(sequence);
    }

    @Override
    public String toString() {
        return "ICEContact{" +
                "categoryID=" + categoryID +
                ", code='" + code + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", category=" + category +
                '}';
    }
}


