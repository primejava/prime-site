package org.primejava.cms.flow;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.log4j.Logger;
import org.primejava.cms.notice.service.NoticeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope("prototype")
@Component(value = "flowNoticeService")
public class FlowNoticeServiceImpl implements TaskListener{

    private static Logger LOGGER = Logger.getLogger(FlowNoticeServiceImpl.class);

    @Autowired
    private NoticeMessage noticeMessage;


    @Override
    public void notify(DelegateTask delegateTask) {
    	System.out.println("调用发送邮件的监听器。。。");
        String noticeId = (String) delegateTask.getVariableLocal(FlowContants.MESSAGE);
        noticeMessage.setNoticeId(noticeId);
        noticeMessage.execute();
    }
}
