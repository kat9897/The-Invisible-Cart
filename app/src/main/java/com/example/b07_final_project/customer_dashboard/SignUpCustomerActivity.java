package com.example.b07_final_project;

import androidx.annotation.NonNull;
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

/*
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
 */


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
        //mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication
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

                signIn(email, password);

            }
        });
    }


    private void signIn(String email, String password) {

        if (singleton.customerExists(email)){

            // customer already exists

            return;
        }

        singleton.newCustomer(email, name, password);
        Customer customer = singleton.loginCustomer(email, password);

        // login succeeded
        Intent intent = new Intent(SignUpCustomerActivity.this, MainActivity.class);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    /*
    private void signIn(String email, String password) {
        //https://firebase.google.com/docs/auth/android/password-auth#java_3
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String uid = task.getResult().getUser().getUid();
                            Customers u = new Customers(uid,name,"",email,password);
                            firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customer").child(uid);

                            firebaseDatabase.child(mAuth.getCurrentUser().getUid()).setValue(u)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUpCustomerActivity.this, "User registered to database", Toast.LENGTH_SHORT).show();
                                            } else {
                                                System.out.println("onComplete: " + task.getException().getMessage());
                                            }
                                        }
                                    });


                            // Sign in success, Move to MainActivity
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpCustomerActivity.this,"Account Created",Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(SignUpCustomerActivity.this, MainActivity.class);
                            intent.putExtra("Userid", uid);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println("onComplete: " + task.getException().getMessage());
                        }
                    }
                });
    }
    */

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}