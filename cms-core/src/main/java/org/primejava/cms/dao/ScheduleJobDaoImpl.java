package org.primejava.cms.dao;

import java.util.List;

import org.primejava.basic.dao.BaseDao;
import org.primejava.cms.model.ScheduleJob;
import org.springframework.stereotype.Repository;
@Repository("scheduleJobDao")
public class ScheduleJobDaoImpl extends BaseDao<ScheduleJob> implements ScheduleJobDao {

	@Override
	public int insertSelective(ScheduleJob record) {
		this.add(record);
		return 0;
	}

	@Override
	public ScheduleJob selectByPrimaryKey(String jobId) {
		return this.findById(jobId, ScheduleJob.class);
	}

	@Override
	public int updateByPrimaryKeySelective(ScheduleJob record) {
		this.update(record);
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ScheduleJob record) {
		this.update(record);
		return 0;
	}


	@Override
	public int deleteByPrimaryKey(String jobId) {
		return this.batchExecute("delete from ScheduleJob where id=? ",jobId);
	}

	@Override
	public List<ScheduleJob> findAll() {
		return this.list("from ScheduleJob");
	}

}
