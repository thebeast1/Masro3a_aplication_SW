package com.example.mohamed_reda.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

    ArrayList<Person> users;
    ArrayList<Item> items;

    LayoutInflater inflater;
    PostAdapter(Activity activity,ArrayList<Item> items,ArrayList<Person> users)
    {
        this.users = users;
        this.items = items;
        inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v = inflater.inflate(R.layout.posts,null);

        TextView Pname=(TextView) v.findViewById(R.id.Pname);
        TextView Iname=(TextView) v.findViewById(R.id.Iname);
        TextView Idesc=(TextView) v.findViewById(R.id.Idesc);
        ImageView Ppic=(ImageView) v.findViewById(R.id.pic);
        ImageView Ipic=(ImageView) v.findViewById(R.id.Ipic);

        Pname.setText(users.get(i).getName());
        Iname.setText(items.get(i).getName());
        Idesc.setText(items.get(i).getDesc());
        Ppic.setImageResource(R.drawable.ic_launcher_background);//users.get(i).getPic());
        Ipic.setImageResource(R.drawable.ic_launcher_background);//items.get(i).getPic());

        return v;
    }
}
