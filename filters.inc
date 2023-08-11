<div id="divFilters" class="divFilters">
	<table class="filter_tbl" cellpadding="0" cellspacing="0" align="right" border="0">
		<tr height="26">
			<td class="border_top_left_fltr" width="5">
				&nbsp;
			</td>
			<td class="border_top_fltr" width="90">
				Live Status
			</td>
			<td class="border_top_fltr">
				<select id="cbLiveStatus" class="dropdown" onkeyup="UDModule.COMP.changeFocus_CV (event, 'cbCurrencies')">
					<option value="-1">[All]</option>
					<option value="1">Live</option>
					<option value="0">Non-Live</option>					
				</select>
			</td>
			<td class="border_top_right_fltr" width="5">
				&nbsp;
			</td>
		</tr>		
		<tr height="26">
			<td class="border_left_fltr">
				&nbsp;
			</td>
			<td>
				Currency
			</td>
			<td>
				<select id="cbCurrencies" class="dropdown">
					<option value="0">[All]</option>
				</select>
			</td>
			<td class="border_right_fltr">
				&nbsp;
			</td>
		</tr>		
		<tr height="30">
			<td class="border_top_left_bottom_fltr">
				&nbsp;
			</td>
			<td class="border_top_bottom_fltr">
				<font class="clear" title="Click here to close filters..." onclick="closeFiltersClicked_106510409 ()">C L O S E</font>
			</td>
			<td class="border_top_bottom_fltr" align="right">
				<input type="button" id="btnShow_Filter" class="button" value="&nbsp;Show&nbsp;" onclick="showFiltersClicked_106510409 ()" />
			</td>
			<td class="border_top_bottom_right_fltr">
				&nbsp;
			</td>
		</tr>
	</table>
</div>