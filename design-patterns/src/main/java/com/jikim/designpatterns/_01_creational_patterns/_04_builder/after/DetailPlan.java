package com.jikim.designpatterns._01_creational_patterns._04_builder.after;

public class DetailPlan {
	int day;
	String plan;

	public DetailPlan(int day, String plan) {
		this.day = day;
		this.plan = plan;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "DetailPlan{" +
			"day=" + day +
			", plan='" + plan + '\'' +
			'}';
	}
}
