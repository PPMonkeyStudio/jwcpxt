package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;

/**
 * 
 * @author JXX
 *
 */
public class RectificationFeedbackDTO {
	private jwcpxt_feedback_rectification feedbackRectification;
	private jwcpxt_dissatisfied_feedback dissatisfiedFeedback;
	private jwcpxt_unit unit;
	private jwcpxt_user user;

	// private UnitDTO unitDTO;

	/*
	 * @Override public String toString() { return
	 * "RectificationFeedbackDTO [feedbackRectification=" + feedbackRectification +
	 * ", dissatisfiedFeedback=" + dissatisfiedFeedback + ", unitDTO=" + unitDTO +
	 * "]"; }
	 */

	/*
	 * public UnitDTO getUnitDTO() { return unitDTO; }
	 * 
	 * public void setUnitDTO(UnitDTO unitDTO) { this.unitDTO = unitDTO; }
	 */

	public RectificationFeedbackDTO(jwcpxt_feedback_rectification feedbackRectification,
			jwcpxt_dissatisfied_feedback dissatisfiedFeedback, jwcpxt_unit unit, jwcpxt_user user) {
		super();
		this.feedbackRectification = feedbackRectification;
		this.dissatisfiedFeedback = dissatisfiedFeedback;
		this.unit = unit;
		this.user = user;
	}

	public RectificationFeedbackDTO() {
		super();
	}

	@Override
	public String toString() {
		return "RectificationFeedbackDTO [feedbackRectification=" + feedbackRectification + ", dissatisfiedFeedback="
				+ dissatisfiedFeedback + ", unit=" + unit + ", user=" + user + "]";
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

	public jwcpxt_user getUser() {
		return user;
	}

	public void setUser(jwcpxt_user user) {
		this.user = user;
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
