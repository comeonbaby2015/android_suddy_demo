package demo.ui_listview_arrayadapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * <pre>
 * ��������ArrayAdapter��
 * </pre>
 * 
 */
public class MainActivity extends ActionBarActivity {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.lv);

		// ��������Դ
		List<String> dataSource = getPlanetsSpinnerDataSource();
		// ����������
		// ��������Դ��������������
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, dataSource);
		// �������������������
		mListView.setAdapter(arrayAdapter);
	}

	// ����ģ�������Դ
	private List<String> getPlanetsSpinnerDataSource() {
		List<String> dataSource = new ArrayList<String>();
		dataSource.add("Mercury");
		dataSource.add("Venus");
		dataSource.add("Earth");
		dataSource.add("Jupiter");
		dataSource.add("Saturn");
		dataSource.add("Uranus");
		dataSource.add("Neptune");
		dataSource.add("Android");
		dataSource.add("C++");
		dataSource.add("C#");
		dataSource.add("Java");
		dataSource.add("Java2");
		return dataSource;
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
