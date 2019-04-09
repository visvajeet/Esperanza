package vishu4484.esperanza;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static vishu4484.esperanza.MainActivity.device_id;
import static vishu4484.esperanza.MainActivity.theme_color;
import static vishu4484.esperanza.R.layout.activity_partake;

public class PartakeActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar tb;
    private TextView forgot_pass;
    private EditText mail,pass;
    private EditText nameMake_ac,emailMake_ac,passwordMake_ac;
    Button GateIn,makeAc;
    ImageView logo1,logo2;

   private ScrollView scrollView_signIn,scrollView_signUp;
    private Window window;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    AlertDialog dialog;
    View mview;
    private String email,password,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_partake);
        anim();
        tb = (Toolbar) findViewById(R.id.toolbar_partake);


        if (theme_color != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.parseColor("#FF03A9F4"));
            }
        }
        tb.setBackgroundColor(Color.parseColor("#FF03A9F4"));
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        if (!CheckNetwork.isInternetAvailable(PartakeActivity.this)) {
            Toast.makeText(PartakeActivity.this, "Internet Connection Required", Toast.LENGTH_SHORT).show();
        }

        scrollView_signIn= (ScrollView) findViewById(R.id.scroll_signIn);
        scrollView_signUp= (ScrollView) findViewById(R.id.scroll_signUp);




        auth = FirebaseAuth.getInstance();
        if ((auth.getCurrentUser() != null)) {
            finish();
            startActivity(new Intent(getApplicationContext(), DashBoard.class));
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        databaseReference = FirebaseDatabase.getInstance().getReference();




        forgot_pass = (TextView) findViewById(R.id.forgot_pass);
        //TextView title = (TextView) findViewById(R.id.esperanza_partake);
        TextView signUp = (TextView) findViewById(R.id.signUp);
        TextView signIn = (TextView) findViewById(R.id.signIn);
        GateIn = (Button) findViewById(R.id.getIn);
        makeAc = (Button) findViewById(R.id.makeAc);
        mail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);

        nameMake_ac = (EditText) findViewById(R.id.name_makeAc);
        passwordMake_ac = (EditText) findViewById(R.id.password_makeAc);
        emailMake_ac = (EditText) findViewById(R.id.email_makeAc);

        forgot_pass.setOnClickListener(this);
        makeAc.setOnClickListener(this);
        GateIn.setOnClickListener(this);

       // title.setTextColor(Color.parseColor(theme_color));
        signUp.setTextColor(Color.parseColor(theme_color));
        GateIn.setBackgroundColor(Color.parseColor(theme_color));
        effect1();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb.setTitle("Sign In");
                if (Build.VERSION.SDK_INT >= 21) {
                    window.setStatusBarColor(Color.parseColor(theme_color));
                }
                tb.setBackgroundColor(Color.parseColor(theme_color));
                effect2();
                scrollView_signUp.setVisibility(View.GONE);
                scrollView_signIn.setVisibility(View.VISIBLE);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb.setTitle("Sign up");
                if (Build.VERSION.SDK_INT >= 21) {
                    window.setStatusBarColor(Color.parseColor("#FF03A9F4"));
                }
                tb.setBackgroundColor(Color.parseColor("#FF03A9F4"));
                 effect1();
                scrollView_signIn.setVisibility(View.GONE);
                scrollView_signUp.setVisibility(View.VISIBLE);
            }
        });

    }

    private void signUpUser() {

        email = emailMake_ac.getText().toString().trim();
        password = passwordMake_ac.getText().toString().trim();
        name = nameMake_ac.getText().toString().trim();
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        if ((!email.equals("") && name.length() > 2) && !name.equals("") && (!password.equals("") && password.length() > 5) && CheckNetwork.isInternetAvailable(PartakeActivity.this) && name.matches("[a-zA-Z]+")) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    Exception exc = task.getException();
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        saveInfo();
                        Toast.makeText(PartakeActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                        finish();
                        restart();
                        startActivity(new Intent(getApplicationContext(), DashBoard.class));
                    }
                    else {
                        progressDialog.dismiss();
                        if (exc.getMessage().contains("The email address is badly formatted.")) {
                            Toast.makeText(PartakeActivity.this, "Email is badly formatted", Toast.LENGTH_SHORT).show();
                        } else if (exc.getLocalizedMessage().contains("The email address is already in use by another account.")) {
                            Toast.makeText(PartakeActivity.this, "Email is already registered", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PartakeActivity.this, "Could not Register please try again", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }

        else {
            progressDialog.dismiss();
            if (!CheckNetwork.isInternetAvailable(PartakeActivity.this)) {
                Toast.makeText(PartakeActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(name)) {
                Toast.makeText(PartakeActivity.this, "Please enter name ", Toast.LENGTH_SHORT).show();
            } else if (!name.matches("[a-zA-Z]+")) {
                Toast.makeText(PartakeActivity.this, "Name must be in characters without space", Toast.LENGTH_SHORT).show();
            } else if (name.length() < 3)
                Toast.makeText(PartakeActivity.this, "Oops- this is less then 3 characters   ", Toast.LENGTH_SHORT).show();


            else if (TextUtils.isEmpty(email)) {
                Toast.makeText(PartakeActivity.this, "Please enter email ", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(PartakeActivity.this, "Password must contain at least 6 characters", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(PartakeActivity.this, "Please enter password ", Toast.LENGTH_SHORT).show();
            } else if (!CheckNetwork.isInternetAvailable(PartakeActivity.this)) {
                Toast.makeText(PartakeActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        }
    }




    private void saveInfo() {
        FirebaseUser user=auth.getCurrentUser();
        assert user != null;
        DatabaseReference data=databaseReference.child("Registered_User");
        String userName= nameMake_ac.getText().toString().trim();
        Map<String,String> details = new HashMap<>();
        details.put("Imi",device_id);
        details.put("Name",userName);
        details.put("Email",user.getEmail());
        data.setValue(details);

    }




    private void signInUser(){

        String email=mail.getText().toString().trim();
        String password=pass.getText().toString().trim();
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        if((!email.equals("")&&(!password.equals(""))&&CheckNetwork.isInternetAvailable(PartakeActivity.this))) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(PartakeActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                        finish();
                        restart();
                        startActivity(new Intent(getApplicationContext(),DashBoard.class));
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(PartakeActivity.this, "Invalid account details", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

        else{
            progressDialog.dismiss();


            if (!CheckNetwork.isInternetAvailable(PartakeActivity.this)) {
                Toast.makeText(PartakeActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }


            else if (TextUtils.isEmpty(email)) {
                Toast.makeText(PartakeActivity.this, "Please enter email ", Toast.LENGTH_SHORT).show();
            }
             else if (TextUtils.isEmpty(password)) {
                Toast.makeText(PartakeActivity.this, "Please enter password ", Toast.LENGTH_SHORT).show();
            }

        }




    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.makeAc){
           signUpUser();
        }
        else if(v.getId()==R.id.getIn){
            signInUser();
        }

        else if(v.getId()==R.id.forgot_pass){
            mview = getLayoutInflater().inflate(R.layout.activity_forgot_password, null);
            AlertDialog.Builder m_builder=new AlertDialog.Builder(PartakeActivity.this);
            Button bt= (Button) mview.findViewById(R.id.reset_pass);
            forgot_pass= (EditText) mview.findViewById(R.id.email_reset);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset();

                }
            });

            m_builder.setView(mview);
            dialog = m_builder.create();
            dialog.show();
        }
    }
    public void reset()
    {
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        final String email=forgot_pass.getText().toString().trim();
        if(!email.equals("")) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(PartakeActivity.this, "Password sent to email ", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(PartakeActivity.this, "Failed to send password", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(PartakeActivity.this, "Please enter email" + email, Toast.LENGTH_SHORT).show();

        }
    }

    public void restart(){
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

  private void anim(){

      logo1 = (ImageView) findViewById(R.id.pesLogo1);
      logo2 = (ImageView) findViewById(R.id.pesLogo2);

              Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),
              R.anim.rotate);
              logo2.startAnimation(rotate);
              logo1.startAnimation(rotate);
    }

    private void effect1(){
        scrollView_signUp.animate().scaleX(1.0f);
        scrollView_signIn.animate().scaleX(0.0f);
        scrollView_signUp.animate().scaleY(1.0f);
        scrollView_signIn.animate().scaleY(0.0f);

    }
    private void effect2(){
        scrollView_signUp.animate().scaleX(0.0f);
        scrollView_signIn.animate().scaleX(1.0f);
        scrollView_signUp.animate().scaleY(0.0f);
        scrollView_signIn.animate().scaleY(1.0f);

    }


}




