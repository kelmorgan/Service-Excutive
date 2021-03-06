package com.newgen.SEA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

public class Appraisal_Window extends Commons implements IFormServerEventHandler, Constants {
	public static Logger logger = LogGEN.getLoggerInstance(Appraisal_Window.class);

	@Override
	public void beforeFormLoad(FormDef arg0, IFormReference iFormRef) {
		logger.info("--beforeFormLoad Appraisal_Window--");
		WDGeneralData wdgeneralObj = iFormRef.getObjGeneralData();
		String activityname =wdgeneralObj.getM_strActivityName();
    
		try {
			
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
            
            iFormRef.setStyle("statustype", "visible", "true");
			iFormRef.setValue("statustype", "Appraisal Window Date and Time Set Up");
			iFormRef.setStyle("frame49", "disable", "true");
                      
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

		case checkDate:{
			

			Date d= new Date();
			String caseinitationtime=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
    		logger.info("Appraisal Window executeServerEvent CLICK--> caseinitationtime ::"+caseinitationtime); 
    		 
			String opendate = (String) ifr.getValue("openDate");
			String closedate = (String) ifr.getValue("closeDate");
			String todaydate=new SimpleDateFormat("dd/MM/yyyy").format(d);
			logger.info("Appraisal Window  --> ExecuteSeverEvent--> todaydate. ::"+todaydate);
			logger.info("Appraisal Window  --> ExecuteSeverEvent-->  opendate"+opendate);
			logger.info("Appraisal Window  --> ExecuteSeverEvent-->  closedate "+closedate);
			Date tod=new Date(opendate);
			Date tcd=new Date(closedate);
			logger.info("Appraisal Window  --> ExecuteSeverEvent-->  tod "+tod);
			logger.info("Appraisal Window  --> ExecuteSeverEvent-->  tcd "+tcd);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			logger.info("Appraisal Window  --> ExecuteSeverEvent-->  objSDF "+sdf);
			
			Date openDate = sdf.parse(opendate);
			logger.info("Appraisal Window  --> ExecuteSeverEvent--> Open Date Formatted "+openDate);
			Date closeDate = sdf.parse(closedate);
			logger.info("Appraisal Window  --> ExecuteSeverEvent--> Close Date Formatted "+closeDate);
			
			int diff = openDate.compareTo(closeDate);
			logger.info("Appraisal Window  --> ExecuteSeverEvent--> Date difference"+diff);

			
			
			
		}
		
		case CLICK:{
			
			String opendate = (String) ifr.getValue("openDate");
			String closedate = (String) ifr.getValue("closeDate");
			String decision = (String)ifr.getValue("decison");
	     	if (decision.equalsIgnoreCase("Submit")){
	     	ifr.setValue("windowstatus", "");
			ifr.setValue("windowstatus", "ACTIVE");
			logger.info("Appraisal Window  --> ExecuteSeverEvent--> decision. ::"+decision);
			ifr.setValue("CaseInitiatedTime",closedate);
            return "Appraisal window will be opened from " + opendate + " to " + closedate;
           }	
			 break;
			
		}
	
		
		case ONLOSTFOCUS:{
		}
	
		
		case FORMLOAD: {
	}


		case ONCHANGE:{ 
		}
			
			
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
			
			case "checkwindowstatus":{
				boolean check = checkwindowstatus(ifr);
				boolean checkd = checkCloseDate(ifr);
				if ((check == false)&& (checkd==false)){
					ifr.setStyle("tab2", "disable", "true");
					return "Previous Apprasaisal window has CLOSED but has not been routed to EXIT; Kindly go to the SEA_HCMD_Appraisal_Window_Modification Queue and route previous window to Exit";
				}
				if ((check == false)&& (checkd==true)){
					ifr.setStyle("tab2", "disable", "true");
					return "Previous Appraisal window is still ACTIVE; New Appraisal window cannot be Initiated";
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
