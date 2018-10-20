package com.mydomain.app.week9.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mydomain.app.week9.R;
import com.mydomain.app.week9.adapter.ContactAdapter;
import com.mydomain.app.week9.adapter.ContactItemClickListener;
import com.mydomain.app.week9.model.Contact;
import com.mydomain.app.week9.util.DBHelper;

import java.util.List;

public class ListContactActivity extends AppCompatActivity implements ContactItemClickListener{
    private RecyclerView recyclerView;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        dbHelper = new DBHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private List<Contact> getAllContacts() {
        return dbHelper.getAllContacts();
    }

    @Override
    protected void onStart(){
        super.onStart();
        ContactAdapter contactAdapter = new ContactAdapter(getAllContacts(), this);
        recyclerView.setAdapter(contactAdapter);

    }

    @Override
    public void onContactItemClick(int contactId){
        Toast.makeText(this, "Contact Id: "+ contactId, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(DBHelper.COLUMN_ID, contactId);
        startActivity(i);
    }
}
