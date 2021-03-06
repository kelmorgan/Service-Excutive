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


public class Line_Manager_workdesk extends Commons implements IFormServerEventHandler, Constants {

	@Override
	public void beforeFormLoad(FormDef arg0, IFormReference iFormRef) {
		logger.info("--beforeFormLoad Line Manager--");
		iFormRef.setStyle("frame23", "disable", "true");
		iFormRef.setStyle("frame22", "disable", "true");
		iFormRef.setStyle("frame24", "disable", "true");
		iFormRef.setStyle("frame27", "disable", "true");
		iFormRef.setStyle("frame28", "visible", "false"); 

		
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
