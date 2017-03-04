package iss.nus.medipal.AppFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.medipal.asyncTask.AddICE;
import iss.nus.medipal.asyncTask.ListICE;

/**
 * Created by richard on 7/3/17.
 */

public class Medipal {
    private List<ICE> iceList;

    private AddICE taskICEAdd;
    private ListICE taskICEList;

    public ICE addICE(String name, Context context) {
        ICE ice = new ICE(name);
        taskICEAdd = new AddICE(context);
        taskICEAdd.execute(ice);
        return ice;
    }

    public List<ICE> getICE(Context context) {
        taskICEList = new ListICE(context);
        taskICEList.execute((Void) null);

        try {
            iceList = taskICEList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (iceList == null) {
            iceList = new ArrayList<ICE>();
        }

        return new ArrayList<ICE>(iceList);
    }
}
