package com.sayurun.appBuyer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class tabComo extends Activity{

    EditText txtSearch;
    TextView txtLoading;
    GridView lstView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_como);

        txtSearch = (EditText) findViewById(R.id.txtSearch);
        txtLoading = (TextView) findViewById(R.id.txtLoading);
        lstView = (GridView) findViewById(R.id.lstView);

        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER){
                    if(!txtSearch.getText().toString().isEmpty()) {
                        hideKeyboard();
                        findData();
                    }
                }
                return false;
            }
        });

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(txtSearch.getText().toString().isEmpty()){
                    listData();
                }
            }
        });

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GlobalVar.strChoNid = ((TextView) view.findViewById(R.id.nid)).getText().toString().trim();
                GlobalVar.strChoTxt = ((TextView) view.findViewById(R.id.txt)).getText().toString().trim();
                GlobalVar.strChoImg = ((TextView) view.findViewById(R.id.imgurl)).getText().toString().trim();
                GlobalVar.imgCho = ((ImageView) view.findViewById(R.id.img)).getDrawable();
                GlobalVar.runCho = true;
                Main.self.getTabHost().setCurrentTab(1);
            }
        });

        listData();
    }

    private void viewData(String str_input){
        JSONObject jsonObject;
        ArrayList<String> nidList = new ArrayList<String>();
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

                nidList.add(id);
                txtList.add(sayur);
                imgList.add(ServerConst.IMAGES_URL+img+ServerConst.IMAGES_EXT);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        lstView.setAdapter(new AdapterComo(getBaseContext(), nidList, txtList, imgList));
        if(nidList.size()==0){
            txtLoading.setText("Komoditas tidak ditemukan");
        }else{
            txtLoading.setText("Silahkan Pilih Komoditas");
        }
    }

    private void listData(){
        txtLoading.setText("Menyiapkan Komoditas");
        class listData extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewData(s);
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

    private void findData(){
        txtLoading.setText("Mencari Komoditas");
        final String sayur = txtSearch.getText().toString().trim();

        class findData extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                viewData(s);
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
