package com.yc.util;

/**
 * 判空工具类
 * @author 外哥
 *
 */
public class StringUtil {
	public static boolean checkNull( String ... strs ) {
		if ( strs == null || strs.length <= 0  ) {
			 return true ;
		}
		
		for (String str : strs) {
			if ( str == null || "".equals(str)) {
				return true ;
			}
		}
		return false ;
	}
}
