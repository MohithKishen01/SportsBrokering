package enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.csuper;

import java.util.*;
import org.w3c.dom.*;

import enlj.webenv.utils.*;
import enlj.projenv.logics.*;

import enlj.p103http.commonsv1.resource.logics.framework.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.amode.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.aweb.SBOConstants;
import enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.aweb.*;

public class SBOConnectionSM extends SBOConnection
{
    public SBOConnectionSM (Document oDocument, HCLoginData oLoginData)
    {
		super (oDocument, oLoginData);
    }

	public void destroy ()
	{
		super.destroy ();
	}
	
	protected void executeStage_ReadyState ()
	{
		setCurrentStage (kST_ReadyState);
		CURRENT_STATUSID = kCS_DataSuccess;

		addSiteParam (SBOConstants.kSP_CurrentStage, "" + getCurrentStage ());
		setCurrentStatus ("Ready State", CURRENT_STATUSID);
	}

	protected void executeStage_Thread ()
	{
		if (isErrorInHashWindows () == false)
		{
    		if (isReadyState ())
    		{
    			if (isSiteLoginFeatureEnabled (SBOConstants.kSLF_RecordBets))
    			{
    				checkSettledBetWindow ();
    				checkPendingBetWindow ();
    				checkPCWLWindow ();

    			}
    		}
    		setWindowFetchStatus ();
		}
		else
			resetToHome ();
	}
	
	private void checkPendingBetWindow ()
	{
		String oWindowKey = SBOConstants.kWK_PendingBet;
		if (isWindowCreated (oWindowKey) == false)
		{
			SBOBetWinPendingSM oWindow = new SBOBetWinPendingSM 
				(getDocument (), getLoginData (), getHttpClient (), oWindowKey);

			Hashtable hashSiteParams = cloneHashSiteParams ();
			oWindow.initWindow (hashSiteParams);
			oWindow.createThread (SBOConstants.kTN_PendingBet, SBOConstants.kTIM_PendingBet);
			oWindow.startThread ();

			addWindow (oWindowKey, oWindow);
		}
		
		if (isWindowCreated (oWindowKey))
		{
			String oTodayDate = getTodayDate (SBOConstants.kTZ_ZoneOffset);
			SBOBetWinPendingSM oWindow = (SBOBetWinPendingSM)getWindow (oWindowKey);

			oWindow.addDateRecord (oTodayDate, oTodayDate);
			addWindow (oWindowKey, oWindow);
		}
	}

	private void checkSettledBetWindow ()
	{
		String oWindowKey = SBOConstants.kWK_SettledBet;
		if (isWindowCreated (oWindowKey) == false)
		{
			SBOBetWinSettledSM oWindow = new SBOBetWinSettledSM
				(getDocument (), getLoginData (), getHttpClient (), oWindowKey);

			Hashtable hashSiteParams = cloneHashSiteParams ();
			oWindow.initWindow (hashSiteParams);
			oWindow.createThread (SBOConstants.kTN_SettledBet, SBOConstants.kTIM_SettledBet);
			oWindow.startThread ();

			addWindow (oWindowKey, oWindow);
		}

		String oPullDate = getUserBetsPullDate ();
		if (oPullDate.equals (RecConstants.kDefaultDate) == false)
		{
			if (isWindowCreated (oWindowKey))
			{
				SBOBetWinSettledSM oWindow = (SBOBetWinSettledSM)getWindow (oWindowKey);
				
				oWindow.addDateRecord (oPullDate, oPullDate);
				addWindow (oWindowKey, oWindow);
			}
		}
	}
	
	private void checkPCWLWindow ()
	{
		String oWindowKey = getLoginValueString (HCLoginData.kUserName) + "_" + SBOConstants.kWK_PCWLWindow;
		if (isWindowCreated (oWindowKey) == false)
		{
			SBOPCWLWinSM oWindow = new SBOPCWLWinSM
				 (getDocument (), getLoginData (), getHttpClient (), oWindowKey);

			Hashtable hashSiteParams = new Hashtable ();
			hashSiteParams.put (SBOConstants.kSP_HostName, getHostName ());
			hashSiteParams.put (SBOConstants.kSP_CurrentStage, getCurrentStage ());
			
			oWindow.initWindow (hashSiteParams);
			oWindow.createThread (oWindowKey, SBOConstants.kTIM_PCWL_SM);
			oWindow.startThread ();

			addWindow (oWindowKey, oWindow);
		}
			
		String oPullDate = getUserPullDate_PCWL ();
		if (oPullDate.equals (RecConstants.kDefaultDate) == false)
		{
    		if (isWindowCreated (oWindowKey))
    		{
    			SBOPCWLWinSM oWindow = (SBOPCWLWinSM)getWindow (oWindowKey);
    			oWindow.addDateRecord (oPullDate, oPullDate);
    			addWindow (oWindowKey, oWindow);
    		}
		}
	}

	private void setWindowFetchStatus ()
	{
		if (isWindowCreated (SBOConstants.kWK_PendingBet))
		{
			SBOBetWinPendingSM oPendingWindow = (SBOBetWinPendingSM)getWindow (SBOConstants.kWK_PendingBet);
			if (isWindowCreated (SBOConstants.kWK_SettledBet))
			{
				SBOBetWinSettledSM oSettledWindow = (SBOBetWinSettledSM)getWindow (SBOConstants.kWK_SettledBet);

				if (oPendingWindow.isFetchStatusEnabled () && oPendingWindow.isFetchCompleted ())
				{
					oPendingWindow.setFetchStatus (false);
					oPendingWindow.setFetchCompleted (false);

					oSettledWindow.setFetchStatus (true);
					oSettledWindow.setFetchCompleted (false);
				}
				else if (oSettledWindow.isFetchStatusEnabled () && oSettledWindow.isFetchCompleted ())
				{
					oSettledWindow.setFetchStatus (false);
					oSettledWindow.setFetchCompleted (false);

					oPendingWindow.setFetchStatus (true);
					oPendingWindow.setFetchCompleted (false);
				}
			}
		}
	}

	private void log (String oMessage)
	{
		logMessage (oMessage);
	}
}
