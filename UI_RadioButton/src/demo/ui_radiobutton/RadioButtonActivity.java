package demo.ui_radiobutton;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Toast;

public class RadioButtonActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radiobutton);

		// RadioButton����Ҫ�¼�:onCheckedChanged������ѡ�к�ȡ����������
		RadioButton radioButton;
		radioButton = (RadioButton) findViewById(R.id.radio_pirates);
		radioButton.setChecked(true);
		radioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Toast.makeText(RadioButtonActivity.this, "pirates��ѡ����2", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(RadioButtonActivity.this, "pirates��ȡ����2", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	// RadioButton����Ҫ�¼�:onClick������ѡ����������
	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();
		switch (view.getId()) {
		/*
		 * case R.id.radio_pirates: if (checked) {
		 * Toast.makeText(UIViewActivity.this, "pirates��ѡ����",
		 * Toast.LENGTH_SHORT).show(); } break;
		 */

		case R.id.radio_ninjas:
			if (checked) {
				Toast.makeText(RadioButtonActivity.this, "ninjas��ѡ����", Toast.LENGTH_SHORT).show();
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
