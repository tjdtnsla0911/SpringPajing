package com.ora.vo;

public class Pajing {
	int startPage;
	int nowPage;
	int allpage;
	int downPage;
	int upPage;
	int realAllPage;
	
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getRealAllPage() {
		return realAllPage;
	}
	public void setRealAllPage(int realAllPage) {
		this.realAllPage=realAllPage;
	}

	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getAllpage() {
		return allpage;
	}
	public void setAllpage(int allpage) {
		this.allpage = allpage;
	}
	public int getDownPage() {
		return downPage;
	}
	public void setDownPage(int downPage) {
		this.downPage = downPage;
	}
	public int getUpPage() {
		return upPage;
	}
	public void setUpPage(int upPage) {
		this.upPage = upPage;
	}
	@Override
	public String toString() {
		return "Pajing [startPage=" + startPage + ", nowPage=" + nowPage + ", allpage=" + allpage + ", downPage="
				+ downPage + ", upPage=" + upPage + ", realAllPage=" + realAllPage + "]";
	}
	public Pajing() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
