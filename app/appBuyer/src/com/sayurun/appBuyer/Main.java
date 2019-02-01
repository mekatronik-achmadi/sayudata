package com.sayurun.appBuyer;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import java.io.File;
import java.util.Random;

public class Main extends TabActivity {
    public static Main self;

    final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    TextView txtToken;

    Handler hdlChkInet;
    Runnable runChkInet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        txtToken = (TextView) findViewById(R.id.txtToken);
        SharedPreferences idToken = getApplicationContext().getSharedPreferences(GlobalVar.fPrefName,0);
        File fpref = new File("/data/data/" + getPackageName() +  "/shared_prefs/" + GlobalVar.fPrefName + ".xml");
        if(fpref.exists()){
            GlobalVar.idTokenApp = idToken.getString("token",null);
            txtToken.setText("ID Token: "+GlobalVar.idTokenApp);
        }
        else {
            GlobalVar.idTokenApp = getRandomString(8);

            SharedPreferences.Editor editToken = idToken.edit();
            editToken.putString("token",GlobalVar.idTokenApp);
            editToken.commit();

            txtToken.setText("ID Token: "+GlobalVar.idTokenApp);
        }

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tabComo = tabHost.newTabSpec("Comodity");
        TabHost.TabSpec tabChoice = tabHost.newTabSpec("Choice");
        TabHost.TabSpec tabOrder = tabHost.newTabSpec("Order");
        TabHost.TabSpec tabGoing = tabHost.newTabSpec("Going");
        TabHost.TabSpec tabBuyer = tabHost.newTabSpec("Buyer");

        tabComo.setIndicator("Sayur");
        tabComo.setContent(new Intent(this,tabComo.class));

        tabChoice.setIndicator("Pilihan");
        tabChoice.setContent(new Intent(this,tabChoice.class));

        tabOrder.setIndicator("Pesan");
        tabOrder.setContent(new Intent(this,tabOrder.class));

        tabGoing.setIndicator("Daftar");
        tabGoing.setContent(new Intent(this,tabGoing.class));

        tabBuyer.setIndicator("Anda");
        tabBuyer.setContent(new Intent(this,tabBuyer.class));

        tabHost.addTab(tabComo);
        tabHost.addTab(tabChoice);
        tabHost.addTab(tabOrder);
        tabHost.addTab(tabGoing);
        tabHost.addTab(tabBuyer);

        hdlChkInet = new Handler();
        runChkInet = new Runnable() {
            @Override
            public void run() {
                if(checkNetwork()){
                    GlobalVar.netAvail = true;
                }
                else{
                    GlobalVar.netAvail = false;
                }
                hdlChkInet.postDelayed(this,1000);
            }
        };
        hdlChkInet.post(runChkInet);
    }

    private String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i) {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }


    private boolean checkNetwork(){
        ConnectivityManager cm = (ConnectivityManager) self.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo an = cm.getActiveNetworkInfo();
        return ((an != null) && (an.isConnected() && (an.isAvailable())));
    }
}
