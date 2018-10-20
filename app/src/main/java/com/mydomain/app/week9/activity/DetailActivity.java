package com.mydomain.app.week9.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mydomain.app.week9.R;
import com.mydomain.app.week9.model.Contact;
import com.mydomain.app.week9.util.DBHelper;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private DBHelper dbHelper;
    private Contact contact;

    private TextView tvId;
    private EditText etName, etEmail, etPhone;
    private Button btnUpdateContact, btnDeleteContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper = new DBHelper(this);

        int contactId = getIntent().getIntExtra(DBHelper.COLUMN_ID, 0);
        contact = getContactFromId(contactId);

        tvId = (TextView) findViewById(R.id.tvId);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);

        if (contact != null){
            tvId.setText("ID: " + contact.getId());
            etName.setText(contact.getName());
            etEmail.setText(contact.getEmail());
            etPhone.setText(contact.getPhone());
        }

        btnUpdateContact = (Button) findViewById(R.id.btnUpdateContact);
        btnDeleteContact = (Button) findViewById(R.id.btnDeleteContact);
        btnUpdateContact.setOnClickListener(this);
        btnDeleteContact.setOnClickListener(this);
    }

    private Contact getContactFromId(int contactId){
        return dbHelper.getContactFromId(contactId);
    }

    @Override
    public void onClick(View v) {
        if (contact  == null){
            return;
        }
        if (v == btnUpdateContact){
            contact.setName(etName.getText().toString());
            contact.setEmail(etEmail.getText().toString());
            contact.setPhone(etPhone.getText().toString());

            dbHelper.updateContact(contact);

            Toast.makeText(this, "Contact updated!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (v == btnDeleteContact){
            dbHelper.deleteContact(contact.getId());

            contact = null;

            Toast.makeText(this, "Contact deleted!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
