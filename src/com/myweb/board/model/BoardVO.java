package com.myweb.board.model;

import java.sql.Timestamp;

public class BoardVO {
	
/*
bno NUMBER(10, 0) not null,
writer VARCHAR2(50) not null,
title VARCHAR2(200) not null,
content VARCHAR2(2000) not null,
regdate date default sysdate,
hit NUMBER(10,0) default 0
*/
	//테이블명과 동일하게 생성한다
	private int bno;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate; //Timestamp로 생성
	private int hit;
	
	public BoardVO() {}

	public BoardVO(int bno, String writer, String title, String content, Timestamp regdate, int hit) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", writer=" + writer + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", hit=" + hit + "]";
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
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

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
	
	
	
	
}



































