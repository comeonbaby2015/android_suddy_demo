package com.example.c7_6_listview_scrollviewconflict;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * <pre>
 * ListView之ScrollView与ListView事件冲突功能。
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends Activity {
	private final static String TAG = "CY";
	private ListView mContentLv;
	private ScrollView mSv;
	// 关联ListView的适配器
	private MyAdapter mMyAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContentLv = (ListView) findViewById(R.id.lv_content);
		mSv = (ScrollView) findViewById(R.id.sv);

		mMyAdapter = new MyAdapter(getData());
		mContentLv.setAdapter(mMyAdapter);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (MotionEvent.ACTION_MOVE == ev.getAction()) {
			mContentLv.dispatchTouchEvent(ev);
			mSv.dispatchTouchEvent(ev);
		}
		return super.dispatchTouchEvent(ev);
	}

	class MyAdapter extends BaseAdapter {
		List<String> list;

		public MyAdapter(List<String> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView textView = null;
			if (null == convertView) {
				textView = new TextView(MainActivity.this);
				System.out.println("In getView func，convertView 为空，position=" + position + ",convertView=" + convertView + ",parent=" + parent);
			} else {
				textView = (TextView) convertView;
				System.out.println("In getView func，convertView 非空，position=" + position + ",convertView=" + convertView + ",parent=" + parent);
			}
			textView.setHeight(155);
			textView.setText(getItem(position).toString());
			return textView;
		}

	}

	private List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			list.add("行" + i);
		}
		return list;
	}

}
