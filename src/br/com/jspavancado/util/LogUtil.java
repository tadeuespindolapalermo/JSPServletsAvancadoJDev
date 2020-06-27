package br.com.jspavancado.util;

import org.apache.log4j.Logger;

public class LogUtil {
	
	public static Logger getLogger(Object object) {
		return Logger.getLogger(object.getClass());		
	}

}
