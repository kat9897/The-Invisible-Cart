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
import com.example.b07_final_project.customer_dashboard.LoginCustomerActivity;
import com.example.b07_final_project.helper.FirebaseModel;
import com.example.b07_final_project.helper.LoginPresenter;
import com.example.b07_final_project.helper.MVPview;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;

import java.util.regex.Pattern;

public class SignUp_Owner extends AppCompatActivity implements  MVPview{

    private EditText edtEmail, edtPassword, edtConfirmPassword;

    private EditText edtName, edtphn, edtstorename;

    private Button btnSignUp;
    private CheckBox showpassword;
    private String name, phoneNumber;

    //FirebaseAuth mAuth;
    //DatabaseReference firebaseDatabase;
    //FirebaseDatabase db;

    LoginPresenter presenter;
    MVPview thisActivity = this;

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

        if (LoginPresenter.getID() == null) {LoginPresenter.Initialize(new FirebaseModel(), Singleton.getID());}
        presenter = LoginPresenter.getID();

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
                phoneNumber = edtphn.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmpassword = edtConfirmPassword.getText().toString().trim();
                String storename = edtstorename.getText().toString().trim();


                presenter.ownerSignupClicked(thisActivity, name, email, password, confirmpassword, phoneNumber, storename);
            }
        });

    }

    @Override
    public void makeToast(MVPview toastView, String message) {
        Toast.makeText((SignUp_Owner) toastView, message, Toast.LENGTH_SHORT).show();
    }

    public void emptyTextBoxes(){
        edtName.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
        edtphn.setText("");
        edtstorename.setText("");
    }

    public void ownerSignedUp() {

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