package com.newgen.SEA;

import org.apache.log4j.Logger;

import com.newgen.iforms.user.Constants;
import com.newgen.SEA.Commons;
import com.newgen.SEA.LogGEN;
import com.newgen.SEA.SocketService;
import com.newgen.SEA.XMLParser;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;

import org.apache.log4j.Logger;

import java.util.List;

public class getStaffDetails extends Commons implements  Constants{
	public static Logger logger = LogGEN.getLoggerInstance(getStaffDetails.class);
	 XMLParser xmlParser = new XMLParser();
	 SocketService socketService = new SocketService();
	 
	 String response="";
	 String winame;
	 String inputXml;
	 String outputXml;
	 String serviceName = "";
	 public String fetchStaffDetails (IFormReference ifr, String varInputParam,String sData){
		    String supervisorID = (String) ifr.getValue("solID");
		    logger.info("supervisorID: "+ supervisorID);
		  
	    	
	    	serviceName ="fetchSEStaffDetails";
	    	String varBody;
	    	
	    	logger.info("serviceName: "+ serviceName);
	    	 
	        logger.info("varInputParam: "+ varInputParam);
	      
	        try
	        {
	        	outputXml = socketService.executeIntegrationCall(ifr, serviceName,varInputParam, "");
	        	logger.info("--varInputParam >>>"+varInputParam);
	        	logger.info("output xml: " + outputXml);
	        	
	        	String[] arg = outputXml.split("~");
	            String status = arg[0];
	            
	            if ("SUCCESS".equalsIgnoreCase(status)) 
	            {
	            	xmlParser.setInputXML(outputXml);
	            	String StaffName = xmlParser.getValueOf("StaffName");
			        logger.info("StaffName ::::" + StaffName);
			      
	            	if(!(StaffName.equalsIgnoreCase("")|| StaffName.equalsIgnoreCase(null)))
	            	{
	            		String Department = xmlParser.getValueOf("Department");
	            		logger.info("Department ::::" + Department);
	            		String Solid = xmlParser.getValueOf("Solid");
			            logger.info("Sol_id ::::" + Solid);
			            String StaffID = xmlParser.getValueOf("StaffID");
	            		logger.info("StaffID ::::" + StaffID);
	            		String JobName = xmlParser.getValueOf("JobName");
			            logger.info("JobName ::::" + JobName);
			            String Location = xmlParser.getValueOf("Location");
			            logger.info("Location ::::" + Location);
			            String ReportingMgrName = xmlParser.getValueOf("ReportingMgrName");
	            		logger.info("ReportingMgrName ::::" + ReportingMgrName);
	            		String ReportingMgrUserID = xmlParser.getValueOf("ReportingMgrUserID");
			            logger.info("ReportingMgrUserID ::::" + ReportingMgrUserID);
			            String ReportingMgrJobRole = xmlParser.getValueOf("ReportingMgrJobRole");
			            logger.info("ReportingMgrJobRole ::::" + ReportingMgrJobRole);
			            
			            //response = "Staff Details Fetched successfully";
			            	            		
							ifr.setValue("appraiseeBranch", Department);
				             logger.info("appraiseeBranch ::::" + Department);
				             ifr.setValue("appraiseeName", StaffName);
				             logger.info("appraiseeName ::::" + StaffName);
				             ifr.setValue("appraiseeSol", Solid);
				             logger.info("appraiseeSol ::::" + Solid);
				             ifr.setValue("appraiseeJobRole", JobName);
				             logger.info("appraiseeJobRole ::::" + JobName);
				             
				             if (!(Solid.equalsIgnoreCase(supervisorID))){
					                logger.info("Finished checking");  
					        		ifr.setStyle("sharedKPI_table", "disable", "true");
					        		ifr.setStyle("individualKPI_table", "disable", "true");
					        		ifr.setStyle("button38", "disable", "true");
					        		ifr.setStyle("workResult_table", "disable", "true");
					        		ifr.setStyle("performance_table", "disable", "true");
					        		ifr.setStyle("interpersonal_table", "disable", "true");
					        		ifr.setStyle("button39", "disable", "true");
					        		ifr.setStyle("remark", "disable", "true");
									ifr.setStyle("decision", "disable", "true");
									ifr.setStyle("button37", "disable", "true");
									ifr.setValue("totalScore", "");
									ifr.setValue("overallRating", "");
									ifr.setValue("totaloverallscore", "");
									ifr.setValue("softskillpercent", "");
									ifr.setValue("sumWeightedAverage", "");
						        	ifr.setValue("kpipercent", "");
						        	ifr.setValue("table16_average", "");
						        	ifr.setValue("table17_average", "");
						        	response = StaffName +" does not belong to the same SOL as the Supervisor, Kindly input Appraisee in the same SOL";         	  
					              } 
				             else {
				            	    ifr.setStyle("sharedKPI_table", "disable", "false");
					        		ifr.setStyle("individualKPI_table", "disable", "false");
					        		ifr.setStyle("button38", "disable", "false");
					        		ifr.setStyle("workResult_table", "disable", "false");
					        		ifr.setStyle("performance_table", "disable", "false");
					        		ifr.setStyle("interpersonal_table", "disable", "false");
					        		ifr.setStyle("button39", "disable", "false");
					        		ifr.setStyle("remark", "disable", "false");
									ifr.setStyle("decision", "disable", "false");
									ifr.setStyle("button37", "disable", "false");
									ifr.setStyle("table16_average", "disable", "true");
									ifr.setStyle("table17_average", "disable", "true");
									ifr.setStyle("table16_shared_kpi", "disable", "true");
									ifr.setStyle("table16_kpi_weight", "disable", "true");
									ifr.setStyle("table16_Target", "disable", "true");
									ifr.setStyle("table17_individual_kpi", "disable", "true");
									ifr.setStyle("table17_description", "disable", "true");
									ifr.setStyle("table17_kpi_weight", "disable", "true");
									ifr.setStyle("table17_Target", "disable", "true");
									ifr.setValue("totalScore", "");
									ifr.setValue("overallRating", "");
									ifr.setValue("totaloverallscore", "");
									ifr.setValue("softskillpercent", "");
									ifr.setValue("sumWeightedAverage", "");
						        	ifr.setValue("kpipercent", "");
						        	ifr.setValue("table16_average", "");
						        	ifr.setValue("table17_average", "");
									
				             }
		            	}
	            	else
	            	{
	            		response = "Details are not Availabe. Kindly Input Correct Staff Details";
	            		
	            	}
	            }
	            else
	            {
	            	response = "Staff does not Exist";
	            }
	        }
	        catch (Exception e) {

	                logger.info("fetchStaffDetails : e.getMessage() : " + e.getMessage());
	                response = e.getMessage();

	                logger.info("v: retMsg :  : " + response);

	                return response;
	            }
	        
	        return response;
	    }

}
