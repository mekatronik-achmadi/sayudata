package com.example.syrAdmin;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    ListView lstComo;
    Runnable listComoReq;
    Handler listComoHndl;

    ListView lstSearch;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_comodity);

        txtComoEntry = (EditText) findViewById(R.id.txtComoEntry);
        txtComoSearch = (EditText) findViewById(R.id.txtComoSearch);
        txtComoDelete = (EditText) findViewById(R.id.txtComoDelete);

        btnComoEntry = (Button) findViewById(R.id.btnComoEntry);
        btnComoSearch = (Button) findViewById(R.id.btnComoSearch);
        btnComoDelete = (Button) findViewById(R.id.btnComoDelete);

        lstComo = (ListView) findViewById(R.id.lstComo);
        lstComo.setAdapter(null);

        lstSearch = (ListView) findViewById(R.id.lstSearch);
        lstSearch.setAdapter(null);

        btnComoEntry.setOnClickListener(this);
        btnComoSearch.setOnClickListener(this);
        btnComoDelete.setOnClickListener(this);

        listComoHndl = new Handler();
        listComoReq = new Runnable() {
            @Override
            public void run() {
                listComo();
                listComoHndl.postDelayed(this,500);
            }
        };
        listComoHndl.post(listComoReq);
    }

    private void addComo(){
        final String sayur = txtComoEntry.getText().toString().trim();
        txtComoEntry.setText("");

        class addComo extends AsyncTask<Void,Void,String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
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

    private void viewListComo(String str_input){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> arrayList= new ArrayList<HashMap<String, String>>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_COMO_RESULT);
            String id;
            String sayur;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_COMO_ID);
                sayur = jo.getString(ServerConst.TAG_COMO_SAYUR);

                HashMap<String,String> comodity = new HashMap();
                comodity.put("no",Integer.toString(i+1));
                comodity.put(ServerConst.TAG_COMO_ID,id);
                comodity.put(ServerConst.TAG_COMO_SAYUR,sayur);
                arrayList.add(comodity);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabComo.this, arrayList,R.layout.list_comodity,
                new String[]{"no",ServerConst.TAG_COMO_ID,ServerConst.TAG_COMO_SAYUR},
                new int[]{R.id.no,R.id.id,R.id.sayur});

        lstComo.setAdapter(listAdapter);
    }

    private void listComo(){
        class listComo extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewListComo(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetReq(ServerConst.SERVER_URL + ServerConst.URL_COMO_LIST);
                return s;
            }
        }

        listComo lc = new listComo();
        lc.execute();
    }

    private void viewFindComo(String str_input){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> arrayList= new ArrayList<HashMap<String, String>>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_COMO_RESULT);
            String id;
            String sayur;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_COMO_ID);
                sayur = jo.getString(ServerConst.TAG_COMO_SAYUR);

                HashMap<String,String> comodity = new HashMap();
                comodity.put("no",Integer.toString(i+1));
                comodity.put(ServerConst.TAG_COMO_ID,id);
                comodity.put(ServerConst.TAG_COMO_SAYUR,sayur);
                arrayList.add(comodity);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabComo.this, arrayList,R.layout.list_comodity,
                new String[]{"no",ServerConst.TAG_COMO_ID,ServerConst.TAG_COMO_SAYUR},
                new int[]{R.id.no,R.id.id,R.id.sayur});

        lstSearch.setAdapter(listAdapter);
    }

    private void findComo(){
        final String sayur = txtComoSearch.getText().toString().trim();
        txtComoSearch.setText("");

        class findComo extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewFindComo(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetRequestParam(ServerConst.SERVER_URL + ServerConst.URL_COMO_FIND,sayur);
                return s;
            }
        }

        findComo fc = new findComo();
        fc.execute();
    }

    private void delComo(){
        final String id = txtComoDelete.getText().toString().trim();
        txtComoDelete.setText("");

        class delComo extends AsyncTask<Void,Void,String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(tabComo.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap();
                params.put(ServerConst.KEY_COMO_ID,id);

                ReqHandler rh = new ReqHandler();
                String res = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_COMO_DEL, params);
                return res;
            }
        }

        delComo dc = new delComo();
        dc.execute();
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onClick(View v) {
        if(v == btnComoEntry){
            if(!txtComoEntry.getText().toString().isEmpty()) {
                hideKeyboard();
                addComo();
            }
        }else if(v == btnComoSearch){
            if(!txtComoSearch.getText().toString().isEmpty()) {
                hideKeyboard();
                findComo();
            }
        }else if(v == btnComoDelete){
            if(!txtComoDelete.getText().toString().isEmpty()){
                hideKeyboard();
                delComo();
            }
        }
    }
}
