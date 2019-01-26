package com.example.syrAdmin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    ListView lstComo;
    String JSON_COMO;

    TextView txtJSON;

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

        txtJSON = (TextView) findViewById(R.id.txtJSON);

        lstComo = (ListView) findViewById(R.id.lstComo);
        lstComo.setAdapter(null);

        btnComoEntry.setOnClickListener(this);
        btnComoList.setOnClickListener(this);
    }

    private void addComo(){
        final String sayur = txtComoEntry.getText().toString().trim();
        txtComoEntry.setText("");

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

    private void viewListComo(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> arrayList= new ArrayList<HashMap<String, String>>();
        try{
            jsonObject = new JSONObject(JSON_COMO);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_COMO_JSON);
            String id;
            String sayur;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_COMO_ID);
                sayur = jo.getString(ServerConst.TAG_COMO_SAYUR);

                HashMap<String,String> comodity = new HashMap();
                comodity.put(ServerConst.TAG_COMO_ID,id);
                comodity.put(ServerConst.TAG_COMO_SAYUR,sayur);
                arrayList.add(comodity);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabComo.this, arrayList,R.layout.list_comodity,
                new String[]{ServerConst.TAG_COMO_ID,ServerConst.TAG_COMO_SAYUR},
                new int[]{R.id.id,R.id.sayur});

        lstComo.setAdapter(listAdapter);
    }

    private void listComo(){
        class listComo extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tabComo.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_COMO = s;
                txtJSON.setText(s);
                viewListComo();
            }

            @Override
            protected String doInBackground(Void... params) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetReq(ServerConst.SERVER_URL + ServerConst.URL_COMO_LIST);
                return s;
            }
        }

        listComo lc = new listComo();
        lc.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btnComoEntry){
            if(!txtComoEntry.getText().toString().isEmpty()) {
                addComo();
            }
        }else if(v == btnComoList){
            lstComo.setAdapter(null);
            listComo();
        }
    }
}
