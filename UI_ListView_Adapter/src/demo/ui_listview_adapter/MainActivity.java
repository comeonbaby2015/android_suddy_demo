package demo.ui_listview_adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import demo.ui_listview_adapter.base.ActivityBase;

/**
 * <pre>
 * ʹ�ü̳�BaseAdapterʵ��ListView����ʽΪ����TextView��
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

		// ��������Դ
		List<String> dataSource = getData();
		// ����������
		// ��������Դ��������������
		mAdapter = new MyAdapter(MainActivity.this, dataSource);
		// �������������������
		mListView.setAdapter(mAdapter);
	}

	// �õ�ģ�������Դ
	private List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add(i + 1 + "");
		}
		return list;
	}

}
