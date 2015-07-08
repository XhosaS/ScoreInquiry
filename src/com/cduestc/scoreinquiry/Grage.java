package com.cduestc.scoreinquiry;

public class Grage {
	private String mSubject;
	private Integer mScore;
	private Boolean mPass = true;
	public Boolean getPass() {
		return mPass;
	}
	public void setPass(Boolean pass) {
		mPass = pass;
	}
	public String getSubject() {
		return mSubject;
	}
	public void setSubject(String subject) {
		mSubject = subject;
	}
	public Integer getScore() {
		return mScore;
	}
	public void setScore(Integer score) {
		mScore = score;
	}
}
