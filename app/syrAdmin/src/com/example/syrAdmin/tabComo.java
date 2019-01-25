package com.example.syrAdmin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by farm on 1/25/19.
 */
public class tabComo extends Activity implements View.OnClickListener {
    EditText txtComoEntry;
    EditText txtComoSearch;
    EditText txtComoDelete;

    Button btnComoEntry;
    Button btnComoSearch;
    Button btnComoDelete;
    Button btnComoList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comodity);

        txtComoEntry = (EditText) findViewById(R.id.txtComoEntry);
        txtComoSearch = (EditText) findViewById(R.id.txtComoSearch);
        txtComoDelete = (EditText) findViewById(R.id.txtComoDelete);

        btnComoEntry = (Button) findViewById(R.id.btnComoEntry);
        btnComoSearch = (Button) findViewById(R.id.btnComoSearch);
        btnComoDelete = (Button) findViewById(R.id.btnComoDelete);
        btnComoList = (Button) findViewById(R.id.btnComoList);

        btnComoEntry.setOnClickListener(this);
    }

    private void addComo(){
        final String sayur = txtComoEntry.getText().toString().trim();

        class addComo extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tabComo.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tabComo.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap();
                params.put(ServerConst.KEY_COMO_SAYUR,sayur);

                ReqHandler rh = new ReqHandler();
                String res = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_COMO_ADD, params);
                return res;
            }
        }

        addComo ac = new addComo();
        ac.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btnComoEntry){
            if(!txtComoEntry.getText().toString().isEmpty()) {
                addComo();
            }
        }
    }
}
