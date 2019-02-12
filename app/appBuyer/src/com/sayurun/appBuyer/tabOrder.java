package com.sayurun.appBuyer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class tabOrder extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_order);
        Toast.makeText(this,"Coming not too soon",Toast.LENGTH_SHORT);
    }
}