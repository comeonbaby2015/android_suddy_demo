package demo.ui_listview_adapter.base;

import android.view.ViewGroup;

public interface IActivityGroup {

	public ViewGroup getContentView();

	public void switchBackground(int bg_res);

	public void startLocation();

	public void stopLocation();
}
