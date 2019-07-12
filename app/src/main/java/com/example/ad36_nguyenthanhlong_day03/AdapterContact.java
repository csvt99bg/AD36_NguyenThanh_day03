package com.example.ad36_nguyenthanhlong_day03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterContact extends BaseAdapter {
    ArrayList<Contact> contactList;

    public AdapterContact(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater Inflater = LayoutInflater.from(viewGroup.getContext());
        View v = Inflater.inflate(R.layout.item_contact, viewGroup, false);
        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvNumber = v.findViewById(R.id.tvNumber);
        Contact contact = contactList.get(i);
        tvName.setText(contact.getName());
        tvNumber.setText(String.valueOf(contact.getNumber()));
        ImageView imgIcon = v.findViewById(R.id.edIcon);
        if (contact.isIcon())
            imgIcon.setImageResource(R.drawable.ic_person_outline_black_24dp);
        return v;
    }
}
