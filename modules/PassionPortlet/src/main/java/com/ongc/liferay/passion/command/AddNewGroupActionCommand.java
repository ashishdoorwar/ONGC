package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.UserDao;
import com.ongc.liferay.passion.dao.Impl.GroupDaoImpl;
import com.ongc.liferay.passion.dao.Impl.UserDaoImpl;
import com.ongc.liferay.passion.model.Group;
import com.ongc.liferay.passion.model.User;
import com.ongc.liferay.passion.service.UserService;
import com.ongc.liferay.passion.service.Impl.UserServiceImpl;
import com.ongc.liferay.passion.util.CommonUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 *  
 * @author Ranjeet
 */
@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" +PassionPortletKeys.PASSION,
				"mvc.command.name=add_new_group"
		},
		service = MVCActionCommand.class
		)
public class AddNewGroupActionCommand extends BaseMVCActionCommand {

	UserService userDao = new UserServiceImpl(); 
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		User user =userDao.getUser();
		String groupName=ParamUtil.getString(actionRequest, "groupName");
		String members=ParamUtil.getString(actionRequest, "members");
		
		boolean checkParameter = CommonUtil.checkParameter(actionRequest);
		if(checkParameter) {
			SessionErrors.add(actionRequest,"error");
			actionResponse.setRenderParameter("mvcPath", "/jsp/addNewGroup.jsp");
		}
		else {
		GroupDaoImpl gDao=new GroupDaoImpl();
		Group group=new Group();
		group.setGroupName(groupName);
		
		group.setMembers(new String[]{members});
		gDao.insertNewGroup(user, group);
			
		}
	}

}
