package iss.nus.medipal.application.ice;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.medipal.asyncTask.AddICE;
import iss.nus.medipal.asyncTask.ListICE;

/**
 * Created by richard on 4/3/17.
 */

public class ICEController {
    public ICEController() {}

    public ICE addICE(String name, Context context) {
        ICE ice = new ICE(name);
        AddICE addIce = new AddICE(context);
        addIce.execute(ice);

        return ice;
    }

    public List<ICE> listIce(Context context) {
        List<ICE> ices = null;
        ListICE listIce = new ListICE(context);
        listIce.execute((Void) null);

        try {
            ices = listIce.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (ices == null) {
            ices = new ArrayList<ICE>();
        }

        return new ArrayList<ICE>(ices);
    }
}
