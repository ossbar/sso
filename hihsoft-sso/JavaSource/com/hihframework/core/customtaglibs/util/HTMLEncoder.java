/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.customtaglibs.util;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.log4j.Logger;

/**
 * <p> Title:处理html解释的辅助类 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class HTMLEncoder {
	private static Logger log = Logger.getLogger(HTMLEncoder.class);
   public HTMLEncoder() {
   }

   public static String encode(String str) {
      if (str == null) {
         return null;
      }
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < str.length(); i++) {
         char c = str.charAt(i);
         if (c == '<' || c == '>' || c == '\'' || c == '&') {
            sb.append(String.valueOf(String.valueOf( (new StringBuffer("&#")).append(Integer.toString(c)).append(';'))));
         } else {
            sb.append(c);
         }
      }
      return sb.toString();
   }

   public static String decode(String str) {
      StringBuffer sb = new StringBuffer();
      try {
         for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '&') {
               StringBuffer strChar = new StringBuffer();
               c = str.charAt(++i);
               for (c = str.charAt(++i); c != ';'; c = str.charAt(++i)) {
                  strChar.append(c);
               }
               log.info(strChar.toString());
               char cc = (char) Integer.parseInt(strChar.toString());
               sb.append(cc);
            } else {
               sb.append(c);
            }
         }
      }
      catch (Exception exception) {}
      return sb.toString();
   }

   public static String encodeUrlParameters(Hashtable params) {
      StringBuffer s = new StringBuffer();
      Enumeration keys = params.keys();
      int i=0;
      while (keys.hasMoreElements()) {
         String key = (String) keys.nextElement();
         if(key.equals("action")){
        	 s.insert(0,params.get(key));
         }
         else{
	         if(i == 0){
	        	 s.append('?');
	         }
	         else{
	        	 s.append('&');
	         }
	         s.append(key+"="+params.get(key));
	         i++;
         }
         
        // if(s.indexOf(key)<0){
        	// s = String.valueOf(String.valueOf( (new StringBuffer("?")).append(key).append("=").append((String) params.get(key))));
        // }
      }
//      while (keys.hasMoreElements()) {
//         String key = (String) keys.nextElement();
//	       //  if(s.indexOf(key)<0){
//		         s = String.valueOf(s) +
//		         String.valueOf(String.valueOf(String.valueOf( (new StringBuffer("&")).append(key).append("=").append( (String) params.get(key)))));
//	       //  }  
//         }
      return s.toString();
   }

   public static String U2C(String s) {
      try {
         if (s == null) {
            return "";
         }
         byte[] u = s.getBytes("iso-8859-1");
         return new String(u, "GB2312");
      }
      catch (java.io.UnsupportedEncodingException e) {
         e.printStackTrace();
         return "";
      }
   }

}
