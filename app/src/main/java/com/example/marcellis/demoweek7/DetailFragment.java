package com.example.marcellis.demoweek7;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_detail, container, false);

       EditText etdetailVar = (EditText) view.findViewById(R.id.etDetail);

        String passedData = getActivity().getIntent().getStringExtra(MainFragment.REMINDER);


        if (getArguments() != null) {
            String value = getArguments().getString(MainFragment.REMINDER);
            etdetailVar.setText(value);
        }


     //   Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
     //   getActivity().setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                getActivity().finish();
            }
        });



        return view;
    }

}
