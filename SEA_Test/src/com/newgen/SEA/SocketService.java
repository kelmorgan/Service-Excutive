package com.newgen.SEA;

import java.awt.Event;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.newgen.SEA.LogGEN;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

public class SocketService {
    
    public static Logger logger= LogGEN.getLoggerInstance(SocketService.class);
    String wiName;
    
        String propFile = System.getProperty("user.dir") + File.separator + "INTEGRATION_Properties" + File.separator + "Integration.properties";
        Properties propbasic = loadPropertyFile(propFile);
        String SocketIP = propbasic.getProperty("SocketIP");
        String tmpSocketPort = propbasic.getProperty("SocketPort");
        int SocketPort = Integer.parseInt(tmpSocketPort);
        //String fetchSESTAFFDETAILS_inputxml;
        String varInputXMLSEA;
        
         
    public String executeIntegrationCall(IFormReference ifr, String serviceName, String inputParam, String inputXml) 
    {
   
    String result = "";
    String varInputXML = "";
    logger.info("serviceName >> :::::" + serviceName);
    logger.info("inputParam >> :::::" + inputParam);
    String[] argRD=inputParam.split("~");
    
    
    if("fetchSEStaffDetails".equalsIgnoreCase(serviceName))
    {
    	varInputXMLSEA= propbasic.getProperty("fetchSESTAFFDETAILS_inputxml");
    }
    
    	
        logger.info("Replace inputParam >> :::::" + varInputXMLSEA);
        
        if("fetchSEStaffDetails".equalsIgnoreCase(serviceName))
        {
        	varInputXMLSEA = varInputXMLSEA.replaceAll("&<StaffID>&",argRD[0]);
            logger.info("Replace inputParam >> :::::" + varInputXMLSEA);
            varInputXML = serviceName+"~"+varInputXMLSEA+"~"+inputParam+"~";
            logger.info("InputXML To Socket >> :::::" + varInputXML);
        }
        
    
    try
    {
      logger.info("Inside Socket Try BLock");
      Socket socket = new Socket(SocketIP, SocketPort);

      System.out.println("Client Connected.....");
      logger.info("Client Connected.....");
      DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
      if ((varInputXML != null) && (varInputXML.length() > 0))
      {
        dout.write(varInputXML.getBytes("UTF-16LE"));
        dout.flush();
      }

      try
      {
    	  logger.info("Inside DataInputStream Try BLock");
    	  DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
          byte[] readBuffer = new byte[1000000];
          int num = in.read(readBuffer);
          logger.info("Num:::::"+ num);
          if (num > 0) {  
            byte[] arrayBytes = new byte[num];
            System.arraycopy(readBuffer, 0, arrayBytes, 0, num);
            result = new String(arrayBytes, "UTF-16LE");
            System.out.println("Result===>>" + result);
          }
          logger.info("Result===>>" + result);

      }
      catch (SocketException localSocketException)
      {
    	  localSocketException.printStackTrace();
      }
      catch (IOException i)
      {
        i.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
    	varInputXML="";
    }

    return result;
  }
    
      
  	public static Properties loadPropertyFile(String filePath) {
		logger.info("Method called");

		logger.info("Property File Path: " + filePath);

		Properties propObj = null;
		try {
			propObj = new Properties();
			propObj.load(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			logger.info("Error");
			propObj = null;
		} catch (IOException e) {
			logger.info("Error IO");
			propObj = null;
		}

		logger.info("METHOD_ENDS");
		return propObj;
	}
    
}