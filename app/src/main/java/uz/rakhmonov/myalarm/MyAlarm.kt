package uz.rakhmonov.myalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast

private const val TAG = "MyAlarm"

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

            // MediaPlayer obyektini aniqlaymiz va alarmni ijro etamiz
            val mediaPlayer = MediaPlayer.create(context, R.raw.rain)
            mediaPlayer.start()
        Data.mediaPlayer=mediaPlayer

            Toast.makeText(context, "Alarm ishga tushirildi", Toast.LENGTH_LONG).show()
        }
}