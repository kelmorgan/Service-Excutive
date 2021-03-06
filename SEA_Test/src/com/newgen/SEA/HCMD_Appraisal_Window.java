package com.newgen.SEA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.iforms.user.Constants;
import com.newgen.mvcbeans.model.WorkdeskModel;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;

public class HCMD_Appraisal_Window extends Commons implements IFormServerEventHandler, Constants {
	public static Logger logger = LogGEN.getLoggerInstance(HCMD_Appraisal_Window.class);

	@Override
	public void beforeFormLoad(FormDef arg0, IFormReference iFormRef) {
		logger.info("--beforeFormLoad Appraisal_Window--");
		WDGeneralData wdgeneralObj = iFormRef.getObjGeneralData();
		String activityname =wdgeneralObj.getM_strActivityName();
    
		try {
			Date d= new Date();		
			logger.info("--beforeFormLoad--"); 
			
			iFormRef.setValue("EntryDateTime", wdgeneralObj.getM_strCreatedDateTime());
			iFormRef.setValue("curr_ws", wdgeneralObj.getM_strActivityName());
		
			String query = "SELECT USER_ID, SOLE_ID, BRANCH_NAME, USER_NAME FROM USR_0_FBN_USR_BRANCH_MAPPING WHERE UPPER(USER_ID)=UPPER('" + iFormRef.getUserName() + "')";
           
		            
            logger.info(query);

        List<List<String>> getGeneralDetails = iFormRef.getDataFromDB(query);
        	

        	iFormRef.setValue("userID", iFormRef.getUserName());          
            
            String branchId = getGeneralDetails.get(0).get(1);
            iFormRef.setValue("solID", branchId);
            logger.info("this is branch id: "+branchId );
            
            
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//			logger.info("Appraisal Window  --> beforeformload  "+sdf);
//			
//			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//			logger.info("Appraisal Window  --> beforeformload  "+sdf);
//            
//            String todaydate=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
//    		logger.info("Appraisal Window --> todays date ::"+todaydate); 
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			logger.info("Appraisal Window  --> beforeformload  "+sdf);
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			logger.info("Appraisal Window  --> beforeformload  "+sdf);
            
            String todaydate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
    		logger.info("Appraisal Window --> todays date ::"+todaydate); 
            
    		
    		String querystatus = "select closeDate from ext_sea where " 
            		+" windowstatus IS NOT NULL and openDate IS NOT NULL "
            		+" and closeDate is not null AND currentDate IS NOT NULL " 
            		+" ORDER BY currentDate DESC ";
           
            logger.info("Appraisal Window --> querystatus ::"+querystatus); 
            
            List<List<String>> data=new ArrayList<List<String>>();
			data=iFormRef.getDataFromDB(querystatus);
			logger.info("Appraisal Window --> data ::"+data); 
			logger.info("Appraisal Window--> data.size ::"+data.size());
			String closeddatetime="";
    		if(!data.isEmpty())
    		{
    			closeddatetime=data.get(0).get(0);
    			logger.info("Appraisal Window--> closeddatetime ::"+closeddatetime); 	
    			
    			Date todayDate = sdf.parse(todaydate);
    			logger.info("Appraisal Window  --> ExecuteSeverEvent--> Todays date formatted "+todayDate);
    			
    			Date closeDate = sdf2.parse(closeddatetime);
    			logger.info("Appraisal Window  --> ExecuteSeverEvent--> Close Date Formatted "+closeDate);
    			
    			int diff = todayDate.compareTo(closeDate);
    			logger.info("Appraisal Window  --> ExecuteSeverEvent--> Date difference"+diff);
    			
    			if(diff > 0) {
    				
    				iFormRef.setStyle("statustype", "visible", "true");
    				iFormRef.setValue("statustype", "Update Appraisal Window Date and Time");
    				iFormRef.setStyle("frame49", "disable", "true");
    				iFormRef.setStyle("openDate", "disable", "true");
	    			logger.info("Appraisal Window--> ::"+closeDate +" closed Day Time has elapsed"); 	
	    			
	    		}
	    		else
	    		{
	    			iFormRef.setStyle("statustype", "visible", "true");
    				iFormRef.setValue("statustype", "Update Appraisal Window Date and Time");
    				iFormRef.setStyle("frame49", "disable", "true");
    				iFormRef.setStyle("openDate", "disable", "false");
	    			logger.info("Appraisal Window---> Time still intact:"+closeDate ); 	
		    	
	    		}
    		}
            
            
            
		} catch (Exception ex) {
			logger.info("Exception in beforeFormLoad:" + ex.toString() + " \nStack trace:" + ex.getStackTrace());
		}
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
	public String executeServerEvent(IFormReference ifr, String sControlName, String sEventName, String sData) {

		try {   
			logger.info("--executeServerEvent--");
			logger.info("sEventName-->" + sEventName);
			logger.info("sControlName-->" + sControlName);
			logger.info("sData-->" + sData);

		switch (sEventName) {	

		case CLICK:{
			String opendate = (String) ifr.getValue("openDate");
			String closedate = (String) ifr.getValue("closeDate");
			String decision = (String)ifr.getValue("decison");
			
			if (decision.equalsIgnoreCase("Submit")){
				
			ifr.setValue("windowstatus", "");
			ifr.setValue("windowstatus", "ACTIVE");
			logger.info("Appraisal Window  --> ExecuteSeverEvent--> decision. ::"+decision);		
            return "Appraisal window has been extended to " + closedate;
           }
			
			if (decision.equalsIgnoreCase("Exit")){
		     	ifr.setValue("windowstatus", "");
				ifr.setValue("windowstatus", "CLOSED");
				logger.info("Appraisal Window  --> ExecuteSeverEvent--> decision. ::"+decision);
				
	            return "Appraisal window will be closed and Workitem will route to EXIT ";
	           }	
		
			 break;
			
		}
	
		
		case ONLOSTFOCUS:{
		}
	
		
		case FORMLOAD: {
	}


		case ONCHANGE:{}
			
			
		case CUSTOM: {
			switch (sControlName) {
			
			
			case "checkDate":{
				boolean check = checkDate(ifr);
				if (check == false){
					
					ifr.setStyle("frame33", "disable", "true");
					return "Close Date cannot be the same or less than Open Date; Kindly enter a future close Date";
				
				}
				else {
					ifr.setStyle("frame33", "disable", "false");
				}
				break;
			}
			
			case "checkDateUpdate":{
				boolean check = checkDateUpdate(ifr);
				if (check == false){
					
					ifr.setStyle("frame33", "disable", "true");
					return "Close Date cannot be the same or less than previous Close Date; Kindly enter a future Close Date";
				
				}
				else {
					ifr.setStyle("frame33", "disable", "false");
				}
				break;
			}
			}
		}
		case ONDONE: {}
		case ONLOAD: {}
		
		}	
		
	}	catch (Exception ex) {

	}

return null;
	}

		

	@Override
	public String generateHTML(EControl arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomFilterXML(FormDef arg0, IFormReference arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String introduceWorkItemInWorkFlow(IFormReference arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String introduceWorkItemInWorkFlow(IFormReference arg0, HttpServletRequest arg1, HttpServletResponse arg2,
			WorkdeskModel arg3) {
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
