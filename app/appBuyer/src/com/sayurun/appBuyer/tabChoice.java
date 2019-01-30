package com.sayurun.appBuyer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by farm on 1/30/19.
 */
public class tabChoice extends Activity {

    TextView txtNid;
    TextView txtSayur;

    Handler hdlComo;
    Runnable runComo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_choice);

        txtNid = (TextView) findViewById(R.id.txtNid);
        txtSayur = (TextView) findViewById(R.id.txtSayur);

        hdlComo = new Handler();
        runComo = new Runnable() {
            @Override
            public void run() {
                hdlComo.postDelayed(this,100);
            }
        };
        hdlComo.post(runComo);
    }
}
