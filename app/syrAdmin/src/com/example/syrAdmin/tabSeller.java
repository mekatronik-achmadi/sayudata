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
public class tabSeller extends Activity implements View.OnClickListener{
    EditText txtSellerEntry;
    EditText txtSellerSearch;
    EditText txtSellerDelete;

    Button btnSellerEntry;
    Button btnSellerSearch;
    Button btnSellerDelete;

    ListView lstSeller;
    Runnable listSellerReq;
    Handler listSellerHndl;

    ListView lstSearch;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller);

        txtSellerEntry = (EditText) findViewById(R.id.txtSellerEntry);
        txtSellerSearch = (EditText) findViewById(R.id.txtSellerSearch);
        txtSellerDelete = (EditText) findViewById(R.id.txtSellerDelete);

        btnSellerEntry = (Button) findViewById(R.id.btnSellerEntry);
        btnSellerSearch = (Button) findViewById(R.id.btnSellerSearch);
        btnSellerDelete = (Button) findViewById(R.id.btnSellerDelete);

        lstSeller = (ListView) findViewById(R.id.lstSeller);
        lstSeller.setAdapter(null);

        lstSearch = (ListView) findViewById(R.id.lstSearch);
        lstSearch.setAdapter(null);

        btnSellerEntry.setOnClickListener(this);
        btnSellerSearch.setOnClickListener(this);
        btnSellerDelete.setOnClickListener(this);

        listSellerHndl = new Handler();
        listSellerReq = new Runnable() {
            @Override
            public void run() {
                listSeller();
                listSellerHndl.postDelayed(this,500);
            }
        };
        listSellerHndl.post(listSellerReq);
    }

    private void addSeller(){
        final String name = txtSellerEntry.getText().toString().trim();
        txtSellerEntry.setText("");

        class addComo extends AsyncTask<Void,Void,String> {

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
                HashMap<String,String> params = new HashMap();
                params.put(ServerConst.KEY_SELLER_NAME,name);

                ReqHandler rh = new ReqHandler();
                String res = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_SELLER_ADD, params);
                return res;
            }
        }

        addComo ac = new addComo();
        ac.execute();
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

                HashMap<String,String> comodity = new HashMap();
                comodity.put("no",Integer.toString(i+1));
                comodity.put(ServerConst.TAG_SELLER_ID,id);
                comodity.put(ServerConst.TAG_SELLER_NAME,name);
                arrayList.add(comodity);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabSeller.this, arrayList,R.layout.list_seller,
                new String[]{"no",ServerConst.TAG_SELLER_ID,ServerConst.TAG_SELLER_NAME},
                new int[]{R.id.no,R.id.id,R.id.name});

        lstSeller.setAdapter(listAdapter);
    }

    private void listSeller(){
        class listComo extends AsyncTask<Void,Void,String>{
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

        listComo lc = new listComo();
        lc.execute();
    }

    private void viewFindComo(String str_input){
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

                HashMap<String,String> comodity = new HashMap();
                comodity.put("no",Integer.toString(i+1));
                comodity.put(ServerConst.TAG_SELLER_ID,id);
                comodity.put(ServerConst.TAG_SELLER_NAME,name);
                arrayList.add(comodity);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListAdapter listAdapter = new SimpleAdapter(
                tabSeller.this, arrayList,R.layout.list_seller,
                new String[]{"no",ServerConst.TAG_SELLER_ID,ServerConst.TAG_SELLER_NAME},
                new int[]{R.id.no,R.id.id,R.id.name});

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
                viewFindComo(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetRequestParam(ServerConst.SERVER_URL + ServerConst.URL_SELLER_FIND,name);
                return s;
            }
        }

        findSeller fc = new findSeller();
        fc.execute();
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
                HashMap<String,String> params = new HashMap();
                params.put(ServerConst.KEY_SELLER_ID,id);

                ReqHandler rh = new ReqHandler();
                String res = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_SELLER_DEL, params);
                return res;
            }
        }

        delSeller dc = new delSeller();
        dc.execute();
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onClick(View v){
        if(v == btnSellerEntry){
            if(!txtSellerEntry.getText().toString().isEmpty()){
                hideKeyboard();
                addSeller();
            }
        }else if(v == btnSellerSearch){
            if(!txtSellerSearch.getText().toString().isEmpty()){
                hideKeyboard();
                findSeller();
            }
        }else if(v == btnSellerDelete){
            if(!txtSellerDelete.getText().toString().isEmpty()){
                hideKeyboard();
                delSeller();
            }
        }
    }
}
