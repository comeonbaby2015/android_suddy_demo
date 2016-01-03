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
 * AutoCompleteTextView方式1：数据源为source array
 * AutoCompleteTextView方式2：数据源为String array数组
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
		// 建立数据源。数据源为source array
		List<String> dataSource = getData();
		// 建立适配器
		// 建立数据源和适配器的连接
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, dataSource);
		// 绑定适配器到界面组件
		mInput1Act.setAdapter(arrayAdapter);
	}

	private void showView2() {
		AutoCompleteTextView mInput2Act = (AutoCompleteTextView) findViewById(R.id.act_input2);

		// 建立数据源，数据源为为string array
		// Get the string array
		String[] countries = getResources().getStringArray(R.array.countries_array);
		// 建立适配器
		// 建立数据源和适配器的连接
		// Create the adapter and set it to the AutoCompleteTextView
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
		// 关联适配器到界面组件
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
