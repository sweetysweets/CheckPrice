package org.edu.nju.model;

import java.util.Date;

public class Comment {
	int id;
	String content;
	int userId;
	Date time;
	int concreteCommodityId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getConcreteCommodityId() {
		return concreteCommodityId;
	}
	public void setConcreteCommodityId(int concreteCommodityId) {
		this.concreteCommodityId = concreteCommodityId;
	}
	
}
