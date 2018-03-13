package org.primejava.cms.service;

import java.util.List;

import org.primejava.cms.model.ScheduleJob;
import org.quartz.SchedulerException;

public interface JobTaskService {

	List<ScheduleJob> getAllTask();

	void addTask(ScheduleJob job);

	ScheduleJob getTaskById(String jobId);

	void changeStatus(String jobId, String cmd) throws SchedulerException;

	void updateCron(String jobId, String cron) throws SchedulerException;

	void addJob(ScheduleJob job) throws SchedulerException;

	List<ScheduleJob> getAllJob() throws SchedulerException;

	List<ScheduleJob> getRunningJob() throws SchedulerException;

	void pauseJob(ScheduleJob scheduleJob) throws SchedulerException;

	void resumeJob(ScheduleJob scheduleJob) throws SchedulerException;

	void deleteJob(ScheduleJob scheduleJob) throws SchedulerException;

	void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException;

	void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException;

}
