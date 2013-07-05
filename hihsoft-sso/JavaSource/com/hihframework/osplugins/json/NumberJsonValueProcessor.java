package com.hihframework.osplugins.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @author hihsoft.co.,ltd
 *
 */
public class NumberJsonValueProcessor implements JsonValueProcessor {

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {   
        return process(value);   
    }   
  
    public Object processObjectValue(String key, Object value,   
        JsonConfig jsonConfig) {   
        return process(value);   
    }   
  
    private Object process(Object value) {   
        if(value==null)return "";  
        else return value;
    }   

}
