/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie - Bookie Performance</font></H5>
*/

function getInitParam_106510409b ()
{
	var xmlText = "<DataSet>";
	xmlText += UDModule.PROJ.getProcessXML (kB_BookiePerfByLeague);
	xmlText += getBookieListXML_106510409b ();
	xmlText += "</DataSet>";

	return xmlText;
}

function getBookieListXML_106510409b ()
{	
    var xmlText = "<" + kCR_B_Bookie + "><id>";
	xmlText += T21_BookiePerformance + UDModule.Gk_DATA_SEP;
	xmlText += kSR_B_BookiePerformance + UDModule.Gk_DATA_SEP;
	xmlText += getFilterValue_106510409b (kFT_WinLoseTypeId) + UDModule.Gk_DATA_SEP;    
	xmlText += getFilterValue_106510409b (kFT_FromDate) + UDModule.Gk_DATA_SEP;
	xmlText += getFilterValue_106510409b (kFT_ToDate) + UDModule.Gk_DATA_SEP;	
	xmlText += m_oBookieIds + UDModule.Gk_DATA_SEP;
	xmlText += getFilterValue_106510409b (kFT_LeagueId) + UDModule.Gk_DATA_SEP;
	xmlText += m_oBetTypeIds + UDModule.Gk_DATA_SEP;
	xmlText += getFilterValue_106510409b (kFT_PTBetsId) + UDModule.Gk_DATA_SEP;
	xmlText += getFilterValue_106510409b (kFT_LiveStatusId) + UDModule.Gk_DATA_SEP;
	xmlText += getFilterValue_106510409b (kFT_CurrencyId) + UDModule.Gk_DATA_SEP;
	xmlText += getFilterValue_106510409b (kFT_CompanyUnitId);
    xmlText += "</id></" + kCR_B_Bookie + ">";

    return xmlText;
}