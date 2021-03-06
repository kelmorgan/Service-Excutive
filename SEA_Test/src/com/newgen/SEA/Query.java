package com.newgen.SEA;

import java.util.List;

import org.json.simple.JSONArray;

import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;

public class Query extends Commons implements IFormServerEventHandler {

	@Override
	public void beforeFormLoad(FormDef arg0, IFormReference iFormRef) {
		logger.info("--beforeFormLoad Rework HBS--");
		
		
		
		String query = "SELECT UBM.USERID, UBM.BRANCHID BRANCHID FROM LMT_USR_BRANCH_MAPPING_MTR UBM WHERE UPPER(USERID)=UPPER('" + iFormRef.getUserName() + "')";
        
        logger.info(query);



    List<List<String>> getGeneralDetails = iFormRef.getDataFromDB(query);

    	iFormRef.setValue("USERID_HB", iFormRef.getUserName());
   
        
        String branchId = getGeneralDetails.get(0).get(1);
        
        iFormRef.setValue("sol_idbranch", branchId);
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
	public String executeServerEvent(IFormReference arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
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
