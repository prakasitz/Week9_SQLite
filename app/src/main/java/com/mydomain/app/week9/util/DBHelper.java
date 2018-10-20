package com.mydomain.app.week9.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mydomain.app.week9.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "contactsdb";
    public static final String TABLE_NAME = "contacts";

    //column names
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PHONE = "PHONE";

    public DBHelper(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, " + "EMAIL TEXT, " + "PHONE TEXT);");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'JOHN DOE', 'JOHN_DOE@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'JANE DOE', 'JANE_DOE@MAIL.COM', '23234234');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'JACK DOE', 'JACK_DOE@MAIL.COM', '4645657678');");

        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'AAAA AAAA', 'AAAA@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'BBBB BBBB', 'BBBB@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'CCCC CCCC', 'CCCC@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'DDDD DDDD', 'DDDD@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'EEEE EEEE', 'EEEE@MAIL.COM', '0123456');");

        Log.d("TAG_DBHelper", "DBHelper.onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("TAG_DBHelper", "DBHelper.onUpgrade()");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'JOHN DOE', 'JOHN_DOE@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'JANE DOE', 'JANE_DOE@MAIL.COM', '23234234');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'JACK DOE', 'JACK_DOE@MAIL.COM', '4645657678');");

        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'AAAA AAAA', 'AAAA@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'BBBB BBBB', 'BBBB@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'CCCC CCCC', 'CCCC@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'DDDD DDDD', 'DDDD@MAIL.COM', '0123456');");
        db.execSQL("INSERT INTO CONTACTS VALUES (NULL, 'EEEE EEEE', 'EEEE@MAIL.COM', '0123456');");
    }

    public Contact getContactFromId(int contactId){
        Contact contact = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _ID, NAME, EMAIL, PHONE FROM CONTACTS WHERE _ID = " + contactId, null);
        if(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);

            contact = new Contact(id, name, email, phone);
        }
        db.close();
        return contact;
    }

    public List<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _ID, NAME, EMAIL, PHONE FROM CONTACTS", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);

            Contact c = new Contact(id, name, email, phone);
            contacts.add(c);
        }
        db.close();
        return contacts;
    }

    public List<Contact> getSearchContacts(String keyword){
        String condition = " WHERE NAME LIKE '%" + keyword + "%'";
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _ID, NAME, EMAIL, PHONE FROM CONTACTS" + condition, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);

            Contact c = new Contact(id, name, email, phone);
            contacts.add(c);
        }
        db.close();
        return contacts;
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM CONTACTS " + "WHERE _ID = " + id);
        db.close();
    }

    public void updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "UPDATE CONTACTS SET NAME = '" + contact.getName() + "', " +
                        "EMAIL = '" + contact.getEmail() + "', " +
                        "PHONE = '" + contact.getPhone() + "' " +
                        "WHERE _ID = " + contact.getId()
        );
        db.close();
    }

    public void addContact(String name, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "INSERT INTO CONTACTS VALUES (" +
                        "NULL, " +
                        "'" + name + "', " +
                        "'" + email + "', " +
                        "'" + phone + "'" +
                        ")"
        );
        db.close();
    }
}