package com.board.model.vo;

import java.util.Date;

public class Comment {
	private int commentNumber;
	private String commentWriter;
	private String commentContent;
	private int boardNumber;
	private int refNumber;
	private int commentLevel;
	private Date commentDate;
	
	public Comment() {
		
	}
	
	public Comment(int commentNumber, String commentWriter, String commentContent, int boardNumber, int refNumber,
			int commentLevel, Date commentDate) {
		super();
		this.commentNumber = commentNumber;
		this.commentWriter = commentWriter;
		this.commentContent = commentContent;
		this.boardNumber = boardNumber;
		this.refNumber = refNumber;
		this.commentLevel = commentLevel;
		this.commentDate = commentDate;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public String getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public int getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(int refNumber) {
		this.refNumber = refNumber;
	}

	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	
	
}
