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
public class tabBuyer extends Activity implements View.OnClickListener{
    EditText txtBuyerEntry;
    EditText txtBuyerSearch;
    EditText txtBuyerDelete;

    Button btnBuyerEntry;
    Button btnBuyerSearch;
    Button btnBuyerDelete;

    GridView lstBuyer;
    Runnable listBuyerReq;
    Handler listBuyerHndl;

    GridView lstSearch;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_buyer);

        txtBuyerEntry = (EditText) findViewById(R.id.txtBuyerEntry);
        txtBuyerSearch = (EditText) findViewById(R.id.txtBuyerSearch);
        txtBuyerDelete = (EditText) findViewById(R.id.txtBuyerDelete);

        btnBuyerEntry = (Button) findViewById(R.id.btnBuyerEntry);
        btnBuyerSearch = (Button) findViewById(R.id.btnBuyerSearch);
        btnBuyerDelete = (Button) findViewById(R.id.btnBuyerDelete);

        lstBuyer = (GridView) findViewById(R.id.lstBuyer);
        lstBuyer.setAdapter(null);

        lstSearch = (GridView) findViewById(R.id.lstSearch);
        lstSearch.setAdapter(null);

        btnBuyerEntry.setOnClickListener(this);
        btnBuyerSearch.setOnClickListener(this);
        btnBuyerDelete.setOnClickListener(this);

        listBuyerHndl = new Handler();
        listBuyerReq = new Runnable() {
            @Override
            public void run() {
                listBuyer();
            }
        };

        listBuyerHndl.postAtTime(listBuyerReq,500);
    }

    private void addBuyer(){
        final String name = txtBuyerEntry.getText().toString().trim();
        txtBuyerEntry.setText("");

        class addBuyer extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(tabBuyer.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<String, String>();
                params.put(ServerConst.KEY_BUYER_NAME,name);

                ReqHandler rh = new ReqHandler();
                String res = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_BUYER_ADD, params);
                return res;
            }
        }

        addBuyer objAdd = new addBuyer();
        objAdd.execute();
    }

    private void viewListBuyer(String str_input){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> arrayList= new ArrayList<HashMap<String, String>>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_BUYER_RESULT);
            String id;
            String name;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_BUYER_ID);
                name = jo.getString(ServerConst.TAG_BUYER_NAME);

                HashMap<String,String> buyer = new HashMap<String, String>();
                buyer.put(ServerConst.TAG_BUYER_ID,id);
                buyer.put(ServerConst.TAG_BUYER_NAME,name);
                arrayList.add(buyer);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabBuyer.this, arrayList,R.layout.list_buyer,
                new String[]{ServerConst.TAG_BUYER_ID,ServerConst.TAG_BUYER_NAME},
                new int[]{R.id.id,R.id.name});

        lstBuyer.setAdapter(listAdapter);
    }

    private void listBuyer(){
        class listBuyer extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewListBuyer(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetReq(ServerConst.SERVER_URL + ServerConst.URL_BUYER_LIST);
                return s;
            }
        }

        listBuyer objList = new listBuyer();
        objList.execute();
    }

    private void viewFindBuyer(String str_input){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> arrayList= new ArrayList<HashMap<String, String>>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_BUYER_RESULT);
            String id;
            String name;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_BUYER_ID);
                name = jo.getString(ServerConst.TAG_BUYER_NAME);

                HashMap<String,String> buyer = new HashMap<String, String>();
                buyer.put(ServerConst.TAG_BUYER_ID,id);
                buyer.put(ServerConst.TAG_BUYER_NAME,name);
                arrayList.add(buyer);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabBuyer.this, arrayList,R.layout.list_buyer,
                new String[]{ServerConst.TAG_BUYER_ID,ServerConst.TAG_BUYER_NAME},
                new int[]{R.id.id,R.id.name});

        lstSearch.setAdapter(listAdapter);
    }

    private void findBuyer(){
        final String name = txtBuyerSearch.getText().toString().trim();
        txtBuyerSearch.setText("");

        class findBuyer extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewFindBuyer(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetRequestParam(ServerConst.SERVER_URL + ServerConst.URL_BUYER_FIND,name);
                return s;
            }
        }

        findBuyer objFind = new findBuyer();
        objFind.execute();
    }

    private void delBuyer(){
        final String id = txtBuyerDelete.getText().toString().trim();
        txtBuyerDelete.setText("");

        class delBuyer extends AsyncTask<Void,Void,String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(tabBuyer.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<String, String>();
                params.put(ServerConst.KEY_BUYER_ID,id);

                ReqHandler rh = new ReqHandler();
                String res = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_BUYER_DEL, params);
                return res;
            }
        }

        delBuyer objDel = new delBuyer();
        objDel.execute();
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onClick(View v){
        if(v == btnBuyerEntry){
            if(!txtBuyerEntry.getText().toString().isEmpty()){
                hideKeyboard();
                addBuyer();
                listBuyerHndl.postAtTime(listBuyerReq,500);
            }
        }else if(v == btnBuyerSearch) {
            if (!txtBuyerSearch.getText().toString().isEmpty()) {
                hideKeyboard();
                findBuyer();
                listBuyerHndl.postAtTime(listBuyerReq,500);
            }
        }else if(v == btnBuyerDelete){
            if(!txtBuyerDelete.getText().toString().isEmpty()){
                hideKeyboard();
                delBuyer();
                listBuyerHndl.postAtTime(listBuyerReq,500);
            }
        }
    }
}
