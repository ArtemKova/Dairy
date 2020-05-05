package ru.ka.dairy4;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Fragment1 extends Fragment implements View.OnClickListener {
    public static int day;
    public static int month;
    public static int year;
    public DatePicker mDatePicker;

    public int hour;
    public int min;


    public Fragment1() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_1, container, false);

        mDatePicker = rootView.findViewById(R.id.datePicker);
        Button button1 = (Button) rootView.findViewById(R.id.button1);
        Button button2 = (Button) rootView.findViewById(R.id.button2);

        mDatePicker.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

//

        return rootView;

    }

    @Override
    public void onClick(View v) {
        int buttonIndex = translateIdToIndex(v.getId());

        day=getDayOfMonth();
        month=getMonth();
        year=getYear();
        hour=Fragment2.hour;
        min=Fragment2.min;


        OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
        assert listener != null;
        listener.onButtonSelected(buttonIndex,day,month,year,hour,min);





    }
    private int translateIdToIndex(int id) {
        int index = -1;
        switch (id) {
            case R.id.button1:
                index = 1;
                break;
            case R.id.button2:
                index = 2;
                break;

        }
        return index;
    }

    public int getDayOfMonth() {
//
        int day = mDatePicker.getDayOfMonth();


        return day;
    }

    public int getMonth() {
//
        int month=mDatePicker.getMonth();


        return month;
    }

    public int getYear() {
//
        int year=mDatePicker.getYear();
        return year;
    }


    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonIndex,int day, int motnth, int year, int hour , int min);
    }
}