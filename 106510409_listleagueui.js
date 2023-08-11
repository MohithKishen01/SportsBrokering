/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie</font></H5>    
*/

var m_arrNodes_LL = new Array ();
var m_arrSelectedIds_LL = new Array ();

function buildCommonListUI_LL_106510409 ()
{	
	UDModule.COMP.setDisplay ("trPerformanceList", "none");
    UDModule.COMP.setDisplay ("trLeagueList", "block");

	if (m_arrNodes_LL.length > 0)
	{
		var objCommon = new CommonListObj_BB ();
		objCommon.ArrNodes		= m_arrNodes_LL;
		objCommon.SelectedIds	= m_arrSelectedIds_LL.join ("_");
	    
		objCommon.HeaderLeft	= m_arrHeaders_CL [iH_CommonName_LL];
		objCommon.ColumnCount	= kColumnCount_LL;
		objCommon.ColumnWidth	= 300;
		objCommon.kE_ChkElement = kE_ChkElement_LL;	
		
		objCommon.kEM_NameClicked			= "lblClicked_LL_106510409";
		objCommon.kEM_SelectAllClicked		= "lblSelectAllClicked_LL_106510409 ()";
		objCommon.kEM_ClearAllClicked		= "lblClearAllClicked_LL_106510409 ()";
		objCommon.kEM_CloseClicked			= "closeClicked_LL_106510409 ()";
		objCommon.kEM_SetSelectionClicked	= "setSelectionClicked_LL_106510409 ()";
		
		UDModule.COMP.replaceDynamicHtml ("tdLeagueList", objCommon.buildCommonListUI (objCommon));
		objCommon = null;
	}
	else
		UDModule.COMP.replaceDynamicHtml ("tdLeagueList", UDModule.TBL.getNoDataPresentEx (995, 100, 1, ""));
}

function storeSelectedIds_LL_106510409 ()
{
	m_arrSelectedIds_LL = new Array ();
	
    for (var nIndex = 0; nIndex < m_arrNodes_LL.length; nIndex++)
	{
		var arrInfo = m_arrNodes_LL [nIndex];
		var nLeagueId =  arrInfo [iCommonId_LL];	

		var oElement = UDModule.COMP.getElement (kE_ChkElement_LL + nLeagueId);
		if (oElement != null && oElement.checked)
			m_arrSelectedIds_LL [m_arrSelectedIds_LL.length] = nLeagueId;
	}
}

function getSelectedIds_LL_106510409 ()
{
    return m_arrSelectedIds_LL;
}

function setCommonList_LL_106510409 (arrNodes)
{
	m_arrNodes_LL = arrNodes;
}

function lblShowClicked_LL_106510409 ()
{
	if (isValidDate_106510409 ())
	{
		UDModule.COMP.setDisplay ("tdShowLeagues", "none");
		UDModule.COMP.setDisplay ("tdHideLeagues", "block");

		toggleView_BT_106510409 ();
		toggleView_BO_106510409 ();
	
		showLeagueClicked_LL_106510409 ();
		buildCommonListUI_LL_106510409 ();
	}
}

function showLeagueClicked_LL_106510409 ()
{
	var xmlDoc = UDModule.PROJ.processRequest (getLeagueListParam_106510409 ());
	if (UDModule.PROJ.isValidStatus (xmlDoc))    
	{
		if (isValidStatus_T5_106510409 (xmlDoc))
		{                   			                 
			var nodeList = xmlDoc.getElementsByTagName (kSR_Leagues);
			var arrNodes = UDModule.DOM.convertNodesToArray (nodeList);
		    setCommonList_LL_106510409 (arrNodes);
		}
	}	
}

function lblHideClicked_LL_106510409 ()
{
	UDModule.COMP.setDisplay ("trPerformanceList", "block");
    toggleView_LL_106510409 ();
}

function lblClicked_LL_106510409 (nLeagueId)
{
    var oElement = UDModule.COMP.getElement (kE_ChkElement_LL + nLeagueId);	
	if (oElement != null)	
		oElement.checked = !oElement.checked;
}

function lblSelectAllClicked_LL_106510409 ()
{
    for (var nIndex = 0; nIndex < m_arrNodes_LL.length; nIndex++)
	{
		var arrInfo = m_arrNodes_LL [nIndex];
		var nLeagueId =  arrInfo [iCommonId_LL];	

		var oElement = UDModule.COMP.getElement (kE_ChkElement_LL + nLeagueId);
		if (oElement != null)
			oElement.checked = true;
	}		
}

function lblClearAllClicked_LL_106510409 ()
{
    for (var nIndex = 0; nIndex < m_arrNodes_LL.length; nIndex++)
	{
		var arrInfo = m_arrNodes_LL [nIndex];
		var nLeagueId =  arrInfo [iCommonId_LL];	

		var oElement = UDModule.COMP.getElement (kE_ChkElement_LL + nLeagueId);
		if (oElement != null)
			oElement.checked = false;
	}		
}

function closeClicked_LL_106510409 ()
{    
	UDModule.COMP.setDisplay ("trPerformanceList", "block");

    UDModule.COMP.setDisplay ("tdHideLeagues", "none");
	UDModule.COMP.setDisplay ("tdShowLeagues", "block");
	
	UDModule.COMP.replaceDynamicHtml ("tdLeagueList", "");
}

function setSelectionClicked_LL_106510409 ()
{
	storeSelectedIds_LL_106510409 ();
	toggleView_LL_106510409 ();
}

function clearValues_LL_106510409 ()
{
	m_arrSelectedIds_LL = new Array ();
    toggleView_LL_106510409 ();
}

function toggleView_LL_106510409 ()
{
    UDModule.COMP.setDisplay ("tdShowLeagues", "block");
	UDModule.COMP.setDisplay ("tdHideLeagues", "none");
	UDModule.COMP.setDisplay ("trLeagueList", "none");
}