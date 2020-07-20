package com.fm.designstar.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class Tool{
	/** 是否大于2.3，大于2.3才让adapter用自己的onClickListener */
	public final static boolean VERSION_UP_GINGERBREAD_MR_1;
	
	static {
		VERSION_UP_GINGERBREAD_MR_1 = Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1;
	}

	private static final char hexDigits[] = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static final String MD5(final String s) {
		final String MD5 = "MD5";
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance(MD5);
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			char[] str = new char[32];
			for (int i = 0, j = 0; i < 16; i++) {
				byte b = messageDigest[i];
				str[j++] = hexDigits[b >>> 4 & 0xf];
				// 取字节中低 4 位的数字转换
				str[j++] = hexDigits[b & 0xf];
			}
			return new String(str);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 转32位大写MD5
	public final static String get32MD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			Log.e("Tool", "NoSuchAlgorithmException:" + e.toString());
		} catch (UnsupportedEncodingException e) {
			Log.e("Tool", "UnsupportedEncodingException:" + e.toString());
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			}else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}

	// 小数进位
	public static double carryAigit(float number) {
		return Math.ceil(number);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	/***
	 * sp转px
	 * @param
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    }

	// 检查银行卡号
	public static boolean checkBankCard(String cardId) {
		char bit = getBankCardCheckCode(cardId
				.substring(0, cardId.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;
	}

	public static char getBankCardCheckCode(String nonCheckCodeCardId) {
		if (nonCheckCodeCardId == null
				|| nonCheckCodeCardId.trim().length() == 0
				|| !nonCheckCodeCardId.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

	// 微信分享使用
	public static byte[] bmpToByteArray(final Bitmap bmp,
			final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	// 金额由10000分转为100.00元
	public static String toDeciDouble(String num) {
		if (isBlank(num)) {
			return "0.00";
		}
		if(num.contains(".")){
			num = num.substring(0, num.indexOf("."));
		}
		int len = num.length();
		if (len==2 && num.startsWith("-")) {
			return num.substring(0, 1)  +"0.0"+ num.substring(len - 1);
		}else if (len==3 && num.startsWith("-")) {
			return num.substring(0, 1)  +"0."+ num.substring(len - 2);
		}else if (len == 1) {
			return "0.0" + num;
		} else if (len == 2) {
			return "0." + num;
		} else {
			return num.substring(0, len - 2) + "." + num.substring(len - 2);
		}
	}

    /**
     * 转两位小数 单位：元
     * @param moneyInY
     * @return
     */
    public static String toDeciDouble2(double moneyInY) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(moneyInY);
    }
	/**
	 * 转两位小数 四舍5入 单位：元
	 * @param moneyInY
	 * @return
	 */
	public static String toDeciDoubleHalf(double moneyInY) {
		DecimalFormat df = new DecimalFormat("#0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(moneyInY);
	}
    /***
     * 转一位小数（留下一位小数，但是不会四舍五入）
     * @param moneyInY
     * @return
     */
    public static float toDeciDouble1(float moneyInY) {
    	DecimalFormat df = new DecimalFormat("#0.0");
    	float val = 0.0f;
    	try {
			
    		val = Float.parseFloat(df.format(moneyInY));
			
		} catch (Exception e) {
		}
    	return val; 
    }
	// 金额由10000分转为100元
	public static String toIntAccount(String num) {
		if (isBlank(num)) {
			return "0";
		}
		return "" + Long.parseLong(num) / 100;
	}
	
	// 金额由10000分转为100元
	public static long toIntAccountLong(String num) {
		if (isBlank(num)) {
			return 0;
		}
		return Long.parseLong(num) / 100;
	}

	/** 金额由1000000分转为10,000元 */
	public static String toDivAccount(String num) {
		if (isBlank(num)) {
			return "0";
		}
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(Long.parseLong(num) / 100);
	}

	// 金额由10000.00转为10,000.00元
	public static String toDivAccount2(String num) {
		if (isBlank(num)) {
			return "0";
		}
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(Double.parseDouble(num));
	}
	
	// 可以适配00的情况
	public static String toDivAccount3(String num) {
		if (isBlank(num)) {
			return "0";
		}
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(Double.parseDouble(num));
	}

	public static String toFFDouble(double num) {
		if (Double.isNaN(num)|| Double.isInfinite(num)) {
			return "0.00";
		}
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		BigDecimal bd = new BigDecimal(num);
		num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return toDeciDouble(nf.format(num * 100));
	}

	// 将输入的数字转为0.00格式的double
	public static String toForDouble(String num) {
		if (Tool.isBlank(num)) {
			return "0.00";
		}
		return (new DecimalFormat("0.00")).format(Double.parseDouble(num));
	}

	// 将输入的数字转为0.00格式的double
	public static Double toForDouble2(String num) {
		if (Tool.isBlank(num)) {
			return 0.00;
		}
		return Double.parseDouble((new DecimalFormat("0.00")).format(Double.parseDouble(num)));
	}

	public static String trimHeadZero(String num) {
		if (isBlank(num)) {
			return "0";
		} else {
			return num.replaceFirst("^0*", "");
		}
	}
	// 判断字符串对象为null或者""
	public static boolean isBlank(String str) {
		return (str == null || str.isEmpty() || "null".equals(str));
	}

	public static List<String> toList(JSONArray arr) {
		List<String> str_list = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++) {
			try {
				str_list.add(arr.getString(i));
			} catch (JSONException e) {
				Log.e("Tool", "Failed To List");
				return str_list;
			}
		}
		return str_list;
	}
	/**
	 * 判断是否连续点击
	 *
	 *  对于 startActivity 设置 singletop 无效果
	 *  则这样 防止 连续点击跳重复界面
	 */
	private static long lastClickTime;
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 500) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
	public static boolean isFastDoubleClicktwo() {
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 1500) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
	public static void showLog(String tag, String result){
		int start;
		int end;
		int length=4000;
		if (result.length()<length){
			Log.e(tag,result);
		}else{
			int multiple = result.length()/length;
			for (int i = 0; i < multiple; i++) {
				start=i*length;
				end=i*length+length;
				Log.e(tag,result.substring(start,end));
			}
			Log.e(tag,result.substring(result.length()-result.length()%length,result.length()));
		}
	}
	/**
	 * 添加日历事件、日程
	 *
	 * @param mActivity     上下文
	 */
	/*public static void  closeKeyboard( Activity mActivity){
		InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
		assert imm != null;
		imm.hideSoftInputFromWindow(Objects.requireNonNull(mActivity.getCurrentFocus()).getWindowToken(), 0);
	}*/

	/********
	 * 判断相机是否可用
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isCamareAviable(Context context)
	{
		boolean flag = false;
		try {
			Camera camera = null;
			camera = Camera.open();
			camera.release();
			flag = true;

		} catch (Exception e) {
		}
		return flag;
	}


	/**
	 * 开启倒计时
	 *
	 * @param time 倒计时时间  单位秒
	 */
	public static Subscription countTime(final int time,Subscriber<Integer> subscriber) {
		//第一个参数延时时间，第二个参数间隔时间，第三个参数时间单位
		Subscription subscription= Observable.interval(0, 1, TimeUnit.SECONDS) //从0开始，每间隔1秒+1
				.observeOn(AndroidSchedulers.mainThread())
				.map(new Func1<Long, Integer>() {
					@Override
					public Integer call(Long aLong) {
						return time - aLong.intValue();//通过操作符变成从60秒，每秒减去返回的值；
					}
				}).take(time+1)//取前60个数字，1-60；
				.subscribe(subscriber);
		return subscription;
	}
}
