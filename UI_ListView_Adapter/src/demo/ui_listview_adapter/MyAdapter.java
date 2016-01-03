package demo.ui_listview_adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * <pre>
 * 自定义适配器UserSpinnerAdapter
 * </pre>
 */
public class MyAdapter extends BaseAdapter {
	private Context mContext;
	private List<New> mDataSource;

	/*
	 * private class UserSpinnerItem { private TextView user; private TextView
	 * address; }
	 */
	MyAdapter(Context context, List<New> pDataSource) {
		mContext = context;
		mDataSource = pDataSource;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDataSource.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDataSource.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		Item item;

		// TODO:性能
		// 得到view实例
		if (null == convertView) {
			// 新建view实例，目的是新建name和address的view实例
			item = new Item();

			// 得到view resource
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);

			// 关联view实例与view resource
			item.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			item.content = (TextView) convertView.findViewById(R.id.tv_content);

			// 设置tag，目的是重复回收使用view实例
			convertView.setTag(item);

		} else {
			// 重复回收使用tag view实例
			item = (Item) convertView.getTag();
		}

		// 设置view实例的内容显示
		item.icon.setImageBitmap(mDataSource.get(position).icon_data);
		item.content.setText(mDataSource.get(position).content_data);

		return convertView;
	}
}

class Item {
	public ImageView icon;
	public TextView content;
}

class New {
	public Bitmap icon_data;
	public String content_data;

}
