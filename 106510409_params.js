/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie</font></H5>    
*/

function getInitParam_106510409 ()
{
    var xmlText = "<Dataset>";
    xmlText += UDModule.PROJ.getProcessXML (kInitData);
    xmlText += "</Dataset>";

    return xmlText;
}

function getBetTypeListParam_106510409 ()
{
	var xmlText = "<DataSet>";
	xmlText += UDModule.PROJ.getProcessXML (kMainBetTypes);
	xmlText += getCommonListXML_106510409 ();
	xmlText += "</DataSet>";

	return xmlText;
}

function getLeagueListParam_106510409 ()
{
	var xmlText = "<DataSet>";
	xmlText += UDModule.PROJ.getProcessXML (kLeagues);
	xmlText += getCommonListXML_106510409 ();
	xmlText += "</DataSet>";

	return xmlText;
}

function getBookieListParam_106510409 ()
{
    var xmlText = "<Dataset>";
    xmlText += UDModule.PROJ.getProcessXML (kBookies);
	xmlText += getCommonListXML_106510409 ();
    xmlText += "</Dataset>";

    return xmlText;
}

function getCommonListXML_106510409 ()
{
    var xmlText = "<" + kCR_CommonList + "><id>";
	xmlText += UDModule.DATE.getSQLDate (getFromDate_106510409 ()) + UDModule.Gk_DATA_SEP;
	xmlText += UDModule.DATE.getSQLDate (getToDate_106510409 ()) + UDModule.Gk_DATA_SEP;
	xmlText += getCompanyUnitId_106510409 ();
    xmlText += "</id></" + kCR_CommonList + ">";

    return xmlText;
}

function getBookiePerformanceParam_106510409 ()
{
    var xmlText = "<Dataset>";
    xmlText += UDModule.PROJ.getProcessXML (kBookiePerformance);
    xmlText += getBookiePerformanceXML_106510409 ();
    xmlText += "</Dataset>";

    return xmlText;
}

function getBookiePerformanceXML_106510409 ()
{
	var arrBookieIds = getSelectedIds_BO_106510409 ();
    var oBookieIds = arrBookieIds.length > 0 ? arrBookieIds.join (",") : "0";
    
    var arrLeagueIds = getSelectedIds_LL_106510409 ();
    var oLeagueIds = arrLeagueIds.length > 0 ? arrLeagueIds.join (",") : "-1";
    
    var arrBetTypeIds = getSelectedIds_BT_106510409 ();
    var oBetTypeIds = arrBetTypeIds.length > 0 ? arrBetTypeIds.join (",") : "0";

	var xmlText = "<" + kCR_Performance + "><id>";
	xmlText += GT_BookiePerformance + UDModule.Gk_DATA_SEP;
	xmlText += kSR_BookiePerformance + UDModule.Gk_DATA_SEP;
    xmlText += getWinLoseTypeId_106510409 () + UDModule.Gk_DATA_SEP;	
	xmlText += UDModule.DATE.getSQLDate (getFromDate_106510409 ()) + UDModule.Gk_DATA_SEP;
	xmlText += UDModule.DATE.getSQLDate (getToDate_106510409 ()) + UDModule.Gk_DATA_SEP;	
	xmlText += oBookieIds + UDModule.Gk_DATA_SEP;
	xmlText += oLeagueIds + UDModule.Gk_DATA_SEP;
	xmlText += oBetTypeIds + UDModule.Gk_DATA_SEP;
	xmlText += getPtBetsOptionId_106510409 () + UDModule.Gk_DATA_SEP;
	xmlText += getLiveStatusId_106510409 () + UDModule.Gk_DATA_SEP;
    xmlText += getCurrencyId_106510409 () + UDModule.Gk_DATA_SEP;
	xmlText += getCompanyUnitId_106510409 ();	
    xmlText += "</id></" + kCR_Performance + ">";
       
    return xmlText;
}

function getAccountPerformanceParam_106510409 ()
{
    var xmlText = "<Dataset>";
    xmlText += UDModule.PROJ.getProcessXML (kAccountPerformance);
    xmlText += getAccountPerformanceXML_106510409 ();
    xmlText += "</Dataset>";

    return xmlText;
}

function getAccountPerformanceXML_106510409 ()
{
	var arrBookieIds = getSelectedIds_BO_106510409 ();
    var oBookieIds = arrBookieIds.length > 0 ? arrBookieIds.join (",") : "0";
    
    var arrLeagueIds = getSelectedIds_LL_106510409 ();
    var oLeagueIds = arrLeagueIds.length > 0 ? arrLeagueIds.join (",") : "-1";
    
    var arrBetTypeIds = getSelectedIds_BT_106510409 ();
    var oBetTypeIds = arrBetTypeIds.length > 0 ? arrBetTypeIds.join (",") : "0";

	var xmlText = "<" + kCR_Performance + "><id>";
	xmlText += GT_AccountPerformance + UDModule.Gk_DATA_SEP;
	xmlText += kSR_AccountPerformance + UDModule.Gk_DATA_SEP;
    xmlText += getWinLoseTypeId_106510409 () + UDModule.Gk_DATA_SEP;	
	xmlText += UDModule.DATE.getSQLDate (getFromDate_106510409 ()) + UDModule.Gk_DATA_SEP;
	xmlText += UDModule.DATE.getSQLDate (getToDate_106510409 ()) + UDModule.Gk_DATA_SEP;	
	xmlText += oBookieIds + UDModule.Gk_DATA_SEP;
	xmlText += oLeagueIds + UDModule.Gk_DATA_SEP;
	xmlText += oBetTypeIds + UDModule.Gk_DATA_SEP;
	xmlText += getPtBetsOptionId_106510409 () + UDModule.Gk_DATA_SEP;
	xmlText += getLiveStatusId_106510409 () + UDModule.Gk_DATA_SEP;
    xmlText += getCurrencyId_106510409 () + UDModule.Gk_DATA_SEP;
	xmlText += getCompanyUnitId_106510409 ();	
    xmlText += "</id></" + kCR_Performance + ">";
       
    return xmlText;
}

function getLeaguePerformanceParam_106510409 ()
{
    var xmlText = "<Dataset>";
    xmlText += UDModule.PROJ.getProcessXML (kLeaguePerformance);
    xmlText += getLeaguePerformanceXML_106510409 ();
    xmlText += "</Dataset>";

    return xmlText;
}

function getLeaguePerformanceXML_106510409 ()
{
	var arrBookieIds = getSelectedIds_BO_106510409 ();
    var oBookieIds = arrBookieIds.length > 0 ? arrBookieIds.join (",") : "0";
    
    var arrLeagueIds = getSelectedIds_LL_106510409 ();
    var oLeagueIds = arrLeagueIds.length > 0 ? arrLeagueIds.join (",") : "-1";
    
    var arrBetTypeIds = getSelectedIds_BT_106510409 ();
    var oBetTypeIds = arrBetTypeIds.length > 0 ? arrBetTypeIds.join (",") : "0";

	var xmlText = "<" + kCR_Performance + "><id>";
	xmlText += GT_LeaguePerformance + UDModule.Gk_DATA_SEP;
	xmlText += kSR_LeaguePerformance + UDModule.Gk_DATA_SEP;   
    xmlText += getWinLoseTypeId_106510409 () + UDModule.Gk_DATA_SEP;	
	xmlText += UDModule.DATE.getSQLDate (getFromDate_106510409 ()) + UDModule.Gk_DATA_SEP;
	xmlText += UDModule.DATE.getSQLDate (getToDate_106510409 ()) + UDModule.Gk_DATA_SEP;	
	xmlText += oBookieIds + UDModule.Gk_DATA_SEP;
	xmlText += oLeagueIds + UDModule.Gk_DATA_SEP;
	xmlText += oBetTypeIds + UDModule.Gk_DATA_SEP;
	xmlText += getPtBetsOptionId_106510409 () + UDModule.Gk_DATA_SEP;
	xmlText += getLiveStatusId_106510409 () + UDModule.Gk_DATA_SEP;
    xmlText += getCurrencyId_106510409 () + UDModule.Gk_DATA_SEP;
	xmlText += getCompanyUnitId_106510409 ();	
    xmlText += "</id></" + kCR_Performance + ">";
       
    return xmlText;
}