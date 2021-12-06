package com.example.b07_final_project.customer_dashboard;

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
import com.example.b07_final_project.helper.FirebaseModel;
import com.example.b07_final_project.helper.LoginPresenter;
import com.example.b07_final_project.helper.MVPview;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;

import java.util.regex.Pattern;

public class SignUpCustomerActivity extends AppCompatActivity implements MVPview {

    private EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    private Button btnSignUp;
    private CheckBox showpassword;
    private String name;

    LoginPresenter presenter;

    MVPview thisActivity = this;

    //FirebaseAuth mAuth;
    //DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_customer);
        // Hide TitleBar
        getSupportActionBar().hide();
        edtName = findViewById(R.id.edtTxtName_signincustomer);
        edtEmail = findViewById(R.id.edtTxtEmail_signincustomer);
        edtPassword = findViewById(R.id.edtTxtPassword_signincustomer);
        edtConfirmPassword = findViewById(R.id.edtTxtConfirmPassword_signincustomer);
        btnSignUp = findViewById(R.id.btnsignIn_signincustomer);
        showpassword = findViewById(R.id.showpassword);

        if (LoginPresenter.getID() == null) {LoginPresenter.Initialize(new FirebaseModel(), Singleton.getID());}
        presenter = LoginPresenter.getID();

        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
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
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmpassword = edtConfirmPassword.getText().toString().trim();
                name = edtName.getText().toString().trim();

                presenter.customerSignupClicked(thisActivity, name, email, password, confirmpassword);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUpCustomerActivity.this, LoginCustomerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void makeToast(MVPview toastView, String message) {
        Toast.makeText((SignUpCustomerActivity) toastView, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emptyTextBoxes(){
        edtName.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
    }

    @Override
    public void signupOrLogin() {

        // login succeeded
        Intent intent = new Intent(SignUpCustomerActivity.this, Main_Customer.class);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}