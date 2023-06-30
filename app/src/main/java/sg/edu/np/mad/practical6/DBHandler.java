package sg.edu.np.mad.practical6;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.Random;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users.db";

    public DBHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE Users" +
                "(" +
                "Id" + " INTEGER" + "," +
                "Name" + " TEXT" + "," +
                "Description" + " TEXT" + "," +
                "Followed" + " BOOLEAN" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);

        for (int i = 0; i < 20; i++) {
            Random num1 = new Random();
            Integer numint1 = num1.nextInt(999999999);

            Random num2 = new Random();
            Integer numint2 = num2.nextInt(999999999);

            Random random = new Random();
            boolean bool = random.nextBoolean();

            User m = new User();
            m.Username = "Name" + numint1;
            m.Description = "Description " + numint2;
            m.Follow = bool;
            m.Id = i;

            ContentValues values = new ContentValues();
            values.put("Id", m.getId());
            values.put("Name", m.getUsername());
            values.put("Description", m.getDescription());
            values.put("Followed", m.isFollowed());

            db.insert("Users", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "Users");
        onCreate(db);
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM Users";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            User uinfo = new User();
            uinfo.setId(Integer.parseInt(cursor.getString(0)));
            uinfo.setUsername(cursor.getString(1));
            uinfo.setDescription(cursor.getString(2));
            uinfo.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            userList.add(uinfo);
        }
        cursor.close();
        return userList;
    }

    public void updateUser(User usertoupdate){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        if (usertoupdate.Follow == false) {
            values.put("Followed", false);
            db.update("Users", values, "Id = ?", new String[]{"" + usertoupdate.Id});
        }
        else {
            values.put("Followed", true);
            db.update("Users", values, "Id = ?", new String[]{"" + usertoupdate.Id});
        }
    }
}


