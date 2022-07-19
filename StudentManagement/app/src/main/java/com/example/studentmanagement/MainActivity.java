package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    EditText searchET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_students);
        // Create database
        createDatabase();

        String sql = "select * from student";
        Cursor cursor =db.rawQuery(sql, null);

        cursor.moveToPosition(-1);
//        while (cursor.moveToNext()) {
//            String mssv = cursor.getString(0);
//            String name = cursor.getString(1);
//            String dob = cursor.getString(2);
//            String email = cursor.getString(3);
//
//            Log.v("TAG", mssv + name + dob + email);
//        }
        StudentAdapter studentAdapter = new StudentAdapter(cursor);
        listView.setAdapter(studentAdapter);

        searchET = findViewById(R.id.search_edittext);

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = searchET.getText().toString();
                Log.d("SEARCH", text);
//                studentAdapter.
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void createDatabase() {
        File storagePath = getApplication().getFilesDir();
        String myDbPath = storagePath + "/" + "student_management";
        try {
            db = SQLiteDatabase.openDatabase(myDbPath, null,
                    SQLiteDatabase.CREATE_IF_NECESSARY);
//            createTable();
        } catch (SQLiteException e) {
            Log.d("Exc", e.getMessage());
        }
    }

    private void createTable() {
        Faker faker = new Faker();
        db.beginTransaction();
        try {
            db.execSQL("create table student("
                        + "mssv text PRIMARY KEY, "
                        + "name text,"
                        + "ngay_sinh date," +
                    "email text)");
            db.execSQL("insert into student(mssv, name, ngay_sinh, email) values(" + "'20194016', " + "'Pham Van Diep'," + "'2001-11-17'," + "'phamdiepa1k55@gmail.com')");
            for (int i = 0; i < 10; i++) {
                String sql = "insert into student(mssv, name, ngay_sinh, email) values("
                        + "'"
                        + faker.phoneNumber.phoneNumber()+ "','"
                        + faker.name.name() + "','"
                        + faker.date.birthday() + "','"
                        + faker.internet.email() + "')";
                Log.d("SQL", sql);
                db.execSQL(sql);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e) {

        }
        finally {
            db.endTransaction();
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}