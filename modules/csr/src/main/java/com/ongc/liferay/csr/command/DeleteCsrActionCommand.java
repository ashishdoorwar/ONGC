package com.ongc.liferay.csr.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ongc.liferay.csr.constants.CsrPortletKeys;
import com.ongc.liferay.csr.model.CSRProgramModel;
import com.ongc.liferay.csr.service.CsrService;
import com.ongc.liferay.csr.service.Impl.CsrServiceImpl;

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
			"javax.portlet.name=" +CsrPortletKeys.CSR,
			"mvc.command.name=delete_csr_program"
		},
		service = MVCActionCommand.class
	)
public class DeleteCsrActionCommand  extends BaseMVCActionCommand {

	private CsrService csrService=new CsrServiceImpl();
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		int id=ParamUtil.getInteger(actionRequest, "id");
		CSRProgramModel cSRProgramModel = csrService.findById(id);
	
	}

	
}
