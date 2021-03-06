package com.newgen.SEA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import com.newgen.iforms.user.Constants;

import org.apache.log4j.Logger;

import com.newgen.SEA.SocketService;
import com.newgen.SEA.XMLParser;
//import com.newgen.CI.Commons;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;

public class Appraisee_workdesk extends Commons implements IFormServerEventHandler, Constants {

	@Override
	public void beforeFormLoad(FormDef arg0, IFormReference iFormRef) {
		logger.info("--beforeFormLoad COSM--");
		
		iFormRef.setStyle("frame23", "disable", "true");
		iFormRef.setStyle("frame22", "disable", "true");
		iFormRef.setStyle("frame24", "disable", "true");
		iFormRef.setStyle("frame27", "disable", "true");
//		iFormRef.setStyle("frame10", "disable", "true");
//		iFormRef.setStyle("frame20", "disable", "true");
//		iFormRef.setStyle("frame16", "disable", "true");
		
		String query = "SELECT USER_ID, SOLE_ID, BRANCH_NAME FROM USR_0_FBN_USR_BRANCH_MAPPING WHERE UPPER(USER_ID)=UPPER('" + iFormRef.getUserName() + "')";
        
        logger.info(query);



    List<List<String>> getGeneralDetails = iFormRef.getDataFromDB(query);

    	iFormRef.setValue("userID", iFormRef.getUserName());
        
        
    	String branchName = getGeneralDetails.get(0).get(2);
        logger.info("branch name: " + branchName);
        iFormRef.setValue("userBranch", branchName);
        
        
        String branchId = getGeneralDetails.get(0).get(1);
        iFormRef.setValue("solID", branchId);
        logger.info("this is branch id: "+branchId );

	}

	@Override
	public String executeCustomService(FormDef arg0, IFormReference arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray executeEvent(FormDef arg0, IFormReference arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String executeServerEvent(IFormReference iFormRef, String sControlName, String sEventName, String sData) {
		try {   
			
		switch (sEventName) {
		

		case CLICK:		
		break;
		
		case ONLOSTFOCUS:
		break;
		
		case FORMLOAD: {
	
			}
			break;

		case ONCHANGE:
			break;
			
		case CUSTOM: {
			switch (sControlName) {
			
		case "checkdisclaimer":{
			String checkbox = (String) iFormRef.getValue("checkbox10");
			  if (checkbox.equalsIgnoreCase("False") || checkbox.equalsIgnoreCase("") || checkbox.equalsIgnoreCase(null))  { 
				  return "Kindly Acknowledge and Agree to the Disclaimer Content by Checking the box";	
            }
		}
					
		case "checkcloseDateApp":{
			boolean check = checkCloseDate(iFormRef);
			boolean checkd = checkwindowstatusIni(iFormRef);
			if ((check == false) || (checkd==false)){	
				iFormRef.setStyle("frame28", "disable", "true");
				iFormRef.setStyle("frame26", "disable", "true");
				return "Appraisal Window is CLOSED; Kindly contact HCMD for further Enquiries";	
			}
			else {
				iFormRef.setStyle("frame28", "disable", "false");
				iFormRef.setStyle("frame26", "disable", "false");
			}
			break;
		}
		
		case "checkcloseDateAppSubmit":{
			String decision = (String)iFormRef.getValue("decison");
	     	if (decision.equalsIgnoreCase("Submit")||decision.equalsIgnoreCase("Return")) {
			boolean check = checkCloseDate(iFormRef);
			boolean checkd = checkwindowstatusIni(iFormRef);
			if ((check == false) || (checkd==false)){	
				iFormRef.setStyle("frame28", "disable", "true");
				iFormRef.setStyle("frame26", "disable", "true");
				return "Appraisal Window is CLOSED; Kindly contact HCMD for further Enquiries";	
			}
			else {
				iFormRef.setStyle("frame28", "disable", "false");
				iFormRef.setStyle("frame26", "disable", "false");
			}
			break;
		}
		
		}
			}
		}
		case ONDONE: 
		break; 
	case ONLOAD: {
		
	}
	break;
		
		}	
		
	}	catch (Exception ex) {

	}

return null;

}


	@Override
	public String getCustomFilterXML(FormDef arg0, IFormReference arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String setMaskedValue(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return paramString2;
	}

	@Override
	public JSONArray validateSubmittedForm(FormDef arg0, IFormReference arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
