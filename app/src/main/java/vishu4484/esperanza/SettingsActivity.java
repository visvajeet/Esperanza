package vishu4484.esperanza;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static vishu4484.esperanza.MainActivity.edit;
import static vishu4484.esperanza.MainActivity.editor;
import static vishu4484.esperanza.MainActivity.pref;
import static vishu4484.esperanza.MainActivity.pref2;
import static vishu4484.esperanza.MainActivity.theme_color;

public class SettingsActivity extends AppCompatActivity {
    Toolbar tb;
    TextView theme;
    View mview;
    AlertDialog dialog;
    Switch aSwitch;



    AlertDialog.Builder mbuilder;
    GridView gv;
    String match;
    String[] gridColor = {"#F44336", "#B71C1C", "#E91E63", "#9C27B0", "#FF1744", "#F50057", "#2196F3", "#1E88E5", "#03A9F4",
            "#00BCD4",
            "#D50000",
            "#FFFF00",
            "#E75480",
            "#FF1C00",
            "#B57EDC",
            "#FF0000",
            "#30D5C8",
            "#0F4D92",
            "#FF5722",
            "#DD2C00",
            "#000000",
            "#607D8B",
            "#69F0AE",
            "#039BE5",};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tb = (Toolbar) findViewById(R.id.toolbar_settings);
        aSwitch= (Switch) findViewById(R.id.switch2);
        aSwitch.getThumbDrawable().setColorFilter(Color.parseColor(theme_color),PorterDuff.Mode.MULTIPLY);
        aSwitch.getTrackDrawable().setColorFilter(Color.parseColor(theme_color), PorterDuff.Mode.MULTIPLY);

        if(theme_color!=null) {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.parseColor(theme_color));
            }
        }

        tb.setBackgroundColor(Color.parseColor(theme_color));
        setSupportActionBar(tb);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        theme = (TextView) findViewById(R.id.textView18);
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mview = getLayoutInflater().inflate(R.layout.model_cp, null);
                gv = (GridView) mview.findViewById(R.id.gv2);
                mbuilder = new AlertDialog.Builder(SettingsActivity.this);
                AdapterColor adapter = new AdapterColor(SettingsActivity.this, gridColor);
                gv.setAdapter(adapter);
                mbuilder.setView(mview);
                dialog = mbuilder.create();
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                dialog.show();
                dialog.getWindow().setLayout(width, height);



        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editor = pref.edit();

                switch (position) {
                    case 0:
                        match = gridColor[0];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 1:
                        match = gridColor[1];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;

                    case 2:
                        match = gridColor[2];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 3:
                        match = gridColor[3];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 4:
                        match = gridColor[4];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 5:
                        match = gridColor[5];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 6:
                        match = gridColor[6];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 7:
                        match = gridColor[7];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 8:
                        match = gridColor[8];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 9:
                        match = gridColor[9];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 10:
                        match = gridColor[10];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 11:
                        match = gridColor[11];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;

                    case 12:
                        match = gridColor[12];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 13:
                        match = gridColor[13];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 14:
                        match = gridColor[14];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 15:

                        match = gridColor[15];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 16:
                        match = gridColor[16];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 17:

                        match = gridColor[17];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 18:
                        match = gridColor[18];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 19:

                        match = gridColor[19];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 20:
                        match = gridColor[20];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 21:

                        match = gridColor[21];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 22:
                        match = gridColor[22];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 23:

                        match = gridColor[23];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;
                    case 24:
                        match = gridColor[24];
                        editor.putString("color", match);
                        editor.commit();
                        restart();
                        break;


                }
            }
        });
    }
});
         edit = pref2.edit();
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckNetwork.isInternetAvailable(SettingsActivity.this)) {
                    if (aSwitch.isChecked()) {

                        edit.putInt("notif", 1);
                        edit.commit();
                        colorSet();

                    } else {
                        edit.putInt("notif", 2);
                        edit.commit();
                        colorDisable();

                    }
                }
                else{
                    Toast.makeText(SettingsActivity.this, "Internet Connection Required", Toast.LENGTH_SHORT).show();
                }

                MainActivity.re();
            }
        });

        if(pref2.getInt("notif",0)==1){
            aSwitch.setChecked(true);
            colorSet();
        }
        else{
            aSwitch.setChecked(false);
            colorDisable();
        }
    }



    public void restart(){
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
    public void colorSet()
    {
        aSwitch.getThumbDrawable().setColorFilter(Color.parseColor(theme_color),PorterDuff.Mode.MULTIPLY);
        aSwitch.getTrackDrawable().setColorFilter(Color.parseColor(theme_color), PorterDuff.Mode.MULTIPLY);

    }
    public void colorDisable()
    {
        aSwitch.getThumbDrawable().setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY);
        aSwitch.getTrackDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}



