package iss.nus.medipal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by richard on 4/3/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "medipalData";
    private static final int DATABASE_VERSION = 4;

    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS";

    public static final String ICE_TABLE = "ice";
    public static final String REMINDER_TABLE = "reminders";
    public static final String APPOINTMENT_TABLE = "appointment";
    public static final String CONSUMPTION_TABLE = "consumption";

    public static final String ID = "id";
    public static final String WHERE_ID_EQUALS = ID + " =?";

    public static final String Med_ID = "medicineId";
    public static final String WHERE_MEDID_EQUALS = Med_ID + " =?";

    public static final String NAME = "name";
    public static final String CONTACT_NO = "contactNo";
    public static final String CONTACT_TYPE = "contactType";
    public static final String DESCRIPTION = "description";
    public static final String SEQUENCE = "sequence";

    public static final String FREQUENCY = "frequency";
    public static final String START_TIME = "startTime";
    public static final String INTERVAL = "interval";

    public static final String LOCATION ="location";
    public static final String APPOINTMENT ="appointmentDT";

    public static final String Personal_Bio_TABLE = "personalbio";
    public static final String Personal_ID = "id";
    public static final String Personal_NAME = "name";
    public static final String DOB = "dateOfBirth";
    public static final String IDNo = "idno";
    public static final String Address = "address";
    public static final String PostalCode = "postalCode";
    public static final String Height = "height";
    public static final String BloodType = "bloodtype";

    public static final String MEDICINEID ="medicineId";
    public static final String DOSAGEQUANTITY ="quantity";
    public static final String CONSUMEDON ="consumedOn";

    public static final String MEDICINE_TABLE = "medicine";
    public static final String MEDICINE_ID = "medicineId";
    public static final String MEDICINE_NAME = "medicineName";
    public static final String MEDICINE_DESCRIPTION = "medicineDescription";
    public static final String MEDICINE_CATEGORY_ID = "categoryId";
    public static final String QUANTITY = "quantity";
    public static final String REMIND = "reminderFlag";
    public static final String REMINDER_ID = "reminderId";
    public static final String DOSAGE = "dosage";
    public static final String CONSUMED_QUANTITY = "consumedQuantity";
    public static final String THERSHOLD = "thershold";
    public static final String DATE_ISSUED = "dateIssued";
    public static final String EXPIRE_FACTOR = "expireFactor";

    public static final String CATEGORY_TABLE = "category";
    public static final String CAT_ID = "id";
    public static final String CATEGORY = "categoryShortDescriptions";
    public static final String CODE = "code";
    public static final String CAT_DESCRIPTION = "description";

    private static final String CREATE_ICE_TABLE =
            "CREATE TABLE " + ICE_TABLE +
                    "(" + ID + " INTEGER PRIMARY KEY, " +
                    NAME + " TEXT, " +
                    CONTACT_NO + " TEXT, " +
                    CONTACT_TYPE + " INTEGER, " +
                    DESCRIPTION + " TEXT, " +
                    SEQUENCE + " INTEGER)";

    private static final String CREATE_REMINDERS_TABLE =
            "CREATE TABLE " + REMINDER_TABLE +
                    "(" + ID + " INTEGER PRIMARY KEY, " +
                    FREQUENCY + " INTEGER, " +
                    START_TIME + " DATE, " +
                    INTERVAL + " INTEGER)";

    private static final String CREATE_Personal_Bio_TABLE =
            "CREATE TABLE " + Personal_Bio_TABLE +
                    "(" + Personal_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Personal_NAME + " VARCHAR(100), " +
                    DOB + " DATE, " +
                    IDNo + " TEXT, " +
                    Address + " TEXT, " +
                    PostalCode + " TEXT," +
                    Height + " INTEGER," +
                    BloodType + " TEXT)";

    private static final String CREATE_MEDICINE_TABLE =
            "CREATE TABLE " + MEDICINE_TABLE +
                    "(" + ID + " INTEGER PRIMARY KEY, " +
                    MEDICINE_NAME + " TEXT, " +
                    MEDICINE_DESCRIPTION + " TEXT, " +
                    MEDICINE_CATEGORY_ID + " INTEGER, " +
                    REMINDER_ID + " INTEGER, " +
                    REMIND + " BOOLEAN, " +
                    QUANTITY + " INTEGER," +
                    DOSAGE + " INTEGER, " +
                    CONSUMED_QUANTITY + " INTEGER, " +
                    THERSHOLD + " INTEGER, " +
                    EXPIRE_FACTOR + " INTEGER, " +
                    DATE_ISSUED + " DATE )";

    private static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + CATEGORY_TABLE +
                    "(" + ID + " INTEGER PRIMARY KEY, " +
                    CATEGORY + " TEXT, " +
                    CODE + " TEXT, " +
                    CAT_DESCRIPTION + " TEXT, " +
                    REMIND + " BOOLEAN)";

    public static final String Measurement_TABLE = "measurements";
    public static final String Measurement_ID = "id";
    public static final String Systolic = "systolic";
    public static final String Diastolic = "diastolic";
    public static final String Pulse = "pulse";
    public static final String Temperature = "Temperature";
    public static final String Weight = "weight";
    public static final String MeasuredOn = "measuredOn";

    private static final String CREATE_Measurement_TABLE =
            "CREATE TABLE " + Measurement_TABLE +
                    "(" + Measurement_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Systolic + " INTEGER, " +
                    Diastolic + " INTEGER, " +
                    Pulse + " INTEGER, " +
                    Temperature + " REAL, " +
                    Weight + " INTEGER, " +
                    MeasuredOn + " DATE)";

    public static final String Health_Bio_TABLE = "healthbio";
    public static final String HealthBio_ID = "id";
    public static final String Condition = "condition";
    public static final String StartDate = "startDate";
    public static final String ConditionType = "ConditionType";

    private static final String CREATE_Health_Bio_TABLE =
            "CREATE TABLE " + Health_Bio_TABLE +
                    "(" + HealthBio_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Condition + " TEXT, " +
                    StartDate + " DATE, " +
                    ConditionType + " TEXT)";

    private static final String CREATE_APPOINTMENT_TABLE =
            "CREATE TABLE " + APPOINTMENT_TABLE +
                    "(" + ID + " INTEGER PRIMARY KEY, " +
                    LOCATION + " TEXT, " +
                    APPOINTMENT + " DATE, " +
                    DESCRIPTION + " TEXT)";

    private static final String CREATE_CONSUMPTION_TABLE =
            "CREATE TABLE " + CONSUMPTION_TABLE +
                    "(" + ID + " INTEGER PRIMARY KEY, " +
                    MEDICINEID + " INTEGER, " +
                    DOSAGEQUANTITY + " INTEGER, " +
                    CONSUMEDON + " DATE)";

    private static DataBaseHelper instance;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null) {
            System.out.println("Creating database..");
            instance = new DataBaseHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ICE_TABLE);
        db.execSQL(CREATE_REMINDERS_TABLE);

        db.execSQL(CREATE_Personal_Bio_TABLE);
        db.execSQL(CREATE_Health_Bio_TABLE);
        db.execSQL(CREATE_Measurement_TABLE);

        db.execSQL(CREATE_APPOINTMENT_TABLE);
        db.execSQL(CREATE_CONSUMPTION_TABLE);

        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(), "Upgrading data base from " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, ICE_TABLE));
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, REMINDER_TABLE));
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, Personal_Bio_TABLE));
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, Health_Bio_TABLE));
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, Measurement_TABLE));
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, APPOINTMENT_TABLE));
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, CONSUMPTION_TABLE));
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, CATEGORY_TABLE));
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, MEDICINE_TABLE));
        onCreate(db);
    }
}
