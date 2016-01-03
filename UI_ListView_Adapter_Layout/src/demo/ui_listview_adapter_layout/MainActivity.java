package demo.ui_listview_adapter_layout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * <pre>
 * 使用继承BaseAdapter实现ListView，方式为inflate View。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	private ListView mListView;
	private MyAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.ll);
		// 创建Adapter
		mAdapter = new MyAdapter(MainActivity.this, getData());
		// 关联Adapter
		mListView.setAdapter(mAdapter);
	}

	private List<String> getData() {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			data.add(i + "");
		}
		return data;
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
