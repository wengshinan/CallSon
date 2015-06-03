package com.family.callson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * Created by wengshinan on 2015/6/1.
 */
public class MainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView text = new TextView(container.getContext());
        text.setText("Fragment content");
        text.setGravity(Gravity.CENTER);

        return text;
    }
}
