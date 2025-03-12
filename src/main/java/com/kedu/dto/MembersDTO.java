package com.kedu.dto;

public class MembersDTO {
	
	private String id;
	private String pw;
	private String name;
	private String contact;
	private String profile_image;
	
	public MembersDTO() {}
	
	public MembersDTO(String id, String pw, String name, String contact, String profile_image) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.contact = contact;
		this.profile_image = profile_image;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
