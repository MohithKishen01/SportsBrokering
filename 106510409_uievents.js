/*
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie</font></H5>    
*/

function companyUnitChanged_106510409 ()
{
	reportByChanged_106510409 ();
}

function reportByChanged_106510409 ()
{
	clearValues_BT_106510409 ();
    clearValues_LL_106510409 ();
	clearValues_BO_106510409 ();

	unload_106510409 ();
	UDModule.COMP.setDisplay ("trPerformanceList", "none");
}

function lblPtBetsOptionClicked_106510409 ()
{
    UDModule.COMP.getElement ("chkPTBetsOption").checked = !UDModule.COMP.getElement ("chkPTBetsOption").checked;
}

function dateFltrClicked_106510409 ()
{        
	clearValues_BT_106510409 ();
    clearValues_LL_106510409 ();
    clearValues_BO_106510409 ();
}

function dateClicked_106510409 ()
{	
	du_openDatePicker (txtToDate);	
}

function showFiltersClicked_106510409 ()
{
	showFilters_106510409 (false);
	showClicked_106510409 ();
}

function showClicked_106510409 ()
{
	if (isValidDate_106510409 ())
	{
		unload_106510409 ();
		UDModule.COMP.setDisplay ("trPerformanceList", "block");

		toggleView_BT_106510409 ();
		toggleView_LL_106510409 ();
		toggleView_BO_106510409 ();	
	                   
		UDModule.COMP.replaceDynamicHtml ("tdView", "");
		UDModule.COMP.setDisplay ("divFilters", "none");

		if (getReportTypeId_106510409 () == kRT_BookiePerf)
		{
			var xmlDoc = UDModule.PROJ.processRequest (getBookiePerformanceParam_106510409 ());
			if (UDModule.PROJ.isValidStatus (xmlDoc))    
			{
				if (isValidStatus_T7_106510409 (xmlDoc))
					buildBookiePerformanceUI_106510409 (xmlDoc);
			}
		}
		else if (getReportTypeId_106510409 () == kRT_AccountPerf)
		{
			var xmlDoc = UDModule.PROJ.processRequest (getAccountPerformanceParam_106510409 ());
			if (UDModule.PROJ.isValidStatus (xmlDoc))    
			{
				if (isValidStatus_T8_106510409 (xmlDoc))
					buildAccountPerformanceUI_106510409 (xmlDoc);
			}
		}
		else if (getReportTypeId_106510409 () == kRT_LeaguePerf)
		{
			var xmlDoc = UDModule.PROJ.processRequest (getLeaguePerformanceParam_106510409 ());
			if (UDModule.PROJ.isValidStatus (xmlDoc))    
			{
				if (isValidStatus_T9_106510409 (xmlDoc))
					buildLeaguePerformanceUI_106510409 (xmlDoc);
			}
		}
	}
}

function object_bookieNameClicked (nBookieId, oBookieName, oCurrencyCode)
{	    
	var oFromDate = UDModule.DATE.getSQLDate (getFromDate_106510409 ());
	var oToDate = UDModule.DATE.getSQLDate (getToDate_106510409 ());

	var nWinLoseTypeId = getWinLoseTypeId_106510409 ();
	var nPTBetsId = getPtBetsOptionId_106510409 ();
	var nLiveStatusId = getLiveStatusId_106510409 ();
	var nCurrencyId = getCurrencyId_106510409 ();

	var oParam = nBookieId + UDModule.Gk_DATA_SEP;
	oParam += oBookieName + UDModule.Gk_DATA_SEP;
	oParam += oCurrencyCode + UDModule.Gk_DATA_SEP;
	oParam += oFromDate + UDModule.Gk_DATA_SEP;
	oParam += oToDate + UDModule.Gk_DATA_SEP;
	oParam += nWinLoseTypeId + UDModule.Gk_DATA_SEP;
	oParam += nPTBetsId + UDModule.Gk_DATA_SEP;
	oParam += nLiveStatusId + UDModule.Gk_DATA_SEP;
	oParam += nCurrencyId + UDModule.Gk_DATA_SEP;
	oParam += getCompanyUnitId_106510409 ();

	var windowStyle = "height=640, width=1000, left=5, top=5, status=yes, toolbar=no, " +
		"menubar=no, location=no, resizable=yes, scrollbars=yes";

	m_arrPopups [iW_League] = 
		window.open ("../en/a_leagueperformance/leagueperformance" + UDModule.PROJ.PageExtension + "?" + oParam, "leagueperformance", windowStyle);
		
    m_arrPopups [iW_League].focus ();
}

function object_leagueNameClicked (nLeagueId, oLeagueName)
{
	var oCurrencyCode = "";
	var oFromDate = UDModule.DATE.getSQLDate (getFromDate_106510409 ());
	var oToDate = UDModule.DATE.getSQLDate (getToDate_106510409 ());

	var nWinLoseTypeId = getWinLoseTypeId_106510409 ();
	var nPTBetsId = getPtBetsOptionId_106510409 ();
	var nLiveStatusId = getLiveStatusId_106510409 ();
	var nCurrencyId = getCurrencyId_106510409 ();	    	

	var oParam = nLeagueId + UDModule.Gk_DATA_SEP;
	oParam += oLeagueName + UDModule.Gk_DATA_SEP;
	oParam += oCurrencyCode + UDModule.Gk_DATA_SEP;
	oParam += oFromDate + UDModule.Gk_DATA_SEP;
	oParam += oToDate + UDModule.Gk_DATA_SEP;
	oParam += nWinLoseTypeId + UDModule.Gk_DATA_SEP;
	oParam += nPTBetsId + UDModule.Gk_DATA_SEP;
	oParam += nLiveStatusId + UDModule.Gk_DATA_SEP;
	oParam += nCurrencyId + UDModule.Gk_DATA_SEP;
	oParam += getCompanyUnitId_106510409 ();

	var windowStyle = "height=640, width=1000, left=5, top=5, status=yes, toolbar=no, " +
		"menubar=no, location=no, resizable=yes, scrollbars=yes";

	m_arrPopups [iW_Bookie] = 
		window.open ("../en/b_bookieperformance/bookieperformance" + UDModule.PROJ.PageExtension + "?" + oParam, "bookieperformance", windowStyle);
	
	m_arrPopups [iW_Bookie].focus ();
}