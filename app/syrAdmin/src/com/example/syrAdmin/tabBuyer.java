package com.example.syrAdmin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by farm on 1/25/19.
 */
public class tabBuyer extends Activity implements View.OnClickListener{
    EditText txtBuyerEntry;
    EditText txtBuyerSearch;
    EditText txtBuyerDelete;

    Button btnBuyerEntry;
    Button btnBuyerSearch;
    Button btnBuyerDelete;

    ListView lstBuyer;
    Runnable listBuyerReq;
    Handler listBuyerHndl;

    ListView lstSearch;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer);

        txtBuyerEntry = (EditText) findViewById(R.id.txtBuyerEntry);
        txtBuyerSearch = (EditText) findViewById(R.id.txtBuyerSearch);
        txtBuyerDelete = (EditText) findViewById(R.id.txtBuyerDelete);

        btnBuyerEntry = (Button) findViewById(R.id.btnBuyerEntry);
        btnBuyerSearch = (Button) findViewById(R.id.btnBuyerSearch);
        btnBuyerDelete = (Button) findViewById(R.id.btnBuyerDelete);

        lstBuyer = (ListView) findViewById(R.id.lstBuyer);
        lstBuyer.setAdapter(null);

        lstSearch = (ListView) findViewById(R.id.lstSearch);
        lstSearch.setAdapter(null);

        btnBuyerEntry.setOnClickListener(this);
        btnBuyerSearch.setOnClickListener(this);
        btnBuyerDelete.setOnClickListener(this);

        listBuyerHndl = new Handler();
        listBuyerReq = new Runnable() {
            @Override
            public void run() {
//                listBuyer();
                listBuyerHndl.postDelayed(this,500);
            }
        };
        listBuyerHndl.post(listBuyerReq);
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onClick(View v){

    }
}
