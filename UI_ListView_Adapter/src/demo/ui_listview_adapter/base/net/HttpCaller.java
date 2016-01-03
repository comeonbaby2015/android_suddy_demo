package demo.ui_listview_adapter.base.net;

import java.io.File;
import java.util.Properties;

import org.apache.http.protocol.HttpRequestHandler;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView.ScaleType;
import demo.ui_listview_adapter.base.bean.BmpRequestPackage;
import demo.ui_listview_adapter.base.service.MyServ;
import demo.ui_listview_adapter.base.util.ComConfigReader;
import demo.ui_listview_adapter.base.util.ConfigReader;

public class HttpCaller {

	private static final String TAG = "HTTP_CALLER";

	private static HttpCaller instance;

	private Context c;

	private HttpRequestHandler httpReqHandler;

	private BitmapDownloadHandler bmpReqHandler;

	public static final int REQUEST_ID_INVALID = 0;

	public static final int REQUEST_ID_MAX = Integer.MAX_VALUE - 2;

	private int reqID = REQUEST_ID_INVALID;

	private Properties beans;

	private String cacheKeyword;

	private String mainServerUrl = ConfigReader.getConfig(ConfigReader.CONFIG_KEY_HTTP_SERVER_URL);

	private String picsServerUrl = ConfigReader.getConfig(ConfigReader.CONFIG_KEY_PICS_SERVER_URL);

	private HttpCaller() {
		initReqHandler();
	}

	public synchronized static HttpCaller getCaller() {
		if (instance == null) {
			instance = new HttpCaller();
		}
		return instance;
	}

	public void initContext(Context c) {
		this.c = c;
		// loadBeans();
	}

	private void initReqHandler() {
		// this.httpReqHandler = (HttpRequestHandler)
		// MyServ.getService(HttpRequestHandler.TAG);
		this.bmpReqHandler = (BitmapDownloadHandler) MyServ.getService(BitmapDownloadHandler.TAG);
	}

	// private void loadBeans() {
	// if (beans == null) {
	// beans = new Properties();
	// try {
	// beans.load(c.getResources().openRawResource(R.raw.res_bean_mapper));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }

	private String getUrl(String module_id) {
		String url = mainServerUrl + ComConfigReader.getModuleURL(c, module_id);
		return url;
	}

	private boolean vaild(String url, String module_id, Object param) {
		if (url == null || "".equals(url)) {
			Log.w(TAG, "Url parse error");
			return false;
		}
		if (module_id == null || "".equals(module_id)) {
			Log.w(TAG, "module id is null");
			return false;
		}
		if (param == null) {
			Log.w(TAG, "params is null");
			return false;
		}
		return true;
	}

	public void setCacheKeyword(String keyword) {
		cacheKeyword = keyword;
	}

	/**
	 * Call a remote process
	 * 
	 * @param module_id
	 * @param param
	 * @return request_id
	 */
	// public synchronized int call(String module_id, Object param) {
	//
	// return _call(module_id, param, null);
	// }
	//
	// public synchronized int call(String module_id, Object param, Handler
	// reHandler) {
	// return _call(module_id, param, reHandler);
	// }
	//
	// private synchronized int _call(String module_id, Object param, Handler
	// reHandler) {
	// String url = getUrl(module_id);
	//
	// if (!vaild(url, module_id, param)) {
	// return REQUEST_ID_INVALID;
	// }
	//
	// if (reqID >= REQUEST_ID_MAX) {
	// reqID = REQUEST_ID_INVALID;
	// }
	// reqID++;
	// boolean pushed = false;
	// String sResBean = beans.getProperty(module_id);
	// if (sResBean != null && !"".equals(sResBean)) {
	// try {
	// Class<?> resBean = Class.forName(sResBean);
	// pushed =
	// httpReqHandler.sendMessage(httpReqHandler.obtainMessage(HttpRequestHandler.HTTP_REQUEST,
	// new RequestPackage(url, module_id, reqID, System.currentTimeMillis(),
	// resBean, param,
	// cacheKeyword, reHandler)));
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// cacheKeyword = null;
	// }
	// }
	// if (!pushed) {
	// reqID--;
	// return REQUEST_ID_INVALID;
	// }
	//
	// return reqID;
	// }

	public synchronized int loadBitmap(String filePath, Handler reHandler, ScaleType scaleType) {
		String url = picsServerUrl + (filePath.startsWith(File.separator) ? "" : File.separator) + filePath;

		if (url == null || "".equals(url)) {
			Log.w(TAG, "Url parse error");
			return REQUEST_ID_INVALID;
		}

		if (reqID >= REQUEST_ID_MAX) {
			reqID = REQUEST_ID_INVALID;
		}

		reqID++;
		boolean pushed = false;

		pushed = bmpReqHandler
				.sendMessage(bmpReqHandler.obtainMessage(BitmapDownloadHandler.BMP_REQUEST, new BmpRequestPackage(url, reqID, filePath, System.currentTimeMillis(), reHandler, scaleType)));
		if (!pushed) {
			reqID--;
			return REQUEST_ID_INVALID;
		}

		return reqID;
	}

	public static class id {
		public static final String MODULE_ID_QUERY_HOMEPAGE_SHOPS = "C001";

		public static final String MODULE_ID_LOGIN = "U001";

		public static final String MODULE_ID_REGISTER = "U002";
	}
}
