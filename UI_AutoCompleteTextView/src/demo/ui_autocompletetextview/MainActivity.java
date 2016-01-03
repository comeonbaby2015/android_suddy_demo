package demo.ui_autocompletetextview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import demo.c3_4_autocompletetextview.R;

/**
 * <pre>
 * AutoCompleteTextView��ʽ1������ԴΪsource array
 * AutoCompleteTextView��ʽ2������ԴΪString array����
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		showView1();
		showView2();
	}

	private void showView1() {
		AutoCompleteTextView mInput1Act = (AutoCompleteTextView) findViewById(R.id.act_input1);
		// ��������Դ������ԴΪsource array
		List<String> dataSource = getData();
		// ����������
		// ��������Դ��������������
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, dataSource);
		// �����������������
		mInput1Act.setAdapter(arrayAdapter);
	}

	private void showView2() {
		AutoCompleteTextView mInput2Act = (AutoCompleteTextView) findViewById(R.id.act_input2);

		// ��������Դ������ԴΪΪstring array
		// Get the string array
		String[] countries = getResources().getStringArray(R.array.countries_array);
		// ����������
		// ��������Դ��������������
		// Create the adapter and set it to the AutoCompleteTextView
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
		// �������������������
		mInput2Act.setAdapter(adapter);
	}

	private List<String> getData() {
		List<String> list = new ArrayList<String>();
		list.add("Afghanistan");
		list.add("Albania");
		list.add("Algeria");
		list.add("American Samoa");
		list.add("Andorra");
		list.add("Angola");
		list.add("Anguilla");
		list.add("Antarctica");
		return list;
	}
}
