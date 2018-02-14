package com.readcontacts.main;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tvContacts;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContacts = (TextView) findViewById(R.id.tvContacts);
        context = MainActivity.this;
        readContact(context);
    }

    private void readContact(Context context) {
        StringBuilder builder = new StringBuilder();

        int i = 0;
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            builder.append(i + "> Name: " + name + "\n");
            builder.append("  Phone No: " + phoneNumber + "\n\n");
            i++;
            Log.e("tag", i + "> Name: " + name);
            Log.e("tag", "  phoneNumber: " + phoneNumber + "\n\n");
        }
        phones.close();

        tvContacts.setText(builder.toString());
    }
}
