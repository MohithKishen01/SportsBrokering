package enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.csuper;

import java.util.*;
import org.w3c.dom.*;

import org.apache.http.impl.client.*;

import enlj.p103http.commonsv1.resource.logics.framework.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.amode.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.aweb.*;

public class SBOBetWinPendingSM extends SBOBetWinPending
{
	SBOListWinSuper m_oSuperWindow = null;

    public SBOBetWinPendingSM (Document oDocument, HCLoginData oLoginData, CloseableHttpClient oHttpClient, String oWindowKey)
    {
		super (oDocument, oLoginData, oHttpClient, oWindowKey);
    }

	public void destroy ()
	{
		super.destroy ();
		
		if (m_oSuperWindow != null)
			m_oSuperWindow.destroy ();
	}

	public void initPageValidText ()
	{
		super.initPageValidText ();
	}

	protected void processThreadTask ()
	{
		if (isFetchStatusEnabled ())
		{
			setFetchCompleted (false);
			super.processThreadTask ();
			setFetchCompleted (true);
		}
	}
	
	protected void fetchBetsFromWebsite ()
	{
		if (isReadyState () && getSuperWindow () != null)
		{
			navigateToOutStandingNewPage ();
			
			SBOListWinSuper oSuperWindow = getSuperWindow ();
			oSuperWindow.initListParser ();
			oSuperWindow.initBetParams (getBetParams ());
			
			while (oSuperWindow.moveNextMaster ())
			{
				while (oSuperWindow.moveNextAgent ())
				{
					while (oSuperWindow.moveNextMember ())
					{
						Hashtable hashParamsEx = new Hashtable ();
						hashParamsEx.put (BetConstants.kPP_MatchDate, getFromDate ());
						hashParamsEx.put (SBOConstants.kSP_IsUpdateWL, getSiteParam (SBOConstants.kSP_IsUpdateWL));

						String oBetContent = oSuperWindow.getBetContent ();
						oSuperWindow.updateCode (hashParamsEx);

						SBOBetParserPending oParser = new SBOBetParserPending (getLogFilePath (), getWindowKey (), hashParamsEx);
						if (oParser.parseContent (oBetContent))
							addBetParserToArray (oParser);
					}
				}
			}

			try
			{
    			ArrayList arrParser = getBetParserArray ();
    			
    			if (arrParser != null)
    			{
        			for (int nIndex = 0; nIndex < arrParser.size (); nIndex++)
        			{
        				SBOBetParser oParser = (SBOBetParser)arrParser.get (nIndex);
        				recordBetsToDB (oParser);
        			}
        			setBetParserArray (null);
    			}
			}
			catch (Exception oException)
			{
				log ("SBOBetWinPendingSM:fetchBetsFromWebsite:Exception:" + oException.toString ());
			}
		}
	}
	
	protected SBOListWinSuper getSuperWindow ()
	{
		if (m_oSuperWindow == null)
		{
			m_oSuperWindow = new SBOListWinSuper (getDocument (), getLoginData (), getHttpClient (), SBOConstants.kWK_PendingBet);
			m_oSuperWindow.initWindow (getHashSiteParams ());
			m_oSuperWindow.setHashBlockedMembers (getHashBlockedMembers ());
		}
		else
			m_oSuperWindow.setHashSiteParams (getHashSiteParams ());

		return m_oSuperWindow;
	}

	public boolean isErrorInWindow ()
	{
		boolean bStatus = super.isErrorInWindow ();
		if (bStatus == false)
		{
			if (getSuperWindow () != null)
				bStatus = getSuperWindow ().isErrorInWindow ();
		}
		
		return bStatus;
	}

	private void log (String oMessage)
	{
		logMessage (oMessage);
	}
}
