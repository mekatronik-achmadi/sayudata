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
public class tabSeller extends Activity{
    EditText txtSellerEntry;
    EditText txtSellerSearch;
    EditText txtSellerDelete;

    Button btnSellerEntry;
    Button btnSellerSearch;
    Button btnSellerDelete;

    GridView lstSeller;
    Runnable listSellerReq;
    Handler listSellerHndl;

    GridView lstSearch;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_seller);

        txtSellerEntry = (EditText) findViewById(R.id.txtSellerEntry);
        txtSellerSearch = (EditText) findViewById(R.id.txtSellerSearch);
        txtSellerDelete = (EditText) findViewById(R.id.txtSellerDelete);

        btnSellerEntry = (Button) findViewById(R.id.btnSellerEntry);
        btnSellerSearch = (Button) findViewById(R.id.btnSellerSearch);
        btnSellerDelete = (Button) findViewById(R.id.btnSellerDelete);

        lstSeller = (GridView) findViewById(R.id.lstSeller);
        lstSeller.setAdapter(null);

        lstSearch = (GridView) findViewById(R.id.lstSearch);
        lstSearch.setAdapter(null);

        btnSellerEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtSellerEntry.getText().toString().isEmpty()){
                    hideKeyboard();
                    addSeller();
                    listSellerHndl.postAtTime(listSellerReq,500);
                }
            }
        });
        btnSellerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtSellerSearch.getText().toString().isEmpty()){
                    hideKeyboard();
                    findSeller();
                    listSellerHndl.postAtTime(listSellerReq,500);
                }
            }
        });
        btnSellerDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtSellerDelete.getText().toString().isEmpty()){
                    hideKeyboard();
                    delSeller();
                    listSellerHndl.postAtTime(listSellerReq,500);
                }
            }
        });

        listSellerHndl = new Handler();
        listSellerReq = new Runnable() {
            @Override
            public void run() {
                listSeller();
            }
        };

        listSellerHndl.postAtTime(listSellerReq,500);
    }

    private void addSeller(){
        final String name = txtSellerEntry.getText().toString().trim();
        txtSellerEntry.setText("");

        class addSeller extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(tabSeller.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<String, String>();
                params.put(ServerConst.KEY_SELLER_NAME,name);

                ReqHandler rh = new ReqHandler();
                String res = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_SELLER_ADD, params);
                return res;
            }
        }

        addSeller objAdd = new addSeller();
        objAdd.execute();
    }

    private void viewListSeller(String str_input){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> arrayList= new ArrayList<HashMap<String, String>>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_SELLER_RESULT);
            String id;
            String name;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_SELLER_ID);
                name = jo.getString(ServerConst.TAG_SELLER_NAME);

                HashMap<String,String> seller = new HashMap<String, String>();
                seller.put(ServerConst.TAG_SELLER_ID,id);
                seller.put(ServerConst.TAG_SELLER_NAME,name);
                arrayList.add(seller);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabSeller.this, arrayList,R.layout.list_seller,
                new String[]{ServerConst.TAG_SELLER_ID,ServerConst.TAG_SELLER_NAME},
                new int[]{R.id.id,R.id.name});

        lstSeller.setAdapter(listAdapter);
    }

    private void listSeller(){
        class listSeller extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewListSeller(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetReq(ServerConst.SERVER_URL + ServerConst.URL_SELLER_LIST);
                return s;
            }
        }

        listSeller objList = new listSeller();
        objList.execute();
    }

    private void viewFindSeller(String str_input){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> arrayList= new ArrayList<HashMap<String, String>>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_SELLER_RESULT);
            String id;
            String name;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_SELLER_ID);
                name = jo.getString(ServerConst.TAG_SELLER_NAME);

                HashMap<String,String> seller = new HashMap<String, String>();
                seller.put(ServerConst.TAG_SELLER_ID,id);
                seller.put(ServerConst.TAG_SELLER_NAME,name);
                arrayList.add(seller);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabSeller.this, arrayList,R.layout.list_seller,
                new String[]{ServerConst.TAG_SELLER_ID,ServerConst.TAG_SELLER_NAME},
                new int[]{R.id.id,R.id.name});

        lstSearch.setAdapter(listAdapter);
    }

    private void findSeller(){
        final String name = txtSellerSearch.getText().toString().trim();
        txtSellerSearch.setText("");

        class findSeller extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewFindSeller(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetRequestParam(ServerConst.SERVER_URL + ServerConst.URL_SELLER_FIND,name);
                return s;
            }
        }

        findSeller objFind = new findSeller();
        objFind.execute();
    }

    private void delSeller(){
        final String id = txtSellerDelete.getText().toString().trim();
        txtSellerDelete.setText("");

        class delSeller extends AsyncTask<Void,Void,String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(tabSeller.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<String, String>();
                params.put(ServerConst.KEY_SELLER_ID,id);

                ReqHandler rh = new ReqHandler();
                String res = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_SELLER_DEL, params);
                return res;
            }
        }

        delSeller objDel = new delSeller();
        objDel.execute();
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
