package demo.ui_spinner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * <pre>
 * Spinner：下拉框
 * 示例1：mHobbySn，Spinner的数据来源是string array
 * 示例2: mPlanetsSn，Spinner的数据来源是source array，适配器为ArrayAdapter
 * 示例3：mUserSn，Spinner的数据来源是source array，适配器为自定义UserSpinnerAdapter
 * </pre>
 */
public class SpinnerActivity extends Activity {
	Spinner mHobbySn;
	Spinner mPlanetsSn;
	Spinner mUserSn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_spinner);
		findViews();
		setValues();
		setListeners();
	}

	/**
	 * <pre>
	 * 查找控件
	 * </pre>
	 */
	private void findViews() {
		mHobbySn = (Spinner) findViewById(R.id.sn_hoppy);
		mPlanetsSn = (Spinner) findViewById(R.id.sn_planets);
		mUserSn = (Spinner) findViewById(R.id.sn_user);
	}

	/**
	 * <pre>
	 * 初始化控件
	 * </pre>
	 */
	private void setValues() {

		doPlanetsSpinnerShow();
		doUserSpinnerShow();
	}

	/**
	 * <pre>
	 * 设置控件事件监听
	 * </pre>
	 */
	private void setListeners() {
		mPlanetsSn.setOnItemSelectedListener(itemSelectedListener);
		mUserSn.setOnItemSelectedListener(itemSelectedListener2);
	}

	/**
	 * 创建planets Spinnner（下拉列表）的数据源
	 * 
	 * <pre>
	 * <?xml version="1.0" encoding="utf-8"?>
	 * <resources>
	 *     <string-array name="planets_array">
	 *         <item>Mercury</item>
	 *         <item>Venus</item>
	 *         <item>Earth</item>
	 *         <item>Mars</item>
	 *         <item>Jupiter</item>
	 *         <item>Saturn</item>
	 *         <item>Uranus</item>
	 *         <item>Neptune</item>
	 *     </string-array>
	 * </resources>
	 * </pre>
	 */
	private List<String> getPlanetsSpinnerDataSource() {
		List<String> dataSource = new ArrayList<String>();
		dataSource.add("Mercury");
		dataSource.add("Venus");
		dataSource.add("Earth");
		dataSource.add("Jupiter");
		dataSource.add("Saturn");
		dataSource.add("Uranus");
		dataSource.add("Neptune");
		return dataSource;
	}

	// 显示Planets下拉框
	private void doPlanetsSpinnerShow() {
		// 建立数据源
		List<String> ds = getPlanetsSpinnerDataSource();
		// 建立适配器
		// 建立数据源DdataSource和适配器Adapter的连接
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpinnerActivity.this, android.R.layout.simple_list_item_1, ds);
		// 绑定adapter到界面组件
		mPlanetsSn.setAdapter(adapter);
	}

	// 设置Planets的OnItemSelectedListener
	AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(SpinnerActivity.this, "点击了 position=" + position + "---" + getPlanetsSpinnerDataSource().get(position), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	};

	// 设置User Spinner的数据源
	private List<User> getUserSpinnerDataSource() {
		// TODO 实际中，数据源可能来源于数据库，文件或网络数据
		List<User> dataSource = new ArrayList<User>();
		dataSource.add(new User("hello", "山东"));
		dataSource.add(new User("新月", "山东"));
		dataSource.add(new User("向往", "安徽"));
		return dataSource;
	}

	// 显示User Spinner数据
	private void doUserSpinnerShow() {
		// 建立数据源
		List<User> list = getUserSpinnerDataSource();
		// 建立适配器
		// 建立数据源和适配器的连接
		UserSpinnerAdapter adapter = new UserSpinnerAdapter(SpinnerActivity.this, list);
		// 绑定适配器到界面组件
		mUserSn.setAdapter(adapter);
	}

	// 设置User Spinner的OnItemSelectedListener事件
	AdapterView.OnItemSelectedListener itemSelectedListener2 = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(SpinnerActivity.this,
					"点击了 position = " + position + "---" + getUserSpinnerDataSource().get(position).getAddr() + "---" + getUserSpinnerDataSource().get(position).getAddr(), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	};

}
