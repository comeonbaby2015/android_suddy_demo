package demo.ui_listview_adapter.base.bean;

public class ResponsePackage {
	private int reqID;

	private long responseTime;

	private Object param;

	private String module_id;

	public ResponsePackage() {
	}

	public int getReqID() {
		return reqID;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public Object getParam() {
		return param;
	}

	public void setReqID(int reqID) {
		this.reqID = reqID;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public String getModule_id() {
		return module_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

}
