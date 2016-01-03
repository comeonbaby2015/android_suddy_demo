package demo.ui_listview_adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.LruCache;

public class CacheCommon {

	private static File mCacheDir;

	private static final int MAX_CACHE_SIZE = 70 * 1024 * 1024; // 70M

	// »º´æ
	private static final LruCache<String, Long> cache = new LruCache<String, Long>(MAX_CACHE_SIZE) {
		@Override
		public int sizeOf(String key, Long value) {
			return value.intValue();
		}

		@Override
		protected void entryRemoved(boolean evicted, String key, Long oldValue, Long newValue) {
			File file = getFile(key);
			if (file != null)
				file.delete();
		}
	};

	private static File getFile(String fileName) {
		File file = new File(mCacheDir, fileName);
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		return file;
	}

	public static void init(Context context) {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
			mCacheDir = context.getExternalCacheDir();
		} else {
			mCacheDir = context.getCacheDir();
		}
	}

	public static boolean putBitmap(String fileName, Bitmap bitmap) {
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		File file = getFile(fileName);
		if (file != null) {
			return true;
		}
		FileOutputStream fos = getOutputStream(fileName);
		if (fos == null) {
			return false;
		}
		// boolean saved = bitmap.compress(CompressFormat.JPEG, 100, fos);
		try {
			fos.write(CommonUtil.encrypt(CommonUtil.Bitmap2Bytes(bitmap, CompressFormat.JPEG, 100)));
			fos.flush();
			fos.close();
			synchronized (cache) {
				cache.put(fileName, getFile(fileName).length());
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private static FileOutputStream getOutputStream(String fileName) {
		if (mCacheDir == null)
			return null;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(mCacheDir.getAbsolutePath() + File.separator + fileName);

			return fos;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static BitmapFactory.Options sBitmapOptions;
	static {
		sBitmapOptions = new BitmapFactory.Options();
		sBitmapOptions.inPurgeable = true; // bitmap can be purged to disk
	}

	public static Bitmap getBitmap(String fileName) {
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		File bitmapFile = getFile(fileName);
		if (bitmapFile != null) {
			Bitmap bitmap;
			try {
				FileInputStream fis = new FileInputStream(bitmapFile);
				byte[] bs = new byte[(int) bitmapFile.length()];
				fis.read(bs);
				fis.close();
				bs = CommonUtil.decrypt(bs);
				bitmap = BitmapFactory.decodeByteArray(bs, 0, bs.length, sBitmapOptions);
				// bitmap = BitmapFactory.decodeStream(new
				// FileInputStream(bitmapFile), null, sBitmapOptions);
				return bitmap;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public static boolean saveJsonFile(String json, String fileName) {
		if (fileName == null || "".equals(fileName)) {
			return false;
		}
		File file = getFile(fileName);
		if (file != null) {
			return true;
		}
		FileOutputStream fos = getOutputStream(fileName);
		if (fos == null) {
			return false;
		}
		try {
			fos.write(CommonUtil.encrypt(json.getBytes("utf8")));
			fos.flush();
			fos.close();
			synchronized (cache) {
				cache.put(fileName, getFile(fileName).length());
			}
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static String getJsonFile(String fileName) {
		if (fileName == null || "".equals(fileName)) {
			return null;
		}
		File file = getFile(fileName);
		if (file != null) {
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] bs = new byte[(int) file.length()];
				fis.read(bs);
				fis.close();
				return new String(CommonUtil.decrypt(bs));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

}
