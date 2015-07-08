package com.cduestc.scoreinquiry;

import java.io.IOException;

import org.json.JSONException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Handler handler;
	EditText userName;
	EditText passWord;
	String admin;
	String pwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		userName = (EditText)findViewById(R.id.et_main_username);
		passWord = (EditText)findViewById(R.id.et_main_password);
		SharedPreferences share = getSharedPreferences("userinfo",Activity.MODE_WORLD_READABLE);
		String i =share.getString("username", "");
		String str = share.getString("password", "");
		userName.setText(i);
		passWord.setText(str);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void Login(View v){
		
			admin = userName.getText().toString();
			pwd = passWord.getText().toString();
			SharedPreferences sharedPreferences = getSharedPreferences("userinfo",Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			editor.putString("username", admin);
			editor.putString("password",pwd);
			editor.commit();		
			Intent i = new Intent(this,TwoActivity.class);
			i.putExtra("username", admin);
			i.putExtra("password", pwd);
			startActivity(i);			
	}
}
