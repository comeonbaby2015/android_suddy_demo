package demo.ui_listview_adapter.base.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpUtil {

	private static Gson gson;

	private HttpUtil() {
	}

	static {
		GsonBuilder builder = new GsonBuilder();
		gson = builder.create();
	}

	public static <T> T parseJson(String jobj, Class<T> resBean)
			throws Exception {
		return jsonToObject(jobj, resBean);
	}

	public static String objectToJson(Object obj) throws Exception {
		if (obj == null) {
			throw new NullPointerException();
		}
		return gson.toJson(obj);
	}

	public static <T> T jsonToObject(String jobj, Class<T> resBean)
			throws Exception {
		if (jobj == null) {
			throw new NullPointerException();
		}
		return gson.fromJson(jobj, resBean);
	}
}
