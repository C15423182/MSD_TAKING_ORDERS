package com.example.hn.assignmentfinal;


import android.app.admin.ConnectEvent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;

public class dbconnect extends SQLiteOpenHelper
{
    //variables
    private static final int DB_VERSION = 6;

    //database and table names
    public static final String DB_NAME = "restuarantd";
    public static final String TABLE_USERS = "usertable";
    public static final String TABLE_ORDER = "ordertable";
    //user table attributes
    public static final String COL_1A = "username";
    public static final String COL_1B = "name";
    public static final String COL_1C = "password";
    public static final String COL_1D = "email";
    public static final String COL_1E = "business";
    //order table attributes
    public static final String COL_2A = "table_id";
    public static final String COL_2B = "food_name";
    public static final String COL_2C = "food_des";
    public static final String COL_2D = "drink_name";
    public static final String COL_2E = "waiter_name";

    //db creating tables query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + COL_1A + " TEXT PRIMARY KEY,"
            + COL_1B + " TEXT, "
            + COL_1C + " TEXT, "
            + COL_1D + " TEXT, "
            +  COL_1E + " TEXT " + ")";



    private String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
            + COL_2A + " INTEGER PRIMARY KEY ,"
            + COL_2B + " TEXT, "
            + COL_2C + " TEXT, "
            + COL_2D + " TEXT, "
            + COL_2E + " TEXT, "
            + " FOREIGN KEY ( " + COL_2E + ") REFERENCES " + TABLE_USERS + " ( " + COL_1A + " ) " +
            ") ";

    public dbconnect(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_ORDER_TABLE +");");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS +");");
        onCreate(db);
    }

    //functions
    public void AddUser ( User userexmaple)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COL_1A,userexmaple.getUsername());
        v.put(COL_1B,userexmaple.getName());
        v.put(COL_1C,userexmaple.getPassword());
        v.put(COL_1D,userexmaple.getEmail());
        v.put(COL_1E,userexmaple.getBusiness());

        db.insert(TABLE_USERS,null,v);
        db.close();
    }
    public boolean CheckUser(String checkusername)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor row = db.rawQuery("SELECT " + COL_1A + " FROM " + TABLE_USERS + " WHERE " + COL_1A + " = ?", new String[]{checkusername});
        if(row.moveToFirst())
        {
            row.close();
            return true;
        }
        else
        {
            row.close();
            return false;
        }
    }

    public boolean CheckLoginExists(String checkusername,String checkpassword)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor searchname = db.rawQuery("SELECT " + COL_1A + " FROM " + TABLE_USERS
            + " WHERE " + COL_1A + " = ?", new String[]{checkusername});
        Cursor searchpass = db.rawQuery("SELECT " + COL_1C + " FROM " + TABLE_USERS
                + " WHERE " + COL_1C + " = ?", new String[]{checkpassword});

        if(searchname.moveToFirst())
        {
            if(searchpass.moveToFirst())
            {
                searchname.close();
                searchpass.close();
                return true;
            }
            else
            {
                searchname.close();
                searchpass.close();
                return false;
            }
        }
        else
        {
            searchname.close();
            searchpass.close();
            return false;
        }
    }

    public User GetUserDetails ( String inputusername)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor request = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COL_1A + " = ?", new String[]{inputusername});

        if(request !=null)
        {
            request.moveToFirst();
        }

        User Current_User = new User (request.getString(0),request.getString(1),
                request.getString(2),request.getString(3),request.getString(4));

        return Current_User;
    }

    public void AddOrder(int Table_id, String Foodname, String Fooddes, String Drink, String waiter)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COL_2A,Table_id);
        v.put(COL_2B,Foodname);
        v.put(COL_2C,Fooddes);
        v.put(COL_2D,Drink);
        v.put(COL_2E,waiter);

        db.insert(TABLE_ORDER,null,v);
        db.close();
    }

    public void DeleteOrder(int table_id)
    {
        String id = String.valueOf(table_id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ORDER,COL_2A + " = ? ", new String[] {id});
        //db.execSQL("DELETE FROM " + TABLE_ORDER + " WHERE " + COL_2A + " = " + id );
        db.close();
    }

    public void UpdateUserDetails(String newname,String newpwd,String newemail,String newbsuiness)
    {

    }
}
