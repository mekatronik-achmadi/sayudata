package com.sayurun.appBuyer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class tabChoice extends Activity {

    private tabChoice self;

    Handler hdlComo;
    Runnable runComo;

    ImageView imgView;
    TextView txtSayur;
    ListView lstProvide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        self = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_choice);

        imgView = (ImageView) findViewById(R.id.imgView);
        txtSayur = (TextView) findViewById(R.id.txtSayur);
        lstProvide = (ListView) findViewById(R.id.lstProvide);

        hdlComo = new Handler();
        runComo = new Runnable() {
            @Override
            public void run() {
                if(GlobalVar.runCho==true){
                    GlobalVar.runCho=false;
                    imgView.setImageDrawable(GlobalVar.imgCho);
//                    txtSayur.setText(GlobalVar.strChoTxt);
                    findData();
                }
                hdlComo.postDelayed(this,100);
            }
        };
        hdlComo.post(runComo);
    }

    private void viewData(String str_input){
        JSONObject jsonObject;
        ArrayList<String> strHarga= new ArrayList<String>();
        ArrayList<String> strSatuan= new ArrayList<String>();
        ArrayList<String> strSeller= new ArrayList<String>();
        ArrayList<String> strStok= new ArrayList<String>();
        ArrayList<String> strArea= new ArrayList<String>();
        try{
            jsonObject = new JSONObject(str_input);
            JSONArray result = jsonObject.getJSONArray(ServerConst.TAG_CHOICE_RESULT);
            String harga;
            String satuan;
            String seller;
            String stok;
            String area;

            for(int i = 0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);

                harga = jo.getString(ServerConst.TAG_CHOICE_HARGA);
                strHarga.add(harga);

                satuan = jo.getString(ServerConst.TAG_CHOICE_SATUAN);
                strSatuan.add(satuan);

                seller = jo.getString(ServerConst.TAG_CHOICE_SELLER);
                strSeller.add(seller);

                stok = jo.getString(ServerConst.TAG_CHOICE_STOK);
                strStok.add(stok);

                area = jo.getString(ServerConst.TAG_CHOICE_AREA);
                strArea.add(area);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        txtSayur.setText(str_input);
        lstProvide.setAdapter(new AdapterChoice(getBaseContext(),strHarga,strSatuan,strSeller,strStok,strArea));
    }

    private void findData(){
        final String idsayur = GlobalVar.strChoNid;

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
                String s = rh.sendGetRequestParam(ServerConst.SERVER_URL + ServerConst.URL_PROVIDE_COMO_FIND,idsayur);
                return s;
            }
        }
        findData objFind = new findData();
        objFind.execute();
    }
}
