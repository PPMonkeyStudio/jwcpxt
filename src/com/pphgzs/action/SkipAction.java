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

	public String skipServiceInstance() {
		return "skipServiceInstance";
	}

	public String skipAbarbeitungIndex() {
		return "skipAbarbeitungIndex";
	}

	public String skipAbarbeitungManager() {
		return "skipAbarbeitungManager";
	}

	public String skipNavbarAbarbeitung() {
		return "navbarAbarbeitung";
	}

	public String skipNavbarAppraisal() {
		return "navbarAppraisal";
	}

	public String skipAbarbeitungProceed() {
		return "skipAbarbeitungProceed";
	}

	public String skipAbarbeitungCheck() {
		return "skipAbarbeitungCheck";
	}

	/* hy */
	public String skipQuestionnaireDetails() {
		return "skipQuestionnaireDetails";
	}

	public String skipQuestionnaireDetailsJsp() {
		return "skipQuestionnaireDetailsJsp";
	}

	public String skipReturnedPartyInformation() {
		return "skipReturnedPartyInformation";
	}

	public String skipPoliceAssessmentPage() {
		return "skipPoliceAssessmentPage";
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

}
