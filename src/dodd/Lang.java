package dodd;

import it.unimi.dsi.fastutil.objects.*;

public class Lang {
	
	private static final Object2ObjectMap<String, Object2ObjectMap<String, String>> LOCALE_MAP = new Object2ObjectOpenHashMap<>();
	
	public static String locale_name;
	
	public static String localize(String format, Object... args) {
		return String.format(getLocalized(format), args);
	}
	
	public static Object2ObjectMap<String, String> getLocale() {
		Object2ObjectMap<String, String> locale = LOCALE_MAP.get(locale_name);
		if (locale == null) {
			locale = new Object2ObjectOpenHashMap<>();
			LOCALE_MAP.put(locale_name, locale);
		}
		return locale;
	}
	
	private static String getLocalized(String format) {
		return getLocale().getOrDefault(format, format);
	}
}
