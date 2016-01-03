package demo.ui_listview_adapter.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import demo.ui_listview_adapter.R;

public class ConfigReader {

	public static final String CONFIG_KEY_HTTP_SERVER_URL = "HttpServerUrl";
	
	public static final String CONFIG_KEY_PICS_SERVER_URL = "PicsServerUrl";

	public static final String CONFIG_KEY_HTTP_TIMEOUT = "HttpTimeout";
	
	public static final String CONFIG_KEY_UDP_INTERVAL = "UDPInterval";

	public static final int DEFAULT_HTTP_TIMEOUT = 10000;
	
	public static final int DEFAULT_UDP_INTERVAL = 300000;

	private static Properties config;

	private ConfigReader() {
	}

	public static boolean loadConfig(Context c) {
		InputStream is = c.getResources().openRawResource(R.raw.config);
		config = new Properties();
		try {
			config.load(is);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static String getConfig(String key) {
		if (key == null || "".equals(key)) {
			return "";
		}
		if (config == null) {
			return "";
		}
		return config.getProperty(key);
	}
}
