package demo.ui_ratingbar;

import com.example.c3_8_ratingbar.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * <pre>
 * ���ֿؼ�RatingBar
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {
	private RatingBar mRatingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRatingBar = (RatingBar) findViewById(R.id.ratingBar1);
		mRatingBar.setMax(5);
		// ���ֿؼ�RatingBar���¼�RatingBar.OnRatingBarChangeListener
		mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, Float.toString(rating), Toast.LENGTH_SHORT).show();
			}
		});
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
