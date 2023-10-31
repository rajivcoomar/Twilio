package com.rajivcoomar.ivrtest.readthemanual.model;

public class TestCaseRow {
	
	private String Bot;
	private int Index;
	private String UserInput;
	private String Type;
	
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getBot() {
		return Bot;
	}
	public void setBot(String bot) {
		Bot = bot;
	}
	public int getIndex() {
		return Index;
	}
	public void setIndex(int index) {
		Index = index;
	}
	public String getUserInput() {
		return UserInput;
	}
	public void setUserInput(String userInput) {
		UserInput = userInput;
	}
	@Override
	public String toString() {
		return "TestCaseRow [Bot=" + Bot + ", Index=" + Index + ", UserInput=" + UserInput + ", Type=" + Type
				 + "]";
	}

}
