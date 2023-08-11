package enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.csuper;

import java.util.*;

import org.w3c.dom.*;

import enlj.webenv.utils.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.amode.*;
import enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.aweb.*;

public class SBOPCWLParserSM extends SBOPCWLParser
{
	String m_oSuperCode = "notset";

	/* Master Array List Index */
	private final int kDI_MemberVolume		= 3;
	private final int kDI_MasterTotal 		= 4;
	private final int kDI_SuperWinLoss 		= 5;
	private final int kDI_SuperCommission	= 6;
	private final int kDI_SuperTotal 		= 7;
	private final int kDI_Company 			= 8;

	/* Data Cell Index */
	private final int kWI_MasterId 			= 1;
	private final int kWI_MasterCode 		= 3;
	private final int kWI_MasterTurnover	= 4;
	private final int kWI_MasterTotal		= 14;
	private final int kWI_SuperWinLoss		= 15;
	private final int kWI_SuperCommission	= 16;
	private final int kWI_SuperTotal		= 17;
	private final int kWI_CompanyTotal		= 18;
	
	protected int kDataLength 	= 20; 
			
	public SBOPCWLParserSM (int nSiteLoginId, String oCurrencyCode, String oLogFilePath)
	{
		super (nSiteLoginId, oCurrencyCode, oLogFilePath);
	}

	public boolean parseContent (String oContent)
	{
		try
		{
			setSuperCode (oContent);
			
			String [] arrRows = getRows (oContent);
			if (arrRows != null && arrRows.length > 0)
			{
				for (int nIndex = 1; nIndex < arrRows.length; nIndex++)
				{
					String oRow = arrRows [nIndex];

					String [] arrCells = getCells (oRow);
					if (isValidColumnCount (arrCells))
					{
						String [] arrInfo = parseCells (arrCells);
						this.add (arrInfo);
					}
				}
			}
		}
		catch (Exception oException)
		{
			log ("LFPinBetParser:parseContent:Exception:" + oException.toString ());
		}

		return (this.size () > 0);
	}
	
	protected boolean isValidColumnCount (String [] arrCells)
	{
		boolean bValid = false;
		
		if (arrCells.length == kDataLength)
			bValid = true;

		return bValid;
	}

	private String [] parseCells (String [] arrCells)
	{
		String [] arrColumns = initDataColumns ();

		try
		{
			arrColumns [SBOConstants.kDI_SiteLoginId] 	= "" + getSiteLoginId ();
			arrColumns [SBOConstants.kDI_CommonCode] 	= arrCells [kWI_MasterCode].trim ();
			arrColumns [SBOConstants.kDI_CommonId] 		= arrCells [kWI_MasterId].trim ();
			
			arrColumns [kDI_MemberVolume] 	= formatAmount (arrCells [kWI_MasterTurnover].trim ());
			arrColumns [kDI_MasterTotal] 	= formatAmount (arrCells [kWI_MasterTotal].trim ());
			arrColumns [kDI_SuperWinLoss] 	= formatAmount (arrCells [kWI_SuperWinLoss].trim ());
			arrColumns [kDI_SuperCommission]= formatAmount (arrCells [kWI_SuperCommission].trim ());
			arrColumns [kDI_SuperTotal] 	= formatAmount (arrCells [kWI_SuperTotal].trim ());
			arrColumns [kDI_Company] 		= formatAmount (arrCells [kWI_CompanyTotal].trim ());
		}
		catch (Exception oException)
		{
			log ("SBOPCWLParserSM:parseCells:Exception:" + oException.toString ());
		}
		
		return arrColumns;
	}

	private String [] initDataColumns ()
	{
		String [] arrColumns = new String [9];

		arrColumns [SBOConstants.kDI_SiteLoginId] 	= "" + getSiteLoginId ();
		arrColumns [SBOConstants.kDI_CommonCode] 	= "notset";
		arrColumns [SBOConstants.kDI_CommonId] 		= "0";
		
		arrColumns [kDI_MemberVolume] 	= "0";
		arrColumns [kDI_MasterTotal] 	= "0";
		arrColumns [kDI_SuperWinLoss] 	= "0";
		arrColumns [kDI_SuperCommission]= "0";
		arrColumns [kDI_SuperTotal] 	= "0";
		arrColumns [kDI_Company] 		= "0";
		
		return arrColumns;
	}

	protected void setSuperCode (String oContent)
	{
		oContent = DataUtil.formatValue (oContent, "\">", 1);
		oContent = DataUtil.formatValue (oContent, "</a>", 0);
		
		m_oSuperCode = oContent;
	}
	
	public String getSuperCode ()
	{
		return m_oSuperCode;
	}

	private void log (String oMessage)
	{
		logMessage (oMessage);
	}
}
