package com.htglimited.RoamingLogger;

import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RoamingLogger extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView tv = new TextView(this);
        Context ctx = this.getApplicationContext();
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        Button btnSend;
        EditText emailBody;
        
        btnSend = (Button) findViewById(R.id.btnEmailSend);
        emailBody = (EditText) findViewById(R.id.txtViewEmailBody);
        
        if (btnSend != null) {
        	btnSend.setOnClickListener(new OnClickListener() {
        		public void onClick(View v) {
    				if (v.getId() == R.id.btnEmailSend) {
    					sendEmail();
    				}
    			}
        	});
        }
        
        if (tm == null)
        {
                tv.setText("Could not get instance of TelephonyManager");
        }
        else
        {
                
                String myText =   "Network Operator Name(ONS): '" + tm.getNetworkOperatorName() + "'\n" 
                				+ "SIM Operator Name(SPN): '" + tm.getSimOperatorName() + "'\n"
                				+ "Roaming flag: '" + tm.isNetworkRoaming() + "'\n"
                				+ "Network Operator(OP): '"+ tm.getNetworkOperator() + "'\n"
                				+ "SIM Operator(SP): '" + tm.getSimOperator() + "'\n"
                				+ "Network Country ISO (NETcISO): '" + tm.getNetworkCountryIso() + "'\n"
                				+ "SIM Country ISO (SIMcISO): '" + tm.getSimCountryIso() + "'\n";
                
                //tv.setText(myText);
                emailBody.setText(myText);
                
        }
        
        
        this.setContentView(tv);
    }
    
	/**
	 * Method to send email
	 */
	protected void sendEmail() {
		TextView tv = new TextView(this);
		// Setup the recipient in a String array
		String[] mailto = { "wyatt.neal@gmail.com" };
		//String[] ccto = { "somecc@somedomain.com" };
		// Create a new Intent to send messages
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		// Add attributes to the intent
		sendIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
		//sendIntent.putExtra(Intent.EXTRA_CC, ccto);
		sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Roaming Logger Result"
				.toString());
		sendIntent.putExtra(Intent.EXTRA_TEXT, tv.getText().toString());
		// sendIntent.setType("message/rfc822");
		sendIntent.setType("text/plain");
		startActivity(Intent.createChooser(sendIntent, "MySendMail"));
	}
}