package org.primejava.cms.flow;

import org.primejava.cms.model.Group;
import org.primejava.cms.model.User;

public interface SynchronizationFlowUserManger {
	 
    /**
     * 保存或更新用户数据. <br/>
     * @author liguo
     * @param user
     */
    void saveOrUpdateUser(User user);
    
    /**
     * 删除用户数据. <br/>
     * @author liguo
     * @param userId
     */
    void removeUser(String userId);
    
    /**
     * 保存或更新用户组
     * @author liguo
     * @param group
     */
    void saveOrUpdateGroup(Group group);
    
    /**
     * 删除用户组数据
     * @author liguo
     * @param groupId
     */
    void removeGroup(Group group);
}
