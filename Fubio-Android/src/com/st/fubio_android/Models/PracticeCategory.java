package com.st.fubio_android.Models;

import java.io.Serializable;

import android.graphics.Bitmap;


public class PracticeCategory implements Serializable{
	String id, title, description, imageUrl, token, sort, productId, price;
	boolean isPrivate;
	Bitmap image;

	public PracticeCategory(String id, String itemTitle, String itemDescription, String itemImageUrl, String itemToken, String itemSort, boolean isPrivate, String price) {
		this.id = id;
		this.title = itemTitle;
		this.description = itemDescription.replace("\\n", " ");
		this.imageUrl = itemImageUrl;
		this.token = itemToken;
		this.sort = itemSort;
		this.isPrivate = isPrivate;
		this.productId = null;
		this.price = price;
		this.image = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
}
