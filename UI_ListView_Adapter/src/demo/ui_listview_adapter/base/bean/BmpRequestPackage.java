package demo.ui_listview_adapter.base.bean;

import android.os.Handler;
import android.widget.ImageView.ScaleType;

public class BmpRequestPackage {

	private String url;

	private int reqID;

	private String filePath;

	private long requestTime;

	private Handler reHandler;

	private ScaleType scaleType;

	public BmpRequestPackage(String url, int reqID, String filePath, long requestTime, Handler reHandler, ScaleType scaleType) {
		this.url = url;
		this.reqID = reqID;
		this.filePath = filePath;
		this.requestTime = requestTime;
		this.reHandler = reHandler;
		this.scaleType = scaleType;
	}

	public Handler getReHandler() {
		return reHandler;
	}

	public long getRequestTime() {
		return requestTime;
	}

	public String getUrl() {
		return url;
	}

	public int getReqID() {
		return reqID;
	}

	public String getFilePath() {
		return filePath;
	}

	public ScaleType getScaleType() {
		return scaleType;
	}

}
