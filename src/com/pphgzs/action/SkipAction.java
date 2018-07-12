package com.pphgzs.action;

public class SkipAction {
	private String definitionId;
	private String serviceClientId;

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

	public String skipPoliceAssessmentPageJsp() {
		return "skipPoliceAssessmentPageJsp";
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getServiceClientId() {
		return serviceClientId;
	}

	public void setServiceClientId(String serviceClientId) {
		this.serviceClientId = serviceClientId;
	}

}
