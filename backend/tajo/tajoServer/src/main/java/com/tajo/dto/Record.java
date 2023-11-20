package com.tajo.dto;

public class Record {
	String userid;
	int distance;
	int cal;
	int time;
	String lastride;
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	

	public String getLastride() {
		return lastride;
	}

	public void setLastride(String lastride) {
		this.lastride = lastride;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getCal() {
		return cal;
	}

	public void setCal(int cal) {
		this.cal = cal;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	
}
