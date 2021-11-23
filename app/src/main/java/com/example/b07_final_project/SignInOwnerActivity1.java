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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignInOwnerActivity1 extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtConfirmPassword;
    private Button btnSignUp;
    private Switch swtchtoCustomerMode;
    private CheckBox showpassword;
    private String name,phn;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    FirebaseAuth mAuth;
    DatabaseReference firebaseDatabase;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_owner1);
        Intent intent = getIntent();
        name = intent.getStringExtra("name").trim();
        phn = intent.getStringExtra("phn").trim();

        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication

        // TODO --> Fix Not showing up (on Firebase)
//        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

//        //TODO
//        db= FirebaseDatabase.getInstance();
//        db.getReference().child("Node").setValue("Val");

        edtEmail = findViewById(R.id.edtTxtEmail_signinowner1);
        edtPassword = findViewById(R.id.edtTxtPassword_signinowner1);
        edtConfirmPassword = findViewById(R.id.edtTxtConfirmPassword_signinowner1);
        btnSignUp = findViewById(R.id.btnsignIn_signinowner1);
        showpassword = findViewById(R.id.showpassword_signinowner1);
        swtchtoCustomerMode = findViewById(R.id.switch1_signinOwner1);


        swtchtoCustomerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!swtchtoCustomerMode.isChecked()){
                    Intent intent = new Intent(SignInOwnerActivity1.this, LoginCustomerActivity.class );
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
            }
        });
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
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmpassword = edtConfirmPassword.getText().toString().trim();

                //check if stings are empty using TextUtils
                //Email
                if (TextUtils.isEmpty(email)) { //email is empty
                    Toast.makeText(SignInOwnerActivity1.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
                    Toast.makeText(SignInOwnerActivity1.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Password
                if (TextUtils.isEmpty(password)) { //password is empty
                    Toast.makeText(SignInOwnerActivity1.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 8) {
                    Toast.makeText(SignInOwnerActivity1.this, "Password must have at least 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Confirm Password
                if (!password.equals(confirmpassword)) {
                    Toast.makeText(SignInOwnerActivity1.this, "Your passwords do not match", Toast.LENGTH_SHORT).show();
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
                            Users u = new Users(1,uid,name,phn,email,password);
                            firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Owner").child(uid);

                            firebaseDatabase.child(mAuth.getCurrentUser().getUid()).setValue(u)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignInOwnerActivity1.this, "User registered to database", Toast.LENGTH_SHORT).show();
                                            } else {
                                                System.out.println("onComplete: " + task.getException().getMessage());
                                            }
                                        }
                                    });

                            // Sign in success, Move to MainActivity_owner
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignInOwnerActivity1.this,"Account Created",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(SignInOwnerActivity1.this, MainActivity_Owner.class));
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