package com.pphgzs.domain.DTO;

/**
 * 
 * @author JXX
 *
 */
public class StatisQuestionDTO {
	private QuestionOptionAnswerDTO questionOptionAnswerDTO;
	private int count;

	public QuestionOptionAnswerDTO getQuestionOptionAnswerDTO() {
		return questionOptionAnswerDTO;
	}

	public void setQuestionOptionAnswerDTO(QuestionOptionAnswerDTO questionOptionAnswerDTO) {
		this.questionOptionAnswerDTO = questionOptionAnswerDTO;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StatisQuestionDTO [questionOptionAnswerDTO=" + questionOptionAnswerDTO + ", count=" + count + "]";
	}

	public StatisQuestionDTO(QuestionOptionAnswerDTO questionOptionAnswerDTO, int count) {
		super();
		this.questionOptionAnswerDTO = questionOptionAnswerDTO;
		this.count = count;
	}

	public StatisQuestionDTO() {
		super();
	}

}
