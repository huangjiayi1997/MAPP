package com.example.asus.assignment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by ASUS on 22/1/2018.
 */

public class DialogToConfirmSelection extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //arraylist for display:
        final String [] mDisplaySelectedList;
        //arraylist created after full selection
        final ArrayList<Integer> mUserItems = new ArrayList<>();
        //check if item is checked
        boolean[] checkedItems;

        //initialize mDisplaoySelectedList
        mDisplaySelectedList = new String[SelectContact.selectedContactArrayList.size()];


        //iteratation to add required items from selectedContactArrayList to display
        for (int i = 0; i < SelectContact.selectedContactArrayList.size(); i++) {
            mDisplaySelectedList[i]=(SelectContact.selectedContactArrayList.get(i).getContact());
        }

        //initalize checked item
        //act as a checkboxes, get the number of check boxes we need by mDisplaySelectedList.size()
        checkedItems = new boolean[mDisplaySelectedList.length];

        //
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        //set title of dialog box
        mBuilder.setTitle("Event Will be shared to:");
        //set multiple choice item
        mBuilder.setMultiChoiceItems(mDisplaySelectedList, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                //check if the item is checked
                if(isChecked){
                    //check is the item is already included in the arraylist
                    // if included, remove it
                    //if not, remove it
                    if (mUserItems.contains(position)){
                        mUserItems.add(position);
                    }
                }
                else if(mUserItems.contains(position)){
                    mUserItems.remove(position);
                }
            }


        });
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String item=" ";

                //loop through mUserITEMS
                for(int i=0;i<mUserItems.size();i++){
                    //define an empty string
                    //list item
                    item=item + mDisplaySelectedList[mUserItems.get(i)];
                    if(i!=mUserItems.size() -1){
                        item= item + ",";
                    }
                }
                //get the values selected
            }

        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();;
            }
        });
        return mBuilder.create();
    }}

