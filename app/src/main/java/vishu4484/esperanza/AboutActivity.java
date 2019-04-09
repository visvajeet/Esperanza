package vishu4484.esperanza;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import static vishu4484.esperanza.MainActivity.theme_color;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar tb;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    AppBarLayout appBarLayout;
    ImageButton fb,insta,twitter,website;
    LinearLayout dheeraj,amey,vishu;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tb= (Toolbar) findViewById(R.id.toolbar_about);
        //recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager =new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

        //adapter=new RecyclerAdapter();
        //recyclerView.setAdapter(adapter);

        appBarLayout= (AppBarLayout) findViewById(R.id.appbr);

        fb= (ImageButton) findViewById(R.id.fb_link);
        insta= (ImageButton) findViewById(R.id.insta_link);
       twitter= (ImageButton) findViewById(R.id.twitter_link);
        website= (ImageButton) findViewById(R.id.web_link);


        dheeraj= (LinearLayout) findViewById(R.id.dheeraj);
        amey= (LinearLayout) findViewById(R.id.amey);
        vishu= (LinearLayout) findViewById(R.id.vishu);

        dheeraj.setOnClickListener(this);
        vishu.setOnClickListener(this);
       amey.setOnClickListener(this);
        fb.setOnClickListener(this);
       insta.setOnClickListener(this);
        twitter.setOnClickListener(this);
        website.setOnClickListener(this);








        if(theme_color!=null) {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.parseColor("#FFA8A959"));
            }
        }
       setSupportActionBar(tb);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fb_link:
                Intent f = new Intent(Intent.ACTION_VIEW);
                f.setData(Uri.parse("https://www.facebook.com/PES.Esperanza"));
                startActivity(f);
                break;
            case R.id.insta_link:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.instagram.com/pesit_esperanza"));
                startActivity(i);
                break;
            case R.id.twitter_link:
                Intent t = new Intent(Intent.ACTION_VIEW);
                t.setData(Uri.parse("https://twitter.com/Pesit_Esperanza"));
                startActivity(t);
                break;
            case R.id.web_link:
                Intent w = new Intent(Intent.ACTION_VIEW);
                w.setData(Uri.parse("http://esperanza17.com"));
                startActivity(w);
                break;
            case R.id.dheeraj:
                dialContactPhone("9902262634");
                break;
            case R.id.vishu:
                dialContactPhone("9983214776");
                break;
            case R.id.amey:
                dialContactPhone("8095925893");
                break;

        }

    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));


    }
}



