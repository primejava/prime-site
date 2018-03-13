package org.primejava.cms.flow;


import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primejava.cms.model.Group;
import org.primejava.cms.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("assignUserListener")
public class AssignHandlerTaskUserListener implements TaskListener{

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long       serialVersionUID = 1L;

    private final Logger            LOGGER           = Logger.getLogger(AssignHandlerTaskUserListener.class);

    private Expression              groupType;
    @Autowired
    private GroupService       groupService;

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @author xs
     * @param delegateTask
     * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
     */

    @Override
    public void notify(DelegateTask delegateTask) {
        // TODO Auto-generated method stub
        if (null == groupType || StringUtils.isBlank(groupType.getExpressionText())) {
            return;
        }
        System.out.println("调用监听器。。。。"+groupType);
        List<Group> groups = groupService.findUserGroupByType(groupType.getExpressionText());
        if (groups.isEmpty()) {
            return;
        }
        List<String> groupIds = new ArrayList<String>();
        for (Group group : groups) {
            groupIds.add(group.getId().toString());
        }
        LOGGER.info("flow process [" + delegateTask.getProcessInstanceId() + "] assign user group :" + groupIds);
        
        delegateTask.addCandidateGroups(groupIds);
    }
}
