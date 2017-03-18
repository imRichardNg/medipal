package iss.nus.medipal.AppFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.medipal.asyncTask.AddICEContact;
import iss.nus.medipal.asyncTask.AddReminder;
import iss.nus.medipal.asyncTask.DeleteICEContact;
import iss.nus.medipal.asyncTask.DeleteReminder;
import iss.nus.medipal.asyncTask.EditICEContact;
import iss.nus.medipal.asyncTask.EditReminder;
import iss.nus.medipal.asyncTask.GetICEContactList;
import iss.nus.medipal.asyncTask.GetReminderList;

/**
 * Created by richard on 7/3/17.
 */

public class Medipal {
    private List<ICEContact> reminderList;

    private AddICEContact taskICEAdd;
    private GetICEContactList taskICEList;
    private EditICEContact taskICEEdit;
    private DeleteICEContact deleteICEContact;


    public void addICE(String name, String contactNo, int contactType, String description, Context applicationContext) {
        ICEContact iceContact = new ICEContact(name, contactNo, contactType, description);

        taskICEAdd = new AddICEContact(applicationContext);
        taskICEAdd.execute(iceContact);
    }

    public List<ICEContact> getICEContacts(Context context) {
        taskICEList = new GetICEContactList(context);
        taskICEList.execute((Void) null);

        try {
            reminderList = taskICEList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (reminderList == null) {
            reminderList = new ArrayList<ICEContact>();
        }

        Collections.sort(reminderList, (o1, o2) -> {
            if (o1.getSequence() < o2.getSequence()) {
                return -1;
            } else if (o1.getSequence() > o2.getSequence()) {
                return 1;
            } else {
                return 0;
            }
        });

        return new ArrayList<ICEContact>(reminderList);
    }

    public void editICE(int id, String name, String contactNo, int contactType, String description, int sequence, Context context) {
        ICEContact iceContact = new ICEContact(id, name, contactNo, contactType, description, sequence);
        taskICEEdit = new EditICEContact(context);
        taskICEEdit.execute(iceContact);
    }

    public void deleteICE(int id, Context context) {
        ICEContact iceContact = new ICEContact(id);

        deleteICEContact = new DeleteICEContact(context);
        deleteICEContact.execute(iceContact);
    }

    public void addReminder(Date startDate, int frequency, int interval, Context context) {
        Reminder reminder = new Reminder(startDate, frequency, interval);

        AddReminder addReminder = new AddReminder(context);
        addReminder.execute(reminder);
    }

    public void editReminder(int id, Date startDate, int frequency, int interval, Context context) {
        Reminder reminder = new Reminder(id, startDate, frequency, interval);

        EditReminder editReminder = new EditReminder(context);
        editReminder.execute(reminder);
    }

    public void deleteReminder(int id, Context context) {
        Reminder reminder = new Reminder(id);

        DeleteReminder deleteReminder = new DeleteReminder(context);
        deleteReminder.execute(reminder);
    }

    public List<Reminder> getReminderList(Context context) {
        GetReminderList getReminderList = new GetReminderList(context);
        getReminderList.execute((Void) null);

        List<Reminder> reminderList = null;

        try {
            reminderList = getReminderList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (reminderList == null) {
            reminderList = new ArrayList<Reminder>();
        }

        return new ArrayList<Reminder>(reminderList);
    }
}
