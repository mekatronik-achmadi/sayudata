package com.example.syrAdmin;

import android.graphics.drawable.Drawable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by farm on 1/25/19.
 */
public class ReqHandler {

    public Drawable getImageReq(String reqURL){
        Drawable img;
        try{
            URL url = new URL(reqURL);
            InputStream is = (InputStream)url.getContent();
            img = Drawable.createFromStream(is, "src");
        }catch (MalformedURLException e) {
            img = null;
        } catch (IOException e) {
            img = null;
        }

        return img;
    }

    public String sendPostReq(String reqURL, HashMap<String,String> PostDataParam){
        URL url;

        StringBuilder sb = new StringBuilder();
        try{
            url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getPostDataString(PostDataParam));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String response;
                while ((response = br.readLine()) != null){
                    sb.append(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    public String sendGetReq(String requestURL){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setReadTimeout(5000);
            con.setConnectTimeout(5000);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String sendGetRequestParam(String requestURL, String id){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL+id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(5000);
            con.setConnectTimeout(5000);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
