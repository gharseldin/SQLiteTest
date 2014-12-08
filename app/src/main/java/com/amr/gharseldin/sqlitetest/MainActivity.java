package com.amr.gharseldin.sqlitetest;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private EditText name;
    private EditText studentId;
    private ListView listView;
    private ArrayList<String> records;
    private ArrayAdapter<String> adapter;
    private DatabaseHelper db;
    private Cursor constantsCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());

        records = new ArrayList<>();
        records.add("Last Name, " + "First Name");

        name = (EditText) findViewById(R.id.name);
        studentId = (EditText) findViewById(R.id.student_id);
        listView = (ListView) findViewById(R.id.data_entered);

        constantsCursor = doQuery();
        constantsCursor.getCount();

        adapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1, records);
        listView.setAdapter(adapter);

    }

    public void insertRecords(View view) {
        records.add(name.getText() + ", " + studentId.getText());
        adapter.notifyDataSetChanged();
    }

    private Cursor doQuery() {
        String query = String.format("SELECT ROWID AS _id, name, student_id FROM students");
        return (db.getReadableDatabase().rawQuery(query, null));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
