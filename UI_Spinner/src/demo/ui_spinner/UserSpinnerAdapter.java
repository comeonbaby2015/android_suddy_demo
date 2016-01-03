package demo.ui_spinner;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * <pre>
 * 自定义适配器UserSpinnerAdapter
 * </pre>
 */
public class UserSpinnerAdapter extends BaseAdapter {
	private Context mContext;
	private List<User> mDataSource;

	private class UserSpinnerItem {
		private TextView user;
		private TextView address;
	}

	UserSpinnerAdapter(Context context, List<User> pDataSource) {
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

		UserSpinnerItem item;

		// TODO:性能
		// 得到view实例
		if (null == convertView) {
			// 新建view实例，目的是新建name和address的view实例
			item = new UserSpinnerItem();

			// 得到view resource
			convertView = LayoutInflater.from(mContext).inflate(R.layout.ui_spinner_unit, null);

			// 关联view实例与view resource
			item.user = (TextView) convertView.findViewById(R.id.tv_name);
			item.address = (TextView) convertView.findViewById(R.id.tv_address);

			// 设置tag，目的是重复回收使用view实例
			convertView.setTag(item);

		} else {
			// 重复回收使用tag view实例
			item = (UserSpinnerItem) convertView.getTag();
		}

		// 设置view实例的内容显示
		item.user.setText(mDataSource.get(position).getName());
		item.address.setText(mDataSource.get(position).getAddr());

		return convertView;
	}
}
