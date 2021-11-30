package com.example.b07_final_project.owner_dashboard;

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
import android.widget.Toast;

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Owners;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUp_Owner extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtConfirmPassword;

    private EditText edtName, edtphn;

    private Button btnSignUp;
    private CheckBox showpassword;
    private String name,phn;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHNNUMBER_REGEX = Pattern.compile("\\d{10}");

    FirebaseAuth mAuth;
    DatabaseReference firebaseDatabase;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication


        edtName = findViewById(R.id.edtTxtName_signinOwner);
        edtphn = findViewById(R.id.edtphn_signinowner);
        edtEmail = findViewById(R.id.edtTxtEmail_signinowner1);
        edtPassword = findViewById(R.id.edtTxtPassword_signinowner1);
        edtConfirmPassword = findViewById(R.id.edtTxtConfirmPassword_signinowner1);
        btnSignUp = findViewById(R.id.btnsignIn_signinowner1);
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

                //check if stings are empty using TextUtils
                //Email
                if (TextUtils.isEmpty(email)) { //email is empty
                    Toast.makeText(SignUp_Owner.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
                    Toast.makeText(SignUp_Owner.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
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

                signInOwner(email, password);
            }
        });

    }

    private void signInOwner(String email, String password) {
        //https://firebase.google.com/docs/auth/android/password-auth#java_3
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Add user to Firebase

                            String uid = task.getResult().getUser().getUid();
                            Owners u = new Owners(uid,name,phn,email,password);

                            firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Owner").child(uid);

                            firebaseDatabase.child(mAuth.getCurrentUser().getUid()).setValue(u)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUp_Owner.this, "User registered to database", Toast.LENGTH_SHORT).show();
                                            } else {
                                                System.out.println("onComplete: " + task.getException().getMessage());
                                            }
                                        }
                                    });

                            // Sign in success, Move to MainActivity_owner
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUp_Owner.this,"Account Created",Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(SignUp_Owner.this, Main_Owner.class);
                            intent.putExtra("Ownerid", uid);
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}