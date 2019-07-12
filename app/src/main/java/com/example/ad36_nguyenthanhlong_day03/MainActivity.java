package com.example.ad36_nguyenthanhlong_day03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    Contact contact1, contact2, contact3, contact4, contact5;
    ArrayList<Contact> contactList;
    AdapterContact adapterContact;
    RelativeLayout btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = findViewById(R.id.lvcontact);
        btAdd = findViewById(R.id.bt_add);
        contactList = new ArrayList<>();

        contact1 = new Contact("Mr A", 112233, true);
        contact2 = new Contact("Mr B", 223344, true);
        contact3 = new Contact("Mr C", 334455, false);
        contact4 = new Contact("Mr D", 445566, true);
        contact5 = new Contact("Mr E", 775566, true);


        contactList.add(contact1);
        contactList.add(contact2);
        contactList.add(contact3);
        contactList.add(contact4);
        contactList.add(contact5);

        adapterContact = new AdapterContact(contactList);
        lvContact.setAdapter(adapterContact);


        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Add_new_Contact.class);
                startActivity(intent);

            }
        });

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_contact, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnChangeContact:
                                Intent intent = new Intent(getBaseContext(), Add_new_Contact.class);
                                startActivity(intent);
                                break;
                            case R.id.mnDeleteContact:
                                contactList.remove(contactList.get(i));
                                adapterContact.notifyDataSetChanged();

                                break;
                            case R.id.mnCallContact:
                                Toast.makeText(getBaseContext(), "Calling", Toast.LENGTH_LONG).show();
                                onBackPressed();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });
        Intent intent1 = getIntent();
        String nameContact = intent1.getStringExtra("name");
        int number = intent1.getIntExtra("numberphone", 0);
        int kticon = intent1.getIntExtra("htIcon", 0);
        boolean hticon;

        if (kticon == 0) {
            hticon = false;

        } else hticon = true;
        Contact contact = new Contact(nameContact, number, hticon);
        contactList.add(contact);

    }
}
