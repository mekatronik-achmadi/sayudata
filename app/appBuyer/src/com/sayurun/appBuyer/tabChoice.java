package com.sayurun.appBuyer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class tabChoice extends Activity {

    Handler hdlComo;
    Runnable runComo;

    TextView txtNid;
    TextView txtSayur;

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
                if(tabComo.runCho==true){
                    txtNid.setText(tabComo.strChoNid);
                    txtSayur.setText(tabComo.strChoTxt);
                    tabComo.runCho=false;
                }
                hdlComo.postDelayed(this,100);
            }
        };
        hdlComo.post(runComo);
    }
}
