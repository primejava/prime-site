package org.primejava.cms.dao;

import java.util.List;
import java.util.Map;

import org.primejava.cms.model.QueryEntity;

public interface QueryDao {
	List<String> getColumnsByClass(Class<?> clz);
	Map<String,String> getColumnsNameAndTypeByClass(Class<?> clz);
	List<QueryEntity> getQueryEntitysByClass(Class<?> clz);
}
