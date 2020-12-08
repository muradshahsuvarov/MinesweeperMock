package com.example.toghrultodo

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var context : Context
    lateinit var alarmManager : AlarmManager


    private val ChanelID = "my_chanel_id"
    private val notId = 225

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotification()
        sendNotification("Welcome", "Hi user :-) Go and test the app")
        //  "context" is initialized as MainActivity
        context = this

        // Initializing alarmManager and type cast the (sysytem-level) service object it as AlarmManager
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home, R.id.nav_todo, R.id.nav_slideshow, R.id.nav_gps
                ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    fun runBroadCastReceiver(milliSeconds: Long){

        // Intent for Broadcast Receiver
        val intent = Intent(context, Receiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                milliSeconds,
                pendingIntent
        )

        Log.d("TOGHRUL", "Alarm set at: " + milliSeconds)
    }

    public fun createNotification(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val title = "Notification title"
            val desc = "Notification Description"
            val imp : Int = NotificationManager.IMPORTANCE_DEFAULT
            val chanel = NotificationChannel(ChanelID, title, imp).apply{
                description = desc
            }

            val notManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notManager.createNotificationChannel(chanel)
        }

    }

    public fun sendNotification(title: String, Details: String){
        val builder = NotificationCompat.Builder(this, ChanelID).setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(Details)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(NotificationManagerCompat.from(this)) {
            notify(notId, builder.build())
        }
    }


    // Broad cast receiver for alarm
    class Receiver : BroadcastReceiver(){


        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("TOGHRUL", "Receiver: " + Date().toString())


            // Vibration calling
            val vibrator: Vibrator
            vibrator = context!!.getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(3000)
        }
    }
}