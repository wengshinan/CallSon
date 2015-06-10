package com.family.callson;

import java.util.List;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;


/**
 * Created by wengshinan on 2015/6/1.
 */
public class MainFragment extends Fragment {

    public static final String Path = "com.family.callson.MainFragment";

    public static List<String> history = new ArrayList<String>();

    private Activity mainActivity;
    private int position;

    private final String[] serviceArray = {"护工","家政","家教","医生","维修","其他"};

    public MainFragment(Activity mainActivity, int num){
        this.mainActivity = mainActivity;
        this.position = num;
    }

    private void getHistory(){
        history.add("1111111");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (position == 0) {
            /*
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this.mainActivity, R.layout.context_row, R.id.header_text, array);

            final ExpandableLayoutListView expandableLayoutListView =
                    (ExpandableListView) contextLayout.findViewById(R.id.listview);
*/
            View contextLayout = inflater.inflate(R.layout.context_layout, null);

            ArrayAdapter<String>  adpter =
                    new ArrayAdapter<String>(mainActivity, R.layout.list_item, R.id.service_name, serviceArray);


            ListView listView = (ListView)contextLayout.findViewById(R.id.listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Intent intent = new Intent(mainActivity, Customize.class);
                    intent.putExtra(MainFragment.Path, position);
                    startActivity(intent);
                }
            });

            listView.setAdapter(adpter);
            //expandableLayoutListView.setAdapter(arrayAdapter);
            return contextLayout;
        } else if (position == 1) {
            View contextLayout = inflater.inflate(R.layout.history, null);

            getHistory();

            ArrayAdapter<String>  adpter =
                    new ArrayAdapter<String>(mainActivity, R.layout.list_item,
                            R.id.service_name, history);
            ListView listView = (ListView) contextLayout.findViewById(R.id.historyList);

            listView.setAdapter(adpter);

            return contextLayout;
        } else if (position == 2) {
            View contextLayout = inflater.inflate(R.layout.feedback, null);
            return contextLayout;
        }
        return null;
    }
}
