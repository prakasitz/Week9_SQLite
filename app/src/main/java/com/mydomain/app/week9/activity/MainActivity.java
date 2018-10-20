package com.mydomain.app.week9.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mydomain.app.week9.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnListContact, btnSearchContact, btnAddContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnListContact = (Button) findViewById(R.id.btnListContact);
        btnListContact.setOnClickListener(this);

        btnSearchContact = (Button) findViewById(R.id.btnSearchContact);
        btnSearchContact.setOnClickListener(this);

        btnAddContact = (Button) findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        if(v == btnListContact){
            i = new Intent(this, ListContactActivity.class);
        }
        else if(v == btnSearchContact){
            i = new Intent(this, SearchContactActivity.class);
        }
        else if(v == btnAddContact){
            i = new Intent(this, AddContactActivity.class);
        }
        startActivity(i);
    }
}
