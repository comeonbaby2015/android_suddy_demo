package demo.ui_listview_adapter.base.bean;

import android.os.Handler;

public class RequestPackage {

	private String url;

	private String module_id;

	private Class<?> resBean;

	private int reqID;

	private long requestTime;

	private Object param;

	private Handler reHandler;

	private String cacheKeyword;

	// public RequestPackage(String url, String module_id, int reqID,
	// long requestTime, Class<?> resBean, Object param) {
	// this.url = url;
	// this.module_id = module_id;
	// this.reqID = reqID;
	// this.requestTime = requestTime;
	// this.resBean = resBean;
	// this.param = param;
	// }

	public RequestPackage(String url, String module_id, int reqID, long requestTime, Class<?> resBean, Object param, String cacheKeyword, Handler reHandler) {
		this.url = url;
		this.module_id = module_id;
		this.reqID = reqID;
		this.requestTime = requestTime;
		this.resBean = resBean;
		this.param = param;
		this.cacheKeyword = cacheKeyword;
		this.reHandler = reHandler;
	}

	public Handler getReHandler() {
		return reHandler;
	}

	public String getModule_id() {
		return module_id;
	}

	public long getRequestTime() {
		return requestTime;
	}

	public Object getParam() {
		return param;
	}

	public String getUrl() {
		return url;
	}

	public int getReqID() {
		return reqID;
	}

	public Class<?> getResBean() {
		return resBean;
	}

	public String getCacheKeyword() {
		return cacheKeyword;
	}

}
