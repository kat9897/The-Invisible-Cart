package com.example.b07_final_project.helper;

import android.content.Context;
import android.widget.Toast;

public interface MVPview {
    void makeToast(MVPview toastView, String message);

    void emptyTextBoxes();

    void signupOrLogin();

}
