/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie</font></H5>    
*/

function buildBookiePerformanceUI_106510409 (xmlDoc)
{
    var nodeList = xmlDoc.getElementsByTagName (kSR_BookiePerformance);
    var arrNodes = UDModule.DOM.convertNodesToArray (nodeList);
    
    var objBookiePerf = new BookiePerformanceObj_AC ();
    objBookiePerf.ArrNodes = arrNodes;
    objBookiePerf.IsShowBookiePerf = true;
	objBookiePerf.IsShowAccountPerf = false;
	objBookiePerf.IsShowLeaguePerf = false;
	objBookiePerf.IsShowBookiePerf_Popup = false;
	objBookiePerf.IsShowBookieLeaguePerf = (UDModule.PROJ.isFunction_PM (kFN_Bookie_LP)) ? true: false;
	
    UDModule.COMP.replaceDynamicHtml ("tdView", objBookiePerf.buildPerformanceUI (objBookiePerf));
    objBookiePerf = null;
}