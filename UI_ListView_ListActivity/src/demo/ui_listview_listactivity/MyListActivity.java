package demo.ui_listview_listactivity;

import android.app.ListActivity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyListActivity extends ListActivity {

	String[] cities = { "成都", "郑州", "北京", "上海", "山东", "济南", "广州", "郑州", "德州", "潍坊", "青岛", "泰安", "邹城", "威海" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView1 = (ListView) findViewById(android.R.id.list);

		listView1.setOnItemSelectedListener(listener);
		listView1.setOnItemClickListener(clickListener);

		// 建立数据源
		// 建立适配器
		// 建立数据源和适配器的连接
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
		// 关联适配器到界面组件
		listView1.setAdapter(adapter);

		NotificationManager nm = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

	}

	final AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(MyListActivity.this, "选中了：" + parent.getSelectedItemId(), 2000).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	};

	final AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(MyListActivity.this, "选中了::：" + parent, 2000).show();
		}
	};

}
