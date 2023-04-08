package uz.rakhmonov.myalarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import uz.rakhmonov.myalarm.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var alarmManager: AlarmManager
    private lateinit var timePicker: TimePicker
    private lateinit var pendingIntent: PendingIntent
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // AlarmManager obyektini aniqlaymiz
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // TimePicker obyektini aniqlaymiz
        timePicker = binding.timePicker

        // Start buttonini aniqlaymiz va unga click listener qo'shamiz
//        val startButton: Button = findViewById(R.id.startButton)
        binding.startButton.setOnClickListener {
            // Alarm vaqtini aniqlaymiz
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, binding.timePicker.hour)
            calendar.set(Calendar.MINUTE, binding.timePicker.minute)

            // Intent obyektini aniqlaymiz va unga actionni qo'shamiz
            val intent = Intent(this, AlarmReceiver::class.java)
            intent.action = "com.example.alarmdemo.ALARM_START"

            // PendingIntent obyektini aniqlaymiz
//            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, FLAG_UPDATE_CURRENT  )

             pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)

            // Alarmni yoqamiz
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            Toast.makeText(this, "Alarm ${calendar.} vaqtda yoqiladi", Toast.LENGTH_LONG).show()
        }

        // Stop buttonini aniqlaymiz va unga click listener qo'shamiz
        binding.stopButton.setOnClickListener {
            // Alarmni bekor qilamiz
//            alarmManager.cancel(pendingIntent)
            Data.mediaPlayer?.stop()

            Toast.makeText(this, "Alarm bekor qilindi", Toast.LENGTH_LONG).show()
        }
    }

    }
