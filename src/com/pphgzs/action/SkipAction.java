package com.pphgzs.action;

public class SkipAction {
	private String definitionId;

	public String skipSidebar() {
		return "skipSidebar";
	}

	public String skipFooter() {
		return "skipFooter";
	}

	public String skipNavbar() {
		return "skipNavbar";
	}

	public String skipUserList() {
		return "skipUserList";
	}

	public String skipManagerIndex() {
		return "skipManagerIndex";
	}

	public String skipManagerUnit() {
		return "skipManagerUnit";
	}

	public String skipManagerUser() {
		return "skipManagerUser";
	}

	public String skipManagerService() {
		return "skipManagerService";
	}

	/* hy */
	public String skipQuestionnaireDetails() {
		return "skipQuestionnaireDetails";
	}
	public String skipQuestionnaireDetailsJsp() {
		return "skipQuestionnaireDetailsJsp";
	}
	

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

}
