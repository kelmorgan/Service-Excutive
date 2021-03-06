package com.newgen.SEA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.newgen.SEA.SocketService;
import com.newgen.SEA.XMLParser;
import com.newgen.SEA.getStaffDetails;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.iforms.user.Constants;

public class Appraiser_Rework extends Commons implements IFormServerEventHandler, Constants {
	private static final String DECISION = null;

	public static Logger logger = LogGEN.getLoggerInstance(Appraiser_Rework.class);
	SocketService sckt = new SocketService();
	getStaffDetails gsd = new getStaffDetails();
	@Override
	public void beforeFormLoad(FormDef arg0, IFormReference iFormRef) {
		logger.info("--beforeFormLoad Rework HBS--");
		
		
		
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
	public JSONArray executeEvent(FormDef arg0, IFormReference arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String executeServerEvent(IFormReference iFormRef, String sControlName, String sEventName, String sData) {
		
		
		
		
		try {   
			logger.info("--executeServerEvent--");
			logger.info("sEventName-->" + sEventName);
			logger.info("sControlName-->" + sControlName);
			logger.info("sData-->" + sData);
			
			
			if (sEventName.equalsIgnoreCase("staff_check")) {
				String setnull = (String) iFormRef.getValue("supervisorID");
				String solid = (String) iFormRef.getValue("appraiseeSol");
				 if (setnull.equalsIgnoreCase("EMPTY") && solid.equalsIgnoreCase("")){  //Here we are enabling the fields. 
	       		  logger.info("Amunt Checked1");
		    	    
		    	    	 
	       		iFormRef.setStyle("appraiseeName", "disable", "false"); 		
	       		iFormRef.setStyle("appraiseeSol", "disable", "false"); 		
	       		iFormRef.setStyle("appraiseeBranch", "disable", "false");	
	       		iFormRef.setStyle("appraiseeJobRole", "disable", "false");
		 	        	 
		   	       	    	
		    	    }
	       	  else if (!(setnull.equalsIgnoreCase("EMPTY"))) { 
	         	 
		       		iFormRef.setStyle("appraiseeName", "disable", "true"); 		
		       		iFormRef.setStyle("appraiseeSol", "disable", "true"); 		
		       		iFormRef.setStyle("appraiseeBranch", "disable", "true");	
		       		iFormRef.setStyle("appraiseeJobRole", "disable", "true");
		    	    
	       		  
	         }
	         
					 
				 }

			String wiName = (String) iFormRef.getControlValue("WorkItemName");

		
				
			String supervisorID = (String) iFormRef.getValue("solID");
			String staffID = (String) iFormRef.getValue("appraiseeSol");
			String staffName = (String) iFormRef.getValue("appraiseeName");
								            
			if (sEventName.equalsIgnoreCase("solCheck")) {
             	
           	 logger.info("Checking SOl ID'S");
           	 logger.info(supervisorID);
           	 logger.info( staffID);
           		           
           if (staffID.equalsIgnoreCase("") || staffID.equalsIgnoreCase(null))  { 
                      	return "Appraisee SOL ID must be Captured";
                 }
           		           
           if (!(staffID.equalsIgnoreCase(supervisorID))){
               logger.info("Finished checking");  
           	  return staffName +" does not belong to the same SOL as the Supervisor, Kindly input Appraisee in the same SOL";         	  
             }
                  
               }
			
			
			if (sEventName.equalsIgnoreCase("kpiReveiwSum")) {
				
				KPIReveiwSum(iFormRef,"sharedKPI_table", "individualKPI_table", "kpi_table", 4, 5, sData);
				  	        	    
       	                }			
		
            
		switch (sEventName) {

		case CLICK:{
			String staffId = (String) iFormRef.getValue("appraiseeID");
	           logger.info("--staffId >>>"+staffId);
	        
	           String varInputParam = staffId + "~";
			logger.info("--varInputParam >>>"+varInputParam);
			String retMsg;
			retMsg = gsd.fetchStaffDetails(iFormRef,varInputParam,sData);
			logger.info("--fetchStaffDetails-- >>>"+retMsg);
			
			logger.info("--fetchStaffDetails varInputParam-- >>>"+varInputParam);
			return retMsg;
			
		}
	
		
		case ONLOSTFOCUS:{
			
			sharedkpi(iFormRef);
		}
						break;
		
		case FORMLOAD: {
			
			iFormRef.setStyle("frame28", "visible", "false"); 
			iFormRef.setStyle("appraiseeID", "disable", "true"); 
			iFormRef.setStyle("button38", "visible", "true");
			iFormRef.setStyle("button39", "visible", "true");
			iFormRef.setStyle("sumWeightedAverage", "mandatory", "true");
			iFormRef.setStyle("totalScore", "mandatory", "true");
			iFormRef.setStyle("overallRating", "mandatory", "true");
			iFormRef.setStyle("totaloverallscore", "mandatory", "true");
			iFormRef.setStyle("sumWeightedAverage", "backcolor", "c66911");
			iFormRef.setStyle("totaloverallscore", "backcolor", "c66911");
			iFormRef.setStyle("overallRating", "backcolor", "c66911");
	
		}
		break;

		case ONCHANGE:{ 
			individual_KPI(iFormRef);
			
		}

		
			break;
			
		case CUSTOM: {
			switch (sControlName) {
			
			case "checkActualValue":{
				boolean check = checkActualValue(iFormRef,"sharedKPI_table", "individualKPI_table", 4, 5, sData);
				if (check == false)
					return "Please Input ALL Actual Values in KPI REVIEW SECTION";
				
				break;
			}
			
			case "checksoftskills":{
				boolean check = checkSoftSkillvalue(iFormRef,"workResult_table", "performance_table", "interpersonal_table", 0, 1, 0, 1, 2, 3, 4, 5, 6, 0, 1, 2, sData);
				if (check == false)
					return "Please Input ALL Values in SOFT SKILLS REVIEW SECTION";
									break;
			}
			
			case "checkvaluesoftskill":{
				boolean check = checkscoresoftskills(iFormRef,"workResult_table", "performance_table", "interpersonal_table");
				if (check == false)
					return "Maximum rating for Soft Skill Review is 5. Kindly check the Values Captured and Enter from 5 to 1";
				
				break;
			}
			
			case "checkvaluesoftskillerror":{
				boolean check = softskillscore(iFormRef, "workResult_table", "performance_table", "interpersonal_table");
				if (check == false)
				return "Maximum rating for any field in Soft Skill Review is 5. Kindly check the Values Captured and Enter from 5 to 1";
				break;
					}
			
			case "checkvaluesharedkpi":{
				boolean check = checkscoresharedkpi(iFormRef,"sharedKPI_table");
				if (check == false)
					return "Maximum Actual value for Shared KPI is 100. Kindly check the Value Captured and Input a Value of 100 and below.";
				
				break;
			}
			
			case "checkvaluecustexp":{
				boolean check = checkactualcustexp(iFormRef, "sharedKPI_table");
				if (check == false)
					return "Maximum Actual value for this field is 100. Kindly check the Value Captured and Input a Value of 100 and below.";
				
				break;
			}
			
			
			case "checkvaluetransvol":{
				boolean check = checkactualtransvol(iFormRef, "individualKPI_table");
				if (check == false)
					return "Maximum Actual value for this field is 30. Kindly check the Value Captured and Input a Value of 30 and below.";
				
				break;
			}
			
			case "checkindivkpi":{
				boolean check = checkindivscore(iFormRef, "individualKPI_table");
				if (check == false)
					return "Maximum Actual value for Transaction Volume and/or Collections Count in Individual KPI is 30. Kindly check the Value Captured and Input a Value of 30 and below.";
				
				break;
			}
			
			case "setSoftSkills":{
				
				SoftSkillReviewSum(iFormRef,"workResult_table", "performance_table", "interpersonal_table", "kpi_table", 0, 1, 0, 1, 2, 3, 4, 5, 6, 0, 1, 2, sData);
				break;
			}
			
			case "erasesofttotal":{
				
				setnullsoftskils(iFormRef);
				break;
			}
			
			case "checkcloseDateRework":{
				boolean check = checkCloseDate(iFormRef);
				if (check == false){	
					iFormRef.setStyle("frame26", "disable", "true");
					return "Appraisal Window is CLOSED; Kindly contact HCMD for further Enquiries";	
				}
	
				break;
			}
			
			case "checkcloseDateReworkSubmit":{
				String decision = (String)iFormRef.getValue("decison");
		     	if (decision.equalsIgnoreCase("SubmitApp")||decision.equalsIgnoreCase("SubmitMan")) {
				boolean check = checkCloseDate(iFormRef);
				boolean checkd = checkwindowstatusIni(iFormRef);
				if ((check == false) || (checkd==false)){			
					iFormRef.setStyle("frame26", "disable", "true");
					return "Appraisal Window is CLOSED; Kindly contact HCMD for further Enquiries";	
				
				}
				else {
					iFormRef.setStyle("frame26", "disable", "false");
				}
				break;
			}
			
			}
			
				}
					}
		case ONDONE: {
			
			String enhancementtype = (String) iFormRef.getValue("ENHANCEMENTTYPE");
			String decision = (String) iFormRef.getValue("DECISION");
			String amount = (String) iFormRef.getValue("AMOUNT");
			
			if (!(amount.equalsIgnoreCase("")));
			double amount1 = Double.parseDouble(amount);
			
			
			if (enhancementtype.equalsIgnoreCase("Transactional") && decision.equalsIgnoreCase("Submit") && amount1 >= (10000000000.00)){
                boolean checkDocs= checkDocuments(iFormRef,wiName,"Confirmation from Business(Transactional Request)");
                boolean checkDocsSupport = checkDocuments(iFormRef,wiName,"Supporting Documents");
                boolean checkDocsfinal = checkDocuments(iFormRef,wiName,"Finacle Screenshot");
                if(checkDocs == false && checkDocsSupport == true && checkDocsfinal == false) {
                       return "Please Attach Confirmation from Business and Finacle Screenshot";
                }
                
                else if(checkDocs == true && checkDocsSupport == false && checkDocsfinal == false) {
                    return "Please Attach Other Supporting Documents and Finacle Screenshot";
             }
                else if(checkDocs == false && checkDocsSupport == false && checkDocsfinal == true) {
                    return "Please Attach Confirmation from Business and other Supporting Documents";
             }
                else if(checkDocs == false && checkDocsSupport == true && checkDocsfinal == true) {
                    return "Please Attach Confirmation from Business";
             }
                else if(checkDocs == true && checkDocsSupport == true && checkDocsfinal == false) {
                    return "Please Attach Finacle Screenshot";
             }
                else if(checkDocs == true && checkDocsSupport == false && checkDocsfinal == true) {
                    return "Please Attach Other Supporting Documents";
             }
                else if(checkDocs == false || checkDocsSupport == false && checkDocsfinal == false) {
                    return "Please Attach Confirmation from Business, Finacle Screenshot and other Supporting Documents";
             }
                
         }
			
			
							
			if (enhancementtype.equalsIgnoreCase("Transactional") && decision.equalsIgnoreCase("Submit") && amount1 < (10000000000.00)){
                boolean checkDocs= checkDocuments(iFormRef,wiName,"Confirmation from Business(Transactional Request)");
                boolean checkDocsSupport = checkDocuments(iFormRef,wiName,"Supporting Documents");
                if(checkDocs == false && checkDocsSupport == true) {
                       return "Please Attach Confirmation from Business";
                }
                
                else if(checkDocs == true && checkDocsSupport == false) {
                    return "Please Attach Other Supporting Documents";
             }
                else if(checkDocs == false || checkDocsSupport == false) {
                    return "Please Attach Confirmation from Business and other Supporting Documents";
             }
                
         }
			
			if (enhancementtype.equalsIgnoreCase("Operational") && decision.equalsIgnoreCase("Submit")){
                String sDocPresentQuery = "SELECT COUNT(1) FROM pdbdocument A, PDBDocumentContent B WHERE A.DocumentIndex=B.DocumentIndex AND B.ParentFolderIndex=(select FolderIndex from PDBFolder where name ='"+ wiName + "') and A.Name like 'Supporting Documents%'"; //Owner = 83
                List docList = new ArrayList();
                docList = iFormRef.getDataFromDB(sDocPresentQuery);
                Iterator itdoc = docList.iterator();
                String sDocCount = "";
                while (itdoc.hasNext()) {
                       sDocCount = itdoc.next().toString();
                       sDocCount = sDocCount.substring(sDocCount.indexOf("[") + 1, sDocCount.indexOf("]"));
                       if (sDocCount.equalsIgnoreCase("0")) {
                              String otp = "";
                              otp = "false";
                              return otp;
                       }
                }
                }
			
	
		}
		break; 
		
		
		case "fetchStaffDetails":{
			String staffId = ((String) iFormRef.getValue(sData));
	           logger.info("--staffId >>>"+staffId);
	        
	           String varInputParam = staffId + "~";
			logger.info("--varInputParam >>>"+varInputParam);
			String retMsg;
			retMsg = gsd.fetchStaffDetails(iFormRef,varInputParam,sData);
			logger.info("--fetchStaffDetails-- >>>"+retMsg);
			
			logger.info("--fetchStaffDetails varInputParam-- >>>"+varInputParam);
			return retMsg;
			
		}
		
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


	@Override
	public String executeCustomService(FormDef arg0, IFormReference arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

}
