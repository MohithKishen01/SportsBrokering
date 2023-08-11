package enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.dmaster;

import java.util.*;
import org.w3c.dom.*;

import org.apache.http.impl.client.*;

import enlj.webenv.utils.*;
import enlj.projenv.logics.*;

import enlj.p103http.commonsv1.resource.logics.framework.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.amode.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.aweb.*;

public class SBOConnectionMA extends SBOConnection
{
    public SBOConnectionMA (Document oDocument, HCLoginData oLoginData)
    {
		super (oDocument, oLoginData);
    }

	public void destroy ()
	{
		super.destroy ();
	}
	
	protected void initPageValidText ()
	{
		super.initPageValidText ();
		addPageValidText ("" + SBOConstants.kST301_ProcessWelcome, "home.aspx");
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
	}
	
	private void log (String oMessage)
	{
//		logMessage (oMessage);
	}
}
