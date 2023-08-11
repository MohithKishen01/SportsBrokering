/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie - League Performance</font></H5>
*/

function getInitParam_106510409a ()
{
	var xmlText = "<DataSet>";
	xmlText += UDModule.PROJ.getProcessXML (kA_LeaguePerfByBookie);
	xmlText += getLeagueListXML_106510409a ();
	xmlText += "</DataSet>";

	return xmlText;
}

function getLeagueListXML_106510409a ()
{
	var xmlText = "<" + kCR_A_League + ">";

	xmlText += "<id>";
	xmlText += kRT_Selected + UDModule.Gk_DATA_SEP;
	xmlText += T11_LeaguePerformance_DR + UDModule.Gk_DATA_SEP;	
	xmlText += kSR_A_LeaguePerformance_DR + UDModule.Gk_DATA_SEP;

    xmlText += getFilterValue_106510409a (kFT_BookieId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_FromDate) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_ToDate) + UDModule.Gk_EXT_SEP;
	xmlText += m_oLeagueIds + UDModule.Gk_EXT_SEP;
	xmlText += m_oBetTypeIds + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_WinLoseTypeId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_LiveStatusId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_PTBetsId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CurrencyId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CompanyUnitId);

    xmlText += "</id>";

	xmlText += "<id>";
	xmlText += kRT_6Days + UDModule.Gk_DATA_SEP;
	xmlText += T11_LeaguePerformance_L6D + UDModule.Gk_DATA_SEP;
	xmlText += kSR_A_LeaguePerformance_L6D + UDModule.Gk_DATA_SEP;

    xmlText += getFilterValue_106510409a (kFT_BookieId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_FromDate) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_ToDate) + UDModule.Gk_EXT_SEP;
	xmlText += m_oLeagueIds + UDModule.Gk_EXT_SEP;
	xmlText += m_oBetTypeIds + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_WinLoseTypeId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_LiveStatusId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_PTBetsId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CurrencyId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CompanyUnitId);
    xmlText += "</id>";
	
	xmlText += "<id>";
	xmlText += kRT_15Days + UDModule.Gk_DATA_SEP;
	xmlText += T11_LeaguePerformance_L15D + UDModule.Gk_DATA_SEP;
	xmlText += kSR_A_LeaguePerformance_L15D + UDModule.Gk_DATA_SEP;

    xmlText += getFilterValue_106510409a (kFT_BookieId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_FromDate) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_ToDate) + UDModule.Gk_EXT_SEP;
	xmlText += m_oLeagueIds + UDModule.Gk_EXT_SEP;
	xmlText += m_oBetTypeIds + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_WinLoseTypeId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_LiveStatusId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_PTBetsId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CurrencyId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CompanyUnitId);
    xmlText += "</id>";

	xmlText += "<id>";
	xmlText += kRT_1Month + UDModule.Gk_DATA_SEP;
	xmlText += T11_LeaguePerformance_L1M + UDModule.Gk_DATA_SEP;
	xmlText += kSR_A_LeaguePerformance_L1M + UDModule.Gk_DATA_SEP;

    xmlText += getFilterValue_106510409a (kFT_BookieId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_FromDate) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_ToDate) + UDModule.Gk_EXT_SEP;
	xmlText += m_oLeagueIds + UDModule.Gk_EXT_SEP;
	xmlText += m_oBetTypeIds + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_WinLoseTypeId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_LiveStatusId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_PTBetsId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CurrencyId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CompanyUnitId);
    xmlText += "</id>";

	xmlText += "<id>";
	xmlText += kRT_3Months + UDModule.Gk_DATA_SEP;
	xmlText += T11_LeaguePerformance_L3M + UDModule.Gk_DATA_SEP;
	xmlText += kSR_A_LeaguePerformance_L3M + UDModule.Gk_DATA_SEP;

    xmlText += getFilterValue_106510409a (kFT_BookieId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_FromDate) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_ToDate) + UDModule.Gk_EXT_SEP;
	xmlText += m_oLeagueIds + UDModule.Gk_EXT_SEP;
	xmlText += m_oBetTypeIds + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_WinLoseTypeId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_LiveStatusId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_PTBetsId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CurrencyId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CompanyUnitId);
    xmlText += "</id>";

	xmlText += "<id>";
	xmlText += kRT_6Months + UDModule.Gk_DATA_SEP;
	xmlText += T11_LeaguePerformance_L6M + UDModule.Gk_DATA_SEP;
	xmlText += kSR_A_LeaguePerformance_L6M + UDModule.Gk_DATA_SEP;

    xmlText += getFilterValue_106510409a (kFT_BookieId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_FromDate) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_ToDate) + UDModule.Gk_EXT_SEP;
	xmlText += m_oLeagueIds + UDModule.Gk_EXT_SEP;
	xmlText += m_oBetTypeIds + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_WinLoseTypeId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_LiveStatusId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_PTBetsId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CurrencyId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CompanyUnitId);
    xmlText += "</id>";

	xmlText += "<id>";
	xmlText += kRT_1Year + UDModule.Gk_DATA_SEP;
	xmlText += T11_LeaguePerformance_L1Y + UDModule.Gk_DATA_SEP;
	xmlText += kSR_A_LeaguePerformance_L1Y + UDModule.Gk_DATA_SEP;

    xmlText += getFilterValue_106510409a (kFT_BookieId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_FromDate) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_ToDate) + UDModule.Gk_EXT_SEP;
	xmlText += m_oLeagueIds + UDModule.Gk_EXT_SEP;
	xmlText += m_oBetTypeIds + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_WinLoseTypeId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_LiveStatusId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_PTBetsId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CurrencyId) + UDModule.Gk_EXT_SEP;
	xmlText += getFilterValue_106510409a (kFT_CompanyUnitId);
    xmlText += "</id>";

	xmlText += "</" + kCR_A_League + ">";

    return xmlText;
}