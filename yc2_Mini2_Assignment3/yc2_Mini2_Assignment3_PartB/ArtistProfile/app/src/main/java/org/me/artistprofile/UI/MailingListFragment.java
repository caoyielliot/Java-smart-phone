package org.me.artistprofile.UI;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.me.artistprofile.R;

/**
 * Created by caoyi on 16/3/25.
 */
public class MailingListFragment extends Fragment implements View.OnClickListener{
    View view;
    private static  final String LOG_TAG = MailingListFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mailinglist,container,false);
        Button send_button=(Button)view.findViewById(R.id.buttonSend);
        send_button.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
         //send email to receipt
        switch (v.getId()){
            case R.id.buttonSend:
                sendEmail();
                break;
        }


    }
    private void sendEmail(){
        EditText subject=(EditText)view.findViewById(R.id.editSubject);
        EditText content=(EditText)view.findViewById(R.id.editMailContent);
        EditText recipient=(EditText)view.findViewById(R.id.editRecipient);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipient.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, content.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            this.getActivity().finish();
            Log.i("Finished sending email", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this.getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
            Log.e(LOG_TAG,"No email client here" + ex.toString());
        }
    }
}
