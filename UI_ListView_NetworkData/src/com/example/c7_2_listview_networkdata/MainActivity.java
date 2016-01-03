package com.example.c7_2_listview_networkdata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	ListView listView;
	ProgressDialog progressDialog;
	ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.lv);

		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setMessage("正在下载，请稍后....");

		new MyAsyncTask().execute("url");

		// 添加AdapterView. OnItemClickListener事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
			}
		});

	}

	private List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			list.add("行" + (i + 1));
		}
		return list;
	}

	class MyAsyncTask extends AsyncTask<String, Integer, List<String>> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog.show();
		}

		@Override
		protected List<String> doInBackground(String... params) {
			// TODO Auto-generated method stub
			// TODO 通过网路下载数据。模拟网络下载数据
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<String> list = getData();
			return list;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(List<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			// 创建适配器。数据源是从网络获取
			arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, result);
			// 加载适配器
			listView.setAdapter(arrayAdapter);
			// 从网路获取数据时，分批次获取数据、填充数据。一旦适配器有数据，直接通知ListView更新数据。
			arrayAdapter.notifyDataSetChanged();

			progressDialog.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
