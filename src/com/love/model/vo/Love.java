package com.love.model.vo;

public class Love {
	
	private int loveNumber;
	private int boardNumber;
	private String userId;
	
	public Love() {
		
	}

	public Love(int loveNumber, int boardNumber, String userId) {
		super();
		this.loveNumber = loveNumber;
		this.boardNumber = boardNumber;
		this.userId = userId;
	}

	public int getLoveNumber() {
		return loveNumber;
	}

	public void setLoveNumber(int loveNumber) {
		this.loveNumber = loveNumber;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
