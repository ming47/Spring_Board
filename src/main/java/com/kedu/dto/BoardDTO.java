package com.kedu.dto;

import java.sql.Timestamp;

public class BoardDTO {

	private String title;
	private String writer;
	private String contents;
	private int seq;
	private Timestamp write_date;
	private int view_count;
	
	public BoardDTO() {}

	public BoardDTO(String title, String writer, String contents, int seq, Timestamp write_date, int view_count) {
		super();
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.seq = seq;
		this.write_date = write_date;
		this.view_count = view_count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Timestamp getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	};
	

}
