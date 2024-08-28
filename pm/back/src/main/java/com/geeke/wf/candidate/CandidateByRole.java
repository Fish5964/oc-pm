package com.geeke.wf.candidate;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.VariableScope;
import com.geeke.admin.dao.UserRoleDao;
import com.geeke.admin.entity.UserRole;
import com.geeke.camundaex.candidate.ICandidate;
import com.geeke.camundaex.entity.ActUser;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.utils.SpringUtils;
import com.google.common.collect.Lists;

/**
 * 获取角色对应用户作为审批后续人
 * @author 
 *
 */
public class CandidateByRole extends SpringUtils implements ICandidate {
	@Override
	public List<ActUser> execute(VariableScope variableScope, String roleId) {
		List<ActUser> actUsers = Lists.newArrayList();
		if(StringUtils.isNotBlank(roleId)) {
			UserRoleDao userRoleDao  = (UserRoleDao) getBean("userRoleDao");
			
	
			List<Parameter> params = Lists.newArrayList();
			
			PageRequest pageRequest;
	    	/*获取子表列表   用户角色*/       
	        params.add(new Parameter("role_id", "=", roleId));
	        pageRequest = new PageRequest(params);
	        
	        List<UserRole> users = userRoleDao.listAll(pageRequest);
	        if(users != null) {
	        	for(UserRole u: users) {
	        		actUsers.add(new ActUser(u.getUser().getId(), u.getUser().getName()));
	        	}
	        }
		}
		return actUsers;
	}
}
