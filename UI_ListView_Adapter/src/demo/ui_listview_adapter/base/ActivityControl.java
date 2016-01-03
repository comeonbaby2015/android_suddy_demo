package demo.ui_listview_adapter.base;

import java.util.Stack;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import demo.ui_listview_adapter.R;
import demo.ui_listview_adapter.base.trigger.TriggerInfo;

public class ActivityControl implements IActivityControl {

	private static ActivityControl instance;

	private ActivityBase currentActivity;

	private MessageHandler handler;

	private Context context;

	private Stack<ActivityBase> stack;

	private FragmentActivity activity;

	private FragmentManager fragMgr;

	private ActivityControl() {

	}

	/**
	 * must be inited in Main Thread
	 * 
	 * @param c
	 */
	public void init() {

		if (handler == null) {
			handler = new MessageHandler();
		}
		if (stack == null) {
			stack = new Stack<ActivityBase>();
		}
	}

	public void setContext(Context c) {
		this.context = c;
		if (c instanceof FragmentActivity) {
			activity = (FragmentActivity) c;
		}
		fragMgr = activity.getSupportFragmentManager();
		fragMgr.addOnBackStackChangedListener(new OnBackStackChangedListener() {

			@Override
			public void onBackStackChanged() {
				// TODO Auto-generated method stub
				System.out.println("stack count::" + fragMgr.getBackStackEntryCount());
			}
		});
	}

	public synchronized static ActivityControl getInstance() {
		if (instance == null) {
			instance = new ActivityControl();
		}
		return instance;
	}

	public void finishActivity(ActivityBase win) {
		if (win != null) {
			stack.remove(win);
		}
	}

	public ActivityBase getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(ActivityBase currentActivity) {
		this.currentActivity = currentActivity;
		if (currentActivity != null) {
			int bg_res = currentActivity.getBg_res();
			if (bg_res != 0) {
				((IActivityGroup) activity).switchBackground(bg_res);
			}
		}
	}

	private int animIn;

	private int animOut;

	private int animPopEnter;

	private int animPopExit;

	public void setActivityAnimation(int animIn, int animOut, int animPopEnter, int animPopExit) {
		this.animIn = animIn;
		this.animOut = animOut;
		this.animPopEnter = animPopEnter;
		this.animPopExit = animPopExit;
	}

	private int bg_res;

	public void setNextActivityBg(int bg_res) {
		this.bg_res = bg_res;
	}

	public int getFragmentStackCount() {
		return fragMgr.getBackStackEntryCount();
	}

	@Override
	public void startActivity(Class<?> targetWin) {
		// TODO Auto-generated method stub
		if (targetWin == null) {
			return;
		}
		if (currentActivity != null && currentActivity.getClass().equals(targetWin)) {
			return;
		}
		try {

			ActivityBase targetActivity = (ActivityBase) targetWin.newInstance();
//			targetActivity.setBg_res(bg_res);
//			bg_res = 0;
//			FragmentTransaction transaction = fragMgr.beginTransaction();
//			// transaction.add(R.id.content_view, targetActivity);
//			transaction.setCustomAnimations(animIn, animOut, animPopEnter, animPopExit);
//			transaction.replace(R.id.content_view, targetActivity, String.valueOf(fragMgr.getBackStackEntryCount()));
//			transaction.addToBackStack(null);
//			transaction.commit();

			// ActivityBase targetActivity = (ActivityBase)
			// targetWin.getConstructor(Context.class).newInstance(context);
			// targetActivity.setVisibility(View.INVISIBLE);
			// activity.addView(targetActivity);
			// targetActivity.onCreate();
			// ActivityBase lastActivity = currentActivity;
			// if (lastActivity != null) {
			// lastActivity.onPause();
			// }
			//
			// if (animIn != null) {
			// animIn.setAnimationListener(new AnimInListener(targetActivity));
			// targetActivity.startAnimation(animIn);
			// } else {
			// targetActivity.setVisibility(View.VISIBLE);
			// }
			// if (animOut != null && lastActivity != null) {
			// lastActivity.startAnimation(animOut);
			// }
			//
			// targetActivity.onResume();
			//
			// stack.push(targetActivity);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void winBack() {
		// TODO Auto-generated method stub

		if (currentActivity != null) {
			activity.onBackPressed();
		}

	}

	@Override
	public void winBackTo(Class<?> targetWin) {
		// TODO Auto-generated method stub
		if (targetWin == null) {
			return;
		}
		if (currentActivity != null && !currentActivity.getClass().equals(targetWin)) {
			FragmentTransaction transaction = fragMgr.beginTransaction();
			int index = fragMgr.getBackStackEntryCount() - 1;
			boolean touchTarget = false;
			while (index > 0) {
				Fragment fragment = fragMgr.findFragmentByTag(String.valueOf(index));
				if (fragment == null) {
					index--;
					continue;
				}
				if (fragment.getClass().equals(targetWin)) {
					touchTarget = true;
					break;
				}
				fragMgr.popBackStack();
				index--;
			}
			if (targetWin.equals(fragMgr.findFragmentByTag("0").getClass())) {
				touchTarget = true;
			}
			if (touchTarget) {
				transaction.commit();
				return;
			}
			ActivityBase targetActivity;
			try {
				targetActivity = (ActivityBase) targetWin.newInstance();
				targetActivity.setBg_res(bg_res);
				bg_res = 0;
				transaction.setCustomAnimations(animIn, 0, animPopEnter, animPopExit);
//				transaction.replace(R.id.content_view, targetActivity, String.valueOf(index + 1));
				transaction.addToBackStack(null);
				transaction.commit();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void winSwitchTo(Class<?> targetWin) {
		// TODO Auto-generated method stub
		if (targetWin == null) {
			return;
		}
		if (currentActivity != null && currentActivity.getClass().equals(targetWin)) {
			return;
		}
		try {
			ActivityBase targetActivity = (ActivityBase) targetWin.newInstance();
			targetActivity.setBg_res(bg_res);
			bg_res = 0;
			FragmentTransaction transaction = fragMgr.beginTransaction();
			// transaction.add(R.id.content_view, targetActivity);
			transaction.setCustomAnimations(animIn, animOut);
//			transaction.replace(R.id.content_view, targetActivity, String.valueOf(fragMgr.getBackStackEntryCount() - 1));
			transaction.commit();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showHelp(Class<?> helpWin) {
		if (currentActivity != null && currentActivity.getClass().equals(helpWin)) {
			return;
		}
		try {
			ActivityBase targetActivity = (ActivityBase) helpWin.newInstance();
			targetActivity.setBg_res(bg_res);
			bg_res = 0;
			FragmentTransaction transaction = fragMgr.beginTransaction();
			transaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
//			transaction.add(R.id.content_view, targetActivity, String.valueOf(fragMgr.getBackStackEntryCount()));
			transaction.addToBackStack(null);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Override
	// public void winForwardTo(Class<?> targetWin, Bundle bundle) {
	// // TODO Auto-generated method stub
	// }
	//
	// @Override
	// public void winBackTo(Class<?> targetWin, Bundle bundle) {
	// // TODO Auto-generated method stub
	// }
	//
	// @Override
	// public void winSwitchTo(Class<?> targetWin, Bundle bundle) {
	// // TODO Auto-generated method stub
	// }

	@Override
	public void onTrigger(int iTriggerID, TriggerInfo info) {
		// TODO Auto-generated method stub
		currentActivity.onTrigger(iTriggerID, info);
	}

	public void sendTriggerToScreen(TriggerInfo info) {
		handler.sendMessage(handler.obtainMessage(MSG_TYPE_TRIGGER, info));
	}

	private class MessageHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_TYPE_TRIGGER:
				if (currentActivity == null) {
					Message newMsg = new Message();
					newMsg.copyFrom(msg);
					handler.sendMessageDelayed(newMsg, 300);
					break;
				} else {
					onTrigger(((TriggerInfo) msg.obj).getTriggerID(), (TriggerInfo) msg.obj);
				}
				break;
			}
		}
	}

	public void showToast(String text, int duration) {
		if (currentActivity == null) {
			return;
		}
		Toast.makeText(context, text, duration).show();
	}

	private static final int MSG_TYPE_TRIGGER = 1;

//	public void setMainBg() {
//
//		ActivityBase mainActivity = ((ActivityBase) fragMgr.findFragmentByTag("0"));
//		// mainActivity.setBg_res(R.drawable.temp_take_order_bg);
//		((IActivityGroup) mainActivity.getActivity()).switchBackground(mainActivity.getBg_res());
//	}

	public void startActivityFromRight(Class<?> cls, int resId) {
		setActivityAnimation(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
		setNextActivityBg(resId);
		startActivity(cls);
	}
}
