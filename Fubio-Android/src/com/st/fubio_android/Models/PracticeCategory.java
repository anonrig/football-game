package com.st.fubio_android.Models;

import android.graphics.Bitmap;


public class PracticeCategory {
	String id, itemTitle, itemDescription, itemImageUrl, itemToken, itemSort, itemProductId, itemPrice;
	boolean isPrivate;
	Bitmap bitmap;
	
	public PracticeCategory(String id, String itemTitle, String itemDescription, String itemImageUrl, String itemToken, String itemSort, boolean isPrivate) {
		this.id = id;
		this.itemTitle = itemTitle;
		this.itemDescription = itemDescription.replace("\\n", " ");
		this.itemImageUrl = itemImageUrl;
		this.itemToken = itemToken;
		this.itemSort = itemSort;
		this.isPrivate = isPrivate;
		this.itemProductId = null;
		this.itemPrice = null;
		this.bitmap = null;
	}

	
	public Bitmap getBitmap() {
		return bitmap;
	}
	

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getItemTitle() {
		return itemTitle;
	}


	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public String getItemImageUrl() {
		return itemImageUrl;
	}


	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}


	public String getItemToken() {
		return itemToken;
	}


	public void setItemToken(String itemToken) {
		this.itemToken = itemToken;
	}


	public String getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(String itemPrice) {
		if(itemPrice != null)
		this.itemPrice = itemPrice;
	}


	public String getPorductID() {
		return itemProductId;
	}


	public void setPorductID(String productId) {
		if(productId != null)
		this.itemProductId = productId;
	}
	
	
	public boolean isPrivate(){
		return isPrivate;
	}
}
