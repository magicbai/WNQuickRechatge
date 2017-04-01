package com.wn.quickrechatge.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 工具类
 * @author Administrator
 *
 */
public class Tool {
	private static final String tag = Tool.class.getSimpleName();
	/** 是否已出现异常 **/
	public static boolean IS_ERROR = false;
	public static Dialog mDialog;
	public static Toast toast = null;
	/**
	 * 文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		boolean isExist = false;
		if (null != filePath && !"".equals(filePath)) {
			File file = new File(filePath);
			if (null != file && file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}

	/**
	 * 文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String[] filePath) {
		boolean isExist = true;
		if (null != filePath && !"".equals(filePath) && filePath.length > 0) {
			for (int i = 0; i < filePath.length; i++) {
				File file = new File(filePath[i]);
				if (null == file || !file.exists()) {
					isExist = false;
				}
			}
		} else {
			isExist = false;
		}
		return isExist;
	}

	/**
	 * 向SD卡写入数据
	 * 
	 * @param bytes
	 * @param path
	 * @param isAppend
	 */
	public static void saveBytes2SDcardFile(byte[] bytes, String path, boolean isAppend) {
		try {
			File file = new File(path);
			File dir = new File(file.getParent());
			if (!dir.exists()) {
				dir.mkdirs();
				dir.setWritable(true);
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(path, isAppend);
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
     * 保存图片到指定路径
     * @param bitmap 图像数据
     * @param path 保存的路径
     * @param isApend 是否追加保存
     * @param quality 图像质量
     */
	public synchronized static void saveBitmap2Folder(Bitmap bitmap,String path,boolean isApend,int quality,String fileType){
		if(null==bitmap || bitmap.isRecycled()){
			Log.d(path, "保存图片_图片已不存在");
			return;
		}
		try {
			File file = new File(path);
			File dir = new File(file.getParent());
			if (!dir.exists()) {
				dir.mkdirs();
				dir.setWritable(true);
			}
			if(!file.exists()){
				file.createNewFile();
			}
			String fileName = file.getName();
			int lastDotIndex = fileName.lastIndexOf('.');
			Bitmap.CompressFormat cf = Bitmap.CompressFormat.JPEG;
			Log.d(tag, "保存图片："+path);
			Log.d(tag, "保存图片_[lastDotIndex,fileName,fileType]=["+lastDotIndex+","+fileName+","+fileType+"]");
			if("jpeg".equalsIgnoreCase(fileType)||"jpg".equalsIgnoreCase(fileType)){
				Log.d(tag, "保存图片:jpeg");
				cf = Bitmap.CompressFormat.JPEG;
			}else if("png".equalsIgnoreCase(fileType)){
				Log.d(tag, "保存图片:png");
				cf = Bitmap.CompressFormat.PNG;
			}else if("img".equalsIgnoreCase(fileType)){
				cf = Bitmap.CompressFormat.PNG;
				Log.d(tag, "保存图片:img");
			}else{
				Log.d(tag, "Else:jpg");
				cf = Bitmap.CompressFormat.JPEG;
			}
			FileOutputStream out = new FileOutputStream(file,isApend);
			Log.d(tag, "保存图片_bitmap.isRecycle = " + bitmap.isRecycled());
			if (bitmap.compress(cf, quality, out)) {
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 强制隐藏软件键盘
	 * @param context
	 * @param focusView 当前获得焦点的View
	 */
	public static boolean hiddenSoftKeyboard(Context context, View focusView){
	    try {
			return ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			Log.w(tag, "hiddenSoftKeyboard exception");
		}
	    return false;
	}
	
	/**
	 * 显示软件键盘
	 * @param context
	 * @param focusView 当前获得焦点的View
	 */
	public static boolean showSoftKeyboard(Context context, View focusView){
		try {
			return ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(focusView, InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			Log.w(tag, "hiddenSoftKeyboard exception");
		}
		return false;
	}
	/**
	 * 获取日期差
	 * @param date1 之前日期
	 * @param date2 今天日期
	 * @return
	 */
	 public static int getBetweenDay(Date date1, Date date2) {  
	        Calendar d1 = new GregorianCalendar();  
	        d1.setTime(date1);  
	        Calendar d2 = new GregorianCalendar();  
	        d2.setTime(date2);  
	        int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);  
	        System.out.println("days="+days);  
	        int y2 = d2.get(Calendar.YEAR);  
	        if (d1.get(Calendar.YEAR) != y2) {  
//	          d1 = (Calendar) d1.clone();  
	            do {  
	                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);  
	                d1.add(Calendar.YEAR, 1);  
	            } while (d1.get(Calendar.YEAR) != y2);  
	        }  
	        return days;  
	    }
	 
	 public static String translateTime(String pushtime){
		 String time = "";
			if(pushtime!=null && !"".equals(pushtime)){
				try {
					int day = Tool.getBetweenDay(DateFormatConfig.SDF_YMDHMS.parse(pushtime), new Date());
					if(day==0){
						time = pushtime.substring(11,16);
					}
					else if(day == 1){
						time = "昨天";
					}
					else{
//						time = pushtime.substring(11,16)+"   "+pushtime.substring(0,10);
						time = pushtime.substring(0,10);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			return time;
	 }

	/**
	 * 描述：
	 * 检查网络连接是否正常
	 */
	public static boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) x.app().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
