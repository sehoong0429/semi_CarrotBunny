package com.qna.model.vo;

import java.util.Date;

public class Qna {

	private int qnaNo;
	private String qnaTitle;
	private String qnaWriter;
	private String qnaContent;
	private Date qnaDate;
	private String qnaAnswer;

	public Qna() {

	}

	public Qna(int qnaNo, String qnaTitle, String qnaWriter, String qnaContent, Date qnaDate, String qnaAnswer) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaWriter = qnaWriter;
		this.qnaContent = qnaContent;
		this.qnaDate = qnaDate;
		this.qnaAnswer = qnaAnswer;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaWriter() {
		return qnaWriter;
	}

	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public Date getQnaDate() {
		return qnaDate;
	}

	public void setQnaDate(Date qnaDate) {
		this.qnaDate = qnaDate;
	}

	public String getQnaAnswer() {
		return qnaAnswer;
	}

	public void setQnaAnswer(String qnaAnswer) {
		this.qnaAnswer = qnaAnswer;
	}

	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaWriter=" + qnaWriter + ", qnaContent="
				+ qnaContent + ", qnaDate=" + qnaDate + ", qnaAnswer=" + qnaAnswer + "]";
	}

}
