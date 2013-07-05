package com.hihframework.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hihsoft.sso.business.model.TsysParameter;
import com.hihsoft.sso.business.service.TsysParameterService;

/**
 * 系统参数帮助类
 * 
 * @author hihsoft.co.,ltd
 * @since 2013-5-28
 */
public class ParamUtil {
	
	private TsysParameterService tsysParameterService;
	private static List<TsysParameter> params = new ArrayList<TsysParameter>();
	private static Map<String, List<TsysParameter>> parsed = new HashMap<String, List<TsysParameter>>();
	private static ParamUtil instance;

	private ParamUtil() {
		tsysParameterService = (TsysParameterService) SpringBeanLoader.getBean("tsysParameterService");
	}
	
	public static ParamUtil getInstance() {
		if (instance == null) {
			instance = new ParamUtil();
		}
		return instance;
	}

	private void initIfEmpty() {
		if (parsed.isEmpty())
			instance.updateParams();
	}

	/**
	 * 按内部值查找显示值
	 * 
	 * @param val
	 * @param codeName
	 * @return
	 * @author
	 * @since 2011-5-28
	 */
	public String getKeyByVal(String codeType, String val) {
		initIfEmpty();
		if (StringHelpers.isNull(val))
			return val;
		List<TsysParameter> list = parsed.get(codeType);
		if (list == null)
			return null;
		for (TsysParameter p : list) {
			if (p.getParano().equals(val)) {
				return p.getParaKey();
			}
		}
		return null;
	}

	/**
	 * 按显示值查找内部值
	 * 
	 * @param key
	 * @param codeName
	 * @return
	 * @author
	 * @since 2011-5-28
	 */
	public String getValByKey(String codeType, String key) {
		initIfEmpty();
		if (StringHelpers.isNull(key))
			return key;
		List<TsysParameter> list = parsed.get(codeType);
		if (list == null)
			return null;
		for (TsysParameter p : list) {
			if (p.getParaKey().equals(key)) {
				return p.getParano();
			}
		}
		return null;
	}

	/**
	 * 取某一类型的编码
	 * 
	 * @param type
	 * @return
	 * @author Xiaojf
	 * @since 2011-6-20
	 */
	public List<TsysParameter> getByType(String type) {
		initIfEmpty();
		return parsed.get(type);
	}

	/**
	 * 将所有编码按类型分类
	 * 
	 * @param params
	 * @return
	 * @author Xiaojf
	 * @since 2011-5-28
	 */
	private void parseList(List<TsysParameter> params) {
		for (TsysParameter p : params) {
			String type = p.getParaType();
			List<TsysParameter> list = parsed.get(type);
			if (list == null) {
				list = new ArrayList<TsysParameter>();
			}
			list.add(p);
			parsed.put(type, list);
		}
	}

	/**
	 * 更新缓存
	 * 
	 * @author Xiaojf
	 * @since 2011-9-15
	 */
	@SuppressWarnings("unchecked")
	public void updateParams() {
		params.clear();
		parsed.clear();
		params.addAll(tsysParameterService.getAllTsysParameter());
		parseList(params);
	}

}
