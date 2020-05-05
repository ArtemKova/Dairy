package ru.ka.dairy4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Fragment2 extends Fragment implements View.OnClickListener  {
    public static int hour;
    public static int min;
    public TimePicker mTimePicker;
    public int day;
    public int month;
    public int year;

    public  Fragment2() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//

            View rootView =
                    inflater.inflate(R.layout.fragment_2, container, false);

        mTimePicker = (TimePicker) rootView.findViewById(R.id.timePicker);



        Button button1 = (Button) rootView.findViewById(R.id.button1);
        Button button2 = (Button) rootView.findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);



        return rootView;
    }
    @Override
    public void onClick(View v) {
        int buttonIndex = translateIdToIndex(v.getId());
        day=Fragment1.day;
        month=Fragment1.month;
        year=Fragment1.year;
        hour=getHour();
        min=getMinute();

        Fragment1.OnSelectedButtonListener listener = (Fragment1.OnSelectedButtonListener) getActivity();
        assert listener != null;
        listener.onButtonSelected(buttonIndex,day,month, year, hour,min);

    }
    int translateIdToIndex(int id) {
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

    public int getHour() {
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();
        return mTimePicker.getHour();
    }

    public int getMinute() {
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();
        int min= mTimePicker.getMinute();


        return min;
    }

    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonIndex);
    }
}