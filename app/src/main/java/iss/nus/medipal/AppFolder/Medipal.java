package iss.nus.medipal.AppFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.medipal.asyncTask.AddHEALTHBIO;
import iss.nus.medipal.asyncTask.AddICEContact;
import iss.nus.medipal.asyncTask.AddMEASUREMENT;
import iss.nus.medipal.asyncTask.AddPERSONALBIO;
import iss.nus.medipal.asyncTask.AddReminder;
import iss.nus.medipal.asyncTask.DeleteHealthBIO;
import iss.nus.medipal.asyncTask.DeleteICEContact;
import iss.nus.medipal.asyncTask.DeleteMEASUREMENT;
import iss.nus.medipal.asyncTask.DeleteReminder;
import iss.nus.medipal.asyncTask.EditICEContact;
import iss.nus.medipal.asyncTask.EditReminder;
import iss.nus.medipal.asyncTask.GetICEContactList;
import iss.nus.medipal.asyncTask.GetReminderList;
import iss.nus.medipal.asyncTask.ListHEALTHBIO;
import iss.nus.medipal.asyncTask.ListMEASUREMENT;
import iss.nus.medipal.asyncTask.ListPERSONALBIO;
import iss.nus.medipal.asyncTask.UpdateHEALTHBIO;
import iss.nus.medipal.asyncTask.UpdateMEASUREMENT;
import iss.nus.medipal.asyncTask.UpdatePERSONALBIO;

/**
 * Created by richard on 7/3/17.
 */

public class Medipal {
    private List<ICEContact> reminderList;

    private List<Personal_Bio> personal_biosList;
    private List<Health_Bio> health_bioList;
    private List<MeasurementData> measurementDataList;

    private AddICEContact taskICEAdd;
    private GetICEContactList taskICEList;
    private EditICEContact taskICEEdit;
    private DeleteICEContact deleteICEContact;

    private AddPERSONALBIO taskPERSONALBIOAdd;
    private ListPERSONALBIO taskPERSONALBIOList;
    private UpdatePERSONALBIO taskPERSONALBIOUpdate;

    private AddHEALTHBIO taskHEALTHBIOAdd;
    private UpdateHEALTHBIO taskHEALTHBIOUpdate;
    private DeleteHealthBIO taskHEALTHBIODelete;
    private ListHEALTHBIO taskHEALTHBIOList;

    private AddMEASUREMENT taskMEASUREMENTAdd;
    private UpdateMEASUREMENT taskMEASUREMENTUpdate;
    private DeleteMEASUREMENT taskMEASUREMENTDelete;
    private ListMEASUREMENT taskMEASUREMENTList;


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


    public Personal_Bio addPERSONALBIO(Personal_Bio personal_bio, Context context) {

        taskPERSONALBIOAdd = new AddPERSONALBIO(context);
        taskPERSONALBIOAdd.execute(personal_bio);
        return personal_bio;
    }

    public Personal_Bio UpdatePERSONALBIO(Personal_Bio personal_bio, Context context) {
        taskPERSONALBIOUpdate = new UpdatePERSONALBIO(context);
        taskPERSONALBIOUpdate.execute(personal_bio);
        return personal_bio;
    }


    public List<Personal_Bio> getPersonal_Bio(Context context) {
        taskPERSONALBIOList = new ListPERSONALBIO(context);
        taskPERSONALBIOList.execute((Void) null);

        try {
            personal_biosList = taskPERSONALBIOList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (personal_biosList == null) {
            personal_biosList = new ArrayList<Personal_Bio>();
        }

        return new ArrayList<Personal_Bio>(personal_biosList);
    }

    public Health_Bio addHEALTHBIO(Health_Bio health_bio, Context context) {

        taskHEALTHBIOAdd = new AddHEALTHBIO(context);
        taskHEALTHBIOAdd.execute(health_bio);
        return health_bio;
    }

    public Health_Bio UpdateHEALTHBIO(Health_Bio health_bio, Context context) {
        taskHEALTHBIOUpdate = new UpdateHEALTHBIO(context);
        taskHEALTHBIOUpdate.execute(health_bio);
        return health_bio;
    }


    public List<Health_Bio> getHealthBio(Context context) {
        taskHEALTHBIOList = new ListHEALTHBIO(context);
        taskHEALTHBIOList.execute((Void) null);

        try {
            health_bioList = taskHEALTHBIOList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (health_bioList == null) {
            health_bioList = new ArrayList<Health_Bio>();
        }

        return new ArrayList<Health_Bio>(health_bioList);
    }

    public Health_Bio DeleteHEALTHBIO(Health_Bio health_bio, Context context) {
        taskHEALTHBIODelete = new DeleteHealthBIO(context);
        taskHEALTHBIODelete.execute(health_bio);
        return health_bio;
    }

    //Measurement starts HERE.!!!!
    public MeasurementData addMEASUREMENT(MeasurementData measurementData, Context context) {

        taskMEASUREMENTAdd = new AddMEASUREMENT(context);
        taskMEASUREMENTAdd.execute(measurementData);
        return measurementData;
    }

    public MeasurementData UpdateMEASUREMENT(MeasurementData measurementData, Context context) {
        taskMEASUREMENTUpdate = new UpdateMEASUREMENT(context);
        taskMEASUREMENTUpdate.execute(measurementData);
        return measurementData;
    }


    public List<MeasurementData> getMeasurementData(Context context) {
        taskMEASUREMENTList = new ListMEASUREMENT(context);
        taskMEASUREMENTList.execute((Void) null);

        try {
            measurementDataList = taskMEASUREMENTList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (measurementDataList == null) {
            measurementDataList = new ArrayList<MeasurementData>();
        }

        return new ArrayList<MeasurementData>(measurementDataList);
    }

    public MeasurementData DeleteMEASUREMENT(MeasurementData measurementData, Context context) {
        taskMEASUREMENTDelete = new DeleteMEASUREMENT(context);
        taskMEASUREMENTDelete.execute(measurementData);
        return measurementData;
    }
}
