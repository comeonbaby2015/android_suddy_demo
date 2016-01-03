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
 * �Զ���������UserSpinnerAdapter
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

		// TODO:����
		// �õ�viewʵ��
		if (null == convertView) {
			// �½�viewʵ����Ŀ�����½�name��address��viewʵ��
			item = new UserSpinnerItem();

			// �õ�view resource
			convertView = LayoutInflater.from(mContext).inflate(R.layout.ui_spinner_unit, null);

			// ����viewʵ����view resource
			item.user = (TextView) convertView.findViewById(R.id.tv_name);
			item.address = (TextView) convertView.findViewById(R.id.tv_address);

			// ����tag��Ŀ�����ظ�����ʹ��viewʵ��
			convertView.setTag(item);

		} else {
			// �ظ�����ʹ��tag viewʵ��
			item = (UserSpinnerItem) convertView.getTag();
		}

		// ����viewʵ����������ʾ
		item.user.setText(mDataSource.get(position).getName());
		item.address.setText(mDataSource.get(position).getAddr());

		return convertView;
	}
}
