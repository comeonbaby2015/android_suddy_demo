package demo.ui_listview_adapter.base.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import demo.ui_listview_adapter.base.bean.BmpRequestPackage;
import demo.ui_listview_adapter.base.bean.BmpResponsePackage;
import demo.ui_listview_adapter.base.trigger.TriggerInfo;

public class BitmapDownloadHandler extends Handler {

	public static final String TAG = "BitmapDownloadHandler";

	public static final int BMP_REQUEST = 0x1;

	public BitmapDownloadHandler(Looper looper) {
		// TODO Auto-generated constructor stub
		super(looper);
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		if (msg.what == BMP_REQUEST) {
			BmpResponsePackage rep = new BmpResponsePackage();
			if (!(msg.obj instanceof BmpRequestPackage)) {
				Log.e(TAG, "IllegalException object in MessageQueue is not instance of RequestPackage");
				return;
			}
			BmpRequestPackage req = (BmpRequestPackage) msg.obj;
			rep.setReqID(req.getReqID());
			rep.setFilePath(req.getFilePath());
			rep.setScaleType(req.getScaleType());
			if (true) {
				Log.d(TAG, "getBitmap:" + req.getUrl());
			}
			doRequest(req.getUrl(), req, rep);

		}
	}

	private interface OnBmpDownloadListener {
		public void OnBmpDownload(byte[] bmp);
	}

	private void doRequest(String url, final BmpRequestPackage req, final BmpResponsePackage rep) {
		new DownloadTask(new OnBmpDownloadListener() {

			@Override
			public void OnBmpDownload(byte[] bmp) {
				// TODO Auto-generated method stub
				if (true) {
					Log.d(TAG, "received:" + (bmp == null ? "111" : "" + bmp));
				}

				rep.setBmp(bmp);

				Handler reHandler = req.getReHandler();
				if (reHandler != null) {
					reHandler.sendMessage(reHandler.obtainMessage(TriggerInfo.id.TRIGGER_ID_RESPONSE, rep));
				}
			}
		}).execute(url);
	}

	private class DownloadTask extends AsyncTask<String, Integer, byte[]> {

		private OnBmpDownloadListener mListener;

		public DownloadTask(OnBmpDownloadListener listener) {
			this.mListener = listener;
		}

		@Override
		protected void onPostExecute(byte[] result) {
			// TODO Auto-generated method stub
			if (mListener != null) {
				mListener.OnBmpDownload(result);
			}
		}

		@Override
		protected byte[] doInBackground(String... params) {
			// TODO Auto-generated method stub
			final DefaultHttpClient client = new DefaultHttpClient();
			final HttpGet getRequest = new HttpGet(params[0]);

			HttpResponse response;
			try {
				response = client.execute(getRequest);
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != HttpStatus.SC_OK) {
					Log.e(TAG, "Request URL failed, error code =" + statusCode);
				}

				HttpEntity entity = response.getEntity();
				if (entity == null) {
					Log.e(TAG, "HttpEntity is null");
				}
				InputStream is = null;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					is = entity.getContent();
					byte[] buf = new byte[1024];
					int readBytes = -1;
					while ((readBytes = is.read(buf)) != -1) {
						baos.write(buf, 0, readBytes);
					}
				} finally {
					if (baos != null) {
						baos.close();
					}
					if (is != null) {
						is.close();
					}
				}
				return baos.toByteArray();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	}
}
