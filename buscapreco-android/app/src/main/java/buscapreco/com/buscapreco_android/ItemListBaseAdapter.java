package buscapreco.com.buscapreco_android;

/**
 * Created by cesar on 20/11/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ItemListBaseAdapter extends BaseAdapter {
    private static ArrayList<Produto> itemDetailsrrayList;

    private LayoutInflater l_Inflater;

    public ItemListBaseAdapter(Context context, ArrayList<Produto> results) {
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itemDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return itemDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.item_details_view, null);
            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
            holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
            holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.price);
            holder.txt_qtdelojas = (TextView) convertView.findViewById(R.id.qtdelojas);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NumberFormat format = NumberFormat.getCurrencyInstance();

        holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
        holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getItemDescription());
        holder.txt_itemPrice.setText(format.format(itemDetailsrrayList.get(position).getPreco()));
        String loja = " loja";
        if(itemDetailsrrayList.get(position).getQtdelojas()>1){
            loja+="s";
        }
        holder.txt_qtdelojas.setText(itemDetailsrrayList.get(position).getQtdelojas()+ loja);
        if(itemDetailsrrayList.get(position).getImagem()==null||itemDetailsrrayList.get(position).getImagem().isEmpty()){
            holder.itemImage.setImageResource(R.drawable.semimagem);
        }else{
            byte[] imageAsBytes = Base64.decode(itemDetailsrrayList.get(position).getImagem().getBytes(), Base64.DEFAULT);
            holder.itemImage.setImageBitmap(
                    Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length), 400, 400, true)
            );
        }

        return convertView;
    }

    static class ViewHolder {
        TextView txt_itemName;
        TextView txt_itemDescription;
        TextView txt_itemPrice;
        TextView txt_qtdelojas;
        ImageView itemImage;
    }
}
