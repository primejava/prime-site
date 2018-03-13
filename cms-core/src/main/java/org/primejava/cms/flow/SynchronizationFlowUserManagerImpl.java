package org.primejava.cms.flow;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.primejava.cms.model.Group;
import org.primejava.cms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 在创建或者删除用户或组的时候同步更新到流程的表中
 * @author liguo
 *
 */
@Component("synchronizationFlowUser")
public class SynchronizationFlowUserManagerImpl implements
		SynchronizationFlowUserManger {
    @Autowired
    protected IdentityService identityService;
    
	@Override
	public void saveOrUpdateUser(User user) {
		org.activiti.engine.identity.User identityUser = identityService.createUserQuery().userId(user.getId()).singleResult();
	    if(null==identityUser){
	           identityUser=identityService.newUser(user.getId());
	       }
	       identityUser.setFirstName(user.getUsername());
	       identityUser.setPassword(user.getPassword());
	       identityUser.setEmail(user.getEmail());
	       identityService.saveUser(identityUser);
	}

	@Override
	public void removeUser(String userId) {
		org.activiti.engine.identity.User identityUser = identityService.createUserQuery().userId(userId).singleResult();
        if(null!=identityUser){
            identityService.deleteUser(userId);
        }
	}

	@Override
	public void saveOrUpdateGroup(Group group) {
		org.activiti.engine.identity.Group identityGroup=identityService.createGroupQuery().groupId(group.getId()).singleResult();
	        if(null==identityGroup){
	            identityGroup=identityService.newGroup(group.getId());
	        }else{
	            List<org.activiti.engine.identity.User> users = identityService.createUserQuery().memberOfGroup(identityGroup.getId()).list();
	            for(org.activiti.engine.identity.User user:users){
	                identityService.deleteMembership(user.getId(), identityGroup.getId());
	            }
	        }
	        identityGroup.setName(group.getName());
	        identityGroup.setType(group.getGroupType());
	        identityService.saveGroup(identityGroup);
	        if(null!=group.getUsers()&&!group.getUsers().isEmpty()){
	            for(String userId:group.getUsers()){
	            	//在将系统中的用户添加到组中的时候，也会同步更新到流程表中
	                identityService.createMembership(userId, group.getId());
	            }
	        }
	}

	@Override
	public void removeGroup(Group group) {

	}

}
