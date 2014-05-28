package com.st.fubio_android.Models;

import android.graphics.Bitmap;

public class Team {
	String id;
	String name;
	String imagename;
	int sort;
	Bitmap image;
	
	public Team(String i, String n, String in, int s) {
		this.id = i;
		this.name = n;
		this.imagename = in;
		this.sort = s;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	public Bitmap getImage() {
		return this.image;
	}
}
