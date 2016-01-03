package demo.ui_listview_adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import demo.ui_listview_adapter.base.ActivityBase;

/**
 * <pre>
 * 使用继承BaseAdapter实现ListView，方式为创建TextView。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends ActivityBase {
	ListView mListView;
	MyAdapter mAdapter;

	@Override
	protected void OnCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.lv);

		// 建立数据源
		List<String> dataSource = getData();
		// 建立适配器
		// 建立数据源和适配器的连接
		mAdapter = new MyAdapter(MainActivity.this, dataSource);
		// 关联适配器到界面组件
		mListView.setAdapter(mAdapter);
	}

	// 得到模拟的数据源
	private List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add(i + 1 + "");
		}
		return list;
	}

}
