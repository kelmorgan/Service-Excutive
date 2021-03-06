package com.newgen.SEA;


import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.newgen.iforms.EControl;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.mvcbeans.model.WorkdeskModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.newgen.iforms.user.Constants;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Commons implements Constants {
		
	public static Logger logger = LogGEN.getLoggerInstance(Commons.class);


	public static void sharedKPI (IFormReference iFormRef) {
	     List<List<String>> dbResult = new ArrayList<List<String>>();
	     String dbQuery= "select kpi_id, shared_kpi, kpi_weight, target from SEA_sharedKPI_table where kpi_id is not null";
	    
	     logger.info("dbQuery: "+ dbQuery);
	       
	     dbResult = iFormRef.getDataFromDB(dbQuery);
	     logger.info("dbCount--------: "+ dbResult.size());
	     logger.info("dbResult: "+ dbResult.get(0).size());
	     logger.info("dbCount11111--------: "+ dbResult.size());
	     JSONArray jsRowArray = new JSONArray();
	     for(List<String> ls : dbResult)
	     {
	         logger.info("Staff Name ---------"+ls.get(0));
	     
	         JSONObject jrow = new JSONObject();
	         jrow.put("kpi_id", ls.get(0));
	         jrow.put("Shared KPIs", ls.get(1));
	         jrow.put("KPI Weight", ls.get(2));
	         jrow.put("Target", ls.get(3));
	         
	         jsRowArray.add(jrow);
	     }
	     iFormRef.addDataToGrid("sharedKPI_table", jsRowArray);
	     iFormRef.setValue("sumKpiWeight", "100");
}
	
	public static void individualKPI (IFormReference iFormRef) {
	     List<List<String>> dbResult = new ArrayList<List<String>>();
	     String dbQuery= "select kpi_id, individual_kpi, description, kpi_weight, target from SEA_individualKPI_table where kpi_id is not null";
	    
	     logger.info("dbQuery: "+ dbQuery);
	       
	     dbResult = iFormRef.getDataFromDB(dbQuery);
	     logger.info("dbCount--------: "+ dbResult.size());
	     logger.info("dbResult: "+ dbResult.get(0).size());
	     logger.info("dbCount11111--------: "+ dbResult.size());
	     JSONArray jsRowArray = new JSONArray();
	     for(List<String> ls : dbResult)
	     {
	         logger.info("Staff Name ---------"+ls.get(0));
	     
	         JSONObject jrow = new JSONObject();
	         jrow.put("kpi_id", ls.get(0));
	         jrow.put("Individual KPI", ls.get(1));
	         jrow.put("Description", ls.get(2));
	         jrow.put("KPI Weight", ls.get(3));
	         jrow.put("Target", ls.get(4));
	         
	         jsRowArray.add(jrow);
	     }
	     iFormRef.addDataToGrid("individualKPI_table", jsRowArray);

	 

	}
	
	public static void kpi_table (IFormReference iFormRef) {
	     List<List<String>> dbResult = new ArrayList<List<String>>();
	     String dbQuery= "select kpi_id, review, k_weight, r_weight from SEA_kpi_table where kpi_id is not null";
	    
	     logger.info("dbQuery: "+ dbQuery);
	       
	     dbResult = iFormRef.getDataFromDB(dbQuery);
	     logger.info("dbCount--------: "+ dbResult.size());
	     logger.info("dbResult: "+ dbResult.get(0).size());
	     logger.info("dbCount11111--------: "+ dbResult.size());
	     JSONArray jsRowArray = new JSONArray();
	     for(List<String> ls : dbResult)
	     {
	         logger.info("Staff Name ---------"+ls.get(0));
	     
	         JSONObject jrow = new JSONObject();
	         jrow.put("kpi_id", ls.get(0));
	         jrow.put("Review", ls.get(1));
	         jrow.put("-", ls.get(2));
	         jrow.put("WEIGHT", ls.get(3));
	         
	         jsRowArray.add(jrow);
	     }
	     iFormRef.addDataToGrid("kpi_table", jsRowArray);

	 

	}
	
	public static void legend_table(IFormReference iFormRef) {
	     List<List<String>> dbResult = new ArrayList<List<String>>();
	     String dbQuery= "select from_percent, to_percent, interpretation from SEA_legend_table";
	    
	     logger.info("dbQuery: "+ dbQuery);
	       
	     dbResult = iFormRef.getDataFromDB(dbQuery);
	     logger.info("dbCount--------: "+ dbResult.size());
	     logger.info("dbResult: "+ dbResult.get(0).size());
	     logger.info("dbCount11111--------: "+ dbResult.size());
	     JSONArray jsRowArray = new JSONArray();
	     for(List<String> ls : dbResult)
	     {
	         logger.info("From percent ---------"+ls.get(0));
	     
	         JSONObject jrow = new JSONObject();
	         jrow.put("FROM", ls.get(0));
	         jrow.put("TO", ls.get(1));
	         jrow.put("INTERPRETATION", ls.get(2));
	         
	         jsRowArray.add(jrow);
	     }
	     iFormRef.addDataToGrid("legend_table", jsRowArray);
}
	
	
	 public void sharedkpi (IFormReference ifr){
					
			float kpisghared_weight = 0;
			float kpishared_target = 0;
			float kpishared_actual = 0;
				
	        String kpisghared_weight1 = (String)ifr.getValue("table16_kpi_weight");
	        logger.info("kpi shared weight: "+ kpisghared_weight1);
	        String kpishared_target1 = (String)ifr.getValue("table16_Target");
	        logger.info("kpi shared target: "+ kpishared_target1);
	        String kpishared_actual1 = (String)ifr.getValue("table16_Actual");
	        logger.info("kpi shared actual: "+ kpishared_actual1);        
	       
	        
	        if (!kpisghared_weight1.equalsIgnoreCase(EMPTY)){
	        	kpisghared_weight = Float.parseFloat(kpisghared_weight1);
				logger.info("kpi shared weight: "+ kpisghared_weight);
			}
	        
	        if (!kpishared_target1.equalsIgnoreCase(EMPTY)){
	        	kpishared_target = Float.parseFloat(kpishared_target1);
	        	logger.info("kpi shared target: "+ kpishared_target);
			}
	        
	        if (!kpishared_actual1.equalsIgnoreCase(EMPTY)){
	        	kpishared_actual = Float.parseFloat(kpishared_actual1);
	        	logger.info("kpi shared actual: "+ kpishared_actual);
			}
	       
	        float kpishared_average = (kpishared_actual/kpishared_target)* kpisghared_weight;
	       
	        String result = String.format("%.2f", kpishared_average);
	        ifr.setValue("table16_average", result);
	        ifr.setValue("sumWeightedAverage", "");
	        ifr.setValue("totaloverallscore", "");
	        ifr.setValue("kpipercent", "");

	    }
	 
	 public boolean checkscoresharedkpi (IFormReference ifr, String table1){
			
			float custexpscore = 0;
			float contcomplscore = 0;
			float rpccompscore = 0;	
			
					String custexp = ifr.getTableCellValue(table1, 0, 3);
					logger.info("KPI quality" + custexp);
					String contcomp = ifr.getTableCellValue(table1, 1, 3);
					logger.info("KPI quantity" + contcomp);	
					String rpccomp = ifr.getTableCellValue(table1, 2, 3);
					logger.info("KPI job_knowledge" + rpccomp);	
				
					
					if (!custexp.equalsIgnoreCase(EMPTY))
						custexpscore = Float.parseFloat(custexp);
					if (!contcomp.equalsIgnoreCase(EMPTY))
						contcomplscore = Float.parseFloat(contcomp);
					if (!rpccomp.equalsIgnoreCase(EMPTY))
						rpccompscore = Float.parseFloat(rpccomp);
					
					if (custexpscore>100 || contcomplscore>100 ||rpccompscore>100){
						return false;	
					}
					return true;
					
				}
	 
	 public boolean checkindivscore (IFormReference ifr, String table1){
			
			float transvol = 0;	
			float collectcount = 0;	
			
			String tranvol = ifr.getTableCellValue(table1, 3, 4);
			logger.info("Transaction Volume" + tranvol);
			String collcount = ifr.getTableCellValue(table1, 4, 4);
			logger.info("Collection Count" + collcount);	
			if (!tranvol.equalsIgnoreCase(EMPTY))
				transvol = Float.parseFloat(tranvol);
			if (!collcount.equalsIgnoreCase(EMPTY))
				collectcount = Float.parseFloat(collcount);
			
			if (transvol>30 || collectcount>30){
				return false;
				
			}
			return true;
			
		}
	 
	 public boolean checkactualcustexp (IFormReference ifr, String table1){
			
			float custexpscore = 0;
			
					String custexp = (String)ifr.getValue("table16_Actual");
					logger.info("CUSTOMER EXPERIENCE" + custexp);
					
					if (!custexp.equalsIgnoreCase(EMPTY))
						custexpscore = Float.parseFloat(custexp);
					
					if (custexpscore>100){
						return false;	
					}
					return true;
					
				}
	
	 public boolean checkactualtransvol (IFormReference ifr, String table1){
			
			float tranvol = 0;	
			
					String transvol = (String)ifr.getValue("table17_Actual");
					logger.info("TRANSACTION VOLUME" + transvol);	
				
					if (!transvol.equalsIgnoreCase(EMPTY))
						tranvol = Float.parseFloat(transvol);
					
					if (tranvol>30){
						return false;	
					}
					return true;
					
				}

	 
	 public void individual_KPI (IFormReference ifr){
		 	
			float kpiindiviadual_weight = 0;
			float kpiindiviadual_target = 0;
			float kpiindiviadual_actual = 0;
			
				
	        String kpiindiviadual_weight1 = (String)ifr.getValue("table17_kpi_weight");
	        logger.info("kpi shared weight: "+ kpiindiviadual_weight1);
	        String kpiindiviadual_target1 = (String)ifr.getValue("table17_Target");
	        logger.info("kpi shared target: "+ kpiindiviadual_target1);
	        String kpiindiviadual_actual1 = (String)ifr.getValue("table17_Actual");
	        logger.info("kpi shared actual: "+ kpiindiviadual_actual1);        
	       
	        
	        if (!kpiindiviadual_weight1.equalsIgnoreCase(EMPTY)){
	        	kpiindiviadual_weight = Float.parseFloat(kpiindiviadual_weight1);
				logger.info("kpi shared weight: "+ kpiindiviadual_weight);
			}
	        
	        if (!kpiindiviadual_target1.equalsIgnoreCase(EMPTY)){
	        	kpiindiviadual_target = Float.parseFloat(kpiindiviadual_target1);
	        	logger.info("kpi shared target: "+ kpiindiviadual_target);
			}
	        
	        if (!kpiindiviadual_actual1.equalsIgnoreCase(EMPTY)){
	        	kpiindiviadual_actual = Float.parseFloat(kpiindiviadual_actual1);
	        	logger.info("kpi shared actual: "+ kpiindiviadual_actual);
			}
	        
	        if ((kpiindiviadual_target <= 5) && (kpiindiviadual_actual<=kpiindiviadual_target)) {
	        	float kpiindiviadual_average = kpiindiviadual_weight;
	        	String result = String.format("%.2f", kpiindiviadual_average);
	        	ifr.setValue("table17_average", result);
	        	ifr.setValue("sumWeightedAverage", "");
	        	ifr.setValue("totaloverallscore", "");
	        	ifr.setValue("kpipercent", "");
	        }
	        
	        if ((kpiindiviadual_target <= 5) && (kpiindiviadual_actual>kpiindiviadual_target)) {
	        	float kpiindiviadual_average = kpiindiviadual_weight * 0;
	        	String result = String.format("%.2f", kpiindiviadual_average);
	        	ifr.setValue("table17_average", result);
	        	ifr.setValue("sumWeightedAverage", "");
	        	ifr.setValue("totaloverallscore", "");
	        	ifr.setValue("kpipercent", "");
	        }
	        if (kpiindiviadual_target > 5) {
	        	float kpiindiviadual_average = (kpiindiviadual_actual/kpiindiviadual_target)* kpiindiviadual_weight;
	        	String result = String.format("%.2f", kpiindiviadual_average);
	        	ifr.setValue("table17_average", result);
	        	ifr.setValue("sumWeightedAverage", "");
	        	ifr.setValue("totaloverallscore", "");
	        	ifr.setValue("kpipercent", "");
	        }         
	        
	    } 
	 
	 public void KPIReveiwSum (IFormReference iFormRef, String table1, String table2, String table3, int table16_average, int table17_average, String sData){
		 
		 String [] count = sData.split("#");
			float averagescoretable12 = 0;
			float averagescoretable22 = 0;
			float softskillpercentnew = 0;
			float softskillpercent = 0;
			
			
			int count1 = Integer.parseInt(count[0]);
			int count2 = Integer.parseInt(count[1]);
			//int count = Integer.parseInt(sData);
			logger.info("This is the count " + count);
				
				for (int i = 0; i < count1; i++){
					String averagescoretable1 = iFormRef.getTableCellValue(table1, i, table16_average);
					logger.info("TThis is the average for table 1" + averagescoretable1);
				
				if (!averagescoretable1.equalsIgnoreCase(EMPTY))
					averagescoretable12 = Float.parseFloat(averagescoretable1) + averagescoretable12;
				}
				
				
				for (int i = 0; i < count2; i++){
					String averagescoretable2 = iFormRef.getTableCellValue(table2, i, table17_average);
					logger.info("TThis is the average for table2 " + averagescoretable2);
				
				if (!averagescoretable2.equalsIgnoreCase(EMPTY))
					averagescoretable22 = Float.parseFloat(averagescoretable2) + averagescoretable22;
				}
				
			float averageTotalTable1 = averagescoretable12;
			logger.info("TThis is the total average score for table 1 " + averageTotalTable1);
			
			float averageTotalTable2 = averagescoretable22;
			logger.info("TThis is the total average score for table 2 " + averageTotalTable2);
				
			float averageTotal = averageTotalTable1 + averageTotalTable2;
			logger.info("TThis is the total average score for table 1&2 " + averageTotal);
			String result = String.format("%.2f", averageTotal);
			iFormRef.setValue("sumWeightedAverage", result);
			
			iFormRef.setTableCellValue(table3, 0, 2, result);
			
			float cellpercent = (averageTotal/100)*100;
			logger.info("cell percent" + cellpercent);
			String cellpercentnew = String.format("%.2f", cellpercent);
			logger.info("cellpercentnew" + cellpercentnew);
			
			iFormRef.setTableCellValue(table3, 0, 3, cellpercentnew + "%");
			iFormRef.setValue("kpipercent", cellpercentnew + "%");
			logger.info("setting KPI percent" + cellpercentnew);
			
			String KPI = iFormRef.getTableCellValue(table3, 1, 2);
			logger.info("KPI new PERCENT" + KPI);
			
			 if (!KPI.equalsIgnoreCase(EMPTY)){
				 softskillpercent = Float.parseFloat(KPI);
					logger.info("kpi percent float:" + softskillpercent);
				}
			 
			 softskillpercentnew = (softskillpercent/5)*100;
			
   
			 float calculation1 = averageTotal*70;
		     float calculation2 = softskillpercentnew*30;
		     
		     float calculation3 = ((calculation1+calculation2)/100);
		     logger.info("total calculation: "+ calculation3);
		     
		     String totalscore = String.format("%.2f", calculation3);
		     logger.info("total score calculation: "+ totalscore);
		     
		     iFormRef.setValue("totaloverallscore", totalscore + "%");
		     
			
			
	 }
	 
public boolean checkActualValue (IFormReference ifr, String table1, String table2, int table16_average, int table17_average, String sData){
			
			 String [] count = sData.split("#");
			 
			int rowcount1 = Integer.parseInt(count[0]);
			int rowcount2 = Integer.parseInt(count[1]);
				
			for (int i = 0; i < rowcount1 ; i++){
				String actualvalue1 = ifr.getTableCellValue(table1, i, table16_average);
				logger.info("TThis is the actual value for shared table " + actualvalue1);				
				if (actualvalue1.equalsIgnoreCase("")){
					return false;
				}
			}
				
			for (int i = 0; i < rowcount2; i++){
			String actualvalue2 = ifr.getTableCellValue(table2, i, table17_average);
			logger.info("TThis is the actual value for individual table " + actualvalue2);
			if (actualvalue2.equalsIgnoreCase("")){
				return false;
				}
			}
		return true;
		}
		
		public void SoftSkillReviewSum (IFormReference iFormRef, String table1, String table2, String table3, String table4, int table18_work_quality, int table18_work_quantity, int table19_job_knowledge, int table19_org_palanning, int table19_decision, int table19_problem_solving, int table19_adaptability, int table19_attendance, int table19_dependability, int table20_communication, int table20_collaboration, int table20_teamwork, String sData){
			 
			 String [] count = sData.split("#");
				float workqualityscore = 0;
				float workquantityscore = 0;
				float jobknowledgescore = 0;
				float orgplanningscore = 0;
				float decisionscore = 0;
				float problemsolvingscore = 0;
				float adaptabilityscore = 0;
				float attendancescore = 0;
				float dependabilityscore = 0;
				float communicationscore = 0;
				float collaborationscore = 0;
				float teamworkscore = 0;
				float kpipercentnew = 0;
				float softskillpercentnew = 0;
						
				int count1 = Integer.parseInt(count[0]);
				int count2 = Integer.parseInt(count[1]);
				int count3 = Integer.parseInt(count[2]);
				
				logger.info("This is the count " + count);
					
					for (int i = 0; i < count1; i++){
						String workquality = iFormRef.getTableCellValue(table1, i, table18_work_quality);
						logger.info("TThis is the average for table 1" + workquality);
						String workquantity = iFormRef.getTableCellValue(table1, i, table18_work_quantity);
						logger.info("TThis is the average for table 1" + workquantity);
					
						if (!workquality.equalsIgnoreCase(EMPTY))
						workqualityscore = Float.parseFloat(workquality);
						
						if (!workquantity.equalsIgnoreCase(EMPTY))
						workquantityscore = Float.parseFloat(workquantity);
					}
					
					for (int i = 0; i < count2; i++){
						String jobknowledge = iFormRef.getTableCellValue(table2, i, table19_job_knowledge);
						logger.info("TThis is the value of table 2 job knowledge" + jobknowledge);
						String orgplanning = iFormRef.getTableCellValue(table2, i, table19_org_palanning);
						logger.info("TThis is the value of table 2 org planning" + orgplanning);
						String decision = iFormRef.getTableCellValue(table2, i, table19_decision);
						logger.info("TThis is the value of table 2 decision" + decision);
						String problemsolving = iFormRef.getTableCellValue(table2, i, table19_problem_solving);
						logger.info("TThis is the value of table 2 problem solving" + problemsolving);
						String adaptability = iFormRef.getTableCellValue(table2, i, table19_adaptability);
						logger.info("TThis is the value of table 2 adaptability" + adaptability);
						String attendance = iFormRef.getTableCellValue(table2, i, table19_attendance);
						logger.info("TThis is the value of table 2 attendance" + attendance);
						String dependability = iFormRef.getTableCellValue(table2, i, table19_dependability);
						logger.info("TThis is the value of table 2 dependability" + dependability);
											
						if (!jobknowledge.equalsIgnoreCase(EMPTY))
							jobknowledgescore = Float.parseFloat(jobknowledge);
						
						if (!orgplanning.equalsIgnoreCase(EMPTY))
							orgplanningscore = Float.parseFloat(orgplanning);
						
						if (!decision.equalsIgnoreCase(EMPTY))
							decisionscore = Float.parseFloat(decision);
						
						if (!problemsolving.equalsIgnoreCase(EMPTY))
							problemsolvingscore = Float.parseFloat(problemsolving);
						
						if (!adaptability.equalsIgnoreCase(EMPTY))
							adaptabilityscore = Float.parseFloat(adaptability);
						
						if (!attendance.equalsIgnoreCase(EMPTY))
							attendancescore = Float.parseFloat(attendance);
						
						if (!dependability.equalsIgnoreCase(EMPTY))
							dependabilityscore = Float.parseFloat(dependability);
					}
					
					for (int i = 0; i < count3; i++){
						String communication = iFormRef.getTableCellValue(table3, i, table20_communication);
						logger.info("TThis is the value of table 3 commmunication" + communication);
						String collaboration = iFormRef.getTableCellValue(table3, i, table20_collaboration);
						logger.info("TThis is the value of table 3 collaboration" + collaboration);
						String teamwork = iFormRef.getTableCellValue(table3, i, table20_teamwork);
						logger.info("TThis is the value of table 3 teamwork" + teamwork);
						
						if (!communication.equalsIgnoreCase(EMPTY))
							communicationscore = Float.parseFloat(communication);
						
						if (!collaboration.equalsIgnoreCase(EMPTY))
							collaborationscore = Float.parseFloat(collaboration);
						
						if (!teamwork.equalsIgnoreCase(EMPTY))
							teamworkscore = Float.parseFloat(teamwork);
					}
					
					
				float workresulttotal = workqualityscore + workquantityscore;
				logger.info("TThis is the total for workresult skill" + workresulttotal);
				
				float performancefactortotal = jobknowledgescore + orgplanningscore + decisionscore + problemsolvingscore + adaptabilityscore + attendancescore + dependabilityscore;
				logger.info("TThis is the total for performance factor skill" + performancefactortotal);
				
				float interpersonalskilltotal = communicationscore + collaborationscore + teamworkscore;
				logger.info("TThis is the total for interpersonal skill" + interpersonalskilltotal);
				
				float totalsoftskills = workresulttotal + performancefactortotal + interpersonalskilltotal;
				logger.info("TThis is the total of all soft skills review score" + totalsoftskills);
				
				String result = String.format("%.2f", totalsoftskills);
				logger.info("result" + result);
				iFormRef.setValue("totalScore", result);

				
				float ratingScore = totalsoftskills/12;
				logger.info("ratingScore" + ratingScore);
				String resultrating = String.format("%.2f", ratingScore);
				iFormRef.setValue("overallRating", resultrating);
				
				float softskillcellpercent = (ratingScore/5)*100;
				String softskillcellpercentnew = String.format("%.2f", softskillcellpercent);
				logger.info("cellpercentnew" + softskillcellpercentnew);
				iFormRef.setTableCellValue(table4, 1, 2, resultrating);
				iFormRef.setTableCellValue(table4, 1, 3, softskillcellpercentnew +"%");
				iFormRef.setValue("softskillpercent", softskillcellpercentnew +"%");
			
				String kpipercent = (String) iFormRef.getValue("kpipercent");
				logger.info("KPI PERCENT" + kpipercent);
				
				String KPI = iFormRef.getTableCellValue(table4, 0, 2);
				logger.info("KPI new PERCENT" + KPI);
				
				 if (!KPI.equalsIgnoreCase(EMPTY)){
			        	kpipercentnew = Float.parseFloat(KPI);
						logger.info("kpi percent float:" + kpipercentnew);
					}
			      
		    		        
			     float calculation1 = kpipercentnew*70;
			     float calculation2 = softskillcellpercent*30;
			     
			     float calculation3 = ((calculation1+calculation2)/100);
			     logger.info("total calculation: "+ calculation3);
			     
			     String totalscore = String.format("%.2f", calculation3);
			     logger.info("total score calculation: "+ totalscore);
			     
			     iFormRef.setValue("totaloverallscore", totalscore + "%");
			     
			    								
		 }

		
		public void setnullsoftskils(IFormReference ifr){
			
			ifr.setValue("totalScore", "");
			ifr.setValue("overallRating", "");
			ifr.setValue("totaloverallscore", "");
			ifr.setValue("softskillpercent", "");
			
		}
		
		public boolean checkSoftSkillvalue (IFormReference iFormRef, String table1, String table2, String table3, int table18_work_quality, int table18_work_quantity, int table19_job_knowledge, int table19_org_palanning, int table19_decision, int table19_problem_solving, int table19_adaptability, int table19_attendance, int table19_dependability, int table20_communication, int table20_collaboration, int table20_teamwork, String sData){
			
			 String [] count = sData.split("#");
			 
			int rowcount1 = Integer.parseInt(count[0]);
			int rowcount2 = Integer.parseInt(count[1]);
			int rowcount3 = Integer.parseInt(count[2]);
				
			for (int i = 0; i < rowcount1 ; i++){
				String workquality = iFormRef.getTableCellValue(table1, i, table18_work_quality);
				logger.info("TThis is the average for table 1" + workquality);
				String workquantity = iFormRef.getTableCellValue(table1, i, table18_work_quantity);
				logger.info("TThis is the average for table 1" + workquantity);			
				if (workquality.equalsIgnoreCase("")||workquantity.equalsIgnoreCase("")){
					return false;
				}
			}
				
			for (int i = 0; i < rowcount2; i++){
				String jobknowledge = iFormRef.getTableCellValue(table2, i, table19_job_knowledge);
				logger.info("TThis is the value of table 2 job knowledge" + jobknowledge);
				String orgplanning = iFormRef.getTableCellValue(table2, i, table19_org_palanning);
				logger.info("TThis is the value of table 2 org planning" + orgplanning);
				String decision = iFormRef.getTableCellValue(table2, i, table19_decision);
				logger.info("TThis is the value of table 2 decision" + decision);
				String problemsolving = iFormRef.getTableCellValue(table2, i, table19_problem_solving);
				logger.info("TThis is the value of table 2 problem solving" + problemsolving);
				String adaptability = iFormRef.getTableCellValue(table2, i, table19_adaptability);
				logger.info("TThis is the value of table 2 adaptability" + adaptability);
				String attendance = iFormRef.getTableCellValue(table2, i, table19_attendance);
				logger.info("TThis is the value of table 2 attendance" + attendance);
				String dependability = iFormRef.getTableCellValue(table2, i, table19_dependability);
				logger.info("TThis is the value of table 2 dependability" + dependability);
									
				if (jobknowledge.equalsIgnoreCase("")||orgplanning.equalsIgnoreCase("")||decision.equalsIgnoreCase("")||problemsolving.equalsIgnoreCase("")||adaptability.equalsIgnoreCase("")||attendance.equalsIgnoreCase("")||dependability.equalsIgnoreCase("")){
				return false;
				}
			}
			
			for (int i = 0; i < rowcount3; i++){
				String communication = iFormRef.getTableCellValue(table3, i, table20_communication);
				logger.info("TThis is the value of table 3 commmunication" + communication);
				String collaboration = iFormRef.getTableCellValue(table3, i, table20_collaboration);
				logger.info("TThis is the value of table 3 collaboration" + collaboration);
				String teamwork = iFormRef.getTableCellValue(table3, i, table20_teamwork);
				logger.info("TThis is the value of table 3 teamwork" + teamwork);
				if (communication.equalsIgnoreCase("")||collaboration.equalsIgnoreCase("")||teamwork.equalsIgnoreCase("")){
					return false;
					}
				}
			
		return true;
		}
		
		public boolean checkscoresoftskills (IFormReference ifr, String table1, String table2, String table3){
			
			float qualityscore = 0;
			float quantityscore = 0;
			float job_knowledgescore = 0;
			float organisingscore = 0;
			float decisionscore = 0;
			float problemscore = 0;
			float adaptabilityscore = 0;
			float attendancescore = 0;
			float dependabilityscore = 0;
			float communicationscore = 0;
			float collaborationscore = 0;
			float teamworkscore = 0;
			
			
			
					String quality = ifr.getTableCellValue(table1, 0, 0);
					logger.info("KPI quality" + quality);
					String quantity = ifr.getTableCellValue(table1, 0, 1);
					logger.info("KPI quantity" + quantity);	
					String job_knowledge = ifr.getTableCellValue(table2, 0, 0);
					logger.info("KPI job_knowledge" + job_knowledge);	
					String organising = ifr.getTableCellValue(table2, 0, 1);
					logger.info("KPI organising" + organising);	
					String decision = ifr.getTableCellValue(table2, 0, 2);
					logger.info("KPI decision" + decision);	
					String problem = ifr.getTableCellValue(table2, 0, 3);
					logger.info("KPI problem" + problem);	
					String adaptability = ifr.getTableCellValue(table2, 0, 4);
					logger.info("KPI adaptability" + adaptability);
					String attendance = ifr.getTableCellValue(table2, 0, 5);
					logger.info("KPI attendance" + attendance);
					String dependability = ifr.getTableCellValue(table2, 0, 6);
					logger.info("KPI dependability" + dependability);
					String communication = ifr.getTableCellValue(table3, 0, 0);
					logger.info("KPI communication" + communication);
					String collaboration = ifr.getTableCellValue(table3, 0, 1);
					logger.info("KPI collaboration" + collaboration);
					String teamwork = ifr.getTableCellValue(table3, 0, 2);
					logger.info("KPI teamwork" + teamwork);
					
					if (!quality.equalsIgnoreCase(EMPTY))
						qualityscore = Float.parseFloat(quality);
					if (!quantity.equalsIgnoreCase(EMPTY))
						quantityscore = Float.parseFloat(quantity);
					if (!job_knowledge.equalsIgnoreCase(EMPTY))
						job_knowledgescore = Float.parseFloat(job_knowledge);
					if (!organising.equalsIgnoreCase(EMPTY))
						organisingscore = Float.parseFloat(organising);
					if (!decision.equalsIgnoreCase(EMPTY))
						decisionscore = Float.parseFloat(decision);
					if (!problem.equalsIgnoreCase(EMPTY))
						problemscore = Float.parseFloat(problem);
					if (!adaptability.equalsIgnoreCase(EMPTY))
						adaptabilityscore = Float.parseFloat(adaptability);
					if (!attendance.equalsIgnoreCase(EMPTY))
						attendancescore = Float.parseFloat(attendance);
					if (!dependability.equalsIgnoreCase(EMPTY))
						dependabilityscore = Float.parseFloat(dependability);
					if (!communication.equalsIgnoreCase(EMPTY))
						communicationscore = Float.parseFloat(communication);
					if (!collaboration.equalsIgnoreCase(EMPTY))
						collaborationscore = Float.parseFloat(collaboration);
					if (!teamwork.equalsIgnoreCase(EMPTY))
						teamworkscore = Float.parseFloat(teamwork);
					
					if (qualityscore>5 || quantityscore>5 ||job_knowledgescore>5 ||organisingscore>5 || decisionscore>5 
							||problemscore>5 || adaptabilityscore >5 || attendancescore>5 || dependabilityscore >5 
							||communicationscore>5 || collaborationscore >5 ||teamworkscore>5){
						return false;
						
					}
					return true;
					
				}
		
		
		
public boolean softskillscore (IFormReference ifr, String table1, String table2, String table3){
			
			float qualityscore = 0;
			float quantityscore = 0;
			float job_knowledgescore = 0;
			float organisingscore = 0;
			float decisionscore = 0;
			float problemscore = 0;
			float adaptabilityscore = 0;
			float attendancescore = 0;
			float dependabilityscore = 0;
			float communicationscore = 0;
			float collaborationscore = 0;
			float teamworkscore = 0;

					String quality = (String)ifr.getValue("table18_work_quality");
					logger.info("KPI quality score" + quality);
					String quantity = (String)ifr.getValue("table18_work_quantity");
					logger.info("KPI quantity score" + quantity);	
					String job_knowledge = (String)ifr.getValue("table19_job_knowledge");
					logger.info("KPI job_knowledge score" + job_knowledge);	
					String organising = (String)ifr.getValue("table19_org_palanning");
					logger.info("KPI organising score" + organising);	
					String decision = (String)ifr.getValue("table19_decision");
					logger.info("KPI decision score" + decision);	
					String problem = (String)ifr.getValue("table19_problem_solving");
					logger.info("KPI problem score" + problem);	
					String adaptability = (String)ifr.getValue("table19_adaptability");
					logger.info("KPI adaptability score" + adaptability);
					String attendance = (String)ifr.getValue("table19_attendance");
					logger.info("KPI attendance score" + attendance);
					String dependability = (String)ifr.getValue("table19_dependability");
					logger.info("KPI dependability score" + dependability);
					String communication = (String)ifr.getValue("table20_communication");
					logger.info("KPI communication score" + communication);
					String collaboration = (String)ifr.getValue("table20_collaboration");
					logger.info("KPI collaboration score" + collaboration);
					String teamwork = (String)ifr.getValue("table20_teamwork");
					logger.info("KPI teamwork score" + teamwork);
					
					if (!quality.equalsIgnoreCase(EMPTY))
						qualityscore = Float.parseFloat(quality);
					if (!quantity.equalsIgnoreCase(EMPTY))
						quantityscore = Float.parseFloat(quantity);
					if (!job_knowledge.equalsIgnoreCase(EMPTY))
						job_knowledgescore = Float.parseFloat(job_knowledge);
					if (!organising.equalsIgnoreCase(EMPTY))
						organisingscore = Float.parseFloat(organising);
					if (!decision.equalsIgnoreCase(EMPTY))
						decisionscore = Float.parseFloat(decision);
					if (!problem.equalsIgnoreCase(EMPTY))
						problemscore = Float.parseFloat(problem);
					if (!adaptability.equalsIgnoreCase(EMPTY))
						adaptabilityscore = Float.parseFloat(adaptability);
					if (!attendance.equalsIgnoreCase(EMPTY))
						attendancescore = Float.parseFloat(attendance);
					if (!dependability.equalsIgnoreCase(EMPTY))
						dependabilityscore = Float.parseFloat(dependability);
					if (!communication.equalsIgnoreCase(EMPTY))
						communicationscore = Float.parseFloat(communication);
					if (!collaboration.equalsIgnoreCase(EMPTY))
						collaborationscore = Float.parseFloat(collaboration);
					if (!teamwork.equalsIgnoreCase(EMPTY))
						teamworkscore = Float.parseFloat(teamwork);
					
					if (qualityscore>5 || quantityscore>5 ||job_knowledgescore>5 ||organisingscore>5 || decisionscore>5 
							||problemscore>5 || adaptabilityscore >5 || attendancescore>5 || dependabilityscore >5 
							||communicationscore>5 || collaborationscore >5 ||teamworkscore>5){
						return false;
					}
					return true;
					
				}


public String getUsersToNextWorkstep(IFormReference ifr, String groupName){
    
    String staffList ="";
    String query = "select distinct (pu.username) "
            + "from pdbuser pu, pdbgroup g, pdbgroupmember gm where gm.groupindex = (select g.groupindex from PDBGroup where GroupName='"+groupName+"')  "
            + "and pu.userindex in (select userindex from pdbgroupmember "
            + "where groupindex = (select groupindex from PDBGroup where GroupName='"+groupName+"')) and g.groupname = '"+groupName+"'";
    logger.info("query: "+query);
   
    List<List<String>> getUsers = ifr.getDataFromDB(query);
    logger.info("getUsers: "+getUsers);
   
    int count = getUsers.size();
   
    logger.info("count: "+count);
     
         String staff = getUsers.get(0).get(0);
         logger.info("staff: "+staff);
         
    return staff.trim();
}
	
public boolean checkDocuments(IFormReference iFormRef,String winame,String supportingDoc){
              String query = "SELECT COUNT(1) " +
                           "FROM pdbdocument A, PDBDocumentContent B " +
                           "WHERE A.DocumentIndex=B.DocumentIndex " +
                           "AND B.ParentFolderIndex=(select FolderIndex from PDBFolder where name ='"+ winame + "') " +
                           "AND A.Name like '"+ supportingDoc +"%'";
              
              logger.info("query of commons in acknowledge copy: "+query);

       
              List<List<String>> docList = iFormRef.getDataFromDB(query);
              logger.info("this is doclist: "+docList);
              Iterator itdoc = docList.iterator();
              String docCount ="";
              while (itdoc.hasNext()) {
                     docCount = itdoc.next().toString();
                     logger.info("this is docCount: "+docCount);
                     docCount = docCount.substring(docCount.indexOf("[") + 1, docCount.indexOf("]"));
                     logger.info("docCount: "+docCount);
                     int docCounts = Integer.parseInt(docCount);
                     logger.info("docCount: "+docCounts);
                     if (docCounts == 0)
                           return false;
              }
              return true;
       }
		
		
public boolean checkDate (IFormReference ifr){
	Date d= new Date();
	String caseinitationtime=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
	logger.info("Appraisal Window executeServerEvent CLICK--> caseinitationtime ::"+caseinitationtime); 
	String opendate = (String) ifr.getValue("openDate");
	String closedate = (String) ifr.getValue("closeDate");
	logger.info("Appraisal Window  --> ExecuteSeverEvent-->  opendate"+opendate);
	logger.info("Appraisal Window  --> ExecuteSeverEvent-->  closedate "+closedate);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	logger.info("Appraisal Window  --> ExecuteSeverEvent-->  objSDF "+sdf);
	
	Date openDate = new Date();
	try {
		openDate = sdf.parse(opendate);
		logger.info("Appraisal Window  -->Commons-> Close Date Formatted "+openDate);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	Date closeDate = new Date();
	try {
		closeDate = sdf.parse(closedate);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	logger.info("Appraisal Window  -->Commons-> Close Date Formatted "+closeDate);
	
	int diff = openDate.compareTo(closeDate);
	logger.info("Appraisal Window  --> ExecuteSeverEvent--> Date difference"+diff);
					
		if (diff >=0 ) {
		return false;
		}
	return true;
					
		}

public Boolean checkOpenDate(IFormReference iFormRef) {
	
	 Date d= new Date();
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Appraisal Window  --> Commons  "+sdf);
	 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Appraisal Window  --> Commons  "+sdf);
  //  String todaydate=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
		 String todaydate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		logger.info("Appraisal Window --> todays date ::"+todaydate); 
  
		
		String querystatus = "select openDate from ext_sea where " 
  		+" windowstatus IS NOT NULL and openDate IS NOT NULL "
  		+" and closeDate is not null AND currentDate IS NOT NULL " 
  		+" ORDER BY currentDate DESC ";
 
  logger.info("Appraisal Window --> querystatus ::"+querystatus); 
  
  		List<List<String>> data=new ArrayList<List<String>>();
  		data=iFormRef.getDataFromDB(querystatus);
		logger.info("Appraisal Window --> data ::"+data); 
		logger.info("Appraisal Window--> data.size ::"+data.size());
		String opendatetime="";
		Date openDate = new Date();
		logger.info("open date "+ openDate);
		Date todayDate = new Date();
		logger.info("today date "+ todayDate);
		Boolean result = null ;
		
		if(!data.isEmpty())
		{
			opendatetime=data.get(0).get(0);
			logger.info("Appraisal Window--> opendatetime ::"+opendatetime); 
			
			try {
				openDate = sdf2.parse(opendatetime);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.info("Exception occurred in parse open  date : " + e.getMessage());
			}
			logger.info("Appraisal Window  --> Commons--> Close Date Formatted "+openDate);
			
			try {
				todayDate = sdf.parse(todaydate);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.info("Exception occurred in parse today  date : " + e.getMessage());
			}
			logger.info("Appraisal Window  --> Commons--> Todays date formatted "+todayDate);
			
			int diff = todayDate.compareTo(openDate);
			logger.info("Appraisal Window  --> Commons--> Date difference "+diff);
			if (diff <0 ) {
				result= false;
				logger.info("Appraisal Window  --> Commons--> Result "+result);
				}
			else {
				result = true;
				logger.info("Appraisal Window  --> Commons--> Result "+result);
				}
		}
		return result;
			}






public Boolean checkCloseDate(IFormReference iFormRef) {
	
	 Date d= new Date();
//	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		logger.info("Appraisal Window  --> Commons  "+sdf);
//	 SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		logger.info("Appraisal Window  --> Commons  "+sdf);
//     String todaydate=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
//		logger.info("Appraisal Window --> todays date ::"+todaydate); 
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Appraisal Window  --> Commons  "+sdf);
	 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Appraisal Window  --> Commons  "+sdf);
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
		Date closeDate = new Date();
		Date todayDate = new Date();
		Boolean result = null ;
		
		if(!data.isEmpty())
		{
			closeddatetime=data.get(0).get(0);
			logger.info("Appraisal Window--> closeddatetime ::"+closeddatetime); 
			
			try {
				closeDate = sdf2.parse(closeddatetime);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.info("Exception occured in parsing close date: "+ e.getMessage());
			}
			logger.info("Appraisal Window  --> Commons--> Close Date Formatted "+closeDate);
			
			try {
				todayDate = sdf.parse(todaydate);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.info("Exception occured in parsing today date: "+ e.getMessage());
			}
			logger.info("Appraisal Window  --> Commons--> Todays date formatted "+todayDate);
			
			int diff = todayDate.compareTo(closeDate);
			logger.info("Appraisal Window  --> Commons--> Date difference"+diff);
			if (diff >0 ) {
				result= false;
				logger.info("Appraisal Window  --> Commons--> Result "+result);
				}
			else {
				result = true;
				logger.info("Appraisal Window  --> Commons--> Result "+result);
				}
		}
		return result;
			}

public boolean checkDateUpdate (IFormReference ifr){
	Date d= new Date();
	String caseinitationtime=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
	logger.info("Appraisal Window executeServerEvent CLICK--> caseinitationtime ::"+caseinitationtime); 
	//String opendate = (String) ifr.getValue("CaseInitiatedTime"); //get the closed date from previous window
	String closedate = (String) ifr.getValue("closeDate");
	logger.info("Appraisal Window  --> ExecuteSeverEvent-->  closedate "+closedate);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	logger.info("Appraisal Window  --> ExecuteSeverEvent-->  objSDF "+sdf);
//	 SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Appraisal Window  --> Commons  "+sdf2);
	
	
	String querystatus = "select closeDate from ext_sea where " 
	   		+" windowstatus IS NOT NULL and openDate IS NOT NULL "
	   		+" and closeDate is not null AND currentDate IS NOT NULL " 
	   		+" ORDER BY currentDate DESC ";
	  
	   logger.info("Appraisal Window --> querystatus ::"+querystatus); 
	   
	   		List<List<String>> data=new ArrayList<List<String>>();
	   		data=ifr.getDataFromDB(querystatus);
			logger.info("Appraisal Window --> data ::"+data); 
			logger.info("Appraisal Window--> data.size ::"+data.size());
			
			String opendatetime="";
			Date closeDate = new Date();
			Date openDate = new Date();
			Boolean result = null ;
			
			if(!data.isEmpty())
			{
				opendatetime=data.get(0).get(0);
				logger.info("Appraisal Window--> closeddatetime ::"+opendatetime); 
				
				try {
					openDate = sdf2.parse(opendatetime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				logger.info("Appraisal Window  --> Commons--> Close Date Formatted "+closeDate);
				
				try {
					closeDate = sdf.parse(closedate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				logger.info("Appraisal Window  --> Commons--> Todays date formatted "+closeDate);
	
	int diff = openDate.compareTo(closeDate);
	logger.info("Appraisal Window  --> ExecuteSeverEvent--> Date difference"+diff);
					
		if (diff >=0 ) {
			result= false;
		}
		result= true;
					
		}
			return result;
}


public Boolean checkwindowstatus(IFormReference iFormRef) {
	
		String querystatus = "select windowstatus from ext_sea where " 
  		+" windowstatus IS NOT NULL and openDate IS NOT NULL "
  		+" and closeDate is not null AND currentDate IS NOT NULL " 
  		+" ORDER BY currentDate DESC ";
 
  logger.info("Appraisal Window --> querystatus ::"+querystatus); 
  
  List<List<String>> data=new ArrayList<List<String>>();
  data=iFormRef.getDataFromDB(querystatus);
		logger.info("Appraisal Window --> data ::"+data); 
		logger.info("Appraisal Window--> data.size ::"+data.size());
		String windowstatus="";
		Boolean result = null ;
		
		if(!data.isEmpty())
		{
			windowstatus=data.get(0).get(0);
			logger.info("Appraisal Window--> closeddatetime ::"+windowstatus); 
			if (windowstatus.equalsIgnoreCase("ACTIVE") ) {
				result= false;
				}
			else {
				result = true;
				}
		}
		return result;
			}

public Boolean checkwindowstatusIni(IFormReference iFormRef) {
	
	String querystatus = "select windowstatus from ext_sea where " 
		+" windowstatus IS NOT NULL and openDate IS NOT NULL "
		+" and closeDate is not null AND currentDate IS NOT NULL " 
		+" ORDER BY currentDate DESC ";

logger.info("Appraisal Window --> querystatus ::"+querystatus); 

List<List<String>> data=new ArrayList<List<String>>();
data=iFormRef.getDataFromDB(querystatus);
	logger.info("Appraisal Window --> data ::"+data); 
	logger.info("Appraisal Window--> data.size ::"+data.size());
	String windowstatus="";
	Boolean result = null ;
	
	if(!data.isEmpty())
	{
		windowstatus=data.get(0).get(0);
		logger.info("Appraisal Window--> closeddatetime ::"+windowstatus); 
		if (windowstatus.equalsIgnoreCase("CLOSED") ) {
			result= false;
			}
		else {
			result = true;
			}
	}
	return result;
		}


public String getStaffID(IFormReference ifr, String sData)
{
		String staffId = "";
		if(sData.equalsIgnoreCase("aworiginatingstaffid"))
		{
	//clear controls
	ifr.setValue("aworiginatingname", "");
	ifr.setValue("aworiginatingstaffgrade", "");
	ifr.setValue("BRANCH_DEPT_ORIGINATING", "");
	staffId= ((String) ifr.getValue("aworiginatingstaffid")).substring(3);
	
		}
		else if(sData.equalsIgnoreCase("IRResponsibleOfficer"))
		{
	//clear controls
	ifr.setValue("responsible_officer_staff_name", "");
	ifr.setValue("Sol_ID_Responsible_officer", "");
	ifr.setValue("BRANCH_DEPT_RESPONSIBLE", "");
	
	staffId= ((String) ifr.getValue("responsible_officer_staff_ID")).substring(3);
		}
		else if(sData.equalsIgnoreCase("IRauthorizing"))
		{
	//clear controls
	ifr.setValue("autorizing_officer_name", "");
	ifr.setValue("Sol_ID_Authorizing", "");
	ifr.setValue("BRANCH_DEPT_Authorizing", "");
	
	
	staffId= ((String) ifr.getValue("authorizing_officer_staff_ID")).substring(3);
		}
		return staffId;
	}


		public String generateHTML(EControl arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public String introduceWorkItemInWorkFlow(IFormReference arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
			// TODO Auto-generated method stub
			return null;
		}

		public String introduceWorkItemInWorkFlow(IFormReference arg0, HttpServletRequest arg1, HttpServletResponse arg2,
				WorkdeskModel arg3) {
			// TODO Auto-generated method stub
			return null;
		}
			
	}
		
	
	

