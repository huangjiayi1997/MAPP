package com.example.asus.assignment;

import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 10/1/2018.
 */

public class ContactArrayAdapter extends RecyclerView.Adapter<ContactArrayAdapter.ViewHolder> {
    private RecyclerViewClickListener mListener;

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    private ArrayList<Contact> ContactArrayList;
    // Constructor of the class
    public ContactArrayAdapter(int layoutId, ArrayList<Contact> itemList, RecyclerViewClickListener listener) {
        listItemLayout = layoutId;
        this.ContactArrayList = itemList;
        this.mListener = listener;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return ContactArrayList == null ? 0 : ContactArrayList.size();
    }

    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view,this.mListener);
        return myViewHolder;
    }
    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView textView_Name = holder.textView_Name;
        TextView textView_Email = holder.textView_Contact;

        textView_Name.setText(ContactArrayList.get(listPosition).getName());
        textView_Email.setText(ContactArrayList.get(listPosition).getContact());

        Log.d("tracker","tracker10");
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView_Name,textView_Contact;
        public CheckBox radioButton_select_contact;

        private RecyclerViewClickListener mListener;
        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            textView_Name = (TextView) itemView.findViewById(R.id.Contact_Name);
            textView_Contact = (TextView) itemView.findViewById(R.id.Contact_contact);
            radioButton_select_contact = (CheckBox) itemView.findViewById(R.id.Contact_selection);
            radioButton_select_contact.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id= view.getId();
            switch (id){
                case R.id.Contact_selection:
                    Log.d("onclick000000", "onClick " + getLayoutPosition() + " " + textView_Name.getText());
                    mListener.onClick(view, getAdapterPosition());
                    break;
            }
        }

    }}

