package org.primejava.basic.model;

public class SystemContext {

	private static ThreadLocal<String> realPath = new ThreadLocal<String>();

	public static String getRealPath() {
		return realPath.get();
	}

	public static void setRealPath(String _realPath) {
		SystemContext.realPath.set(_realPath);
	}

	public static void removeRealPath() {
		realPath.remove();
	}

}
