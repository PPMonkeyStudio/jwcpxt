package com.pphgzs.domain.DTO;

import java.util.List;

/**
 * 
 * @author JXX
 *
 */
public class StatisDIssaServiceDateDTO {
	private String dateScale;
	private List<StatisDIssaServiceDTO> listStatisDIssaServiceDTO;

	public String getDateScale() {
		return dateScale;
	}

	public void setDateScale(String dateScale) {
		this.dateScale = dateScale;
	}

	public List<StatisDIssaServiceDTO> getListStatisDIssaServiceDTO() {
		return listStatisDIssaServiceDTO;
	}

	public void setListStatisDIssaServiceDTO(List<StatisDIssaServiceDTO> listStatisDIssaServiceDTO) {
		this.listStatisDIssaServiceDTO = listStatisDIssaServiceDTO;
	}

	@Override
	public String toString() {
		return "StatisDIssaServiceDateDTO [dateScale=" + dateScale + ", listStatisDIssaServiceDTO="
				+ listStatisDIssaServiceDTO + "]";
	}

	public StatisDIssaServiceDateDTO(String dateScale, List<StatisDIssaServiceDTO> listStatisDIssaServiceDTO) {
		super();
		this.dateScale = dateScale;
		this.listStatisDIssaServiceDTO = listStatisDIssaServiceDTO;
	}

	public StatisDIssaServiceDateDTO() {
		super();
	}

}
