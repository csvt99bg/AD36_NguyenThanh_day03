package com.example.ad36_nguyenthanhlong_day03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_new_Contact extends AppCompatActivity {
    EditText edName, edNumber, edAddress, edEmail;
    Spinner spinner;
    Button btSave, btCancel, btGroup;
    LinearLayout add_Avatar;
    int icon=0;
    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__contact);
        final Intent intent = getIntent();

        add_Avatar = findViewById(R.id.add_Avatar);
        edName = findViewById(R.id.edNameContact);
        edNumber = findViewById(R.id.edNumberContact);
        edEmail = findViewById(R.id.edEmail);
        edAddress = findViewById(R.id.edAddress);
        spinner = findViewById(R.id.TypePhone);
        btSave = findViewById(R.id.bt_Finish);
        btCancel = findViewById(R.id.bt_Cancel);
        btGroup = findViewById(R.id.btGroup);

        ArrayList<String> typePhone = new ArrayList<>();
        typePhone.add("Family");
        typePhone.add("Company");
        typePhone.add("Custum");
        typePhone.add("Other");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, typePhone);
        spinner.setAdapter(arrayAdapter);
        add_Avatar.setOnClickListener(new View.OnClickListener() {
            String[] add_photo = {"Take photo", "Choose photo"};
            String the_Choise;

            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(Add_new_Contact.this).setTitle("Change Photo")
                        .setSingleChoiceItems(add_photo, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                 the_Choise = add_photo[i].toString();
                            }
                        })
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                closeContextMenu();
                            }
                        }).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                icon=1;
                                Toast.makeText(getBaseContext(),the_Choise,Toast.LENGTH_LONG).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btGroup.setOnClickListener(new View.OnClickListener() {
            String[] group = {"Gia đình", "Đối tác", "Bạn bè", "Công ty"};
            boolean[] checks = {false, false, false, false};
            String getname=" ";
            @Override
            public void onClick(View view) {


                AlertDialog alertDialog = new AlertDialog.Builder(Add_new_Contact.this)
                        .setTitle("Select Style Group")
                        .setMultiChoiceItems(group, checks, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                if (checks[i]==true){
                                    getname=getname+" "+group[i];
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Toast.makeText(getBaseContext(), getname, Toast.LENGTH_LONG).show();

                            }
                        }).create();
                alertDialog.show();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edName.getText().toString().isEmpty() || edNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Can't Save", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent1 = new Intent(getBaseContext(), MainActivity.class);

                    String name = edName.getText().toString();
                    String numberphone = edNumber.getText().toString();
                    int num = Integer.valueOf(numberphone);


                    intent1.putExtra("name", name);
                    intent1.putExtra("numberphone", num);
                    intent1.putExtra("htIcon", icon);

                    startActivity(intent1);

                    Toast.makeText(getBaseContext(), "have been add a new contact", Toast.LENGTH_LONG).show();
                    onBackPressed();


                }
            }
        });



    }

}
