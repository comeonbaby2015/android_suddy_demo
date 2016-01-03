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
 * �Զ���������UserSpinnerAdapter
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

		// TODO:����
		// �õ�viewʵ��
		if (null == convertView) {
			// �½�viewʵ����Ŀ�����½�name��address��viewʵ��
			item = new Item();

			// �õ�view resource
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);

			// ����viewʵ����view resource
			item.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			item.content = (TextView) convertView.findViewById(R.id.tv_content);

			// ����tag��Ŀ�����ظ�����ʹ��viewʵ��
			convertView.setTag(item);

		} else {
			// �ظ�����ʹ��tag viewʵ��
			item = (Item) convertView.getTag();
		}

		// ����viewʵ����������ʾ
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
