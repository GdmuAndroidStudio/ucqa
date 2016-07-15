package com.dawnlightning.ucqa.bean.response.consult.detailed;

import android.util.Log;

public class PicsBean {
	public PicsBean(){
		
	}
	private String picurl;
	private String title;
	public String getUrl() {
		return picurl;
	}
	public void setUrl(String url) {
		this.picurl =url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "PicsBean [url=" + picurl + ", title=" + title + "]";
	}
	public PicsBean(String url, String title) {
		super();
		url = url.replaceAll("\"","");
		Log.d("kyo","pic bean url = " + url);
		this.picurl = url;
		this.title = title;
	}

}
