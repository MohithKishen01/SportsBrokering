/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie - League Performance</font></H5>
*/

var kImagePath = "../../../../resource/images/default/info/";

/* Total Row Type */
var kTT_BlockTotalRow	= 1;
var kTT_GrandTotalRow	= 2;

/* Filter Type Ids*/
var kFT_BookieId		= 0;
var kFT_BookieName		= 1;
var kFT_BookieCURCode	= 2;
var kFT_FromDate		= 3;
var kFT_ToDate			= 4;
var kFT_WinLoseTypeId	= 5;
var kFT_PTBetsId		= 6;
var kFT_LiveStatusId	= 7;
var kFT_CurrencyId		= 8;
var kFT_CompanyUnitId	= 9;

/* League Performance Filter */
var kRT_Selected	= 1;
var kRT_6Days		= 2;
var kRT_15Days		= 3;
var kRT_1Month		= 4;
var kRT_3Months		= 5;
var kRT_6Months		= 6;
var kRT_1Year		= 7;

/* Task Ids */
var kA_LeaguePerfByBookie	= "10651040911";

/* Server Row Names */
var kSR_A_LeaguePerformance_DR	= "sr11";
var kSR_A_LeaguePerformance_L6D	= "sr12";
var kSR_A_LeaguePerformance_L15D= "sr13";
var kSR_A_LeaguePerformance_L1M	= "sr14";
var kSR_A_LeaguePerformance_L3M	= "sr15";
var kSR_A_LeaguePerformance_L6M	= "sr16";
var kSR_A_LeaguePerformance_L1Y	= "sr17";

/* Client Row Names */
var kCR_A_League	= "cr11";

/* Status Id */
var T11_LeaguePerformance_DR	= 1101;	// Selected Report
var T11_LeaguePerformance_L6D	= 1102;	// Last 6 Days
var T11_LeaguePerformance_L15D	= 1103;	// Last 15 Days
var T11_LeaguePerformance_L1M	= 1104;	// Last 1 Month
var T11_LeaguePerformance_L3M	= 1105;	// Last 3 Months
var T11_LeaguePerformance_L6M	= 1106;	// Last 6 Months
var T11_LeaguePerformance_L1Y	= 1107;	// Last 1 Year
 
/* Status Message Ids */
var iM11_LeaguePerformance_DR	= 0;
var iM11_LeaguePerformance_L6D	= 1;
var iM11_LeaguePerformance_L15D	= 2;
var iM11_LeaguePerformance_L1M	= 3;
var iM11_LeaguePerformance_L3M	= 4;
var iM11_LeaguePerformance_L6M	= 5;
var iM11_LeaguePerformance_L1Y	= 6;