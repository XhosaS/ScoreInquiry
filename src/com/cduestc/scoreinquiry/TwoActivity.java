package com.cduestc.scoreinquiry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TwoActivity extends Activity{
	String ID;
	String MM;
	static Handler handler;
	int value = 0;
	ListView lv = null;
	List<Grage> resultList = new ArrayList<Grage>(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);		
		Intent i= getIntent();
		ID=i.getStringExtra("username");
		MM=i.getStringExtra("password");
		handler = new Handler(){
			 @Override
			public void handleMessage(Message msg){
				 if(msg.what == 1){
					Iterator it = httpUtil.scores.keySet().iterator();
					while(it.hasNext()){
						String key =(String)it.next();
						Integer val = httpUtil.scores.get(key);
						if(val == -1){
							val = null;
						}
						Grage info = new Grage();
						info.setSubject(key);
						info.setScore(val);
						resultList.add(info);
					}
					showList();
				 }
				 super.handleMessage(msg);
			 }
		};
		NetWorkTask t = new NetWorkTask();
		t.start();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class NetWorkTask extends Thread{
		public void run(){
			try{			
				httpUtil.sendPost(TwoActivity.handler,ID,MM);

			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	public void showList(){
		List<HashMap<String, Object>> sList = 
				new ArrayList<HashMap<String, Object>>();
		
		for(int i = 0; i < resultList.size(); i++){
			HashMap<String, Object> song = new HashMap<String, Object>();
			Grage info = resultList.get(i);
			song.put("科目",info.getSubject());		
			if(info.getScore()==null){
				song.put("成绩",info.getScore());
			}
			else if(info.getScore()<60){
				song.put("不及格成绩", info.getScore());
			}else{
				song.put("成绩",info.getScore());
			}
			sList.add(song);			
			
		}
		SimpleAdapter adapter = new SimpleAdapter(
				this, 
				sList, 
				R.layout.item, 
				new String[]{"科目", "成绩","不及格成绩"}, 
				new int[]{R.id.item_subject, R.id.item_score,R.id.item_score2});
		//获取ListViw 控件对象
		lv = (ListView)findViewById(R.id.item);
		//绑定适配器和listView
		lv.setAdapter(adapter);
		
	}
}
