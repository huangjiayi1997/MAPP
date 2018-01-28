package com.example.asus.assignment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by ASUS on 10/1/2018.
 */

public class SelectContact extends AppCompatActivity {
    private static final int REQUEST_READ_CONTACTS = 444;
    RecyclerView m_recyclerView;
    public static ContactArrayAdapter m_contactArrayAdapter;
    //private ListView mListView;
    public static ArrayList<Contact> m_contactArrayList;
    private ProgressDialog pDialog;
    private Handler updateBarHandler;
    Button shareButton ;
    public static String [] mDisplaySelectedList;
    //for checkbox
    public static ArrayList<Contact> selectedContactArrayList=new ArrayList<Contact>();;
    //public Checkbox checkboxselection;
    public static ArrayList<String> contactToSend=new ArrayList<>();

    Cursor cursor;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_main_activity);

        shareButton=(Button)findViewById(R.id.AddContactButton);

        pDialog = new ProgressDialog(SelectContact.this);
        pDialog.setMessage("Reading contacts...");
        pDialog.setCancelable(false);
        pDialog.show();

        m_contactArrayList = new ArrayList<Contact>();
        updateBarHandler = new Handler();

        getContacts();

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Log.d("error here","select contact page 2");
                Toast.makeText(view.getContext(), "You have selected position jjj" + position, Toast.LENGTH_SHORT).show();
                Contact selectedContactToUpdate = m_contactArrayList.get(position);
                CheckBox checkboxselection=(CheckBox)view;
                if (checkboxselection.isChecked()){
                    String id = selectedContactToUpdate.getID();
                    String Name=selectedContactToUpdate.getName();
                    String Email=selectedContactToUpdate.getEmail();
                    String Phone=selectedContactToUpdate.getContact();
                    selectedContactArrayList.add(new Contact(id,Name,Email,Phone));
                }
                else{
                    String id = selectedContactToUpdate.getID();
                    DeleteUncheckedCheckboxArrayList(id);
                    //if it is unchecked
                }
                //checking the arraylist
                printSelectedArrayList();
            }
        };
        m_contactArrayAdapter = new ContactArrayAdapter(R.layout.contact_list, m_contactArrayList,listener);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_ContactList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.setAdapter(m_contactArrayAdapter);
        // loadEvents();
    }
    public void printSelectedArrayList(){
        //checking the arraylist

        String arrayid;
        String arrayname,arraycontact;
        for (int i=0;i<selectedContactArrayList.size();i++){
            arrayid=selectedContactArrayList.get(i).getID();
            arrayname=selectedContactArrayList.get(i).getName();
            arraycontact=selectedContactArrayList.get(i).getContact();
            Log.d("contact selection:",arrayid+arrayname+arraycontact);

        }
    }

    //delete unchecked checkboxes from arraylist
    public void DeleteUncheckedCheckboxArrayList(String arrayid){
        //checking the arraylist
        Iterator<Contact> iterator= selectedContactArrayList.iterator();
        //int arrayid;
        String arrayname,arrayemail;
        for (int i=0;i<selectedContactArrayList.size();i++){
            if (arrayid==selectedContactArrayList.get(i).getID()){
                //String id=Integer.toString(arrayid);
                Log.d("the id to unselect is",arrayid);
                selectedContactArrayList.remove(i);
            }

        }

    }

    public void onClick (View view) {
        // if (view.getId()==R.id.AddContactButton) {


        //arraylist created after full selection
        final ArrayList<Integer> mUserItems = new ArrayList<>();
        //check if item is checked
        final boolean[] checkedItems;

        //initialize mDisplaoySelectedList
        mDisplaySelectedList = new String[selectedContactArrayList.size()];
        for (int i = 0; i < selectedContactArrayList.size(); i++) {
            mDisplaySelectedList[i]=(selectedContactArrayList.get(i).getContact());
        }
        //initalize checked item
        //act as a checkboxes, get the number of check boxes we need by mDisplaySelectedList.size()
        checkedItems = new boolean[mDisplaySelectedList.length];

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SelectContact.this);
        //set title of dialog box

        mBuilder.setTitle("Event Will be shared to:");
        mBuilder.setMultiChoiceItems(mDisplaySelectedList, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                //check if the item is checked
                if(isChecked){
                    //check is the item is already included in the arraylist
                    // if included, remove it
                    //if not, remove it
                    if (!mUserItems.contains(position)){
                        mUserItems.add(position);
                    }
                }
                else if(mUserItems.contains(position)){
                    mUserItems.remove(position);
                }
            }


        });
        Log.d("checked","button invoke");
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            //not required: checking purpose
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
                    Log.d("item in checkedlist",mDisplaySelectedList[mUserItems.get(i)]);
                    contactToSend.add(mDisplaySelectedList[mUserItems.get(i)]);

                }
                //get the values selected
                Intent intent = new Intent (SelectContact.this,SendOthersSMS.class);

                startActivity(intent);
            }

        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //need to clear misplaylist if user choose cancel
                mDisplaySelectedList = new String[selectedContactArrayList.size()];
                dialogInterface.dismiss();
            }
        });
        mBuilder.show();}//}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 4) {
            //  reflect changes when there is contact newly added
            m_contactArrayAdapter.notifyDataSetChanged();}
    }


    //getting the contact from the book
    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts();
            }
        }
    }

    //retrieve contact
    public void getContacts() {

        if (!mayRequestContacts()) {
            return;
        }

        //m_contactArrayList = new ArrayList<Contact>();

        String phoneNumber = null;
        String email = null;
//reading to app
        //nothing to change
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        StringBuffer output;

        ContentResolver contentResolver = getContentResolver();

        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        // Iterate every contact in the phone
        if (cursor.getCount() > 0) {

            counter = 0;
            while (cursor.moveToNext()) {
                output = new StringBuffer();

                // Update the progress message
                updateBarHandler.post(new Runnable() {
                    public void run() {
                        pDialog.setMessage("Reading contacts : " + counter++ + "/" + cursor.getCount());
                    }
                });
                //get item on the phone
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    //output.append("\n First Name:" + name);

                    //This is to read multiple phone numbers associated with the same contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        // output.append("\n Phone number:" + phoneNumber);

                    }

                    phoneCursor.close();

                    // Read every email id associated with the contact
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (emailCursor.moveToNext()) {

                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));

                        // output.append("\n Email:" + email);

                    }

                    emailCursor.close();

                    String columns[] = {
                            ContactsContract.CommonDataKinds.Event.START_DATE,
                            ContactsContract.CommonDataKinds.Event.TYPE,
                            ContactsContract.CommonDataKinds.Event.MIMETYPE,
                    };

                    String where = ContactsContract.CommonDataKinds.Event.TYPE + "=" + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY +
                            " and " + ContactsContract.CommonDataKinds.Event.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE + "' and " + ContactsContract.Data.CONTACT_ID + " = " + contact_id;

                    String[] selectionArgs = null;
                    String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;

                    Cursor birthdayCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, columns, where, selectionArgs, sortOrder);
                    Log.d("BDAY", birthdayCur.getCount()+"");
                    if (birthdayCur.getCount() > 0) {
                        while (birthdayCur.moveToNext()) {
                            String birthday = birthdayCur.getString(birthdayCur.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
                            //output.append("Birthday :" + birthday);
                            Log.d("BDAY", birthday);
                        }
                    }
                    birthdayCur.close();
                }

                // Add the contact to the ArrayList
                Contact contact = new Contact(contact_id,name,email,phoneNumber);
                m_contactArrayList.add(contact);
            }

            // ListView has to be updated using a ui thread

            // Dismiss the progressbar after 500 millisecondds
            updateBarHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    pDialog.cancel();
                }
            }, 500);
        }

    }

}
