package demo.ui_listview_adapter.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import demo.ui_listview_adapter.base.trigger.TriggerInfo;

public class ActivityBase extends Activity implements IActivityControl {

	private int bg_res;

	private View subContentView;

	@Override
	final public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init(this);
		OnCreate(savedInstanceState);
	}

	protected int getTitleMiddleView() {
		return 0;
	}

	protected View getTitleRightView() {
		return null;
	}

	// @Override
	// final public View onCreateView(LayoutInflater inflater, ViewGroup
	// container, Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	//
	// subContentView = OnCreateView(inflater, ((IActivityGroup)
	// getActivity()).getContentView(), savedInstanceState);
	//
	// return subContentView;
	// }

	private static ActivityControl mWinControl;

	private void init(Context context) {
		mWinControl = ActivityControl.getInstance();
	}

	public final boolean onKeyDown(int keyCode, KeyEvent event) {

		if (event.getRepeatCount() != 0) {
			return true;
		}
		boolean ret = OnKeyDown(keyCode, event);

		if (!ret) {
			switch (keyCode) {
			// case KeyEvent.KEYCODE_BACK:
			// winBack();
			// return true;
			case KeyEvent.KEYCODE_MENU:
				return true;
			default:
				break;
			}
		}
		return ret;
	}

	final public void onResume() {
		super.onResume();
		mWinControl.setCurrentActivity(this);
		System.out.println("onresume");
		OnResume();
	}

	final public void onPause() {
		OnPause();
		mWinControl.setCurrentActivity(null);
		super.onPause();
	}

	final public void onDestroy() {
		OnDestroy();
		super.onDestroy();
	}

	protected void OnCreate(Bundle savedInstanceState) {

	}

	// protected View OnCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// return super.onCreateView(inflater, container, savedInstanceState);
	// }

	protected void OnResume() {

	}

	protected void OnPause() {

	}

	protected void OnDestroy() {

	}

	protected boolean OnKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	protected void showToast(String text, int duration) {
		mWinControl.showToast(text, duration);
	}

	@Override
	public final void startActivity(Class<?> targetWin) {
		// TODO Auto-generated method stub
		mWinControl.startActivity(targetWin);
	}

	@Override
	public final void winBack() {
		// TODO Auto-generated method stub
		mWinControl.winBack();
	}

	@Override
	public final void winBackTo(Class<?> targetWin) {
		// TODO Auto-generated method stub
		mWinControl.winBackTo(targetWin);
	}

	@Override
	public final void winSwitchTo(Class<?> targetWin) {
		// TODO Auto-generated method stub
		mWinControl.winSwitchTo(targetWin);
	}

	// @Override
	// public void winForwardTo(Class<?> targetWin, Bundle bundle) {
	// // TODO Auto-generated method stub
	// mWinControl.winForwardTo(targetWin, bundle);
	// }
	//
	// @Override
	// public void winBackTo(Class<?> targetWin, Bundle bundle) {
	// // TODO Auto-generated method stub
	// mWinControl.winBackTo(targetWin, bundle);
	// }
	//
	// @Override
	// public void winSwitchTo(Class<?> targetWin, Bundle bundle) {
	// // TODO Auto-generated method stub
	// mWinControl.winSwitchTo(targetWin, bundle);
	// };

	@Override
	public final void onTrigger(int iTriggerID, TriggerInfo info) {
		// TODO Auto-generated method stub
		OnTrigger(iTriggerID, info);
	}

	public void OnTrigger(int iTriggerID, TriggerInfo info) {
	}

	// protected void finish() {
	// winBack();
	// }

	public int getBg_res() {
		return bg_res;
	}

	/* package */void setBg_res(int bg_res) {
		this.bg_res = bg_res;
	}

	protected boolean clickAndForward(View view, final Class<?> nextWin, final int animIn, final int animOut, final int animPopEnter, final int animPopExit) {
		if (view == null || nextWin == null) {
			return false;
		}
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ActivityControl.getInstance().setActivityAnimation(animIn, animOut, animPopEnter, animPopExit);
				startActivity(nextWin);
			}
		});
		return true;
	}

}
