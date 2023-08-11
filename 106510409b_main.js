/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie - Bookie Performance</font></H5>
*/

var m_oBookieIds	= 0;
var m_oBetTypeIds	= 0;
var m_arrFilters = new Array ();

/**
    Creates an object of Bookie Performance and initializes all member variables.
	Invokes init function.

    @author     Vinod
    @version    1.0.0
*/
function onload_106510409b ()
{
	Constructor_106510409b ();
	init_106510409b ();
}

function init_106510409b ()
{
	UDModule.PROJ.loadDefaultTheme ();
	
	var oURL = unescape (window.location.href);
	var arrURL = oURL.split ("?");
    m_arrFilters = arrURL [1].split (UDModule.Gk_DATA_SEP);

	UDModule.COMP.replaceDynamicHtml ("tdLeagueHeader", getFilterValue_106510409b (kFT_LeagueName));

	var arrBookieIds = window.opener.getSelectedIds_BO_106510409 ();
    m_oBookieIds = arrBookieIds.length > 0 ? arrBookieIds.join (",") : "0";       
    
    var arrBetTypeIds = window.opener.getSelectedIds_BT_106510409 ();
    m_oBetTypeIds = arrBetTypeIds.length > 0 ? arrBetTypeIds.join (",") : "0";

	initData_106510409b ();	
}

function initData_106510409b ()
{
	var xmlDoc = getProcessRequest_106510409b (getInitParam_106510409b ());
	if (UDModule.PROJ.isValidStatus (xmlDoc))
	{
		if (isValidStatus_T21_106510409b (xmlDoc))		
			buildBookiePerformance_106510409b (xmlDoc);
	}	
}

function getFilterValue_106510409b (nTypeId)
{
	return m_arrFilters [nTypeId];
}

function getProcessRequest_106510409b (oParam)
{
	return window.opener.getProcessRequest_106510409 (oParam);
}