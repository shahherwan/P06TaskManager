package sg.edu.rp.c346.p06taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 14049561 on 25/5/2017.
 */

public class TaskAdapter extends ArrayAdapter<Task>{
    Context context;
    ArrayList<Task> tasks;
    int resource;
    TextView tvName, tvDescription;

    public TaskAdapter(Context context, int resource, ArrayList<Task> tasks){
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables

//        tvID = (TextView) rowView.findViewById(R.id.tvID);
        tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDescription = (TextView) rowView.findViewById(R.id.tvDescription);

        Task task = tasks.get(position);

        int intId = task.getId();
        String id = "" + intId;
//        tvID.setText(id);
        tvName.setText(id + " " + task.getName());
        tvDescription.setText(task.getDescription());

        return rowView;

    }



}
