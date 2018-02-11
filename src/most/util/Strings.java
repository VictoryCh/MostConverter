package most.util;

public final class Strings {
	private Strings() {}
	
/*	  public static <T> T checkNotNull(T reference) {
		    if (reference == null) {
		      throw new NullPointerException();
		    }
		    return reference;
		  }*/
	
	public static String padStart(String string, int minLength, char padChar) {
//		checkNotNull(string);
		if (string.length() == minLength) {
			return string;
		} else if (string.length() > minLength) {
			return string.substring(0, minLength);
		}
	    StringBuilder sb = new StringBuilder(minLength);
	    for (int i = string.length(); i < minLength; i++) {
	      sb.append(padChar);
	    }
	    sb.append(string);
	    return sb.toString();
	  }
	
	public static String padEnd(String string, int minLength, char padChar) {
//		checkNotNull(string);
		 if (string.length() == minLength) {
			return string;
		} else if (string.length() > minLength) {
			return string.substring(0, minLength);
		}
		 
		StringBuilder sb = new StringBuilder(minLength);
		sb.append(string);
		for (int i = string.length(); i < minLength; i++) {
			sb.append(padChar);
		}
		return sb.toString();
	}
	
	public static String insert(String string, int offset, char c) {
		StringBuilder sb = new StringBuilder(string);
		
		sb.insert(offset, c);
		
		return sb.toString();
	}
	
	public static String insert(String string, int offset, String s) {
		StringBuilder sb = new StringBuilder(string);
		
		sb.insert(offset, s);
		
		return sb.toString();
	}
	
	public static boolean isDigit(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
}
