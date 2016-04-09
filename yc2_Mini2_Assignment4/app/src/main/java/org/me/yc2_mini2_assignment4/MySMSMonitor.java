package org.me.yc2_mini2_assignment4;

import android.app.Activity;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by caoyi on 16/3/28.
 */
public class MySMSMonitor extends ListActivity {
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent!=null &&
                    intent.getAction()!=null &&
                    ACTION.compareToIgnoreCase(intent.getAction())==0)
            {
                Object[]pduArray= (Object[]) intent.getExtras().get("pdus");
                SmsMessage[] messages = new SmsMessage[pduArray.length];

                for (int i = 0; i<pduArray.length; i++) {
                    messages[i] = SmsMessage.createFromPdu ((byte[])pduArray [i]);
                }
                Log.d("MySMSMonitor", "SMS Message Received.");
            }
        }


    };
    //display incoming message on screen
    private ListAdapter adapter;
    private static final Uri SMS_INBOX=Uri.parse("content://sms/inbox");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor c=getContentResolver().query(SMS_INBOX,null,null,null,null);
        startManagingCursor(c);
        String[] columns=new String[] { "body" };
        int[] names=new int[]{R.id.msgEditText};
        adapter=new SimpleCursorAdapter(this, R.layout.activity_geo_and_msg,c, columns,names);
        setListAdapter(adapter);
    }
}


