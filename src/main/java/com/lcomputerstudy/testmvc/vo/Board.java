package com.lcomputerstudy.testmvc.vo;

public class Board {
	private String title;
	private String content;
	private String writer;
	private String date;
	private int u_idx;
	private int b_idx;
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getU_idx() {
		return u_idx;
	}
	
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}
	
	public int getB_idx() {
		return b_idx;
	}
	
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	
}
