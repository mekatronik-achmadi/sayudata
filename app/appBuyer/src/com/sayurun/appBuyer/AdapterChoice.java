package com.sayurun.appBuyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterChoice extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> strHarga;
    private ArrayList<String> strSatuan;
    private ArrayList<String> strSeller;
    private ArrayList<String> strStok;
    private ArrayList<String> strArea;

    public AdapterChoice(Context context,
                         ArrayList<String> strHarga,
                         ArrayList<String> strSatuan,
                         ArrayList<String> strSeller,
                         ArrayList<String> strStok,
                         ArrayList<String> strArea)
    {
        this.mContext = context;
        this.strHarga = strHarga;
        this.strSatuan = strSatuan;
        this.strSeller = strSeller;
        this.strStok = strStok;
        this.strArea = strArea;
    }

    private class ViewCell{
        TextView txtHarga;
        TextView txtSatuan;
        TextView txtSeller;
        TextView txtStok;
        TextView txtArea;
        TextView txtDataGlobal;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewCell cell;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_provide,null);

            cell = new ViewCell();
            cell.txtHarga = (TextView) convertView.findViewById(R.id.txtHarga);
            cell.txtSatuan = (TextView) convertView.findViewById(R.id.txtSatuan);
            cell.txtSeller = (TextView) convertView.findViewById(R.id.txtSeller);
            cell.txtStok  = (TextView) convertView.findViewById(R.id.txtStok);
            cell.txtArea = (TextView) convertView.findViewById(R.id.txtArea);
            cell.txtDataGlobal = (TextView) convertView.findViewById(R.id.txtDataGlobal);
            convertView.setTag(cell);
        }
        else{
            cell = (ViewCell) convertView.getTag();
        }

        cell.txtHarga.setText("Rp. "+strHarga.get(position));
        cell.txtSatuan.setText("/"+ strSatuan.get(position));
        cell.txtStok.setText("Stok Tersedia: "+strStok.get(position) + " " + strSatuan.get(position));
        cell.txtSeller.setText("Penyedia: "+strSeller.get(position));
        cell.txtArea.setText("Area Penjualan: "+strArea.get(position));

        return convertView;
    }

    @Override
    public int getCount(){
        return strSeller.size();
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
