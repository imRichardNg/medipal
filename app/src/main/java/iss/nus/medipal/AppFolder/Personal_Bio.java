package iss.nus.medipal.AppFolder;

import java.util.Date;

/**
 * Created by nus on 11/3/17.
 */

public class Personal_Bio {
    private int personalId;
    private String personalName;
    private Date dob;
    private String idNo;
    private String address;
    private String postalcode;
    private int height;
    private String bloodType;

    public int getPersonalId() {
        return personalId;
    }

    public void setPersonalId(int personalId) {
        this.personalId = personalId;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }


    @Override
    public String toString() {
        return "Personal_Bio{" +
                "personalId=" + personalId +
                ", personalName='" + personalName + '\'' +
                ", dob=" + dob +
                ", idNo='" + idNo + '\'' +
                ", address='" + address + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", height=" + height +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}
