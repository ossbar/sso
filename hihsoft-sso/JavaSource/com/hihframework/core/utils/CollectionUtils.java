package com.hihframework.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p> Title:集合操作的Utils函数集合 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public class CollectionUtils {

	private CollectionUtils() {
	}

	/**
	 * 提取集合中的对象的属性(通过Getter函数), 组合成Map.
	 * add by zhujw 集合转换成map
	 * @param collection 来源集合.
	 * @param keyPropertyName 要提取为Map中的Key值的属性名.
	 * @param valuePropertyName 要提取为Map中的Value值的属性名.
	 */
	public static Map fetchPropertyToMap(final Collection collection,
			final String keyPropertyName, final String valuePropertyName)
			throws Exception {
		Map map = new HashMap();
		for (Object obj : collection) {
			map.put(PropertyUtils.getProperty(obj, keyPropertyName),
					PropertyUtils.getProperty(obj, valuePropertyName));
		}
		return map;
	}

	/**
	 * 提取集合中的对象的属性,组合成列表.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List fetchPropertyToList(Collection collection,
			String propertyName) throws Exception {

		List list = new ArrayList();

		for (Object obj : collection) {
			list.add(PropertyUtils.getProperty(obj, propertyName));
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性,组合成由分割符分隔的字符串.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String fetchPropertyToString(Collection collection,
			String propertyName, String separator) throws Exception {
		List list = fetchPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 根据对象ID集合,整理合并集合.
	 * 整理算法为：在源集合中删除不在ID集合中的元素,创建在ID集合中的元素并对其ID属性赋值并添加到源集合中.
	 * 多用于根据http请求中的id列表，修改对象所拥有的子对象集合.
	 * 
	 * @param collection
	 *            源对象集合
	 * @param checkedIds
	 *            目标集合
	 * @param clazz
	 *            集合中对象的类型
	 */
	public static <T, ID> void mergeByCheckedIds(Collection<T> collection,
			Collection<ID> checkedIds, Class<T> clazz) throws Exception {
		mergeByCheckedIds(collection, checkedIds, "id", clazz);
	}

	/**
	 * 根据对象ID集合,整理合并集合.
	 * http请求发送变更后的子对象id列表时，hibernate不适合删除原来子对象集合再创建一个全新的集合
	 * 需采用以下整合的算法：
	 * 在源集合中删除不在ID集合中的元素,创建在ID集合中的元素并对其ID属性赋值并添加到源集合中.
	 * 
	 * @param collection
	 *            源对象集合
	 * @param checkedIds
	 *            目标集合
	 * @param idName
	 *            对象中ID的属性名
	 * @param clazz
	 *            集合中对象的类型
	 */
	public static <T, ID> void mergeByCheckedIds(Collection<T> collection,
			Collection<ID> checkedIds, String idName, Class<T> clazz)
			throws Exception {

		if (checkedIds == null) {
			collection.clear();
			return;
		}

		Iterator<T> it = collection.iterator();

		while (it.hasNext()) {
			T obj = it.next();
			if (checkedIds.contains(PropertyUtils.getProperty(obj, idName))) {
				checkedIds.remove(PropertyUtils.getProperty(obj, idName));
			} else {
				it.remove();
			}
		}

		for (ID id : checkedIds) {
			T obj = clazz.newInstance();
			PropertyUtils.setProperty(obj, idName, id);
			collection.add(obj);
		}
	}
}
