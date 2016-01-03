package demo.ui_listview_adapter.base.util;

import java.util.Properties;

import android.content.Context;

public class ComConfigReader {

	private ComConfigReader() {
	}

	private static Properties p_moduleList;

	private static boolean loadModuleList(Context c) {
//		InputStream is = c.getResources()
//				.openRawResource(R.raw.com_module_list);
//		p_moduleList = new Properties();
//		try {
//			p_moduleList.load(is);
//			return true;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
		return false;
	}

	public static String getModuleURL(Context c, String module_id) {
		if (module_id == null || "".equals(module_id)) {
			return "";
		}
		if (p_moduleList == null && !loadModuleList(c)) {
			return "";
		}
		return p_moduleList.getProperty(module_id);
	}
}
