package demo.ui_listview_adapter.base;

import demo.ui_listview_adapter.base.trigger.TriggerInfo;

public interface IActivityControl {

	public void startActivity(Class<?> targetWin);

	// public void winForwardTo(Class<?> targetWin, Bundle bundle);

	public void winBack();

	public void winBackTo(Class<?> targetWin);

	// public void winBackTo(Class<?> targetWin, Bundle bundle);

	public void winSwitchTo(Class<?> targetWin);

	// public void winSwitchTo(Class<?> targetWin, Bundle bundle);

	public void onTrigger(int iTriggerID, TriggerInfo info);

}
