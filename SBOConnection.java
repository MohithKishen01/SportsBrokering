package enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.aweb;

import java.util.*;
import java.io.*;
import org.w3c.dom.*;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.http.client.methods.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.*;

import enlj.webenv.utils.*;
import enlj.projenv.logics.*;

import enlj.p103http.commonsv1.resource.logics.framework.*;
import enlj.p103http.commonsv1.resource.logics.siteutil.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.amode.*;

abstract public class SBOConnection extends RecConnection
{
	protected int kAdditionalDelay = -110000;

    public SBOConnection (Document oDocument, HCLoginData oLoginData)
    {
		super (oDocument, oLoginData);
		initPageValidText ();
    }

	public void destroy ()
	{
		super.destroy ();
	}

	public void initRecording (String oSecurityCodeURL)
	{
		setSecurityCodeURL (oSecurityCodeURL);

		setOriginalHost (getLoginValueString (HCLoginData.kHostName));
		initHostConfig ();

		String oThreadName = "SBOConnection_" + getLoginValueString (HCLoginData.kSiteLoginId);
		createThread (oThreadName, kAdditionalDelay);
		
		int nStatusId = getLoginValueInt (HCLoginData.kStatusId);
		if (nStatusId == SiteConstants.kLS_Started)
			startThread ();
	}
	
    protected void initHostConfig ()
    {
		initSiteLoginFeatures_PM ();

		if (getHttpClient () != null)
			setHttpClient (null);

		String oHostName = getOriginalHost ();
		setHostName (oHostName);

		CloseableHttpClient oHttpClient = HttpClients.createDefault ();
		setHttpClient (oHttpClient);
		
		setCurrentStage (SiteConstants.kST1_Home);
		setSystemStatus ("CurrentState >> HostConfig >> Done");
    }
	
	protected void initPageValidText ()
	{
		addPageValidText ("" + SiteConstants.kST1_Home, "name=\"username\"");
		addPageValidText ("" + SBOConstants.kST101_UnderMaintainance, "UNDER MAINTENANCE");

		addPageValidText ("" + SiteConstants.kST2_Login, "/Login/Login");
		addPageValidText ("" + SiteConstants.kST3_Welcome, "proccess-welcome.aspx");
		
		addPageValidText ("" + SBOConstants.kST201_AfterLoginPage1, "/Security");
		addPageValidText ("" + SBOConstants.kST202_AfterLoginPage2, "/Security/SecurityCode");
		addPageValidText ("" + SBOConstants.kST203_AfterLoginPage3, "/Login/ProcessSecurityResult");
		addPageValidText ("" + SBOConstants.kST204_AfterLoginSCPage, "name=\"securityCodeForm");
		addPageValidText ("" + SBOConstants.kST205_AfterLoginVSCPage, "/Login/ProcessSecurityResult");
		addPageValidText ("" + SBOConstants.kST206_AfterLoginPSRPage, "/welcome.aspx");
		
		addPageValidText ("" + SBOConstants.kST301_ProcessWelcome, "/webroot/restricted/home.aspx");
		addPageValidText ("" + SBOConstants.kST302_AfterWelcome, "<frame src=\"HomeTop.aspx\"");
		
		
		addPageValidText ("" + SiteConstants.kST4_TandC, "home.aspx");
		addPageValidText ("" + SiteConstants.kST98_ChangePwd, "password.aspx");
		addPageValidText ("" + SiteConstants.kST99_Logout, "login/processsecurityresult");
	}
	
    public boolean startRecording ()
    {
		initHostConfig ();
		startThread ();

		return true;
    }

    public boolean stopRecording ()
    {
		stopThread ();
		destroyConnections ();

		if (getCurrentStage () != SiteConstants.kST1_Home)
			executeStage99_Logout ();

		return true;
    }

	public void resetConnection (HCLoginData oLoginData)
	{
		setLoginData (oLoginData);
		stopRecording ();

		setLoginSecurityCode (kCS_LSCLoadFailure, "0");
		initHostConfig ();

		int nStatusId = getLoginValueInt (HCLoginData.kStatusId);
		if (nStatusId == SiteConstants.kLS_Started)
			startThread ();
	}
	
	protected void resetToHome ()
	{
		log ("SBOConnection >> resetToHome >> CurrentStage >> " + getCurrentStage () + " >> " + CURRENT_STATUSID);

		stopRecording ();
		startRecording ();

		setLoginSecurityCode (kCS_LSCLoadFailure, "0");
		setCurrentStatus ("Login Resetting...", kCS_Logout);
	}

	protected void alertChangePassword ()
	{
		setHostName (getOriginalHost ());
		setCurrentStatus ("Please Change the Password...on the website.", kCS_ChangePwd);
		executeStage98_ChangePwd ();
	}

	protected void checkReadyState ()
	{
		try
		{
			setActionCompleted (false);
			switch (getCurrentStage ())
			{
				case SiteConstants.kST1_Home :
					executeStage1_Home ();
					break;
				case SiteConstants.kST2_Login :
					executeStage2_Login ();
					break;
				case SiteConstants.kST3_Welcome :
					executeStage3_Welcome ();
					break;
				case SiteConstants.kST4_TandC :
					executeStage4_TandC ();
					break;
				case kST_ReadyState :
					executeStage_ReadyState ();
					break;
			}

			executeStage_Thread ();
			setActionCompleted (true);
		}
		catch (Exception oException)
		{
			log ("SBOConnection:checkReadyState:Exception:" + oException.toString ());
			setActionCompleted (true);
		}
	}
	
	protected void executeStage1_Home ()
	{
		setCurrentStage (SiteConstants.kST1_Home);
		CURRENT_STATUSID = 0;
		String oStatusText = "Home"; 
		
		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;
				
		try
		{
			oMethod = createGetMethod ("https://" + getHostName () + "/?lg=EN");
			
			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_OK) ? kCS_HttpSuccess : kCS_HttpFailure;

			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				String oContent = convertToString (oHttpResponse);
				
                if (isPageValidText ("" + SBOConstants.kST101_UnderMaintainance, oContent))
                {
                	oStatusText = getMaintenanceMessage (oContent);
                	CURRENT_STATUSID = kCS_DataFailure; 
                }
                else
                {
                	CURRENT_STATUSID = isPageValidText ("" + SiteConstants.kST1_Home, oContent) ? 
                		kCS_PageSuccess : kCS_PageFailure;
                }
                
                if (CURRENT_STATUSID == kCS_PageSuccess)
                {
                	setSiteParams_Home (oContent);
                	CURRENT_STATUSID = kCS_DataSuccess;
                }
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:executeStage1_Home:Exception:" + oException.toString());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (CURRENT_STATUSID == kCS_DataSuccess)
			executeStage2_Login ();
	}
		
	private String getMaintenanceMessage (String oContent)
	{
		String oTime = DataUtil.formatValue (oContent, "<strong class=\"Red\">", 1);
		oTime = DataUtil.formatValue (oTime, "</strong>", 0);
		
		String [] arrTime = oTime.split (" ");
		String oStatusText = "Maintainace Till " + arrTime [1] + " " + arrTime [2] + " HKT >>";
		
		return oStatusText; 
	}


	protected void storeSecurityCodeImage ()
	{
	}

	protected void executeStage2_Login ()
	{
		setCurrentStage (SiteConstants.kST2_Login);
		CURRENT_STATUSID = 0;
		String oStatusText = "Login";
		
		HttpPost oMethod = null;
		CloseableHttpResponse oHttpResponse = null;
		
		try
		{
			oMethod = createPostMethod ("https://" + getHostName () + "/Captcha");
			oMethod.setEntity (new UrlEncodedFormEntity (getPostData_Login ()));

			RequestConfig oRequestConfig = RequestConfig.custom ().setRedirectsEnabled (false).build ();
			oMethod.setConfig (oRequestConfig);			
			
			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_MOVED_TEMPORARILY) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				String oLocation = "notset";
				if (oHttpResponse.getHeaders ("Location")[0] != null)
					oLocation = oHttpResponse.getHeaders ("Location")[0].getValue ();
				
				CURRENT_STATUSID = isPageValidText ("" + SiteConstants.kST2_Login, oLocation) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:executeStage2_Login:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (CURRENT_STATUSID == kCS_DataSuccess)
			navigateToAfterLogin_Page1 ();
		else if (isValidLoginSecCode ())
			resetToHome ();
	}

	private List <NameValuePair> getPostData_Login ()
	{
		String oUserName = getLoginValueString (HCLoginData.kUserName);
		String oPassword = getLoginValueString (HCLoginData.kPassword);
		String oReqVerToken = getSiteParam (SBOConstants.kSP_ReqVerToken);

		List <NameValuePair> arrParams = new ArrayList<NameValuePair> ();
		arrParams.add (new BasicNameValuePair ("__RequestVerificationToken", oReqVerToken));
		arrParams.add (new BasicNameValuePair ("btnSubmit", "Sign In"));
		arrParams.add (new BasicNameValuePair ("lang", "EN"));
		arrParams.add (new BasicNameValuePair ("username", oUserName));
		arrParams.add (new BasicNameValuePair ("password", oPassword));

		return arrParams;
	}

	protected void navigateToAfterLogin_Page1 ()
	{
		CURRENT_STATUSID = 0;
		String oStatusText = "AfterLogin:Page1"; 

		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;

		try
		{
			oMethod = createGetMethod ("https://" + getHostName () + "/Login/Login");
			
			RequestConfig oRequestConfig = RequestConfig.custom ().setRedirectsEnabled (false).build ();
			oMethod.setConfig (oRequestConfig);			

			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_MOVED_TEMPORARILY) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				String oLocation = "notset";
				if (oHttpResponse.getHeaders ("Location")[0] != null)
					oLocation = oHttpResponse.getHeaders ("Location")[0].getValue ();
			
				CURRENT_STATUSID = isPageValidText ("" + SBOConstants.kST201_AfterLoginPage1, oLocation) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:navigateToAfterLogin_Page1:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (CURRENT_STATUSID == kCS_DataSuccess)
			navigateToAfterLogin_Page2 ();
	}
	
	protected void navigateToAfterLogin_Page2 ()
	{
		CURRENT_STATUSID = 0;
		String oStatusText = "AfterLogin:Page2"; 

		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;
		
		String oLocation = "notset";

		try
		{
			oMethod = createGetMethod ("https://" + getHostName () + "/Security");
			
			RequestConfig oRequestConfig = RequestConfig.custom ().setRedirectsEnabled (false).build ();
			oMethod.setConfig (oRequestConfig);			

			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_MOVED_TEMPORARILY) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				if (oHttpResponse.getHeaders ("Location")[0] != null)
					oLocation = oHttpResponse.getHeaders ("Location")[0].getValue ();

				CURRENT_STATUSID = isPageValidText ("" + SBOConstants.kST202_AfterLoginPage2, oLocation) ||
					isPageValidText ("" + SBOConstants.kST203_AfterLoginPage3, oLocation) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:navigateToAfterLogin_Page2:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (isPageValidText ("" + SBOConstants.kST202_AfterLoginPage2, oLocation) && CURRENT_STATUSID == kCS_DataSuccess)
			navigateToAfterLogin_SCPage ();
		else if (isPageValidText ("" + SBOConstants.kST203_AfterLoginPage3, oLocation) && CURRENT_STATUSID == kCS_DataSuccess)
			navigateToAfterLogin_PSRPage ();
	}

	protected void navigateToAfterLogin_SCPage ()
	{
		CURRENT_STATUSID = 0;
		String oStatusText = "AfterLogin:SecurityCode"; 

		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;
		
		String oContent = "notset";
		
		try
		{
			oMethod = createGetMethod ("https://" + getHostName () + "/Security/SecurityCode");
			
			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_OK) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				oContent = convertToString (oHttpResponse);
				CURRENT_STATUSID = isPageValidText ("" + SBOConstants.kST204_AfterLoginSCPage, oContent) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:navigateToAfterLogin_SCPage:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (CURRENT_STATUSID == kCS_DataSuccess)
			navigateToAfterLogin_VSCPage (oContent);
	}

	protected void navigateToAfterLogin_VSCPage (String oContent)
	{
		CURRENT_STATUSID = 0;
		String oStatusText = "AfterLogin:ValidateSC"; 

		HttpPost oMethod = null;
		CloseableHttpResponse oHttpResponse = null;
				
		try
		{
			oMethod = createPostMethod ("https://" + getHostName () + "/Security/ValidateSecurityCode");
			oMethod.setEntity (new UrlEncodedFormEntity (getPostData_VSC (oContent)));

			RequestConfig oRequestConfig = RequestConfig.custom ().setRedirectsEnabled (false).build ();
			oMethod.setConfig (oRequestConfig);			

			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_MOVED_TEMPORARILY) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				String oLocation = "notset";
				
				if (oHttpResponse.getHeaders ("Location")[0] != null)
					oLocation = oHttpResponse.getHeaders ("Location")[0].getValue ();

				CURRENT_STATUSID = isPageValidText ("" + SBOConstants.kST205_AfterLoginVSCPage, oLocation) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:navigateToAfterLogin_VSCPage:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (CURRENT_STATUSID == kCS_DataSuccess)
			navigateToAfterLogin_PSRPage ();
		else
			resetToHome ();
	}
	
	protected List <NameValuePair> getPostData_VSC (String oContent)
	{
		String oReqVerToken = getSiteParam (SBOConstants.kSP_ReqVerToken);

		String oFirstNumber = DataUtil.formatValue (oContent, "<span id=\"firstposition\">", 1);
		oFirstNumber = DataUtil.formatValue (oFirstNumber, "<sup>", 0).trim ();
		
		String oSecondNumber = DataUtil.formatValue (oContent, "<span id=\"secondposition\">", 1);
		oSecondNumber = DataUtil.formatValue (oSecondNumber, "<sup>", 0).trim ();

		List <NameValuePair> arrParams = new ArrayList<NameValuePair> ();
				
		arrParams.add (new BasicNameValuePair ("__RequestVerificationToken", oReqVerToken));
		arrParams.add (new BasicNameValuePair ("btnSubmit", "Submit"));
		arrParams.add (new BasicNameValuePair ("FirstChar", findDigit (oFirstNumber)));
		arrParams.add (new BasicNameValuePair ("SecondChar", findDigit (oSecondNumber)));
		arrParams.add (new BasicNameValuePair ("hidIsFromSecurityCode", "1"));
		arrParams.add (new BasicNameValuePair ("hidKey", "<%=sessionId %>"));
		arrParams.add (new BasicNameValuePair ("hiduseDesktop", ""));

		return arrParams;
	}
 
	protected String findDigit (String oNumber)
	{
		if (oNumber.equals ("1") || oNumber.equals ("4"))
			oNumber = "1";
		else if (oNumber.equals ("2") || oNumber.equals ("5"))
			oNumber = "2";
		else if (oNumber.equals ("3") || oNumber.equals ("6"))
			oNumber = "3";
		
		return oNumber;
	}
	
	protected void navigateToAfterLogin_PSRPage ()
	{
		CURRENT_STATUSID = 0;
		String oStatusText = "AfterLogin:ProcessSR"; 

		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;
				
		try
		{
			oMethod = createGetMethod ("https://" + getHostName () + "/Login/ProcessSecurityResult");
			
			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_OK) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				String oContent = convertToString (oHttpResponse);
				CURRENT_STATUSID = isPageValidText ("" + SBOConstants.kST206_AfterLoginPSRPage, oContent) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
				{
					setSiteParams_Login (oContent);
					CURRENT_STATUSID = kCS_DataSuccess;
				}
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:navigateToAfterLogin_PSRPage:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (CURRENT_STATUSID == kCS_DataSuccess)
			executeStage3_Welcome ();
		else
			resetToHome ();
	}
	
 	protected void executeStage3_Welcome ()
	{
		setCurrentStage (SiteConstants.kST3_Welcome);
		CURRENT_STATUSID = 0;
		String oStatusText = "Welcome";

		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;

		String oLocation = "notset";

		try
		{
			String oQueryString = getQueryString_Welcome ();
			oMethod = createGetMethod ("https://" + getHostName () + "/welcome.aspx?" + oQueryString);

			RequestConfig oRequestConfig = RequestConfig.custom ().setRedirectsEnabled (false).build ();
			oMethod.setConfig (oRequestConfig);			

			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_MOVED_TEMPORARILY) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				if (oHttpResponse.getHeaders ("Location")[0] != null)
					oLocation = oHttpResponse.getHeaders ("Location")[0].getValue ();

				CURRENT_STATUSID = isPageValidText ("" + SiteConstants.kST3_Welcome, oLocation) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:executeStage3_Welcome:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (CURRENT_STATUSID == kCS_DataSuccess)
			navigateToProcessWelcome (oLocation);
		else
			resetToHome ();
	}

	protected String getQueryString_Welcome ()
	{
		StringBuffer oBuffer = new StringBuffer ();
		oBuffer.append ("id=" + getSiteParam (SBOConstants.kSP_Welcome_ID) + "&");
		oBuffer.append ("key=" + getSiteParam (SBOConstants.kSP_Welcome_KEY) + "&");
		oBuffer.append ("lang=" + getSiteParam (SBOConstants.kSP_Welcome_LANG) + "&");
		oBuffer.append ("useDesktop=yes");

		return oBuffer.toString ();
	}

	protected void navigateToProcessWelcome (String oProcessURL)
	{
		CURRENT_STATUSID = 0;
		
		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;

		String oLocation = "notset";

		try
		{
			oMethod = createGetMethod ("https://" + getHostName () + oProcessURL);
			
			RequestConfig oRequestConfig = RequestConfig.custom ().setRedirectsEnabled (false).build ();
			oMethod.setConfig (oRequestConfig);			

			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_MOVED_TEMPORARILY) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				if (oHttpResponse.getHeaders ("Location")[0] != null)
					oLocation = oHttpResponse.getHeaders ("Location")[0].getValue ();

				if (oLocation.indexOf ("termsconditions.aspx") < 0)
					CURRENT_STATUSID = isPageValidText ("" + SBOConstants.kST301_ProcessWelcome, oLocation) ? kCS_PageSuccess : kCS_PageFailure;
				else
					CURRENT_STATUSID = kCS_PageSuccess;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:navigateToProcessWelcome:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		if (isPageValidText ("" + SiteConstants.kST98_ChangePwd, oLocation))
			alertChangePassword ();
		else if (CURRENT_STATUSID == kCS_DataSuccess && oLocation.indexOf ("termsconditions") >= 0)
			navigateTandCPage ();
		else if (CURRENT_STATUSID == kCS_DataSuccess && oLocation.indexOf ("termsconditions") < 0)
			navigateToAfterWelcomePage ();
		else
			resetToHome ();
	}

	protected void navigateTandCPage ()
	{
		CURRENT_STATUSID = 0;
		
		HttpPost oMethod = null;
		CloseableHttpResponse oHttpResponse = null;

		try
		{
			oMethod = createPostMethod  ("https://" + getHostName () + "/webroot/restricted/tc/termsconditions.aspx");
			oMethod.setEntity (new UrlEncodedFormEntity (getPostData_TandC ()));

			RequestConfig oRequestConfig = RequestConfig.custom ().setRedirectsEnabled (false).build ();
			oMethod.setConfig (oRequestConfig);			

			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_MOVED_TEMPORARILY) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				String oLocation = "notset";
				if (oHttpResponse.getHeaders ("Location")[0] != null)
					oLocation = oHttpResponse.getHeaders ("Location")[0].getValue ();

				CURRENT_STATUSID = isPageValidText ("" + SiteConstants.kST4_TandC, oLocation) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:navigateTandCPage:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		if (CURRENT_STATUSID == kCS_DataSuccess)
			navigateToAfterWelcomePage ();
		else
			resetToHome ();
	}

	private List <NameValuePair> getPostData_TandC ()
	{
		List <NameValuePair> arrParams = new ArrayList<NameValuePair> ();
		arrParams.add (new BasicNameValuePair ("agree", "1"));

		return arrParams;
	}

	protected void navigateToAfterWelcomePage ()
	{
		CURRENT_STATUSID = 0;
		String oStatusText = "AfterWelcome"; 

		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;
	
		try
		{
			oMethod = createGetMethod ("https://" + getHostName () + "/webroot/restricted/home.aspx");
			
			oHttpResponse = getHttpClient ().execute (oMethod);
			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();

			CURRENT_STATUSID = (nStatusCode == HttpStatus.SC_OK) ? kCS_HttpSuccess : kCS_HttpFailure;
			if (CURRENT_STATUSID == kCS_HttpSuccess)
			{
				String oContent = convertToString (oHttpResponse);
				CURRENT_STATUSID = isPageValidText ("" + SBOConstants.kST302_AfterWelcome, oContent) ? kCS_PageSuccess : kCS_PageFailure;

				if (CURRENT_STATUSID == kCS_PageSuccess)
					CURRENT_STATUSID = kCS_DataSuccess;
			}
		}
		catch (Exception oException)
		{
			CURRENT_STATUSID = kCS_DataFailure;
			log ("SBOConnection:navigateToAfterWelcomePage:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}

		setCurrentStatus (oStatusText, CURRENT_STATUSID);

		if (CURRENT_STATUSID == kCS_DataSuccess)
			executeStage4_TandC ();
		else
			resetToHome ();
	}
	
	protected void executeStage4_TandC ()
	{
		setCurrentStage (SiteConstants.kST4_TandC);
		CURRENT_STATUSID = kCS_DataSuccess;
		
		executeStage_ReadyState ();
	}
	
	protected void executeStage98_ChangePwd ()
	{
		setCurrentStage (SiteConstants.kST98_ChangePwd);
		CURRENT_STATUSID = 0;
	}

	protected void executeStage99_Logout ()
	{
		HttpGet oMethod = null;
		CloseableHttpResponse oHttpResponse = null;

		try
		{
			oMethod = createGetMethod ("https://" + getHostName () + "/logout.aspx");
			oHttpResponse = getHttpClient ().execute (oMethod);

			int nStatusCode = oHttpResponse.getStatusLine ().getStatusCode ();
			if (nStatusCode == HttpStatus.SC_OK)
			{
				String oContent = convertToString (oHttpResponse);

				if (isPageValidText ("" + SiteConstants.kST99_Logout, oContent))
				{
					String oUserName = getLoginValueString (HCLoginData.kUserName);
					log ("SBOConnection:executeStage99_Logout >> " + oUserName + "........"); 
				}
			}
		}
		catch (Exception oException)
		{
			log ("SBOConnection:executeStage99_Logout:Exception:" + oException.toString ());
		}

		finally
		{
			try {oMethod.releaseConnection (); oHttpResponse.close ();} 
			catch (Exception oException) {oException.toString ();};
		}
	}

	private void initSiteParams ()
	{
		int nUpdateWL = isSiteLoginFeatureEnabled (SBOConstants.kSLF_UpdateWL) ? 1 : 0;
		
		addSiteParam (SBOConstants.kSP_HostName, "notset");
		addSiteParam (SBOConstants.kSP_SiteCookie, "notset");
		addSiteParam (SBOConstants.kSP_CurrentStage, "0");
		addSiteParam (SBOConstants.kSP_IsUpdateWL, "" + nUpdateWL);

		addSiteParam (SBOConstants.kSP_Welcome_ID, "notset");
		addSiteParam (SBOConstants.kSP_Welcome_KEY, "notset");
		addSiteParam (SBOConstants.kSP_Welcome_LANG, "notset");
		
		/* BET WINDOW --------------------------------------- */
		addSiteParam (SBOConstants.kSP_FromDate, "notset");
		addSiteParam (SBOConstants.kSP_ToDate, "notset");
	}
		
	private void setSiteParams_Home (String oResponse)
	{
		initSiteParams ();
		
		String oReqVerToken = DataUtil.formatValue (oResponse, "__RequestVerificationToken\" type=\"hidden\" value=\"", 1);
		oReqVerToken = DataUtil.formatValue (oReqVerToken, "\" />", 0);
	
		addSiteParam (SBOConstants.kSP_ReqVerToken, oReqVerToken);
	}
	
	private void setSiteParams_Login (String oContent)
	{
		String [] arrData = oContent.split ("action='https://");

		if (arrData.length == 2)
		{
			String oHostName = DataUtil.formatValue (arrData [1], "/welcome.aspx", 0);
			setHostName (oHostName);
		}

		String oId = DataUtil.formatValue (oContent, "name='id' value='", 1);
		oId = DataUtil.formatValue (oId, "'", 0);

		String oKey = DataUtil.formatValue (oContent, "name='key' value='", 1);
		oKey = DataUtil.formatValue (oKey, "'", 0);

		String oLang = DataUtil.formatValue (oContent, "name='lang' value='", 1);
		oLang = DataUtil.formatValue (oLang, "'", 0);

		addSiteParam (SBOConstants.kSP_HostName, getHostName ());
		addSiteParam (SBOConstants.kSP_Welcome_ID, oId);
		addSiteParam (SBOConstants.kSP_Welcome_KEY, oKey);
		addSiteParam (SBOConstants.kSP_Welcome_LANG, oLang);
	}
	
	private void log (String oMessage)
	{
		logMessage (oMessage);
	}
}
