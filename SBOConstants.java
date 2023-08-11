package enlj.p105bet.commonsv11.resource.logics.brecord.sbobet.aweb;

import enlj.p105bet.commonsv11.resource.logics.brecord.amode.*;

public class SBOConstants extends RecConstants
{	
	/* Site Connection Stages */
	public static final int kST101_UnderMaintainance	= 101;
	public static final int kST102_ParamLSC				= 102;
	
	public static final int kST201_AfterLoginPage1		= 201;
	public static final int kST202_AfterLoginPage2		= 202;
	public static final int kST203_AfterLoginPage3		= 203;
	public static final int kST204_AfterLoginSCPage		= 204;
	public static final int kST205_AfterLoginVSCPage	= 205;
	public static final int kST206_AfterLoginPSRPage	= 206;

	public static final int kST301_ProcessWelcome		= 301;
	public static final int kST302_AfterWelcome			= 302;
	
	public static final int kST601_OutstandingNew_PB	= 601;
	public static final int kST602_ReportWinlost_SB		= 602;
	public static final int kST603_NavigateDate			= 603;
	public static final int kST604_WinLose				= 604;

	/* Database Common Index */
	public static final int kDI_SiteLoginId 	= 0;
	public static final int kDI_CommonCode 		= 1;
	public static final int kDI_CommonId 		= 2;
	
	public static final int kDI_SuperCode		= 9;

	/* Parser Param Names */
	public static final String kSP_MasterId_Ex	= "pp_masterid";
	public static final String kSP_AgentId_Ex	= "pp_agentid";
	public static final String kSP_PullDate_Ex	= "pp_pulldate";

	/* Site Param Names */
	public static final String kSP_Welcome_ID		= "sp_welcome_id";
	public static final String kSP_Welcome_KEY		= "sp_welcome_key";
	public static final String kSP_Welcome_LANG		= "sp_welcome_lang";
	public static final String kSP_ReqVerToken		= "sp_reqvertoken";
	
	/* Bet Param Names */
	public static final String kBP_Value_EK		= "bp_value_ek";
	public static final String kBP_Value_P		= "bp_value_p";
	public static final String kBP_Value_MODE	= "bp_value_mode";
	public static final String kBP_MasterId		= "bp_masterid";
	public static final String kBP_AgentId		= "bp_agentid";
	public static final String kBP_MemberId		= "bp_memberid";
	
	/* Win Lose Param Names */
	public static final String kWLP_Value_P		= "wlp_value_p";
	public static final String kWLP_Value_EK	= "wlp_value_ek";
	public static final String kWLP_Value_MODE	= "wlp_value_mode";

	/* Time Zone Offset */
	public static final int kTZ_GMTMinutes		= 480;
	public static final String kTZ_ZoneOffset	= "GMT+08:00";

	/* Thread Delay Time for Windows */
	public static final long kTIM_PendingBet	= -60000;
	public static final long kTIM_SettledBet	= -60000;
	public static final long kTIM_PCWL_SM		= -60000;
	public static final long kTIM_PCWL_MA		= -60000;
	
	/* Thread Name for Windows */
	public static final String kTN_PendingBet	= "sbo_pendingbet";
	public static final String kTN_SettledBet	= "sbo_settledbet";

	/* Pending Bet Window Stages */
	public static final int kWS11_MasterList_PB		= 11;
	public static final int kWS12_AgentList_PB		= 12;
	public static final int kWS13_MemberList_PB		= 13;
	public static final int kWS14_BetList_PB		= 14;
	
	/* Settled Bet Window Stages */
	public static final int kWS21_MasterList_SB		= 21;
	public static final int kWS22_AgentList_SB		= 22;
	public static final int kWS23_MemberList_SB		= 23;
	public static final int kWS24_BetList_SB		= 24;

	/* Win Loss Window Stages */
	public static final int kWS31_WinLose	= 31; 
	public static final int kWS32_PageWL	= 32;
	
	/* Window Key */
	public static final String kWK_WinLose_SM 	= "winlose_sm";
	public static final String kWK_WinLose_MA 	= "winlose_ma";
	public static final String kWK_WinLose_AG	= "winlose_ag";
	
	/* Common List Constants */
	public static final int kCommonId	= 0;
	public static final int kCommonCode	= 1;

	/* Site Param Names */
	public static final String kSP_DDLMonth = "ddlmonth";
	public static final String kSP_DDLYear 	= "ddlyear";
	public static final String kSP_DPFrom	= "dpfrom";
	public static final String kSP_DPTo		= "dpto";
}
