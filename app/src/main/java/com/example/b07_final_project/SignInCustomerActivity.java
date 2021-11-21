package com.example.b07_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignInCustomerActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    Button btnSignUp;
    Switch swtchtoOwnerMode;
    CheckBox showpassword;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);



    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_customer);

        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication
        edtName = findViewById(R.id.edtTxtName_signincustomer);
        edtEmail = findViewById(R.id.edtTxtEmail_signincustomer);
        edtPassword = findViewById(R.id.edtTxtPassword_signincustomer);
        edtConfirmPassword = findViewById(R.id.edtTxtConfirmPassword_signincustomer);
        btnSignUp = findViewById(R.id.btnsignIn_signincustomer);
        showpassword = findViewById(R.id.showpassword);
        swtchtoOwnerMode = findViewById(R.id.switch1_signincustomer);


        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    //TODO
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
                String name = edtName.getText().toString().trim();

                //check if stings are empty using TextUtils
                //Name
                if(TextUtils.isEmpty(name)){ //email is empty
                    Toast.makeText(SignInCustomerActivity.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                    //stop further execution
                    return;
                }

                //Email
                if(TextUtils.isEmpty(email)){ //email is empty
                    Toast.makeText(SignInCustomerActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()){
                    Toast.makeText(SignInCustomerActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Password
                if(TextUtils.isEmpty(password)){ //password is empty
                    Toast.makeText(SignInCustomerActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }else if(password.length() < 8){
                    Toast.makeText(SignInCustomerActivity.this, "Password must have at least 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Confirm Password
                if(!password.equals(confirmpassword)){
                    Toast.makeText(SignInCustomerActivity.this, "Your passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                signIn(email, password);
            }
        });
    }


    private void signIn(String email, String password) {
        //https://firebase.google.com/docs/auth/android/password-auth#java_3
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, Move to MainActivity
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignInCustomerActivity.this,"Account Created",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(SignInCustomerActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}