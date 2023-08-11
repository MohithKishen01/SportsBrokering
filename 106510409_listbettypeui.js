/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie</font></H5>    
*/

var m_arrNodes_BT = new Array ();
var m_arrSelectedIds_BT = new Array ();

function buildCommonListUI_BT_106510409 ()
{	
	UDModule.COMP.setDisplay ("trPerformanceList", "none");
    UDModule.COMP.setDisplay ("trBetTypeList", "block");

	if (m_arrNodes_BT.length > 0)
	{
		var objCommon = new CommonListObj_BB ();
		objCommon.ArrNodes		= m_arrNodes_BT;
		objCommon.SelectedIds	= m_arrSelectedIds_BT.join ("_");
	    
		objCommon.HeaderLeft	= m_arrHeaders_CL [iH_CommonName_BT];
		objCommon.ColumnCount	= kColumnCount_BT;
		objCommon.ColumnWidth	= 150;
		objCommon.kE_ChkElement = kE_ChkElement_BT;	
		
		objCommon.kEM_NameClicked			= "lblClicked_BT_106510409";
		objCommon.kEM_SelectAllClicked		= "lblSelectAllClicked_BT_106510409 ()";
		objCommon.kEM_ClearAllClicked		= "lblClearAllClicked_BT_106510409 ()";
		objCommon.kEM_CloseClicked			= "closeClicked_BT_106510409 ()";
		objCommon.kEM_SetSelectionClicked	= "setSelectionClicked_BT_106510409 ()";
		
		UDModule.COMP.replaceDynamicHtml ("tdBetTypeList", objCommon.buildCommonListUI (objCommon));
		objCommon = null;
	}
	else
		UDModule.COMP.replaceDynamicHtml ("tdBetTypeList", UDModule.TBL.getNoDataPresentEx (995, 100, 1, ""));
}

function storeSelectedIds_BT_106510409 ()
{
	m_arrSelectedIds_BT = new Array ();
	
    for (var nIndex = 0; nIndex < m_arrNodes_BT.length; nIndex++)
	{
		var arrInfo = m_arrNodes_BT [nIndex];
		var nBetTypeId =  arrInfo [iCommonId_BT];	

		var oElement = UDModule.COMP.getElement (kE_ChkElement_BT + nBetTypeId);
		if (oElement != null && oElement.checked)
			m_arrSelectedIds_BT [m_arrSelectedIds_BT.length] = nBetTypeId;
	}
}

function getSelectedIds_BT_106510409 ()
{
    return m_arrSelectedIds_BT;
}

function setCommonList_BT_106510409 (arrNodes)
{
	m_arrNodes_BT = arrNodes;
}

function lblShowClicked_BT_106510409 ()
{
	if (isValidDate_106510409 ())
	{
		UDModule.COMP.setDisplay ("tdShowBetTypes", "none");
		UDModule.COMP.setDisplay ("tdHideBetTypes", "block");
	
		toggleView_BO_106510409 ();
		toggleView_LL_106510409 ();				
	
		showBetTypeClicked_BT_106510409 ();
		buildCommonListUI_BT_106510409 ();
	}
}

function showBetTypeClicked_BT_106510409 ()
{
	var xmlDoc = UDModule.PROJ.processRequest (getBetTypeListParam_106510409 ());
	if (UDModule.PROJ.isValidStatus (xmlDoc))    
	{
		if (isValidStatus_T4_106510409 (xmlDoc))
		{                   			                 	        
			var nodeList = xmlDoc.getElementsByTagName (kSR_MainBetTypes);
			var arrNodes = UDModule.DOM.convertNodesToArray (nodeList);
			setCommonList_BT_106510409 (arrNodes);
		}
	}
}

function lblHideClicked_BT_106510409 ()
{
	UDModule.COMP.setDisplay ("trPerformanceList", "block");
    toggleView_BT_106510409 (); 
}

function lblClicked_BT_106510409 (nBetTypeId)
{
    var oElement = UDModule.COMP.getElement (kE_ChkElement_BT + nBetTypeId);	
	if (oElement != null)	
		oElement.checked = !oElement.checked;
}

function lblSelectAllClicked_BT_106510409 ()
{
    for (var nIndex = 0; nIndex < m_arrNodes_BT.length; nIndex++)
	{
		var arrInfo = m_arrNodes_BT [nIndex];
		var nBetTypeId =  arrInfo [iCommonId_BT];	

		var oElement = UDModule.COMP.getElement (kE_ChkElement_BT + nBetTypeId);
		if (oElement != null)
			oElement.checked = true;
	}		
}

function lblClearAllClicked_BT_106510409 ()
{
    for (var nIndex = 0; nIndex < m_arrNodes_BT.length; nIndex++)
	{
		var arrInfo = m_arrNodes_BT [nIndex];
		var nBetTypeId =  arrInfo [iCommonId_BT];	

		var oElement = UDModule.COMP.getElement (kE_ChkElement_BT + nBetTypeId);
		if (oElement != null)
			oElement.checked = false;
	}
}

function closeClicked_BT_106510409 ()
{
	UDModule.COMP.setDisplay ("trPerformanceList", "block");

    UDModule.COMP.setDisplay ("tdHideBetTypes", "none");
	UDModule.COMP.setDisplay ("tdShowBetTypes", "block");
	
	UDModule.COMP.replaceDynamicHtml ("tdBetTypeList", "");
}

function setSelectionClicked_BT_106510409 ()
{
	storeSelectedIds_BT_106510409 ();
	toggleView_BT_106510409 ();
}

function clearValues_BT_106510409 ()
{
	m_arrSelectedIds_BT = new Array ();
	toggleView_BT_106510409 ();
}

function toggleView_BT_106510409 ()
{
    UDModule.COMP.setDisplay ("tdShowBetTypes", "block");
	UDModule.COMP.setDisplay ("tdHideBetTypes", "none");
	UDModule.COMP.setDisplay ("trBetTypeList", "none");
}