package sg.edu.rp.c346.p06taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    ListView lv;
    TaskAdapter adapter;
    Button btnAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)this.findViewById(R.id.lvShow);
        btnAdd = (Button)this.findViewById(R.id.btnAdd);
        DBHelper dbh = new DBHelper(this);

        final ArrayList<Task> tasks = dbh.getAllTask();

//        tasks.add(new Task(1,"Buy Milk","Low fat"));


        adapter = new TaskAdapter(this, R.layout.row, tasks);

        lv.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        AddActivity.class);
                // Start the new activity
                startActivity(i);





            }
        });
    }
}
