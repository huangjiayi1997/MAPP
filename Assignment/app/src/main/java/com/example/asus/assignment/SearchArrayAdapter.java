package com.example.asus.assignment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 23/1/2018.
 */

public class SearchArrayAdapter extends RecyclerView.Adapter<SearchArrayAdapter.ViewHolder> {


    private RecyclerViewClickListener mListener;

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    private ArrayList<AppEvent> SearchArrayList;
    // Constructor of the class
    public SearchArrayAdapter(int layoutId, ArrayList<AppEvent> itemList, RecyclerViewClickListener listener) {
        listItemLayout = layoutId;
        this.SearchArrayList = itemList;
        this.mListener = listener;
    }
    // get the size of the list
    @Override
    public int getItemCount() {
        return SearchArrayList == null ? 0 : SearchArrayList.size();
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
        //This is where we place code so that the Android system can run them
        //to display the customer data inside each recyclerview rows.
        TextView textView_title = holder.textView_title;

        textView_title.setText(SearchArrayList.get(listPosition).getTitle());
        Log.d("tracker","tracker10");




    }



    // Static inner class to initialize the views of rows
    // i removed static infront of class viewholder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView_title;


        private RecyclerViewClickListener mListener;
        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            //Get the three variables, textView_name, textView_mobileContact and
            //button_edit to reference the elements which are defined the the XML in
            //the customer_liest_item.xml.
            textView_title = (TextView) itemView.findViewById(R.id.SearchTextView_title);
        }

        @Override
        public void onClick(View view) {

            Log.d("Search Item Selected","1"+getLayoutPosition());
            mListener.onClick(view, getLayoutPosition());



        }




    }
    public void setFilter(ArrayList<AppEvent> newList){
        SearchArrayList =new ArrayList<>();
        SearchArrayList.addAll(newList);
        notifyDataSetChanged();
    }



}
