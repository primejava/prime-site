package org.primejava.cms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.primejava.cms.model.QueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("queryDao")
public class QueryDaoImpl implements QueryDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<String> getColumnsByClass(Class<?> clz) {
		ClassMetadata classMetadata=getClassMetadata(clz);
		String[] s=classMetadata.getPropertyNames();
		List<String> reus=new ArrayList<String>(s.length);
		for(int i=0;i<s.length;i++){
			reus.add(s[i]);
		}
		return reus;
	}
	@Override
	public Map<String, String> getColumnsNameAndTypeByClass(Class<?> clz) {
		ClassMetadata classMetadata=getClassMetadata(clz);
		String[] s=classMetadata.getPropertyNames();
		Map<String, String> result=new HashMap<String, String>();
		for(int i=0;i<s.length;i++){
			Type type=classMetadata.getPropertyType(s[i]);
			result.put(s[i],type.getName());
		}
		return result;
	}
	

	@Override
	public List<QueryEntity> getQueryEntitysByClass(Class<?> clz) {
		 Map<String, String> map=getColumnsNameAndTypeByClass(clz);
		 List<QueryEntity> result=new ArrayList<QueryEntity>(map.size());
		 QueryEntity queryEntity=null;
		 List<String> operators=new ArrayList<String>();
		 operators.add("equal");
		 for(String key:map.keySet()){
			 String type=map.get(key);
			 queryEntity=new QueryEntity();
			 queryEntity.setId(key);
			 queryEntity.setLabel(key);
			 queryEntity.setType(type);
			 queryEntity.setOperators(operators);
			 //FIXME
			 if(key.equals("status")){
				 queryEntity.setInput("select");
			 }
			 if(type.equals("timestamp")){
				 continue;
			 }
			 result.add(queryEntity);
		 }
		return result;
	}
	
	private ClassMetadata getClassMetadata(Class<?> clz){
		ClassMetadata classMetadata=this.sessionFactory.getClassMetadata(clz);
		return classMetadata;
	}
	

	
}
