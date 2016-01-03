package demo.ui_listview_adapter.base.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

public class MyServ extends Service {

	private Properties servs;

	private boolean autoServiceStarted;

	private static final String SERVICE_LIST_FILE_NAME = "auto_service_list";

	public static final int SERVICE_START_MESSAGE_ID = 0x100;

	private static Map<String, Handler> services;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		if (servs == null) {
			servs = new Properties();
			try {
				servs.load(getResources().getAssets().open(SERVICE_LIST_FILE_NAME));
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		if (!autoServiceStarted && servs != null && !servs.isEmpty()) {
			Set<Object> keys = servs.keySet();
			for (Object key : keys) {
				try {
					registerService(key.toString(), Class.forName(servs.getProperty(key.toString())));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
			autoServiceStarted = true;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Handler getService(String servName) {
		if (servName == null || "".equals(servName)) {
			throw new IllegalArgumentException("Service Name is null!");
		}
		if (services != null) {
			return services.get(servName);
		}
		return null;
	}

	public static void registerService(String servName, Class<?> clazz) throws Exception {
		if (servName == null || "".equals(servName)) {
			throw new IllegalArgumentException("Service Name is null!");
		}
		if (!Handler.class.equals(clazz.getSuperclass())) {
			throw new IllegalArgumentException("Class of service to register should be extended from android.os.Handler");
		}
		if (services == null) {
			services = new HashMap<String, Handler>();
		}
		HandlerThread thread = new HandlerThread(servName);
		thread.start();
		Handler service = (Handler) clazz.getConstructor(Looper.class).newInstance(thread.getLooper());
		services.put(servName, service);
		service.sendEmptyMessage(SERVICE_START_MESSAGE_ID);
	}
}
