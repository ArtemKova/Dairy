package ru.ka.dairy4;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Fragment1.OnSelectedButtonListener  {

    final static int RQS_TIME = 1;
    private TextView mTimeTextView;
    private TextView mTimeTextView1;
    private TextView mTimeTextView2;
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private CheckBox mRepeatCheckBox;


//    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();
        mDatePicker = findViewById(R.id.datePicker);

        mTimeTextView = (TextView) findViewById(R.id.text);
        mTimeTextView1 = (TextView) findViewById(R.id.text1);
        mTimeTextView2 = (TextView) findViewById(R.id.text2);




        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter); // устанавливаем адаптер
        viewPager.setCurrentItem(0); // выводим второй экран
    }

    public static class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();


                default:
                    return new Fragment1();
            }
        }
        @Override
        public CharSequence getPageTitle(int position){
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String date = format.format(new Date());
            SimpleDateFormat format1 = new SimpleDateFormat("hh.mm");
            String time = format1.format(new Date());
            switch (position){
                case 0:
                    return  date;
                case 1:
                    return time;

            }
            return null;
        }
    }

    private void setAlarm(Calendar targetCal) {

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), RQS_TIME, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);




    }

    private void cancelAlarm() {
        mTimeTextView.setText(R.string.canceled);
        mTimeTextView1.setText("");
        mTimeTextView2.setText("");

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), RQS_TIME, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void onButtonSelected(int buttonIndex, int day, int month, int year, int hour, int min) {


        if (buttonIndex==1){

// подключаем FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Получаем ссылку на второй фрагмент по ID
        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment_2);
        Fragment1 fragment1 = (Fragment1) fragmentManager
                .findFragmentById(R.id.fragment_1);
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();
            assert mDatePicker != null;
            if (day==0){day=fragment1.getDayOfMonth();}
            calSet.set(Calendar.DAY_OF_MONTH,day);
            if (month==0){month=fragment1.getMonth();}
            calSet.set(Calendar.MONTH,month);
            if (year == 0) {year=fragment1.getYear();}
            calSet.set(Calendar.YEAR,year);
            if (hour==0){hour=fragment2.getHour();}
            calSet.set(Calendar.HOUR_OF_DAY, hour);
            if (min==0){min=fragment2.getMinute();}
            calSet.set(Calendar.MINUTE, min);


        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);



        setAlarm(calSet);
    }else{
    cancelAlarm();
    }

   }

}