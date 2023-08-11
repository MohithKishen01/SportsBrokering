/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie - League Performance</font></H5>
*/

function isValidStatus_T11_106510409a (xmlDoc)
{
    var bStatus = false;

    var nodeList = xmlDoc.getElementsByTagName (UDModule.GkSR_Status);
    if (nodeList != null && nodeList.length > 0)
    {
        for (var nIndex = 0; nIndex < nodeList.length; nIndex++)
        {
            var objNode = nodeList [nIndex];
            var arrInfo = UDModule.DOM.getXMLIndexValue (objNode, 0).split (UDModule.Gk_DATA_SEP);
            bStatus = false;

            if (arrInfo [UDModule.GkS_STATUSID] == -1)
            {
                switch (parseInt (arrInfo [UDModule.GkS_TASKID]))
                {
					case T11_LeaguePerformance_DR:
                        alert (m_arrMessages_T11 [iM11_LeaguePerformance_DR]);
                        break;
					case T11_LeaguePerformance_L6D:
                        alert (m_arrMessages_T11 [iM11_LeaguePerformance_L6D]);
                        break;
					case T11_LeaguePerformance_L15D:
                        alert (m_arrMessages_T11 [iM11_LeaguePerformance_L15D]);
                        break;
					case T11_LeaguePerformance_L1M:
                        alert (m_arrMessages_T11 [iM11_LeaguePerformance_L1M]);
                        break;
					case T11_LeaguePerformance_L3M:
                        alert (m_arrMessages_T11 [iM11_LeaguePerformance_L3M]);
                        break;
					case T11_LeaguePerformance_L6M:
                        alert (m_arrMessages_T11 [iM11_LeaguePerformance_L6M]);
                        break;
					case T11_LeaguePerformance_L1Y:
                        alert (m_arrMessages_T11 [iM11_LeaguePerformance_L1Y]);
                        break;		             
                }
                break;
            }
            else
                bStatus = true;
        }
    }
    return bStatus;
}