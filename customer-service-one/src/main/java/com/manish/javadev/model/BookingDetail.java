package com.manish.javadev.model;

public class BookingDetail {
	private int vehicalId;
	private double vehicalPrice;
	private String from;
	private String to;

	public BookingDetail(int vehicalId, double vehicalPrice, String from, String to) {
		super();
		this.vehicalId = vehicalId;
		this.vehicalPrice = vehicalPrice;
		this.from = from;
		this.to = to;
	}

	public BookingDetail() {
		super();
	}

	public int getVehicalId() {
		return vehicalId;
	}

	public void setVehicalId(int vehicalId) {
		this.vehicalId = vehicalId;
	}

	public double getVehicalPrice() {
		return vehicalPrice;
	}

	public void setVehicalPrice(double vehicalPrice) {
		this.vehicalPrice = vehicalPrice;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
