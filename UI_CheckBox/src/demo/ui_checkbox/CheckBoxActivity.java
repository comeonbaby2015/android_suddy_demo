package demo.ui_checkbox;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class CheckBoxActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkbox);
	}

	// ʹ��XML���� android:onClick="onCheckboxClicked"ʵ��Click�¼�����
	public void onCheckboxClicked(View view) {
		boolean isCheked = ((CheckBox) view).isChecked();
		switch (view.getId()) {
		case R.id.checkbox_meat:
			if (isCheked) {
				Toast.makeText(CheckBoxActivity.this, "ѡ����Meat", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.checkbox_cheese:
			if (isCheked) {
				Toast.makeText(CheckBoxActivity.this, "ѡ����Cheese", Toast.LENGTH_SHORT).show();
			}
			break;
		}
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
