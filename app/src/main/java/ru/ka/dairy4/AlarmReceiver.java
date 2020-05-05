package ru.ka.dairy4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    private MediaPlayer mediaPlayer;
    private Button mButton;


    @Override

    public void onReceive(Context context, Intent intent) {

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.budilnick);
        mediaPlayer.start();

        Toast.makeText(context, "Событие которое нельзя пропустить",
                Toast.LENGTH_LONG).show();
        long[] pattern = {0, 300, 400, 200};
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(pattern, 2);
        }
//        Intent note = new Intent(context, Acnote.class);
//        note.setAction(Acnote.ALARM_SERVICE);
    }

}







