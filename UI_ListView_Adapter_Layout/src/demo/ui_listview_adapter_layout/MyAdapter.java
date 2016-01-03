package demo.ui_listview_adapter_layout;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//定义Adapter
public class MyAdapter extends BaseAdapter {
	Context mContext;
	List<String> mList;

	public MyAdapter(Context context, List<String> list) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView;

		// TODO 性能，重用view
		if (null == convertView) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);
			textView = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(textView);
		} else {
			textView = (TextView) convertView.getTag();
		}

		textView.setText(mList.get(position));

		Log.d("CY", "-----position=" + position + ",convertView=" + convertView);

		return convertView;
	}

}
