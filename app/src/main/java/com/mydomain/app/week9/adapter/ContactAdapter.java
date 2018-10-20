package com.mydomain.app.week9.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mydomain.app.week9.R;
import com.mydomain.app.week9.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>{
    private List<Contact> contacts;
    private ContactItemClickListener contactItemClickListener;

    public ContactAdapter(List<Contact> contacts, ContactItemClickListener contactItemClickListener){
        this.contacts = contacts;
        this.contactItemClickListener = contactItemClickListener;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i){
        Contact contact = contacts.get(i);
        contactViewHolder.setContact(contact);
        contactViewHolder.tvId.setText("ID: " + contact.getId());
        contactViewHolder.tvName.setText(contact.getName());
        contactViewHolder.tvEmail.setText(contact.getEmail());
        contactViewHolder.tvPhone.setText(contact.getPhone());
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.contact_item, viewGroup, false);
        return new ContactViewHolder(itemView, contactItemClickListener);
    }

    @Override
    public int getItemCount(){
        return contacts.size();
    }
}

class ContactViewHolder extends RecyclerView.ViewHolder{

    private ContactItemClickListener contactItemClickListener;
    private Contact contact;
    protected TextView tvId;
    protected TextView tvName;
    protected TextView tvEmail;
    protected TextView tvPhone;

    private Context ctx;

    public Context getCtx(){
        return ctx;
    }

    public ContactViewHolder(View v, ContactItemClickListener contactItemClickListener){
        super(v);
        this.contactItemClickListener = contactItemClickListener;
        ctx = v.getContext();
        tvId = (TextView) v.findViewById(R.id.tvId);
        tvName = (TextView) v.findViewById(R.id.tvName);
        tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        tvPhone = (TextView) v.findViewById(R.id.tvPhone);

        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ContactViewHolder.this.contactItemClickListener.onContactItemClick(contact.getId());
            }
        });
    }

    public void setContact(Contact contact){
        this.contact = contact;
    }

}
