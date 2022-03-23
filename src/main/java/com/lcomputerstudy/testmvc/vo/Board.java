package com.lcomputerstudy.testmvc.vo;
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
	
	private int c_idx;
	private int c_board_idx;
	private int c_uidx;
	
	private int c_group;
	private int c_order;
	private int c_depth;
	private int num;
	
	
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

	
	public int getC_idx() {
		return c_idx;
	}

	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}

	public int getC_board_idx() {
		return c_board_idx;
	}

	public void setC_board_idx(int c_board_idx) {
		this.c_board_idx = c_board_idx;
	}
	
	public int getC_uidx() {
		return c_uidx;
	}

	public void setC_uidx(int c_uidx) {
		this.c_uidx = c_uidx;
	}

	public int getC_group() {
		return c_group;
	}

	public void setC_group(int c_group) {
		this.c_group = c_group;
	}

	public int getC_order() {
		return c_order;
	}

	public void setC_order(int c_order) {
		this.c_order = c_order;
	}

	public int getC_depth() {
		return c_depth;
	}

	public void setC_depth(int c_depth) {
		this.c_depth = c_depth;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
}
