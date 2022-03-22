package com.lcomputerstudy.testmvc.vo;
//주석
public class Board {
	private String title;
	private String content;
	private String writer;
	private String date;
	private int u_idx;
	private int b_idx;
	private int view_count;
	private int ROWNUM;
	private int group;
	private int depth;
	private int order;
	private String comment;
	
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
	
	public int getView_count() {
		return view_count;
	}
	
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	public int getROWNUM() {
		return ROWNUM;
	}
	
	public void setRownum(int ROWNUM) {
		this.ROWNUM = ROWNUM;
	}
	public int getGroup() {
		return group;
	}
	
	public void setGroup(int group) {
		this.group=group;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void SetComment(String comment) {
		this.comment = comment;
	}
	
}
