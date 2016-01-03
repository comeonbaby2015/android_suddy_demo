package demo.ui_listview_mutichoice;

import java.util.ArrayList;
import java.util.List;

import com.example.c7_1_listview_mutichoice.R;

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

/**
 * <pre>
 * ListView����ѡģʽ��
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.lv_multi);
		// ����ChoiceModeΪ��ѡ
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		// ������������simple_list_item_multiple_choiceΪ����layout�������Դ�Ǵ���List<String>��
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, getData());
		// ����������
		listView.setAdapter(arrayAdapter);
		// ���AdapterView. OnItemClickListener�¼�
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
			list.add("��" + (i + 1));
		}
		return list;
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
