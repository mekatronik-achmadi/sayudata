package com.sayurun.appBuyer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AdapterComo extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> nidString;
    private ArrayList<String> itemString;
    private ArrayList<String> imgStrURL;

    public AdapterComo(Context context, ArrayList<String> nidString, ArrayList<String> itemString, ArrayList<String> imgStrURL){
        this.mContext = context;
        this.nidString = nidString;
        this.itemString = itemString;
        this.imgStrURL = imgStrURL;
    }

    private class ViewCell{
        TextView nidVw;
        TextView txtVw;
        TextView imgurlVw;
        ImageView imgVw;
    }

    private class GetImgURL extends AsyncTask<String,Void,Bitmap>{
        ImageView imgView;

        public  GetImgURL(ImageView imgView){
            this.imgView = imgView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bmpres) {
            super.onPostExecute(bmpres);
            imgView.setImageBitmap(bmpres);
        }

        @Override
        protected Bitmap doInBackground(String... urls){
            String imgurl = urls[0];
            Bitmap bmp = null;

            try{
                URL url = new URL(imgurl);
                HttpURLConnection.setFollowRedirects(false);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream in = conn.getInputStream();
                    bmp = BitmapFactory.decodeStream(in);
                }else{
                    bmp = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_launcher);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return bmp;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent){

        ViewCell cell;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_como,null);

            cell = new ViewCell();
            cell.nidVw = (TextView) convertView.findViewById(R.id.nid);
            cell.txtVw = (TextView) convertView.findViewById(R.id.txt);
            cell.imgurlVw = (TextView) convertView.findViewById(R.id.imgurl);
            cell.imgVw = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(cell);
        }else{
            cell = (ViewCell) convertView.getTag();
        }

        cell.nidVw.setText(nidString.get(position));
        cell.txtVw.setText(itemString.get(position));
        cell.imgurlVw.setText(imgStrURL.get(position));
        new GetImgURL(cell.imgVw).execute(imgStrURL.get(position));
        cell.imgVw.getLayoutParams().height = 150;

        return convertView;
    }

    @Override
    public int getCount(){
        return this.itemString.size();
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }
}

