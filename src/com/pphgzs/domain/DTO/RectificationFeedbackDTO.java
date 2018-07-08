package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;

/**
 * 
 * @author JXX
 *
 */
public class RectificationFeedbackDTO {
	private jwcpxt_feedback_rectification feedbackRectification;
	private jwcpxt_dissatisfied_feedback dissatisfiedFeedback;
	private UnitDTO unitDTO;

	@Override
	public String toString() {
		return "RectificationFeedbackDTO [feedbackRectification=" + feedbackRectification + ", dissatisfiedFeedback="
				+ dissatisfiedFeedback + ", unitDTO=" + unitDTO + "]";
	}

	public UnitDTO getUnitDTO() {
		return unitDTO;
	}

	public void setUnitDTO(UnitDTO unitDTO) {
		this.unitDTO = unitDTO;
	}

	public jwcpxt_feedback_rectification getFeedbackRectification() {
		return feedbackRectification;
	}

	public void setFeedbackRectification(jwcpxt_feedback_rectification feedbackRectification) {
		this.feedbackRectification = feedbackRectification;
	}

	public jwcpxt_dissatisfied_feedback getDissatisfiedFeedback() {
		return dissatisfiedFeedback;
	}

	public void setDissatisfiedFeedback(jwcpxt_dissatisfied_feedback dissatisfiedFeedback) {
		this.dissatisfiedFeedback = dissatisfiedFeedback;
	}

}
