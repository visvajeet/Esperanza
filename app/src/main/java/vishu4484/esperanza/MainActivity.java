package vishu4484.esperanza;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

import static java.security.AccessController.getContext;
import static vishu4484.esperanza.MyFirebase_InstanceIdService.Topic;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar tb;
    ViewFlipper vf;

    CardView slide;

    static int height_schedule;
    int height_slide_pic;
    static int height_color_pick;
    public static String theme_color;
    private Intent i;
    static String device_id;
    FirebaseAuth auth;
    int partake_layout_height, event_layout_height, schedule_layout_height;
    int partake_layout_width, event_layout_width, schedule_layout_width;
    public static SharedPreferences pref, pref2, pref3, pref4, pref5;
    public static SharedPreferences.Editor editor, edit, editt, edit4, edit5;
    RelativeLayout partake, events, schedule;
    private AlphaAnimation buttonClick = new AlphaAnimation(2F, 0.8F);
    int result;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        pref5 = getApplicationContext().getSharedPreferences("off_msg", MODE_PRIVATE);


        float mobile_height = (float) (getResources().getDisplayMetrics().heightPixels);
        float mobile_width = (float) (getResources().getDisplayMetrics().widthPixels);


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {

            if (hasPer()) {

                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                telephonyManager.getDeviceId();
                device_id = telephonyManager.getDeviceId();

                if(auth.getCurrentUser()==null){
                    if(pref5.getInt("off_msg",0)==0) {
                        off();
                    }
                }

            } else {
                request_per();
            }

        }

        if(Build.VERSION.SDK_INT <=Build.VERSION_CODES.LOLLIPOP) {

            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            telephonyManager.getDeviceId();
            device_id = telephonyManager.getDeviceId();



            if(auth.getCurrentUser()==null){
                if(pref5.getInt("off_msg",0)==0) {
                    off();
                }
            }


        }




        height_slide_pic = (int) (mobile_height * 0.25);
        height_schedule = (int) (mobile_height * 0.11);
        height_color_pick = (int) (mobile_height * 0.25);

        event_layout_height = (int) (mobile_height * 0.27);
        event_layout_width = (int) (mobile_width * 0.32);

        partake_layout_height = (int) (mobile_height * 0.18);
        partake_layout_width = (int) (mobile_width * 0.24);

        schedule_layout_height = (int) (mobile_height * 0.18);
        schedule_layout_width = (int) (mobile_width * 0.24);

        partake = (RelativeLayout) findViewById(R.id.partake);
        events = (RelativeLayout) findViewById(R.id.events);
        schedule = (RelativeLayout) findViewById(R.id.schedule);


        //layout slide java handle
        slide = (CardView) findViewById(R.id.slide);
        slide.getLayoutParams().height = height_slide_pic;

        //layout java handle
        events.getLayoutParams().height = event_layout_height;
        events.getLayoutParams().width = event_layout_width;
        partake.getLayoutParams().height = partake_layout_height;
        partake.getLayoutParams().width = partake_layout_width;
        schedule.getLayoutParams().height = schedule_layout_height;
        schedule.getLayoutParams().width = schedule_layout_width;


        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        pref2 = getApplicationContext().getSharedPreferences("Notification", MODE_PRIVATE);
        pref3 = getApplicationContext().getSharedPreferences("Profile_name", MODE_PRIVATE);
        pref4 = getApplicationContext().getSharedPreferences("Device_id", MODE_PRIVATE);


        edit4 = pref4.edit();
        edit4.putString("Device_id", device_id);
        edit4.commit();



        tb = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(tb);

        vf = (ViewFlipper) findViewById(R.id.vf2);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.startFlipping();


        partake.setOnClickListener(this);
        events.setOnClickListener(this);
        schedule.setOnClickListener(this);


        theme_color = pref.getString("color", "#03a9f4");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor(theme_color));
        }
        tb.setBackgroundColor(Color.parseColor(theme_color));
        partake.setBackgroundColor(Color.parseColor(theme_color));
        events.setBackgroundColor(Color.parseColor(theme_color));
        schedule.setBackgroundColor(Color.parseColor(theme_color));

        
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.partake:
                i = new Intent(this, PartakeActivity.class);
                v.startAnimation(buttonClick);
                startActivity(i);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.events:
                i = new Intent(this, EventsActivity.class);
                v.startAnimation(buttonClick);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.schedule:
                i = new Intent(this, ScheduleActivity.class);
                v.startAnimation(buttonClick);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);

        return true;

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.profile).setTitle(pref3.getString("profile_name", "Profile"));

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            case R.id.about:
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            case R.id.profile:
                i = new Intent(this, PartakeActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;

            case R.id.share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    String sAux = "\nLet me recommend you this application\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=" + getPackageName();
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }

                return true;
            case R.id.rate:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("market://details?id=" + getPackageName()));
                startActivity(i);
                return true;
            case R.id.find_us:
                Intent j = new Intent(Intent.ACTION_VIEW);
                j.setData(Uri.parse("https://www.google.co.in/maps/place/PESIT+South+Campus/@12.8614515,77.6647081,15z/data=!4m5!3m4!1s0x0:0x4bad30b7a7fd5e76!8m2!3d12.8614515!4d77.6647081"));
                startActivity(j);
                return true;
            case R.id.offer:
                off2();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(MainActivity.this, "Please grant permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private boolean hasPer() {
        int res = 0;
        String permissions[] = new String[]{Manifest.permission.READ_PHONE_STATE};

        for (String per : permissions) {
            res = checkCallingOrSelfPermission(per);
            if (!(res == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;

    }



    private void request_per() {
        String permissions[] = new String[]{Manifest.permission.READ_PHONE_STATE};
        requestPermissions(permissions, 123);


    }



    static void re() {
        if (pref2.getInt("notif", 0) == 1) {
            FirebaseMessaging.getInstance().subscribeToTopic(Topic);
        }
        if (pref2.getInt("notif", 0) == 2) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(Topic);
        }
    }



    private void off(){
        final AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
        alertDlg.setTitle("50 OFF");
        alertDlg.setIcon(R.drawable.ruppes);
        alertDlg.setMessage("Sign up through our app and get 50 Ruppes off...");
        alertDlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDlg.setNegativeButton("Never show again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edit5 = pref5.edit();
                edit5.putInt("off_msg", 1);
                edit5.commit();

            }
        });

        alertDlg.create().show();
    }
    private void off2(){
        final AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
        alertDlg.setTitle("50 OFF");
        alertDlg.setIcon(R.drawable.ruppes);
        alertDlg.setMessage("Sign up through our app and get 50 Ruppes off...");
        alertDlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        alertDlg.create().show();
    }

}

