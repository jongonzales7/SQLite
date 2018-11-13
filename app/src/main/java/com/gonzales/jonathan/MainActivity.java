package com.gonzales.jonathan;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    EditText eFname, eLname, ePoints;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        res = helper.selectRecords();
        eFname = findViewById(R.id.etFirstName);
        eLname = findViewById(R.id.etLastName);
        ePoints = findViewById(R.id.etPoints);
    }

    public void insertRecord(View v){
        String firstName = eFname.getText().toString().trim();
        String lastName = eLname.getText().toString().trim();
        int points = Integer.parseInt(ePoints.getText().toString().trim());
        boolean isInserted = helper.insert(firstName, lastName, points);

        if(isInserted) {
            Toast.makeText(this, "Record saved..", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Record not saved..", Toast.LENGTH_LONG).show();
        }

        res = helper.selectRecords();
    }

    public void editRecord(View v){
        String firstName = eFname.getText().toString().trim();
        String lastName = eLname.getText().toString().trim();
        int points = Integer.parseInt(ePoints.getText().toString().trim());
        int id = res.getInt(0);
        boolean isInserted = helper.updateRecord(String.valueOf(id),firstName, lastName, points);

        if(isInserted) {
            Toast.makeText(this, "Record updated..", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Record not updated..", Toast.LENGTH_LONG).show();
        }

        res = helper.selectRecords();
    }

    public void deleteRecord(View v){
        int id = res.getInt(0);
        int success = helper.deleteRecord(""+id);

        if(success > 0) {
            Toast.makeText(this, "Record deleted..", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Record not deleted..", Toast.LENGTH_LONG).show();
        }

        res = helper.selectRecords();
    }


    public void moveFirst(View v){
        res.moveToFirst();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);
        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);

    }

    public void moveLast(View v){
        res.moveToLast();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);
        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);

    }

    public void movePrevious(View v){
        if(res.moveToPrevious()) {
            String fname = res.getString(1);
            String lname = res.getString(2);
            String point = res.getString(3);
            eFname.setText(fname);
            eLname.setText(lname);
            ePoints.setText(point);
        } else {
            res.moveToFirst();
        }

    }

    public void moveNext(View v){
        if(res.moveToNext()) {
            String fname = res.getString(1);
            String lname = res.getString(2);
            String point = res.getString(3);
            eFname.setText(fname);
            eLname.setText(lname);
            ePoints.setText(point);
        } else {
            res.moveToLast();
        }

    }

}
