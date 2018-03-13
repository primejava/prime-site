package org.primejava.cms.dao;

import java.util.List;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.cms.model.ScheduleJob;

public interface ScheduleJobDao extends IBaseDao<ScheduleJob>{
	int deleteByPrimaryKey(String jobId);

	int insertSelective(ScheduleJob record);

	ScheduleJob selectByPrimaryKey(String jobId);

	int updateByPrimaryKeySelective(ScheduleJob record);

	int updateByPrimaryKey(ScheduleJob record);

	List<ScheduleJob> findAll();

}