/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie</font></H5>    
*/

var kImagePath = "../../../resource/images/default/info/";

/* Function Ids */
var kFN_View            = 1;
var kFN_Bookie_LP       = 2; // Bookie League Performance

/* Coloumn Constants */
var kColumnCount_LL	= 1;
var kColumnCount_BT	= 2;
var kColumnCount_BO	= 2;

/* Element Ids */
var kE_ChkElement_BT	= "chkbettypes_";
var kE_ChkElement_LL	= "chkleagues_";
var kE_ChkElement_BO	= "chkbookies_";

/* Popup window Ids */
var iW_League	= 0;
var iW_Bookie	= 1;

/* Report Type Constants */
var kRT_BookiePerf	= 1;
var kRT_AccountPerf	= 2;
var kRT_LeaguePerf	= 3;

/* Total Row Type */
var kTT_BlockTotalRow	= 1;
var kTT_GrandTotalRow	= 2;

/* Task Ids */
var kInitData			= "10651040901";
var kMainBetTypes		= "10651040904";
var kLeagues			= "10651040905";
var kBookies			= "10651040906";
var kBookiePerformance	= "10651040907";
var kAccountPerformance	= "10651040908";
var kLeaguePerformance	= "10651040909";

/* Server Row Names */
var kSR_Currencies			= "sr1";
var kSR_MainBetTypes		= "sr2";	
var kSR_Leagues				= "sr3";
var kSR_Bookies				= "sr4";
var kSR_BookiePerformance	= "sr5";
var kSR_AccountPerformance	= "sr6";
var kSR_LeaguePerformance	= "sr7";
var kSR_CompanyUnits		= "sr8";

/* Client Row Names */
var kCR_CommonList	= "cr1";
var kCR_Performance	= "cr2";

/* Common Header Constants */
var iH_CommonName_BT    = 0;
var iH_CommonName_LL	= 1;
var iH_CommonName_BO    = 2;

/* BetType List Index */
var iCommonId_BT	= 0;
var iCommonName_BT	    = 1;

/* Bookie List Index */
var iCommonId_BO	= 0;
var iCommonName_BO	= 1;

/* League List Index */
var iCommonId_LL	= 0;
var iCommonName_LL	= 1;

/* Messages Constants */
var iM_CompanyUnit	= 0;

/* Status Ids */
var GT_BookiePerformance	= 51;
var GT_AccountPerformance	= 52;
var GT_LeaguePerformance	= 53;

var T1_FunctionPM  = 101;
var T1_Currencies  = 102;
var T1_CompanyUnits= 103;

var T4_MainBetTypes	= 401;
var T5_Leagues	    = 501;
var T6_Bookies		= 601;

/* Status Messages */
var iGM_BookiePerformance	= 0;
var iGM_AccountPerformance	= 1;
var iGM_LeaguePerformance	= 2;

var iM1_FunctionPM  = 0;
var iM1_Currencies	= 1;
var iM1_CompanyUnits= 2;

var iM4_MainBetTypes= 0;
var iM5_Leagues	    = 0;
var iM6_Bookies		= 0;