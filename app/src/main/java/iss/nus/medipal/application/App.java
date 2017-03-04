package iss.nus.medipal.application;

import android.app.Application;

import iss.nus.medipal.application.ice.ICEController;

/**
 * Created by richard on 4/3/17.
 */

public class App extends Application {
    public static ICEController iceController;

    @Override
    public void onCreate() {
        super.onCreate();

        iceController = new ICEController();
    }
}
