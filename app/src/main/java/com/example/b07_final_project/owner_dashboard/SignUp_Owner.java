package com.example.b07_final_project.owner_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;

import java.util.regex.Pattern;

public class SignUp_Owner extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtConfirmPassword;

    private EditText edtName, edtphn, edtstorename;

    private Button btnSignUp;
    private CheckBox showpassword;
    private String name,phn;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHNNUMBER_REGEX = Pattern.compile("\\d{10}");

    //FirebaseAuth mAuth;
    //DatabaseReference firebaseDatabase;
    //FirebaseDatabase db;

    Presenter singleton = Singleton.getID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        //mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication


        edtName = findViewById(R.id.edtTxtName_signinOwner);
        edtphn = findViewById(R.id.edtphn_signinowner);
        edtEmail = findViewById(R.id.edtTxtEmail_signinowner1);
        edtPassword = findViewById(R.id.edtTxtPassword_signinowner1);
        edtConfirmPassword = findViewById(R.id.edtTxtConfirmPassword_signinowner1);
        btnSignUp = findViewById(R.id.btnsignIn_signinowner1);
        edtstorename = findViewById(R.id.Store_Name);
        showpassword = findViewById(R.id.showpassword_signinowner1);



        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()) {
                    edtConfirmPassword.setTransformationMethod(null);
                    edtPassword.setTransformationMethod(null);
                } else {
                    edtPassword.setTransformationMethod(new PasswordTransformationMethod());
                    edtConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edtName.getText().toString().trim();
                phn = edtphn.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmpassword = edtConfirmPassword.getText().toString().trim();
                String storename = edtstorename.getText().toString().trim();

                //check if stings are empty using TextUtils
                //Store Name
                if ( TextUtils.isEmpty(storename)){
                    Toast.makeText(SignUp_Owner.this, "Enter a store Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if(singleton.storeExists(storename)){
                     Toast.makeText(SignUp_Owner.this, "Store Name already exists", Toast.LENGTH_SHORT).show();
                     return;
                  }
                //Email
                if (TextUtils.isEmpty(email)) { //email is empty
                    Toast.makeText(SignUp_Owner.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
                    Toast.makeText(SignUp_Owner.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                } else if(singleton.ownerExists(email)){
                    Toast.makeText(SignUp_Owner.this, "Owner already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Phone
                if (TextUtils.isEmpty(email)) { //email is empty
                    Toast.makeText(SignUp_Owner.this, "Please enter Phone number", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!VALID_PHNNUMBER_REGEX.matcher(phn).find()) {
                    Toast.makeText(SignUp_Owner.this, "Please enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Password
                if (TextUtils.isEmpty(password)) { //password is empty
                    Toast.makeText(SignUp_Owner.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 8) {
                    Toast.makeText(SignUp_Owner.this, "Password must have at least 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Confirm Password
                if (!password.equals(confirmpassword)) {
                    Toast.makeText(SignUp_Owner.this, "Your passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }


                edtName.setText("");
                edtEmail.setText("");
                edtPassword.setText("");
                edtConfirmPassword.setText("");

                signInOwner(email, password, storename);
            }
        });

    }

    private void signInOwner(String email, String password, String storename) {

        // also logs you in
        singleton.newOwner(email, name, password, phn, storename);

        // login succeeded
        Intent intent = new Intent(SignUp_Owner.this, Main_Owner.class);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUp_Owner.this, Login_Owner.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}