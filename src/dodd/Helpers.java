package dodd;

import java.io.IOException;
import java.math.*;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Helpers {
	
	public static String line() {
		try {
			return Main.READER.readLine();
		}
		catch (IOException e) {}
		return null;
	}
	
	public static String pad(String str, int length, char fill) {
		int len = str.length();
		if (len == length) {
			return str;
		}
		else if (len > length) {
			return str.substring(0, length);
		}
		else {
			return str.concat(new String(new char[length - str.length()]).replace('\0', fill));
		}
	}
	
	public static int clamp(int number, int min, int max) {
		return number < min ? min : (number > max ? max : number);
	}
	
	public static long clamp(long number, long min, long max) {
		return number < min ? min : (number > max ? max : number);
	}
	
	public static float clamp(float number, float min, float max) {
		return number < min ? min : (number > max ? max : number);
	}
	
	public static double clamp(double number, double min, double max) {
		return number < min ? min : (number > max ? max : number);
	}
	
	public static String sigFigs(double number, int sigFigs) {
		if (number == (long) number) {
			return Long.toString((long) number);
		}
		if (Double.isInfinite(number) || Double.isNaN(number)) {
			return Double.toString(number);
		}
		BigDecimal bd = new BigDecimal(number);
		bd = bd.round(new MathContext(Math.max(1, sigFigs)));
		return Double.toString(bd.doubleValue());
	}
	
	public static String decimalPlaces(double number, int places) {
		if (number == (long) number) {
			return Long.toString((long) number);
		}
		if (Double.isInfinite(number) || Double.isNaN(number)) {
			return Double.toString(number);
		}
		char[] arr = new char[Math.max(1, places)];
		Arrays.fill(arr, '#');
		DecimalFormat df = new DecimalFormat("0." + new String(arr));
		return df.format(number);
	}
	
	public static String pcSigFigs(double number, int sigFigs) {
		return sigFigs(100 * number, sigFigs) + "%";
	}
	
	public static String pcDecimalPlaces(double number, int places) {
		return decimalPlaces(100 * number, places) + "%";
	}
}
