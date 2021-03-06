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
import android.widget.Switch;
import android.widget.Toast;

import com.example.b07_final_project.R;
import com.example.b07_final_project.customer_dashboard.LoginCustomerActivity;
import com.example.b07_final_project.helper.FirebaseModel;
import com.example.b07_final_project.helper.LoginPresenter;
import com.example.b07_final_project.helper.MVPview;
import com.example.b07_final_project.helper.Owner;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;

import java.util.regex.Pattern;

public class Login_Owner extends AppCompatActivity implements MVPview {

    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnSignUp;
    private Switch swtchtoCustomerMode;
    private CheckBox showpassword;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    //FirebaseAuth mAuth;
    //DatabaseReference firebaseDatabase;

    LoginPresenter presenter;
    MVPview thisActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        //mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtTxtEmailAddress_ownerlogin);
        edtPassword =findViewById(R.id.edtTxtPassword_ownerlogin);
        btnLogin = findViewById(R.id.btnLogin_ownerlogin);
        showpassword = findViewById(R.id.showpassword_login_owner);
        swtchtoCustomerMode = findViewById(R.id.switchtoOwnerMode);
        btnSignUp = findViewById(R.id.btnSignIn_ownerlogin);

        if (LoginPresenter.getID() == null) {LoginPresenter.Initialize(new FirebaseModel(), Singleton.getID());}
        presenter = LoginPresenter.getID();

        swtchtoCustomerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!swtchtoCustomerMode.isChecked()){
                    Intent intent = new Intent(Login_Owner.this, LoginCustomerActivity.class );
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()) {
                    edtPassword.setTransformationMethod(null);
                } else {
                    edtPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        // btnSignUp When clicked -> It will take you to SignUpOwnerActivity
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Owner.this, SignUp_Owner.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        // btnLogin When clicked  -> It will take you to MainActivityOwner
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                //check if stings are empty

                presenter.ownerLoginClicked(thisActivity, email, password);
            }
        });
    }

    @Override
    public void makeToast(MVPview toastView, String message) {
        Toast.makeText((Login_Owner) toastView, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emptyTextBoxes(){
        edtEmail.setText("");
        edtPassword.setText("");
    }

    @Override
    public void signupOrLogin() {

        // login succeeded
        Intent intent = new Intent(Login_Owner.this, Main_Owner.class);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Login_Owner.this, LoginCustomerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}