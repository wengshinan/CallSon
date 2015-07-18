package com.family.callson.Activities;

import java.util.List;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;

import com.family.callson.R;


/**
 * Created by wengshinan on 2015/6/1.
 */
public class MainFragment extends Fragment {

    public static final String Path = "com.family.callson.Activities.MainFragment";

    public static List<String> history = new ArrayList<String>();

    private Activity mainActivity;
    private int position;

    private final String[] serviceArray = {"护工","家政","家教","医生","维修","其他"};

    public MainFragment() {}

    @SuppressLint("ValidFragment")
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

            View contextLayout = inflater.inflate(R.layout.context_layout, null);

            ArrayAdapter<String>  adapter =
                    new ArrayAdapter<>(mainActivity, R.layout.list_item, R.id.service_name, serviceArray);


            ListView listView = (ListView)contextLayout.findViewById(R.id.listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Intent intent = new Intent(mainActivity, Customize.class);
                    intent.putExtra(MainFragment.Path, position);
                    startActivity(intent);
                }
            });

            listView.setAdapter(adapter);

            return contextLayout;
        } else if (position == 1) {
            View contextLayout = inflater.inflate(R.layout.history, null);

            getHistory();

            ArrayAdapter<String>  adapter =
                    new ArrayAdapter<>(mainActivity, R.layout.list_item,
                            R.id.service_name, history);
            ListView listView = (ListView) contextLayout.findViewById(R.id.historyList);

            listView.setAdapter(adapter);

            return contextLayout;
        } else if (position == 2) {
            View contextLayout = inflater.inflate(R.layout.feedback, null);

            Button sendFeedBackButton = (Button) contextLayout.findViewById(R.id.sendFeedbackButton);
            sendFeedBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 发送反馈
                }
            });

            return contextLayout;
        }
        return null;
    }
}
