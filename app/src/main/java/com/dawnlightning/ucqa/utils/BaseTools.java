package com.dawnlightning.ucqa.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class BaseTools {
	
	/** 获取屏幕的宽度 */
	public final static int getWindowsWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/** 获取屏幕的高度 */
	public final static int getWindowsHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/** 获取屏幕的宽度 */
	public final static int getWindowsWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/** 获取屏幕的高度 */
	public final static int getWindowsHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}
}
