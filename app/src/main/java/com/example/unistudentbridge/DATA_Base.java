package com.example.unistudentbridge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class  DATA_Base extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Mix.Current_DataBase";

    public static final String STUDENT_TABLE_NAME = "student_table";
    public static final String TEACHER_TABLE_NAME = "teacher_table";
    public static final String CHAT_TABLE_NAME = "chat_table";
    public static final String NOTICE_TABLE_NAME = "notice_table";
    public static final String TIMETABLE_TABLE_NAME = "timetable_table";
    public static final String ATTENDANCE_TABLE_NAME = "attendance_table";


    public static final String COL_1 = "ENROLLMENT", COL_1_1 = "TEACHERID";
    public static final String COL_2 = "PASSWORD";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "PHONE";
    public static final String COL_6 = "DEPARTMENT";
    public static final String COL_7 = "SEM";


    SQLiteDatabase Current_DataBase;


    public DATA_Base(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + STUDENT_TABLE_NAME + " (ENROLLMENT STRING PRIMARY KEY,PASSWORD TEXT,NAME TEXT,EMAIL TEXT,PHONE INTEGER,DEPARTMENT TEXT,SEM TEXT)");
        db.execSQL("create table " + TEACHER_TABLE_NAME + " (TEACHERID STRING PRIMARY KEY,PASSWORD TEXT,NAME TEXT,EMAIL TEXT,PHONE INTEGER,DEPARTMENT TEXT)");
        db.execSQL("create table " + CHAT_TABLE_NAME + " (DEPARTMENT TEXT,SEM TEXT,CHAT TEXT)");
        db.execSQL("create table " + NOTICE_TABLE_NAME + " (DEPARTMENT TEXT,SEM TEXT ,NOTICE TEXT)");
        db.execSQL("create table " + TIMETABLE_TABLE_NAME + " (DEPARTMENT TEXT ,SEM TEXT,TIMETABLE BLOB)");
        db.execSQL("create table " + ATTENDANCE_TABLE_NAME + " (DATEX DATE, HOUR STRING, ID TEXT, NAME TEXT, SUB TEXT, SEM TEXT, DEPARTMENT TEXT, ISPRESENT boolean)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TEACHER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CHAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NOTICE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TIMETABLE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ATTENDANCE_TABLE_NAME);
        onCreate(db);
    }

    public boolean Student_Signup(String Enrollment, String Password, String Name, String Email, String Phone, String Department, String Sem) {
        Current_DataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Enrollment);
        contentValues.put(COL_2, Password);
        contentValues.put(COL_3, Name);
        contentValues.put(COL_4, Email);
        contentValues.put(COL_5, Phone);
        contentValues.put(COL_6, Department);
        contentValues.put(COL_7, Sem);

        long result = Current_DataBase.insert(STUDENT_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    boolean CheckAcc_Student(String Enrollment) {
        Current_DataBase = this.getWritableDatabase();
        if (Current_DataBase.rawQuery("SELECT * FROM student_table WHERE ENROLLMENT=?", new String[]{Enrollment}).getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    boolean CheckAcc_Teacher(String Teacherid) {
        Current_DataBase = this.getWritableDatabase();
        if (Current_DataBase.rawQuery("SELECT * FROM teacher_table WHERE TEACHERID=?", new String[]{Teacherid}).getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean Teacher_Signup(String TeacherId, String Password, String Name, String Email, String Phone, String Department) {
        Current_DataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_1, TeacherId);
        contentValues.put(COL_2, Password);
        contentValues.put(COL_3, Name);
        contentValues.put(COL_4, Email);
        contentValues.put(COL_5, Phone);
        contentValues.put(COL_6, Department);

        long result = Current_DataBase.insert(TEACHER_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor Student_Login(String Enrollment, String Password) {

        Current_DataBase = this.getWritableDatabase();
        Cursor res = Current_DataBase.rawQuery("select * from " + STUDENT_TABLE_NAME + " where ENROLLMENT = ? and PASSWORD = ? ", new String[]{Enrollment, Password});
        return res;

    }


    public Cursor Teacher_Login(String Teacher_id, String Password) {

        Current_DataBase = this.getWritableDatabase();
        Cursor res = Current_DataBase.rawQuery("select * from " + TEACHER_TABLE_NAME + " where TEACHERID = ? and PASSWORD = ? ", new String[]{Teacher_id, Password});
        return res;
    }


    public String Recover_Student_Password(String Student_Email) {

        try {

            Current_DataBase = this.getWritableDatabase();

            Cursor res = Current_DataBase.rawQuery("select * from " + STUDENT_TABLE_NAME + " where EMAIL = ? ", new String[]{Student_Email});

            res.moveToFirst();
            return res.getString(1);
        } catch (Exception e) {
            return "";
        }

    }


    public String Recover_Teacher_Password(String Teacher_Email) {

        try {

            Current_DataBase = this.getWritableDatabase();

            Cursor res = Current_DataBase.rawQuery("select * from " + TEACHER_TABLE_NAME + " where EMAIL = ? ", new String[]{Teacher_Email});

            res.moveToFirst();
            return res.getString(1);
        } catch (Exception e) {
            return "";
        }

    }


    public Cursor ReadNotice(String Department, String Sem) {
        Current_DataBase = this.getWritableDatabase();
        Cursor res = Current_DataBase.rawQuery("select * from " + NOTICE_TABLE_NAME + " where DEPARTMENT = ? and SEM = ?", new String[]{Department, Sem});

        return res;
    }


    public boolean WriteNotice(String Department, String Sem, String Notice) {

        Current_DataBase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("DEPARTMENT", Department);
        contentValues.put("SEM", Sem);
        contentValues.put("NOTICE", Notice);

        long result = Current_DataBase.insert(NOTICE_TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }


    public byte[] ReadTimeTable(String Department, String Sem) {
        Current_DataBase = this.getWritableDatabase();
        Cursor res = Current_DataBase.rawQuery("select * from " + TIMETABLE_TABLE_NAME + " where DEPARTMENT = ? and SEM = ?", new String[]{Department, Sem});
        res.moveToFirst();
        return res.getBlob(2);
    }

    public boolean WriteTimeTable(String Department, String Sem, byte[] TimeTable) {
        Current_DataBase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("DEPARTMENT", Department);
        contentValues.put("SEM", Sem);
        contentValues.put("TIMETABLE", TimeTable);

        long result = Current_DataBase.insert(TIMETABLE_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean CheckAttendance(String date, String hour, String sub){
        Current_DataBase = this.getWritableDatabase();
        String query="SELECT * FROM "+ATTENDANCE_TABLE_NAME+" WHERE DATEX =? AND HOUR =? AND SUB =?";
        if (Current_DataBase.rawQuery(query,new String[]{date,hour,sub}).getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getAttendance(String dep, String sem){
        Current_DataBase = this.getReadableDatabase();
        Cursor c = Current_DataBase.rawQuery("SELECT * FROM "+ATTENDANCE_TABLE_NAME+" WHERE DEPARTMENT=? AND SEM=? ORDER BY ID", new String[]{dep,sem});
        return c;
    }

    public Cursor getStudent(String dep, String sem) {
        Current_DataBase = this.getReadableDatabase();
        Cursor c= Current_DataBase.rawQuery("SELECT * FROM "+STUDENT_TABLE_NAME+" WHERE DEPARTMENT=? AND SEM=? ORDER BY ENROLLMENT",new String[]{dep,sem});
        return c;

    }

    public boolean WriteChat(String Dept, String Sem, String Msg) {
        Current_DataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DEPARTMENT", Dept);
        contentValues.put("SEM", Sem);
        contentValues.put("CHAT", Msg);

        long result = Current_DataBase.insert(CHAT_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public String ReadChat(String Dept, String Sem) {
        Current_DataBase = this.getWritableDatabase();

        Cursor c = Current_DataBase.rawQuery("SELECT * FROM chat_table WHERE DEPARTMENT=? AND SEM=?", new String[]{Dept, Sem});

        String msg = "";

        while (c.moveToNext()) {

            msg += c.getString(2) + "\n";
        }

        return msg;

    }

    public boolean execAction(String qu) {
        Current_DataBase = this.getReadableDatabase();
        Current_DataBase.execSQL(qu);
            return true;
    }

    public Cursor getStudentCursor(String id,String dep, String sem){
        Current_DataBase = this.getReadableDatabase();
        return Current_DataBase.rawQuery("SELECT * FROM "+ ATTENDANCE_TABLE_NAME+" WHERE ID=? AND SEM =? AND DEPARTMENT =?",new String[]{id,sem,dep});
    }

    public int getPresentCount(Cursor c,String sub){
        int i=0;
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(7).equals("1") && c.getString(4).equals(sub))
                i++;
            c.moveToNext();
        }
        return i;
    }

    public int getAbscentCount(Cursor c, String sub){
        int i=0;
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(7).equals("0") && c.getString(4).equals(sub))
                i++;
            c.moveToNext();
        }
        return i;
    }

    public Cursor getAllStudentData() {
        Current_DataBase = this.getWritableDatabase();
        return Current_DataBase.rawQuery("select * from " + STUDENT_TABLE_NAME, null);
    }

    public Cursor getAllTeacherData() {
        Current_DataBase = this.getWritableDatabase();
        return Current_DataBase.rawQuery("select * from " + TEACHER_TABLE_NAME, null);

    }

    public void deleteStudent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDENT_TABLE_NAME, "ENROLLMENT = ?", new String[]{id});
    }

    public void deleteTeacher(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TEACHER_TABLE_NAME, "TEACHERID = ?", new String[]{id});
    }

    public void resetChat() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + CHAT_TABLE_NAME);
    }

    public void resetNotice() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + NOTICE_TABLE_NAME);
    }

    public void resetTimeTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TIMETABLE_TABLE_NAME);
    }

}
