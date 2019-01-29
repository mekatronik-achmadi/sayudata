package com.example.syrAdmin;

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

/**
 * Created by farm on 1/28/19.
 */
public class CustomAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> itemString;
    private ArrayList<String> imgStrURL;

    Bitmap itemImage;

    public CustomAdapter(Context context, ArrayList<String> itemString, ArrayList<String> imgStrURL){
        this.mContext = context;
        this.itemString = itemString;
        this.imgStrURL = imgStrURL;
    }

    private class ViewCell{
        TextView txtVw;
        ImageView imgVw;
    }

    private void getImgURL(String strURL){

        class getImgURL extends AsyncTask<String, Void, Bitmap>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Bitmap bmp) {
                super.onPostExecute(bmp);
                itemImage = bmp;
            }

            @Override
            protected Bitmap doInBackground(String... urlStr) {
                String imgurl = urlStr[0];
                Bitmap itemImg = null;
                try{
                    URL url = new URL(imgurl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setInstanceFollowRedirects(true);

                    InputStream in = conn.getInputStream();
                    itemImg = BitmapFactory.decodeStream(in);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return itemImg;
            }
        }

        getImgURL imgObj = new getImgURL();
        imgObj.execute(strURL);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        ViewCell cell;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_grid,null);

            cell = new ViewCell();
            cell.txtVw = (TextView) convertView.findViewById(R.id.txt);
            cell.imgVw = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(cell);
        }else{
            cell = (ViewCell) convertView.getTag();
        }

        cell.txtVw.setText(itemString.get(position));
        getImgURL(imgStrURL.get(position));
        cell.imgVw.setImageBitmap(itemImage);

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
