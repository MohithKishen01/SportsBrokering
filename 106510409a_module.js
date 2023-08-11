/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie - League Performance</font></H5>
*/

/**
	This class extends the PEModule and initialise the moduleid, versionid.
*/
function LeaguePerformModule_106510409a ()
{
	this.inheritFrom = PEModule;
	this.inheritFrom ();
	
	/* p106trading - mssqlv51 - p10651soccer - Performance By Bookie - League Performance */
	this.ModuleId   = 106510409;
    this.VersionId  = 1.01;
}

/**
    Initializes objects namely PEProject, PEDocument, PEComponent, PEValidation & PEMessages.
*/
function Constructor_106510409a ()
{
	UDModule	= new LeaguePerformModule_106510409a();
	
	UDModule.PROJ	= new PEProject ();
	UDModule.DOM    = new PEDocument ();
    UDModule.COMP   = new PEComponent ();
    UDModule.TBL	= new PETable ();    
    UDModule.VAL	= new PEValidation ();
    UDModule.MSG    = new PEMessages ();
	UDModule.SPORT  = new PESport ();
    UDModule.MATH	= new PEMath ();
    UDModule.DATE	= new PEDate ();

    UDModule.PROJEX	= new Trading_10651 ();
}