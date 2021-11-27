package com.example.b07_final_project;

public interface View {

    void update();
    // called in response to a database update
    // should reload any database information that's displayed

    /*

    please call the following two functions from the presenter interface (via singleton) in evey view

    addView(this); // place in onResume()
    removeView(this); // place in onPause()

     */

}
