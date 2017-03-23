package iss.nus.medipal.AppFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.medipal.asyncTask.AddAppointment;
import iss.nus.medipal.asyncTask.AddCategory;
import iss.nus.medipal.asyncTask.AddConsumption;
import iss.nus.medipal.asyncTask.AddHEALTHBIO;
import iss.nus.medipal.asyncTask.AddICEContact;
import iss.nus.medipal.asyncTask.AddMEASUREMENT;
import iss.nus.medipal.asyncTask.AddMedicine;
import iss.nus.medipal.asyncTask.AddPERSONALBIO;
import iss.nus.medipal.asyncTask.AddReminder;
import iss.nus.medipal.asyncTask.DeleteAppointment;
import iss.nus.medipal.asyncTask.DeleteCategory;
import iss.nus.medipal.asyncTask.DeleteConsumption;
import iss.nus.medipal.asyncTask.DeleteHealthBIO;
import iss.nus.medipal.asyncTask.DeleteICEContact;
import iss.nus.medipal.asyncTask.DeleteMEASUREMENT;
import iss.nus.medipal.asyncTask.DeleteMedicine;
import iss.nus.medipal.asyncTask.DeleteReminder;
import iss.nus.medipal.asyncTask.EditAppointment;
import iss.nus.medipal.asyncTask.EditCategoryList;
import iss.nus.medipal.asyncTask.EditConsumption;
import iss.nus.medipal.asyncTask.EditICEContact;
import iss.nus.medipal.asyncTask.EditMedicine;
import iss.nus.medipal.asyncTask.EditReminder;
import iss.nus.medipal.asyncTask.GetAppointmentList;
import iss.nus.medipal.asyncTask.GetCategoryList;
import iss.nus.medipal.asyncTask.GetConsumptionList;
import iss.nus.medipal.asyncTask.GetICEContactList;
import iss.nus.medipal.asyncTask.GetMedicineList;
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
    private List<Medicine> medicineList;
    private List<Category> categoryList;

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

    private AddCategory taskCategoryAdd;
    private GetCategoryList taskCategoryList;
    private EditCategoryList taskCategoryEdit;

    private AddMedicine taskMedicineAdd;
    private GetMedicineList taskMedicineList;
    private EditMedicine taskMedicineEdit;
    private DeleteMedicine taskMedicineDelete;

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

    public List<SpinnerObject> getAllReminderList(Context context) {

        GetReminderList getReminderList = new GetReminderList(context);
        getReminderList.execute((Void) null);

        List<SpinnerObject> reminderList = null;

        try {
            reminderList = getReminderList.getReminderList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (reminderList == null) {
            reminderList = new ArrayList<SpinnerObject>();
        }

        return new ArrayList<SpinnerObject>(reminderList);
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

    public void addAppointment(String location, Date appointmentDate, String description, Context context) {
        Appointment appointment = new Appointment(location, appointmentDate, description);

        AddAppointment addAppointment = new AddAppointment(context);
        addAppointment.execute(appointment);
    }

    public void editAppointment(int id, String location, Date appointmentDate, String description, Context context) {
        Appointment appointment = new Appointment(id, location, appointmentDate, description);

        EditAppointment editAppointment = new EditAppointment(context);
        editAppointment.execute(appointment);
    }

    public void deleteAppointment(int id, Context context) {
        Appointment appointment = new Appointment(id);

        DeleteAppointment deleteAppointment = new DeleteAppointment(context);
        deleteAppointment.execute(appointment);
    }

    public List<Appointment> getAppointmentList(Context context) {

        GetAppointmentList getAppointmentList = new GetAppointmentList(context);
        getAppointmentList.execute((Void) null);

        List<Appointment> appointmentList = null;

        try {
            appointmentList = getAppointmentList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (appointmentList == null) {
            appointmentList = new ArrayList<Appointment>();
        }

        return new ArrayList<Appointment>(appointmentList);
    }

    public void addConsumption(int medicineId, int quantity, Date consumedOn, Context context) {
        Consumption consumption = new Consumption(medicineId, quantity, consumedOn);

        AddConsumption addConsumption = new AddConsumption(context);
        addConsumption.execute(consumption);
    }

    public void editConsumptoin(int id, int medicineId, int quantity, Date consumedOn, Context context) {
        Consumption consumption = new Consumption(id, medicineId, quantity, consumedOn);

        EditConsumption editConsumption = new EditConsumption(context);
        editConsumption.execute(consumption);
    }

    public void deleteConsumption(int id, Context context) {
        Consumption consumption = new Consumption(id);

        DeleteConsumption deleteConsumption = new DeleteConsumption(context);
        deleteConsumption.execute(consumption);
    }

    public List<Consumption> getConsumptionList(Context context) {

        GetConsumptionList getConsumptionList = new GetConsumptionList(context);
        getConsumptionList.execute((Void) null);

        List<Consumption> consumptionList = null;

        try {
            consumptionList = getConsumptionList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (consumptionList == null) {
            consumptionList = new ArrayList<Consumption>();
        }

        return new ArrayList<Consumption>(consumptionList);
    }

    public List<SpinnerObject> getAllConsumptionMedicineList(Context context) {

        GetConsumptionList getConsumptionList = new GetConsumptionList(context);
        getConsumptionList.execute((Void) null);

        List<SpinnerObject> medicineConsumptoinList = null;

        try {
            medicineConsumptoinList = getConsumptionList.getConsumptionMedicineList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (medicineConsumptoinList == null) {
            medicineConsumptoinList = new ArrayList<SpinnerObject>();
        }

        return new ArrayList<SpinnerObject>(medicineConsumptoinList);
    }

    public void addCategory(String cat, String code, String categoryDescription, boolean isReminder, Context context) {
        Category category = new Category(cat, code, categoryDescription, isReminder);

        taskCategoryAdd = new AddCategory(context);
        taskCategoryAdd.execute(category);
    }

    public List<Category> getCategoryList(Context context) {
        taskCategoryList = new GetCategoryList(context);
        taskCategoryList.execute((Void) null);

        try {
            categoryList = taskCategoryList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (categoryList == null) {
            categoryList = new ArrayList<Category>();
        }
        return new ArrayList<Category>(categoryList);
    }

    public List<SpinnerObject> getAllCategoryList(Context context) {

        GetCategoryList getCategoryList = new GetCategoryList(context);
        getCategoryList.execute((Void) null);

        List<SpinnerObject> categoryList = null;

        try {
            categoryList = getCategoryList.getCategoryList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (categoryList == null) {
            categoryList = new ArrayList<SpinnerObject>();
        }

        return new ArrayList<SpinnerObject>(categoryList);
    }

    public void editCategory(int categoryID, String cat, String code, String categoryDescription, boolean isReminder, Context context) {
        Category category = new Category(cat, code, categoryDescription, isReminder);
        taskCategoryEdit = new EditCategoryList(context);
        taskCategoryEdit.execute(category);
    }

    public void deleteCategory(int id, Context context) {
        Category category = new Category(id);

        DeleteCategory deleteCategory = new DeleteCategory(context);
        deleteCategory.execute(category);
    }

    public void addMedicine(String medicineName, String medicineDescription, boolean isRemind, int categoryID, int reminderID, int quantity, int dosage, int consumedQuantity, int thereshold, int expireFactor, Date issuedDate, Context applicationContext) {
        Medicine medicine = new Medicine(medicineName, medicineDescription, isRemind, categoryID, reminderID, quantity, dosage, consumedQuantity, thereshold, expireFactor, issuedDate);

        taskMedicineAdd = new AddMedicine(applicationContext);
        taskMedicineAdd.execute(medicine);
    }

    public List<Medicine> getMedicineList(Context context) {
        taskMedicineList = new GetMedicineList(context);
        System.out.println("start getMedList");
        taskMedicineList.execute((Void) null);

        try {
            medicineList = taskMedicineList.get();
            System.out.println("getMedList");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (medicineList == null) {
            medicineList = new ArrayList<Medicine>();
        }

        return new ArrayList<Medicine>(medicineList);
    }

    public void editMedicineList(int medid, String medicineName, String medicineDescription, boolean isRemind, int categoryID, int reminderID, int quantity, int dosage, int consumedQuantity, int thereshold, int expireFactor, Date issuedDate, Context applicationContext) {
        Medicine medicine = new Medicine(medid, medicineName, medicineDescription, isRemind, categoryID, reminderID, quantity, dosage, consumedQuantity, thereshold, expireFactor, issuedDate);
        taskMedicineEdit = new EditMedicine(applicationContext);
        taskMedicineEdit.execute(medicine);
    }

    public void deleteMedicine(int id, Context context) {
        Medicine medicine = new Medicine(id);
        //Delete Medicine
        DeleteMedicine deleteMedicine = new DeleteMedicine(context);
        deleteMedicine.execute(medicine);
        System.out.println("deleteMedicine");
    }
}
