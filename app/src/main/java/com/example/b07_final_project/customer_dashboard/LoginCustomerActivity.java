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
import android.widget.Switch;
import android.widget.Toast;

/*
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
*/

import com.example.b07_final_project.R;
import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.Presenter;
import com.example.b07_final_project.helper.Singleton;
import com.example.b07_final_project.owner_dashboard.Login_Owner;

import java.util.regex.Pattern;

public class LoginCustomerActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin, btnSignUp;
    Switch swtchtoOwnerMode;
    CheckBox showpassword;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    //FirebaseAuth mAuth;
    //DatabaseReference firebaseDatabase;

    Presenter singleton; // for accessing Singleton (Presenter)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);
        // Hide TitleBar
        getSupportActionBar().hide();

        //mAuth = FirebaseAuth.getInstance(); //  Initialize Firebase Authentication
        edtEmail = findViewById(R.id.edtTxtEmailAddress_customerlogin);
        edtPassword = findViewById(R.id.edtTxtPassword_customerlogin);
        btnLogin = findViewById(R.id.btnLogin_customerlogin);
        btnSignUp = findViewById(R.id.btnSignIn_customerlogin);
        showpassword = findViewById(R.id.showpassword_login);
        swtchtoOwnerMode = findViewById(R.id.switchtoOwnerMode);

        //initialize Singleton (Presenter)
        singleton = Singleton.getID();

        swtchtoOwnerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swtchtoOwnerMode.isChecked()){
                    Intent intent = new Intent(LoginCustomerActivity.this, Login_Owner.class );
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
        showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    edtPassword.setTransformationMethod(null);
                } else {
                    edtPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        // btnLogin When clicked -> It will take  MainActivity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                //check if stings are empty

                //Email
                if(TextUtils.isEmpty(email)){ //email is empty
                    Toast.makeText(LoginCustomerActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }       else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()){
                    Toast.makeText(LoginCustomerActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Password
                if(TextUtils.isEmpty(password)){ //password is empty
                    Toast.makeText(LoginCustomerActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // If entered Correctly then Login
                login(email, password);
            }
        });

        // btnLogin When clicked -> It will take to SignInCustomerActivity
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginCustomerActivity.this,SignUpCustomerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }



    private void login(String email, String password) {

        Customer customer = singleton.loginCustomer(email, password);

        if (customer == null){

            // login failed

        } else {

            // login succeeded
            Intent intent = new Intent(LoginCustomerActivity.this, Main_Customer.class);

            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    /*
    // Firebase Authentication
    private void login(String email, String password) {
        //https://firebase.google.com/docs/auth/android/password-auth#java_3
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();

                            firebaseDatabase = FirebaseDatabase.getInstance().getReference();
                            firebaseDatabase.child("Customer").child(uid).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.getValue(Integer.class) == null){
                                        Toast.makeText(getApplicationContext(), "Login in as Admin account", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        int usertype = snapshot.getValue(Integer.class);

                                        if (usertype == 0) {
                                            // Login in success, Move to MainActivity
                                            Intent intent = new Intent(LoginCustomerActivity.this, MainActivity.class);
                                            intent.putExtra("Userid",uid);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Login in as Admin account", Toast.LENGTH_SHORT).show();
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
    */


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}