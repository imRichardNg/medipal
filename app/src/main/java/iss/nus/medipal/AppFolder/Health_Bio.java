package iss.nus.medipal.AppFolder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nus on 12/3/17.
 */

public class Health_Bio implements Serializable {
    private int healthBioId;
    private String condition;
    private Date strartDate;
    private String conditionType;

    public int getHealthBioId() {
        return healthBioId;
    }

    public void setHealthBioId(int healthBioId) {
        this.healthBioId = healthBioId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Date getStrartDate() {
        return strartDate;
    }

    public void setStrartDate(Date strartDate) {
        this.strartDate = strartDate;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    @Override
    public String toString() {
        return "Health_Bio{" +
                "id=" + healthBioId +
                ", condition='" + condition + '\'' +
                ", strartDate=" + strartDate +
                ", conditionType='" + conditionType + '\'' +
                '}';
    }
}
