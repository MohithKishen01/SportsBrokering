/**
    @fileoverview <H5><font color="#8A8A8A">module:</font><font color="#AA6600">Performance By Bookie</font></H5>
*/

var m_bIsFilter = false;

/**
	Sets the filter position
*/
function setFilterPosition_106510409 ()
{
	UDModule.COMP.getElement ("divFilters").style.left = UDModule.COMP.findPosX (UDModule.COMP.getElement ("tblMain")) + 645;
}

/**
	On click displays the filter.
	
	@see #showFilters_106510409	
*/
function moreFiltersClicked_106510409 ()
{
	var bIsVisible = (UDModule.COMP.getElement ("divFilters").style.display == "block");
	showFilters_106510409 (!bIsVisible);	
}

/**
	Displays the filter.
	
	@see #initFilters_106510409	
*/
function showFilters_106510409 (bShow)
{	
	var oShowVisible = "visible", oDisplayFilter = "none";	
	var oLink = m_arrLinks [0];
	var oTitle = m_arrLinks [2];
	
	if (bShow)
	{
		UDModule.COMP.setDisplay ("btnShow", "none");
		m_bIsFilter = true;
				
		oShowVisible = "hidden", oDisplayFilter = "block";		
		oLink = m_arrLinks [1];
		oTitle = m_arrLinks [3];
		UDModule.COMP.setFocus_DD ("cbLiveStatus");
	}
	else
	{
		initFilters_106510409 ();
		UDModule.COMP.setDisplay ("btnShow", "block");
	}
	
	UDModule.COMP.setDisplay ("divFilters", oDisplayFilter);
	UDModule.COMP.replaceDynamicHtml ("fntLinks", oLink);
	UDModule.COMP.getElement ("fntLinks").title = oTitle;
}

/**
	Closes the filter
	
	@see #showFilters_106510409	
*/
function closeFiltersClicked_106510409 ()
{	
	m_bIsFilter = false;
	showFilters_106510409 (false);
}

/**
	Checks for filter	
*/
function isFilter_106510409 ()
{
	return m_bIsFilter;
}