package com.sayurun.appBuyer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class tabChoice extends Activity {

    Handler hdlComo;
    Runnable runComo;

    ImageView imgView;
    TextView txtSayur;
    TextView txtLoading;
    ListView lstProvide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_choice);

        imgView = (ImageView) findViewById(R.id.imgView);
        txtSayur = (TextView) findViewById(R.id.txtSayur);
        txtLoading = (TextView) findViewById(R.id.txtLoading);
        lstProvide = (ListView) findViewById(R.id.lstProvide);

        txtSayur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(GlobalVar.netAvail==true) {
                    imgView.setImageDrawable(GlobalVar.imgCho);
                    findData();
                }
            }
        });

        hdlComo = new Handler();
        runComo = new Runnable() {
            @Override
            public void run() {
                if(GlobalVar.runCho==true){
                    GlobalVar.runCho=false;
                    txtSayur.setText(GlobalVar.strChoTxt);
                }

                if(GlobalVar.netAvail==false){
                    txtLoading.setText("Jaringan Tidak Tersedia");
                    txtSayur.setText("");
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
                    lstProvide.setAdapter(null);
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

        lstProvide.setAdapter(new AdapterChoice(getBaseContext(),strHarga,strSatuan,strSeller,strStok,strArea));
        if(strSeller.size()==0){
            txtLoading.setText("Penjual Tidak Ada");
        }else {
            txtLoading.setText("Silahkan Pilih Harga");
        }
    }

    private void findData(){
        txtLoading.setText("Sedang Mencari");
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
                HashMap<String,String> params = new HashMap<String, String>();
                params.put(ServerConst.KEY_PROVIDE_IDSAYUR,idsayur);
                ReqHandler rh = new ReqHandler();
                String s = rh.sendPostReq(ServerConst.SERVER_URL + ServerConst.URL_PROVIDE_COMO_FIND,params);
                return s;
            }
        }
        findData objFind = new findData();
        objFind.execute();
    }
}
