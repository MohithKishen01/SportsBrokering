	/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie</font></H5>    
*/

function buildAccountPerformanceUI_106510409 (xmlDoc)
{
    var nodeList = xmlDoc.getElementsByTagName (kSR_AccountPerformance);
    var arrNodes = UDModule.DOM.convertNodesToArray (nodeList);
    
    var objBookiePerf = new BookiePerformanceObj_AC ();
    objBookiePerf.ArrNodes = arrNodes;
    objBookiePerf.IsShowBookiePerf = false;
	objBookiePerf.IsShowAccountPerf = true;
	objBookiePerf.IsShowLeaguePerf = false;
	objBookiePerf.IsShowBookiePerf_Popup = false;
    UDModule.COMP.replaceDynamicHtml ("tdView", objBookiePerf.buildPerformanceUI (objBookiePerf));
}
