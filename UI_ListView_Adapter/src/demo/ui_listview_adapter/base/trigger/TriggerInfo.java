package demo.ui_listview_adapter.base.trigger;

public class TriggerInfo {

	public static class id {
		public static final int TRIGGER_ID_START = 0;

		public static final int TRIGGER_ID_TEST = TRIGGER_ID_START + 1;

		public static final int TRIGGER_ID_RESPONSE = TRIGGER_ID_TEST + 1;

		public static final int TRIGGER_ID_LOCATION = TRIGGER_ID_RESPONSE + 1;

		public static final int TRIGGER_ID_LOGIN = TRIGGER_ID_LOCATION + 1;
	}

	private int triggerID;

	private long param1;

	private long param2;

	private long param3;

	private long param4;

	private String sParam;

	private Object objParam;

	public TriggerInfo(int triggerID) {
		this.triggerID = triggerID;
	}

	public int getTriggerID() {
		return triggerID;
	}

	public void setTriggerID(int triggerID) {
		this.triggerID = triggerID;
	}

	public long getParam1() {
		return param1;
	}

	public void setParam1(long param1) {
		this.param1 = param1;
	}

	public long getParam2() {
		return param2;
	}

	public void setParam2(long param2) {
		this.param2 = param2;
	}

	public long getParam3() {
		return param3;
	}

	public void setParam3(long param3) {
		this.param3 = param3;
	}

	public long getParam4() {
		return param4;
	}

	public void setParam4(long param4) {
		this.param4 = param4;
	}

	public String getsParam() {
		return sParam;
	}

	public void setsParam(String sParam) {
		this.sParam = sParam;
	}

	public Object getObjParam() {
		return objParam;
	}

	public void setObjParam(Object objParam) {
		this.objParam = objParam;
	}

}
