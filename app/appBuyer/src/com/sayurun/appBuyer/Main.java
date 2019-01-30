package com.sayurun.appBuyer;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

public class Main extends TabActivity {
    public static Main self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tabComo = tabHost.newTabSpec("Comodity");
        TabHost.TabSpec tabChoice = tabHost.newTabSpec("Choice");

        tabComo.setIndicator("Sayur");
        tabComo.setContent(new Intent(this,tabComo.class));

        tabChoice.setIndicator("Pilihan");
        tabChoice.setContent(new Intent(this,tabChoice.class));

        tabHost.addTab(tabComo);
        tabHost.addTab(tabChoice);
    }
}
