package com.mydomain.app.week9.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mydomain.app.week9.model.Contact;
import com.mydomain.app.week9.adapter.ContactAdapter;
import com.mydomain.app.week9.adapter.ContactItemClickListener;
import com.mydomain.app.week9.util.DBHelper;
import com.mydomain.app.week9.R;

import java.util.List;

public class SearchContactActivity extends AppCompatActivity implements View.OnClickListener, ContactItemClickListener{
    private RecyclerView recyclerView;
    private Button btnSearch;
    private EditText etSearch;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        dbHelper = new DBHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        etSearch = (EditText) findViewById(R.id.etSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        List<Contact> contacts = getSearchContacts();
        recyclerView.setAdapter(new ContactAdapter(contacts, this));
    }

    private List<Contact> getSearchContacts() {
        String keyword = etSearch.getText().toString();
        return dbHelper.getSearchContacts(keyword);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ContactAdapter contactAdapter = new ContactAdapter(getSearchContacts(), this);
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
