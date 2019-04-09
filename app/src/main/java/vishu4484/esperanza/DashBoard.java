package vishu4484.esperanza;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import static vishu4484.esperanza.MainActivity.editt;
import static vishu4484.esperanza.MainActivity.pref3;
import static vishu4484.esperanza.MainActivity.theme_color;
import static vishu4484.esperanza.R.layout.dashboard;

/**
 * Created by Vishu on 2/19/2017.
 */
public class    DashBoard extends AppCompatActivity implements View.OnClickListener{
    private  Button logout,more,delete;
    private TextView userName,userEmail;
    ImageView qr_code;
    private FirebaseAuth auth;
    private FirebaseUser firebaseuser;
    private LinearLayout linearLayout;
    DatabaseReference databaseReference;
    Toolbar toolbar;
    Animation bounce;
    Bitmap bitmap;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Registered_User");

        setContentView(dashboard);
        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(false);

        auth=FirebaseAuth.getInstance();
        firebaseuser=auth.getCurrentUser();
        if(auth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),PartakeActivity.class));

        }

        logout= (Button) findViewById(R.id.signOut);
        more= (Button) findViewById(R.id.more_op);
        delete= (Button) findViewById(R.id.delete_ac);


        linearLayout= (LinearLayout) findViewById(R.id.layout_more);


        more.setOnClickListener(this);
        logout.setOnClickListener(this);
        delete.setOnClickListener(this);
        linearLayout.setOnClickListener(this);

        linearLayout= (LinearLayout) findViewById(R.id.layout_more);

        userName= (TextView) findViewById(R.id.user_name);
        userEmail= (TextView) findViewById(R.id.user_email);
        qr_code= (ImageView) findViewById(R.id.qr_code);

        bounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        userName.startAnimation(bounce);


        if(theme_color!=null) {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.parseColor(theme_color));
            }
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar_dashboard);
        toolbar.setBackgroundColor(Color.parseColor(theme_color));
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        editt = pref3.edit();

        userName.setTextColor(Color.parseColor(theme_color));
        userEmail.setTextColor(Color.parseColor(theme_color));
        logout.setBackgroundColor(Color.parseColor(theme_color));
        more.setBackgroundColor(Color.parseColor(theme_color));




        if (!CheckNetwork.isInternetAvailable(DashBoard.this)) {
            Toast.makeText(DashBoard.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        else {


            progressDialog.setMessage("Fetching Data...");
            progressDialog.show();
            databaseReference.child(firebaseuser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        String name = dataSnapshot.child("Name").getValue().toString();
                        String mail = dataSnapshot.child("Email").getValue().toString();
                        String qr_code_get=dataSnapshot.child("Imi").getValue().toString().concat("\n"+mail);

                        MultiFormatWriter multiFormatWriter =new MultiFormatWriter();

                        QRCodeWriter writer = new QRCodeWriter();
                        try {
                            BitMatrix bitMatrix = writer.encode(qr_code_get, BarcodeFormat.QR_CODE, 150, 150);
                            int width = bitMatrix.getWidth();
                            int height = bitMatrix.getHeight();
                            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                            for (int x = 0; x < width; x++) {
                                for (int y = 0; y < height; y++) {
                                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                                }
                            }
                            qr_code.setImageBitmap(bmp);


                        } catch (WriterException e) {
                            e.printStackTrace();
                        }

                        editt.putString("profile_name", name);
                        editt.putString("profile_email", name);
                        editt.commit();
                        userName.setText(name);
                        userEmail.setText(mail);


                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void restart(){
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {

       if(v.getId()==R.id.more_op) {
           more.setVisibility(View.GONE);
           linearLayout.setVisibility(View.VISIBLE);
       }
          else if(v.getId()==R.id.signOut) {
           createDialog_signOut();

       }

       else if(v.getId()==R.id.delete_ac){
           createDialog();
       }
       }





    private void createDialog_signOut()
    {
        final AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure you want to Log out?");
        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                yes_signOut();
            }
        });
        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();
    }



    private void createDialog()
    {
        final AlertDialog.Builder alertDlg= new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure you want to delete your account?");
        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                yes();
            }
        });
        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();
    }





   private void yes()
    {
        progressDialog.setMessage("Please wait...");
        if (!CheckNetwork.isInternetAvailable(DashBoard.this)) {
            Toast.makeText(DashBoard.this, "Internet Connection Required", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.show();
            removeData();
            firebaseuser.delete().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override

                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(DashBoard.this, "Account has been deleted Successfully", Toast.LENGTH_SHORT).show();
                        auth.signOut();
                        editt.putString("profile_name", "Profile");
                        editt.commit();
                        restart();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(DashBoard.this, "Please try again", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }

    private void removeData() {
        FirebaseUser user=auth.getCurrentUser();
        assert user != null;
        DatabaseReference data=databaseReference.child(user.getUid());
        data.removeValue();
    }

    private void yes_signOut(){

        auth.signOut();
        editt.putString("profile_name","Profile");
        editt.commit();
        finish();
        startActivity(new Intent(getApplicationContext(),PartakeActivity.class));
        restart();
    }

}





