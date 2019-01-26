package com.example.syrAdmin;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tabComo = tabHost.newTabSpec("Comodity");
        TabHost.TabSpec tabSeller = tabHost.newTabSpec("Seller");
        TabHost.TabSpec tabBuyer = tabHost.newTabSpec("Buyer");
        TabHost.TabSpec tabOrder = tabHost.newTabSpec("Order");
        TabHost.TabSpec tabProvide = tabHost.newTabSpec("Provide");

        tabComo.setIndicator("Sayur");
        tabComo.setContent(new Intent(this,tabComo.class));

        tabSeller.setIndicator("Jual");
        tabSeller.setContent(new Intent(this,tabSeller.class));

        tabBuyer.setIndicator("Beli");
        tabBuyer.setContent(new Intent(this,tabBuyer.class));

        tabOrder.setIndicator("Pesan");
        tabOrder.setContent(new Intent(this,tabOrder.class));

        tabProvide.setIndicator("Sedia");
        tabProvide.setContent(new Intent(this,tabProvide.class));

        tabHost.addTab(tabComo);
        tabHost.addTab(tabSeller);
        tabHost.addTab(tabBuyer);
        tabHost.addTab(tabOrder);
        tabHost.addTab(tabProvide);
    }
}
