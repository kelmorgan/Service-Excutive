/*---------------------------------------------------------------------------------------------------------------------------
	NEWGEN SOFTWARE TECHNOLOGIES LIMITED
	Group                        : Application -Projects
	Project/Product              : Stanbic Bank, Nigeria
	Application                  : iBPS
	Module                       : Disbursement
	File Name                    : Disbursement.java
	Author                       : Pritee
	Date (DD/MM/YYYY)            : 22/3/2018
	Description                  : This file consist of all the code to redirect the request to particular java according to workstep.
	------------------------------------------------------------------------------------------------------------------------------
	CHANGE HISTORY
	------------------------------------------------------------------------------------------------------------------------------
	Problem No/CR No   Change Date   Changed By         Change Description

----------------------------------------------------------------------------------------------------------------------------*/

package com.newgen.iforms.user;


import com.newgen.SEA.Appraisal_Window;
import com.newgen.SEA.Appraisee_workdesk;
import com.newgen.SEA.Commons;
import com.newgen.SEA.Exit;
import com.newgen.SEA.HCMD_Appraisal_Window;
import com.newgen.SEA.Appraiser_Initiation;
import com.newgen.SEA.Line_Manager_workdesk;

import com.newgen.SEA.Appraiser_Rework;
import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.omni.wf.util.excp.NGException;
public class SEA extends Commons implements IFormListenerFactory, Constants {

	

	@SuppressWarnings("unchecked")
	@Override
	public IFormServerEventHandler getClassInstance(IFormReference iFormObj) {
		IFormServerEventHandler objActivity = null;
	
		String sProcessName = iFormObj.getProcessName();
		String sActivity = iFormObj.getActivityName();
	

		try {
			if (sProcessName!=null && sProcessName.trim().equalsIgnoreCase("SEA")) {
				 if (sActivity!=null && sActivity.trim().equalsIgnoreCase(Appraiser_Initiation)) {
					objActivity = new Appraiser_Initiation();
				} else if (sActivity!=null && sActivity.trim().equalsIgnoreCase(Appraisee_workdesk)) {
					objActivity = new Appraisee_workdesk();
				} else if (sActivity!=null && sActivity.trim().equalsIgnoreCase(Appraisal_Window)) {
					objActivity = new Appraisal_Window();
				} else if (sActivity!=null && sActivity.trim().equalsIgnoreCase(HCMD_Appraisal_Window)) {
					objActivity = new HCMD_Appraisal_Window();
				} else if (sActivity!=null && sActivity.trim().equalsIgnoreCase(Line_Manager_workdesk)) {
					objActivity = new Line_Manager_workdesk();
				} else if (sActivity!=null && sActivity.trim().equalsIgnoreCase(Appraiser_Rework)) {
					objActivity = new Appraiser_Rework();
				} else if (sActivity!=null && (sActivity.trim().equalsIgnoreCase("Discard") || sActivity.trim().equalsIgnoreCase("Query")
						|| sActivity.trim().equalsIgnoreCase(Exit))) {
					objActivity = new Exit();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objActivity;
	}
}