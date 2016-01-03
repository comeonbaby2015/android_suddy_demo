package demo.ui_listview_adapter.base.bean;

import android.widget.ImageView.ScaleType;

public class BmpResponsePackage {
	private int reqID;

	private String filePath;

	private long responseTime;

	private byte[] bmp;

	private ScaleType scaleType;

	public BmpResponsePackage() {
	}

	public int getReqID() {
		return reqID;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setReqID(int reqID) {
		this.reqID = reqID;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public byte[] getBmp() {
		return bmp;
	}

	public void setBmp(byte[] bmp) {
		this.bmp = bmp;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public ScaleType getScaleType() {
		return scaleType;
	}

	public void setScaleType(ScaleType scaleType) {
		this.scaleType = scaleType;
	}

}
