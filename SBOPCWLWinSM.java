package enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.csuper;

import java.util.*;
import org.w3c.dom.*;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.client.methods.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.*;

import enlj.webenv.utils.*;
import enlj.projenv.logics.*;

import enlj.p105bet.commonsv11.resource.logics.*;
import enlj.p103http.commonsv1.resource.logics.framework.*;
import enlj.p103http.commonsv1.resource.logics.siteutil.SiteConstants;
import enlj.p105bet.commonsv11.resource.logics.brecord.amode.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.blogic.*;

import enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.aweb.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.dmaster.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.eagent.*;

public class SBOPCWLWinSM extends SBOPCWLWin
{
	public SBOPCWLWinSM (Document oDocument, HCLoginData oLoginData, CloseableHttpClient oHttpClient, String oWindowKey)
    {
		super (oDocument, oLoginData, oHttpClient, oWindowKey);
	}

	public void destroy ()
	{
		super.destroy ();
	}
	
	public void initPageValidText ()
	{
		super.initPageValidText ();
	}

	public void fetchWinLoseFromWebsite ()
	{
		navigateWinLossPage ();

		if (isReadyState ())
			processWinLoss ();
	}
	
	private void processWinLoss ()
	{
		try
		{
			SBOPCWLParserSM oParser = getWinLossParser ();

            String oPullDate = getSiteParam (SBOConstants.kSP_PullDate_Ex);
            if (oPullDate == "notset")
        		oPullDate = getFromDate ();
            
            for (int nIndex = 0; nIndex < oParser.size (); nIndex++)
    		{
    			String [] arrValues = (String [])oParser.get (nIndex);
    			
    			String oMasterId = arrValues [SBOConstants.kDI_CommonId];
    			String oMasterCode = arrValues [SBOConstants.kDI_CommonCode];
    			String oWindowKey = oMasterCode + "_" + SBOConstants.kWK_WinLose_MA;
    
    			Hashtable hashSiteParams = new Hashtable ();
    			hashSiteParams.put (SBOConstants.kSP_HostName, getHostName ());
    			hashSiteParams.put (SBOConstants.kSP_CurrentStage, getCurrentStage ());
    			hashSiteParams.put (SBOConstants.kSP_MasterId_Ex, oMasterId);
    			hashSiteParams.put (SBOConstants.kSP_PullDate_Ex, oPullDate);
    			hashSiteParams.put (SBOConstants.kWLP_Value_EK, getWinLoseParam (SBOConstants.kWLP_Value_EK));
    			hashSiteParams.put (SBOConstants.kWLP_Value_P, getWinLoseParam (SBOConstants.kWLP_Value_P));
    			hashSiteParams.put (SBOConstants.kWLP_Value_MODE, getWinLoseParam (SBOConstants.kWLP_Value_MODE));
    			
                SBOPCWLWinMA oWindow = new SBOPCWLWinMA
                    (getDocument (), getLoginData (), getHttpClient (), oWindowKey);
                
    			oWindow.initWindow (hashSiteParams);
    			oWindow.addDateRecord (oPullDate, oPullDate);
    			oWindow.processWinLoss ();
    		}

    		if (oParser.size () > 0)
    			recordWinLoss_MA (oParser, getFromDate ());
		}
		catch (Exception oException)
		{
			log ("SBOPCWLWinSM:processWinLoss:Exception:" + oException.toString ());
		}
	}
	
	private SBOPCWLParserSM getWinLossParser ()
	{
		int nSiteLoginId = getLoginValueInt (HCLoginData.kSiteLoginId);
		String oCurrencyCode = getLoginValueString (HCLoginData.kCurrencyCode);

		SBOPCWLParserSM oParser = new SBOPCWLParserSM (nSiteLoginId, oCurrencyCode, getLogFilePath ());

		try
		{
			String oContent = getWinLossContent ();
			if (oContent.equals ("notset") == false)
				oParser.parseContent (oContent);
		}
		catch (Exception oException)
		{
			log ("SBOPCWLWinSM:getWinLossParser:Exception:" + oException.toString ());
		}
		
		return oParser;
	}

	private String getWinLossContent ()
	{
		CURRENT_STATUSID = 0;

		HttpPost oMethod = null;
		CloseableHttpResponse oHttpResponse = null;

		String oContent = "notset";
		
		try
		{
			String oURL = "https://" + getHostName () + "/webroot/restricted/report2/report_frame.aspx";
			oMethod = createPostMethod (oURL);
			oMethod.setEntity (new UrlEncodedFormEntity (getPostData_Master ()));

			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_OK) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				oContent = convertToString (oHttpResponse);

				CURRENT_STATUSID = (isPageValidText ("" + SBOConstants.kWS32_PageWL, oContent)) ? kCS_PageSuccess : kCS_PageFailure;
				if (CURRENT_STATUSID == kCS_PageSuccess)
    				CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			log ("SBOPCWLWinSM:getWinLossContent:Exception:" + oException.toString ());

			if (oException.toString ().indexOf ("SocketTimeoutException") >= 0)
				CURRENT_STATUSID = kCS_DataSuccess;
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		return oContent;
	}
	
	private List <NameValuePair> getPostData_Master ()
	{
		String [] arrDate = getFromDate ().split ("-");
		String oDate = arrDate [1] + "/" + arrDate [2] + "/" + arrDate [0];

		List <NameValuePair> arrParams = new ArrayList <NameValuePair> ();
		arrParams.add (new BasicNameValuePair ("chart", ""));
		arrParams.add (new BasicNameValuePair ("dpFrom", oDate)); 
		arrParams.add (new BasicNameValuePair ("dpTo", oDate));
		arrParams.add (new BasicNameValuePair ("ek", getWinLoseParam (SBOConstants.kWLP_Value_EK)));
		arrParams.add (new BasicNameValuePair ("ids", ""));
		arrParams.add (new BasicNameValuePair ("mode", getWinLoseParam (SBOConstants.kWLP_Value_MODE)));
		arrParams.add (new BasicNameValuePair ("p", getWinLoseParam (SBOConstants.kWLP_Value_P)));
		arrParams.add (new BasicNameValuePair ("product", "0"));

		return arrParams;
	}

	private void log (String oMessage)
	{
		logMessage (oMessage);
	}
}
