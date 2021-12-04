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
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;

import java.util.regex.Pattern;

public class SignUpCustomerActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    private Button btnSignUp;
    private CheckBox showpassword;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private String name;

    Presenter singleton = Singleton.getID();

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

                //check if stings are empty using TextUtils
                //Name
                if(TextUtils.isEmpty(name)){ //email is empty
                    Toast.makeText(SignUpCustomerActivity.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                    //stop further execution
                    return;
                }

                //Email
                if(TextUtils.isEmpty(email)){ //email is empty
                    Toast.makeText(SignUpCustomerActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()){
                    Toast.makeText(SignUpCustomerActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Password
                if(TextUtils.isEmpty(password)){ //password is empty
                    Toast.makeText(SignUpCustomerActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }else if(password.length() < 8){
                    Toast.makeText(SignUpCustomerActivity.this, "Password must have at least 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Confirm Password
                if(!password.equals(confirmpassword)){
                    Toast.makeText(SignUpCustomerActivity.this, "Your passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                edtName.setText("");
                edtEmail.setText("");
                edtPassword.setText("");
                edtConfirmPassword.setText("");

                signIn(email, password);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUpCustomerActivity.this, LoginCustomerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    private void signIn(String email, String password) {

        if (singleton.customerExists(email)){
            // customer already exists
            Toast.makeText(this, "Customer Already Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        // also logs you in
        singleton.newCustomer(email, name, password);

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