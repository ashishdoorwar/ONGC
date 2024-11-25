package com.ongc.liferay.passion.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.passion.constants.PassionPortletKeys;
import com.ongc.liferay.passion.dao.Impl.GroupDaoImpl;

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
				"mvc.command.name=deleteGroup"
		},
		service = MVCActionCommand.class
		)
public class DeleteGroupActionCommand extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String gid=ParamUtil.getString(actionRequest, "gid");
		GroupDaoImpl daoImpl=new GroupDaoImpl();
		daoImpl.deleteGroup(gid);
	}

}
