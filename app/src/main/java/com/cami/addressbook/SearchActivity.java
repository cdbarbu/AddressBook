package com.cami.addressbook;

import android.content.Intent;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import javax.xml.transform.Result;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void search(View view) {
        Intent intent = new Intent(this, ResultActivity.class);

        EditText editText = (EditText) findViewById(R.id.searchText);
        String searchString = editText.getText().toString();

//        Cursor cursor = getContentResolver().query(
//                android.provider.ContactsContract.Contacts.CONTENT_URI,
//                new String[] { ContactsContract.Contacts.PHOTO_ID,
//                        ContactsContract.Contacts.DISPLAY_NAME,
//                        ContactsContract.Contacts._ID },
//                ContactsContract.Contacts.HAS_PHONE_NUMBER, null,
//                ContactsContract.Contacts.DISPLAY_NAME);

        String toateNumele = "";

        try {
            Cursor photoCursor = getContentResolver().query(
                    Contacts.People.CONTENT_URI,
                    new String[]{//ContactsContract.Contacts.PHOTO_ID,
                            Contacts.People.NAME},
                    Contacts.People.NAME + " = ?",
                    new String[]{searchString}, null);

            photoCursor.moveToFirst();

            while (photoCursor.moveToNext()) {
                //Log.d("Photo Thumbnail", "" + photoCursor.getString(1));
                toateNumele = toateNumele + "\n" + photoCursor.getString(2);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        intent.putExtra("GIGI", toateNumele);

        startActivity(intent);

    }
}
