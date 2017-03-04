package iss.nus.medipal.application;

import android.app.Application;

import iss.nus.medipal.AppFolder.Medipal;

/**
 * Created by richard on 4/3/17.
 */

public class App extends Application {
    public static Medipal medipal;

    @Override
    public void onCreate() {
        super.onCreate();
        medipal = new Medipal();
    }
}
