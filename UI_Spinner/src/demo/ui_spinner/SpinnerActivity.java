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
 * Spinner��������
 * ʾ��1��mHobbySn��Spinner��������Դ��string array
 * ʾ��2: mPlanetsSn��Spinner��������Դ��source array��������ΪArrayAdapter
 * ʾ��3��mUserSn��Spinner��������Դ��source array��������Ϊ�Զ���UserSpinnerAdapter
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
	 * ���ҿؼ�
	 * </pre>
	 */
	private void findViews() {
		mHobbySn = (Spinner) findViewById(R.id.sn_hoppy);
		mPlanetsSn = (Spinner) findViewById(R.id.sn_planets);
		mUserSn = (Spinner) findViewById(R.id.sn_user);
	}

	/**
	 * <pre>
	 * ��ʼ���ؼ�
	 * </pre>
	 */
	private void setValues() {

		doPlanetsSpinnerShow();
		doUserSpinnerShow();
	}

	/**
	 * <pre>
	 * ���ÿؼ��¼�����
	 * </pre>
	 */
	private void setListeners() {
		mPlanetsSn.setOnItemSelectedListener(itemSelectedListener);
		mUserSn.setOnItemSelectedListener(itemSelectedListener2);
	}

	/**
	 * ����planets Spinnner�������б�������Դ
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

	// ��ʾPlanets������
	private void doPlanetsSpinnerShow() {
		// ��������Դ
		List<String> ds = getPlanetsSpinnerDataSource();
		// ����������
		// ��������ԴDdataSource��������Adapter������
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpinnerActivity.this, android.R.layout.simple_list_item_1, ds);
		// ��adapter���������
		mPlanetsSn.setAdapter(adapter);
	}

	// ����Planets��OnItemSelectedListener
	AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(SpinnerActivity.this, "����� position=" + position + "---" + getPlanetsSpinnerDataSource().get(position), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	};

	// ����User Spinner������Դ
	private List<User> getUserSpinnerDataSource() {
		// TODO ʵ���У�����Դ������Դ�����ݿ⣬�ļ�����������
		List<User> dataSource = new ArrayList<User>();
		dataSource.add(new User("hello", "ɽ��"));
		dataSource.add(new User("����", "ɽ��"));
		dataSource.add(new User("����", "����"));
		return dataSource;
	}

	// ��ʾUser Spinner����
	private void doUserSpinnerShow() {
		// ��������Դ
		List<User> list = getUserSpinnerDataSource();
		// ����������
		// ��������Դ��������������
		UserSpinnerAdapter adapter = new UserSpinnerAdapter(SpinnerActivity.this, list);
		// �����������������
		mUserSn.setAdapter(adapter);
	}

	// ����User Spinner��OnItemSelectedListener�¼�
	AdapterView.OnItemSelectedListener itemSelectedListener2 = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(SpinnerActivity.this,
					"����� position = " + position + "---" + getUserSpinnerDataSource().get(position).getAddr() + "---" + getUserSpinnerDataSource().get(position).getAddr(), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	};

}
