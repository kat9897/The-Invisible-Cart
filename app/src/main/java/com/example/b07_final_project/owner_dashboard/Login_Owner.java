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
import android.widget.Switch;
import android.widget.Toast;

import com.example.b07_final_project.R;
import com.example.b07_final_project.customer_dashboard.LoginCustomerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Login_Owner extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnSignUp;
    private Switch swtchtoCustomerMode;
    private CheckBox showpassword;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    FirebaseAuth mAuth;
    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_owner);
        // Hide TitleBar
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtTxtEmailAddress_ownerlogin);
        edtPassword =findViewById(R.id.edtTxtPassword_ownerlogin);
        btnLogin = findViewById(R.id.btnLogin_ownerlogin);
        showpassword = findViewById(R.id.showpassword_login_owner);
        swtchtoCustomerMode = findViewById(R.id.switchtoOwnerMode);
        btnSignUp = findViewById(R.id.btnSignIn_ownerlogin);

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

                //Email
                if(TextUtils.isEmpty(email)){ //email is empty
                    Toast.makeText(Login_Owner.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }       else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()){
                    Toast.makeText(Login_Owner.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Password
                if(TextUtils.isEmpty(password)){ //password is empty
                    Toast.makeText(Login_Owner.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // If entered Correctly then Login
                login(email, password);
            }
        });
    }

    private void login(String email, String password) {
        //https://firebase.google.com/docs/auth/android/password-auth#java_3

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login in success, Move to MainActivity

                            String uid = task.getResult().getUser().getUid();

                            firebaseDatabase = FirebaseDatabase.getInstance().getReference();
                            firebaseDatabase.child("Owner").child(uid).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.getValue(Integer.class) == null){
                                        Toast.makeText(getApplicationContext(), "Login in as Customer account", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        int usertype = snapshot.getValue(Integer.class);

                                        if (usertype == 1) {

                                            Intent intent = new Intent(Login_Owner.this, Main_Owner.class);
                                            intent.putExtra("Ownerid", uid);

                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Login in as customer", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


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