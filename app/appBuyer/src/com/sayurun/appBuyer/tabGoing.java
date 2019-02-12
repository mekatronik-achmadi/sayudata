package com.sayurun.appBuyer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class tabGoing extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_going);
        Toast.makeText(this,"Coming not too soon",Toast.LENGTH_SHORT);
    }
}
