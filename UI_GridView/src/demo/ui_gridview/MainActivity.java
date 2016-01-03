package demo.ui_gridview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.hello.R;

public class MainActivity extends Activity {
	private GridView mImageGv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViews();
		setValues();
		setListeners();
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

	private void findViews() {
		mImageGv = (GridView) findViewById(R.id.gv_images);
	}

	private void setValues() {
		mImageGv.setAdapter(new ImageAdapter(this));
	}

	private void setListeners() {
		mImageGv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
	}
}

class ImageAdapter extends BaseAdapter {
	private Context mContext;

	public ImageAdapter() {
		// TODO Auto-generated constructor stub
	}

	public ImageAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;

		if (null == convertView) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mImageIds[position]);
		return imageView;
	}

	private Integer[] mImageIds = { R.drawable.pro1, R.drawable.pro2, R.drawable.pro3, R.drawable.pro4, R.drawable.pro5, R.drawable.pro6, R.drawable.pro7, R.drawable.pro8, R.drawable.pro9,
			R.drawable.pro10, R.drawable.pro11, R.drawable.pro12, R.drawable.pro13, R.drawable.pro14, R.drawable.pro15, R.drawable.pro16, R.drawable.pro17, R.drawable.pro18, R.drawable.pro19,
			R.drawable.pro20 };

}