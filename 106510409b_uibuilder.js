/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie - Bookie Performance</font></H5>
*/

function buildBookiePerformance_106510409b (xmlDoc)
{
    var nodeList = xmlDoc.getElementsByTagName (kSR_B_BookiePerformance);
    var arrNodes = UDModule.DOM.convertNodesToArray (nodeList);
    
    var objBookiePerf = new BookiePerformanceObj_AC ();
    objBookiePerf.ArrNodes = arrNodes;
    objBookiePerf.IsShowBookiePerf = false;
	objBookiePerf.IsShowAccountPerf = false;
	objBookiePerf.IsShowLeaguePerf = false;
	objBookiePerf.IsShowBookiePerf_Popup = true;
	
    UDModule.COMP.replaceDynamicHtml ("tdBookieView", objBookiePerf.buildPerformanceUI (objBookiePerf));
    objBookiePerf = null;
}