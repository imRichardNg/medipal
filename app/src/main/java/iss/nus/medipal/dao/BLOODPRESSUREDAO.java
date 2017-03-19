package iss.nus.medipal.dao;

import android.content.Context;

import java.util.List;

import iss.nus.medipal.AppFolder.BloodPressure;

/**
 * Created by nus on 18/3/17.
 */

public class BLOODPRESSUREDAO extends DBDAO implements DAOInterface <BloodPressure> {
    public BLOODPRESSUREDAO(Context context){super(context);}


    @Override
    public long save(BloodPressure bloodPressure) {
        return 0;
    }

    @Override
    public long update(BloodPressure bloodPressure) {
        return 0;
    }

    @Override
    public long delete(BloodPressure bloodPressure) {
        return 0;
    }

    @Override
    public BloodPressure get(BloodPressure bloodPressure) {
        return null;
    }

    @Override
    public List<BloodPressure> getAll() {
        return null;
    }
}
