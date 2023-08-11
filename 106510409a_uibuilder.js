/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie - League Performance</font></H5>
*/

var m_oXMLDoc = null;
var m_nInterval = 0;

function showLeaguePerformance_106510409a (xmlDoc)
{
	m_oXMLDoc = xmlDoc;
	showLeaguePerformanceDR_BL_106510409a ();
}

function showLeaguePerformanceDR_BL_106510409a ()
{
    buildLeaguePerformanceUI_106510409a (UDModule.COMP.getElement ("tdLeagueView_DR"), kSR_A_LeaguePerformance_DR, kRT_Selected);
    window.clearInterval (m_nInterval);
    m_nInterval = window.setTimeout ("showLeaguePerformanceL6D_BL_106510409a ()", 300);
}

function showLeaguePerformanceL6D_BL_106510409a ()
{
    buildLeaguePerformanceUI_106510409a (UDModule.COMP.getElement ("tdLeagueView_L6D"), kSR_A_LeaguePerformance_L6D, kRT_6Days);
    window.clearInterval (m_nInterval);
    m_nInterval = window.setTimeout ("showLeaguePerformanceL15D_BL_106510409a ()", 300);
}

function showLeaguePerformanceL15D_BL_106510409a ()
{
    buildLeaguePerformanceUI_106510409a (UDModule.COMP.getElement ("tdLeagueView_L15D"), kSR_A_LeaguePerformance_L15D, kRT_15Days);
    window.clearInterval (m_nInterval);
    m_nInterval = window.setTimeout ("showLeaguePerformanceL1M_BL_106510409a ()", 300);
}

function showLeaguePerformanceL1M_BL_106510409a ()
{
    buildLeaguePerformanceUI_106510409a (UDModule.COMP.getElement ("tdLeagueView_L1M"), kSR_A_LeaguePerformance_L1M, kRT_1Month);
    window.clearInterval (m_nInterval);
    m_nInterval = window.setTimeout ("showLeaguePerformanceL3M_BL_106510409a ()", 300);
}

function showLeaguePerformanceL3M_BL_106510409a ()
{
    buildLeaguePerformanceUI_106510409a (UDModule.COMP.getElement ("tdLeagueView_L3M"), kSR_A_LeaguePerformance_L3M, kRT_3Months);
    window.clearInterval (m_nInterval);
    m_nInterval = window.setTimeout ("showLeaguePerformanceL6M_BL_106510409a ()", 300);
}

function showLeaguePerformanceL6M_BL_106510409a ()
{
    buildLeaguePerformanceUI_106510409a (UDModule.COMP.getElement ("tdLeagueView_L6M"), kSR_A_LeaguePerformance_L6M, kRT_6Months);
    window.clearInterval (m_nInterval);
    m_nInterval = window.setTimeout ("showLeaguePerformanceL1Y_BL_106510409a ()", 300);
}

function showLeaguePerformanceL1Y_BL_106510409a ()
{
    buildLeaguePerformanceUI_106510409a (UDModule.COMP.getElement ("tdLeagueView_L1Y"), kSR_A_LeaguePerformance_L1Y, kRT_1Year);
    window.clearInterval (m_nInterval);
}

function buildLeaguePerformanceUI_106510409a (oElement, oServerRow, nReportTypeId)
{
	var oBookieName = getFilterValue_106510409a (kFT_BookieName);
	var oBookieCUR = getFilterValue_106510409a (kFT_BookieCURCode);
	var oFromDate = getFilterValue_106510409a (kFT_FromDate);
	var oToDate = getFilterValue_106510409a (kFT_ToDate);

    var nodeList = m_oXMLDoc.getElementsByTagName (oServerRow);
    var arrNodes = UDModule.DOM.convertNodesToArray (nodeList);
    
    var objLeaguePerf = new LeaguePerformanceObj_AF ();
    objLeaguePerf.ArrNodes = arrNodes;
    objLeaguePerf.ReportTypeId = nReportTypeId;
	objLeaguePerf.IsShowBookiePerf	= true;
	objLeaguePerf.IsShowPunterPerf	= false;
	objLeaguePerf.CommonName = oBookieName;
	objLeaguePerf.CurrencyCode = oBookieCUR;
	objLeaguePerf.FromDate = oFromDate;
	objLeaguePerf.ToDate = oToDate;		
	
    UDModule.COMP.replaceDynamicHtml (oElement, objLeaguePerf.buildPerformanceUI (objLeaguePerf));
    objLeaguePerf = null;
}