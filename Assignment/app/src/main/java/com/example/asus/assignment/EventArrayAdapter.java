package com.example.asus.assignment;
/**
 * Created by Ah Tan on 1/11/17.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class EventArrayAdapter extends RecyclerView.Adapter<EventArrayAdapter.ViewHolder> {


    private RecyclerViewClickListener mListener;

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    private ArrayList <AppEvent> EventArrayList;
    // Constructor of the class
    public EventArrayAdapter(int layoutId, ArrayList<AppEvent> itemList, RecyclerViewClickListener listener) {
        //listItemLayout = layoutId;
        this.EventArrayList = itemList;
        this.mListener = listener;
    }



    // get the size of the list
    @Override
    public int getItemCount() {
        return EventArrayList == null ? 0 : EventArrayList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view,this.mListener);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        //This is where we place code so that the Android system can run them
        //to display the customer data inside each recyclerview rows.
        TextView textView_title = holder.textView_title;
        TextView textView_time = holder.textView_time;
        textView_title.setText(EventArrayList.get(listPosition).getTitle());
        textView_time.setText(EventArrayList.get(listPosition).getTime());
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView_title;
        public TextView textView_time;
        public Button button_edit;


        //public Button button_delete;


        private RecyclerViewClickListener mListener;
        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;

            //Get the three variables, textView_name, textView_mobileContact and
            //button_edit to reference the elements which are defined the the XML in
            //the customer_liest_item.xml.
            textView_title = (TextView) itemView.findViewById(R.id.TextView_title);
            textView_time = (TextView) itemView.findViewById(R.id.TextView_time);

            //for edit in list
            button_edit = (Button) itemView.findViewById(R.id.Button_Edit);

            //for delete in list
            //button_delete=(Button) itemView.findViewById(R.id.Button_Delete);

            //Defined a click listener only for the button which has the edit icon
            button_edit.setOnClickListener(this);

            //Defined a click listener only for the button which has the delete icon
            //button_delete.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + textView_title.getText());
            mListener.onClick(view, getAdapterPosition());
        }



    }




}
