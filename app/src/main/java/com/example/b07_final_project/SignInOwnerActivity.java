package com.example.b07_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignInOwnerActivity extends AppCompatActivity {
    private EditText edtName, edtphn;
    private Button btnNext;
    private Switch swtchtoCustomerMode;
    public  static final Pattern VALID_PHN_REGEX = Pattern.compile("^\\d{10}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_owner);
        edtName = findViewById(R.id.edtTxtName_signinOwner);
        edtphn = findViewById(R.id.edtphn_signinowner);
        btnNext = findViewById(R.id.btnNext);
        swtchtoCustomerMode = findViewById(R.id.switch1_signinowner);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String phn = edtphn.getText().toString().trim();
                //check if stings are empty using TextUtils
                //Name
                if(TextUtils.isEmpty(name)){ //email is empty
                    Toast.makeText(SignInOwnerActivity.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                    //stop further execution
                    return;
                }
                //Phn Number
                if(TextUtils.isEmpty(phn)){ //email is empty
                    Toast.makeText(SignInOwnerActivity.this, "Please enter your Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!VALID_PHN_REGEX.matcher(phn).find()){
                    Toast.makeText(SignInOwnerActivity.this, "Please enter a valid Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(SignInOwnerActivity.this, SignInOwnerActivity1.class);
                intent.putExtra("name", name);
                intent.putExtra("phn", phn);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //startActivity(new Intent(SignInOwnerActivity.this, SignInOwnerActivity1.class));
            }

        });
        swtchtoCustomerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!swtchtoCustomerMode.isChecked()){
                    Intent intent = new Intent(SignInOwnerActivity.this, LoginCustomerActivity.class );
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
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