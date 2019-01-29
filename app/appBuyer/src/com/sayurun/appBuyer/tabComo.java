package com.sayurun.appBuyer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tabComo extends Activity{

    EditText txtSearch;
    Button btnSearch;

    GridView lstView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_como);

        txtSearch = (EditText) findViewById(R.id.txtSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        lstView = (GridView) findViewById(R.id.lstView);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtSearch.getText().toString().isEmpty()){
                    hideKeyboard();
                    lstView.setAdapter(null);
                    findData();
                }
            }
        });

        findData();
    }

    private void viewList(String str_input){
        JSONObject jsonObject;
        ArrayList<String> txtList = new ArrayList<String>();
        ArrayList<String> imgList = new ArrayList<String>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_COMO_RESULT);
            String id;
            String sayur;
            String img;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_COMO_ID);
                sayur = jo.getString(ServerConst.TAG_COMO_SAYUR);
                img = jo.getString(ServerConst.TAG_COMO_IMG);

                txtList.add(id+":"+sayur);
                imgList.add(ServerConst.IMAGES_URL+img+ServerConst.IMAGES_EXT);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        lstView.setAdapter(new CustomAdapter(getBaseContext(), txtList, imgList));
    }

    private void listData(){
        class listData extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewList(s);
            }

            @Override
            protected String doInBackground(Void... v) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetReq(ServerConst.SERVER_URL + ServerConst.URL_COMO_LIST);
                return s;
            }
        }

        listData objList = new listData();
        objList.execute();
    }

    private void viewFindComo(String str_input){
        JSONObject jsonObject;
        ArrayList<String> txtList = new ArrayList<String>();
        ArrayList<String> imgList = new ArrayList<String>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_COMO_RESULT);
            String id;
            String sayur;
            String img;

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString(ServerConst.TAG_COMO_ID);
                sayur = jo.getString(ServerConst.TAG_COMO_SAYUR);
                img = jo.getString(ServerConst.TAG_COMO_IMG);

                txtList.add(id+":"+sayur);
                imgList.add(ServerConst.IMAGES_URL+img+ServerConst.IMAGES_EXT);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        lstView.setAdapter(new CustomAdapter(getBaseContext(), txtList, imgList));
    }

    private void findData(){
//        final String sayur = txtSearch.getText().toString().trim();
        final String sayur = "bayam";

        class findData extends AsyncTask<Void,Void,String>{
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
        findData objFind = new findData();
        objFind.execute();
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
