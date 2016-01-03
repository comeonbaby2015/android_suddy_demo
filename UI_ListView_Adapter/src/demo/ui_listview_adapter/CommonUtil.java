package demo.ui_listview_adapter;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import demo.ui_listview_adapter.base.bean.BmpResponsePackage;
import demo.ui_listview_adapter.base.net.HttpCaller;
import demo.ui_listview_adapter.base.trigger.TriggerInfo;

public class CommonUtil {

	private static Animation anim_fadein;

	private static final String KEY = "U3d5eGxqY3pmeGZseTgwNg==";

	private static BitmapReceiveHandler mHandler;

	public static void init(Context c) {

		mHandler = new BitmapReceiveHandler();
		anim_fadein = AnimationUtils.loadAnimation(c, R.anim.fade_in);
	}

	public static void getBitmap(String filePath, ImageView view) {
		if (view == null) {
			return;
		}
		Bitmap bitmap = CacheCommon.getBitmap(filePath.substring(filePath.lastIndexOf(File.separator) + 1));
		if (bitmap != null) {
			view.setImageBitmap(bitmap);
			view.startAnimation(anim_fadein);
			return;
		}
		// download
		ScaleType scaleType = view.getScaleType();
		int reqID = HttpCaller.getCaller().loadBitmap(filePath, mHandler, scaleType);
		view.setScaleType(ScaleType.CENTER);
		// view.setImageResource(R.drawable.pic_loading);
		holders.put(reqID, view);
	}

	public static byte[] Bitmap2Bytes(Bitmap bm, CompressFormat format, int quality) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(format, quality, baos);
		return baos.toByteArray();
	}

	public static Bitmap getBitmap(String filePath, OnBitmapLoadListener listener) {
		Bitmap bitmap = CacheCommon.getBitmap(filePath.substring(filePath.lastIndexOf(File.separator) + 1));
		if (bitmap != null) {
			return bitmap;
		}
		int reqID = HttpCaller.getCaller().loadBitmap(filePath, mHandler, null);
		holders.put(reqID, listener);
		return null;
	}

	private static SparseArray<Object> holders = new SparseArray<Object>();

	private static class BitmapReceiveHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.what == TriggerInfo.id.TRIGGER_ID_RESPONSE) {
				if (!(msg.obj instanceof BmpResponsePackage)) {
					Log.e("QGB", "returns obj is not a instance of BmpResponsePackage!");
					return;
				}
				BmpResponsePackage rep = (BmpResponsePackage) msg.obj;
				if (rep == null) {
					Log.e("QGB", "returns obj is null!");
					return;
				}
				Object holder = holders.get(rep.getReqID());
				if (holder == null) {
					Log.e("QGB", "Holder has destoryed!");
					return;
				}
				if (rep.getBmp() == null || rep.getBmp().length <= 0) {
					Log.e("QGB", "Bitmap Download Failed!");
					return;
				}
				if (rep.getFilePath() == null) {
					return;
				}
				Bitmap bmp = BitmapFactory.decodeByteArray(rep.getBmp(), 0, rep.getBmp().length);
				CacheCommon.putBitmap(rep.getFilePath().substring(rep.getFilePath().lastIndexOf(File.separator) + 1), bmp);
				if (holder instanceof ImageView) {
					((ImageView) holder).setScaleType(rep.getScaleType());
					((ImageView) holder).setImageBitmap(bmp);
					((ImageView) holder).startAnimation(anim_fadein);
				} else if (holder instanceof OnBitmapLoadListener) {
					((OnBitmapLoadListener) holder).onBitmapLoad(bmp);
				}
				holders.remove(rep.getReqID());
				bmp = null;
			}
		}
	}

	public static void cancelBitmap(Object holder) {
		if (holders.indexOfValue(holder) >= 0) {
			holders.removeAt(holders.indexOfValue(holder));
		}
	}

	// 加密缓存
	public static byte[] encrypt(byte[] clear) throws Exception {
		// 根据给定的字节数组构造一个密钥
		SecretKeySpec skeySpec = new SecretKeySpec(Base64.decode(KEY, Base64.DEFAULT), "AES");
		// 加密和解密提供密码功能。
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	public static byte[] decrypt(byte[] encrypted) throws Exception {
		// DES ,Triple DES
		SecretKeySpec skeySpec = new SecretKeySpec(Base64.decode(KEY, Base64.DEFAULT), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public static interface OnBitmapLoadListener {
		public void onBitmapLoad(Bitmap bitmap);
	}
}
