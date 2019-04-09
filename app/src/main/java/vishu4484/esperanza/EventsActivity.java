package vishu4484.esperanza;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static vishu4484.esperanza.MainActivity.theme_color;

public class EventsActivity extends AppCompatActivity {

    Toolbar tb;
    GridView gv;
    View mview;
    Button bt,ok,rules_button;
    int flag;
    ImageView event_image;
    AlertDialog dialog;
    LinearLayout ll;
    ScrollView sv;
    CardView info_text;
    RelativeLayout main;
    DatabaseReference databaseReference;
    TextView ruless,event_title_textView,event_time_textView,event_location_textView,event_charge_textView,event_date_textView,event_rule_info_textView,event_coordinator_1,event_coordinator_2;
    LinearLayout call1,call2,info_image;
    String title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2;

    String[] name={"Webphoria","Ideathon","Impromptu","Silent Speak","Mind Sweeper","Mock CID ","Just Code It","Conferral","Backyard Cricket","Pictogram","Fifa",
    "Counter Strike","The Overseer"};
    int img[]={R.drawable.webphoria,R.drawable.ideathon1,R.drawable.impromptu1,R.drawable.silent_speak,R.drawable.mind_sweeper,R.drawable.mock_cid,R.drawable.just_code_it,R.drawable.conferral,
   R.drawable.backyard_cricket,R.drawable.pictogram,R.drawable.fifa,R.drawable.cs,R.drawable.the_overseer};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        if(theme_color!=null) {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.parseColor(theme_color));
            }
        }

        tb = (Toolbar) findViewById(R.id.toolbar_event);
        tb.setBackgroundColor(Color.parseColor(theme_color));
        setSupportActionBar(tb);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (!CheckNetwork.isInternetAvailable(EventsActivity.this)) {
            Toast.makeText(EventsActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        gv = (GridView) findViewById(R.id.gridView);
        Adapter adapter = new Adapter(EventsActivity.this, img, name);
        gv.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Winners");

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        String[] webphoria = getResources().getStringArray(R.array.webphoria);
                        title=webphoria[0];
                        time=webphoria[1];
                        location=webphoria[2];
                        charge=webphoria[3];
                        date=webphoria[4];
                        rules=webphoria[5];
                        c_name_number_1=webphoria[6];
                        c_name_number_2=webphoria[7];
                        c_number_1=webphoria[8];
                        c_number_2=webphoria[9];
                        event_view(R.drawable.webphoria,"webphoria",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;

                    case 1:
                        String[] ideathon = getResources().getStringArray(R.array.ideathon);
                        title=ideathon[0];
                        time=ideathon[1];
                        location=ideathon[2];
                        charge=ideathon[3];
                        date=ideathon[4];
                        rules=ideathon[5];
                        c_name_number_1=ideathon[6];
                        c_name_number_2=ideathon[7];
                        c_number_1=ideathon[8];
                        c_number_2=ideathon[9];

                        event_view(R.drawable.ideathon1,"ideathon",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;

                    case 2:
                        String[] impromptu = getResources().getStringArray(R.array.impromptu);
                        title=impromptu[0];
                        time=impromptu[1];
                        location=impromptu[2];
                        charge=impromptu[3];
                        date=impromptu[4];
                        rules=impromptu[5];
                        c_name_number_1=impromptu[6];
                        c_name_number_2=impromptu[7];
                        c_number_1=impromptu[8];
                        c_number_2=impromptu[9];
                        event_view(R.drawable.impromptu1,"impromptu",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;


                    case 3:
                        String[] silent_speak = getResources().getStringArray(R.array.silent_speak);
                        title=silent_speak[0];
                        time=silent_speak[1];
                        location=silent_speak[2];
                        charge=silent_speak[3];
                        date=silent_speak[4];
                        rules=silent_speak[5];
                        c_name_number_1=silent_speak[6];
                        c_name_number_2=silent_speak[7];
                        c_number_1=silent_speak[8];
                        c_number_2=silent_speak[9];
                        event_view(R.drawable.silent_speak,"silent_speak",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;

                    case 4:
                        String[] mind_sweeper = getResources().getStringArray(R.array.mind_sweeper);
                        title=mind_sweeper[0];
                        time=mind_sweeper[1];
                        location=mind_sweeper[2];
                        charge=mind_sweeper[3];
                        date=mind_sweeper[4];
                        rules=mind_sweeper[5];
                        c_name_number_1=mind_sweeper[6];
                        c_name_number_2=mind_sweeper[7];
                        c_number_1=mind_sweeper[8];
                        c_number_2=mind_sweeper[9];
                        event_view(R.drawable.mind_sweeper,"mind_sweeper",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;


                    case 5:
                        String[] mock_cid = getResources().getStringArray(R.array.mock_cid);
                        title=mock_cid [0];
                        time=mock_cid [1];
                        location=mock_cid [2];
                        charge=mock_cid [3];
                        date=mock_cid [4];
                        rules=mock_cid [5];
                        c_name_number_1=mock_cid [6];
                        c_name_number_2=mock_cid [7];
                        c_number_1=mock_cid [8];
                        c_number_2=mock_cid [9];
                        event_view(R.drawable.mock_cid,"mock_Cid",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;


                    case 6:
                        String[] just_code_it = getResources().getStringArray(R.array.just_code_it);
                        title=just_code_it [0];
                        time=just_code_it [1];
                        location=just_code_it [2];
                        charge=just_code_it [3];
                        date=just_code_it[4];
                        rules=just_code_it [5];
                        c_name_number_1=just_code_it [6];
                        c_name_number_2=just_code_it[7];
                        c_number_1=just_code_it [8];
                        c_number_2= just_code_it [9];
                        event_view(R.drawable.just_code_it,"just_code",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;

                    case 7:
                        String[] conferral = getResources().getStringArray(R.array.conferral);
                        title=conferral [0];
                        time=conferral [1];
                        location=conferral [2];
                        charge=conferral[3];
                        date=conferral[4];
                        rules=conferral [5];
                        c_name_number_1=conferral [6];
                        c_name_number_2=conferral[7];
                        c_number_1=conferral[8];
                        c_number_2= conferral [9];
                        event_view(R.drawable.conferral,"conferral",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;

                    case 8:
                        String[] backyard_cricket_day1 = getResources().getStringArray(R.array.backyard_cricket_day1);
                        title=backyard_cricket_day1[0];
                        time=backyard_cricket_day1[1];
                        location=backyard_cricket_day1[2];
                        charge=backyard_cricket_day1[3];
                        date=backyard_cricket_day1[4];
                        rules=backyard_cricket_day1 [5];
                        c_name_number_1=backyard_cricket_day1 [6];
                        c_name_number_2=backyard_cricket_day1[7];
                        c_number_1=backyard_cricket_day1[8];
                        c_number_2= backyard_cricket_day1 [9];
                        event_view(R.drawable.backyard_cricket,"backyard_cricket_day1",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;


                    case 9:
                        String[] pictogram = getResources().getStringArray(R.array.pictogram);
                        title=pictogram[0];
                        time=pictogram[1];
                        location=pictogram[2];
                        charge=pictogram[3];
                        date=pictogram[4];
                        rules=pictogram [5];
                        c_name_number_1=pictogram [6];
                        c_name_number_2=pictogram[7];
                        c_number_1=pictogram[8];
                        c_number_2= pictogram [9];
                        event_view(R.drawable.pictogram,"pictogram",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;


                    case 10:
                        String[] gaming_fifa_day1 = getResources().getStringArray(R.array.gaming_fifa_day1);
                        title= gaming_fifa_day1[0];
                        time= gaming_fifa_day1[1];
                        location= gaming_fifa_day1[2];
                        charge=gaming_fifa_day1[3];
                        date= gaming_fifa_day1[4];
                        rules=gaming_fifa_day1 [5];
                        c_name_number_1= gaming_fifa_day1 [6];
                        c_name_number_2= gaming_fifa_day1[7];
                        c_number_1= gaming_fifa_day1[8];
                        c_number_2=  gaming_fifa_day1[9];
                        event_view(R.drawable.fifa,"gaming_fifa_day1",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;

                    case 11:
                        String[] gaming_cs_day1 = getResources().getStringArray(R.array.gaming_cs_day1);
                        title= gaming_cs_day1[0];
                        time= gaming_cs_day1[1];
                        location= gaming_cs_day1[2];
                        charge=gaming_cs_day1[3];
                        date= gaming_cs_day1[4];
                        rules=gaming_cs_day1 [5];
                        c_name_number_1= gaming_cs_day1 [6];
                        c_name_number_2= gaming_cs_day1[7];
                        c_number_1= gaming_cs_day1[8];
                        c_number_2=  gaming_cs_day1[9];
                        event_view(R.drawable.cs,"gaming_cs_day1",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;



                    case 12:
                        String[] the_overseer_day1 = getResources().getStringArray(R.array.the_overseer_day1);
                        title= the_overseer_day1[0];
                        time= the_overseer_day1[1];
                        location= the_overseer_day1[2];
                        charge=the_overseer_day1[3];
                        date= the_overseer_day1[4];
                        rules=the_overseer_day1 [5];
                        c_name_number_1= the_overseer_day1 [6];
                        c_name_number_2= the_overseer_day1[7];
                        c_number_1= the_overseer_day1[8];
                        c_number_2=  the_overseer_day1[9];
                        event_view(R.drawable.the_overseer,"the_overseer_day1",title,time,location,charge,date,rules,c_name_number_1,c_name_number_2,c_number_1,c_number_2);
                        break;


                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));


    }


    void event_view(int image_id, final String event_database_title, String title, String time, String location, String charge, String date, String rule, String c1_nn, String c2_nn, String n1, String n2){

        AlertDialog.Builder mbuilder=new AlertDialog.Builder(EventsActivity.this);
        final String event_coordinator_1_number=n1,event_coordinator_2_number=n2;
        mview = getLayoutInflater().inflate(R.layout.activity_dailog, null);

        event_image= (ImageView) mview.findViewById(R.id.events_image);
        event_title_textView= (TextView) mview.findViewById(R.id.event_title);
        event_time_textView= (TextView) mview.findViewById(R.id.event_time);
        event_location_textView= (TextView) mview.findViewById(R.id.event_location);
        event_charge_textView= (TextView) mview.findViewById(R.id.event_charge);
        event_date_textView= (TextView) mview.findViewById(R.id.event_date);
        event_rule_info_textView= (TextView) mview.findViewById(R.id.rule_info);
        event_coordinator_1= (TextView) mview.findViewById(R.id.event_coordinator_1);
        event_coordinator_2= (TextView) mview.findViewById(R.id.event_coordinator_2);

        //initilize values for layout dialog
        event_image.setImageResource(image_id);
        event_title_textView.setText(title);
        event_title_textView.setTextColor(Color.parseColor(theme_color));
        event_time_textView.setText(time);
        event_location_textView.setText(location);
        event_charge_textView.setText(charge);
        event_date_textView.setText(date);
        event_rule_info_textView.setText(rule);
        event_coordinator_1.setText(c1_nn);
        event_coordinator_2.setText(c2_nn);


        main= (RelativeLayout) mview.findViewById(R.id.main);
        info_image= (LinearLayout) mview.findViewById(R.id.info);
        info_text= (CardView) mview.findViewById(R.id.cv);
        rules_button= (Button) mview.findViewById(R.id.rules_button);
        rules_button.setBackgroundColor(Color.parseColor(theme_color));
        bt= (Button) mview.findViewById(R.id.button_contact);
        bt.setTextColor(Color.parseColor(theme_color));
        ok= (Button) mview.findViewById(R.id.button_ok);
        ok.setTextColor(Color.parseColor(theme_color));
        sv= (ScrollView) mview.findViewById(R.id.sv);
        ll= (LinearLayout) mview.findViewById(R.id.ll);
        call1= (LinearLayout) mview.findViewById(R.id.firstnumber);
        call2= (LinearLayout) mview.findViewById(R.id.second_number);
        ruless= (TextView) mview.findViewById(R.id.rules);

        //FETCHING WINNERS
        databaseReference.child(event_database_title).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("winners_name").getValue().toString();
                    if(!name.isEmpty()){
                        get_winners(event_database_title);
                    }}}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        rules_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info_text.setVisibility(View.GONE);
                info_image.setVisibility(View.GONE);
                main.setVisibility(View.GONE);
                sv.setVisibility(View.VISIBLE);
                if(ll.getVisibility()==View.VISIBLE){
                    ll.setVisibility(View.GONE);
                }


            }
        });


        ll.setVisibility(View.GONE);
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(event_coordinator_1_number);
            }
        });
        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(event_coordinator_2_number);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(main.getVisibility()==View.GONE){
                    main.setVisibility(View.VISIBLE);
                    info_text.setVisibility(View.VISIBLE);
                    info_image.setVisibility(View.VISIBLE);
                    sv.setVisibility(View.GONE);
                    if(ll.getVisibility()==View.VISIBLE){
                        ll.setVisibility(View.GONE);
                    }

                }

                else if(main.getVisibility()==View.VISIBLE){
                dialog.dismiss();

                }
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ll.getVisibility()==View.GONE){
                    ll.setVisibility(View.VISIBLE);
                }
                else{
                    ll.setVisibility(View.GONE);
                }


            }


        });

        mbuilder.setView(mview);
        dialog = mbuilder.create();
        dialog.show();

    }

    void get_winners(final String event) {

        databaseReference.child(event).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    String win="Winners";
                    rules_button.setText(win);
                    ruless.setText(dataSnapshot.child("winners").getValue().toString());
                    event_rule_info_textView.setText(dataSnapshot.child("winners_name").getValue().toString());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}







