package com.jikim.designpatterns._01_creational_patterns._04_builder.after;

import java.time.LocalDate;

public class TourDirector {
	private TourPlanBuilder tourPlanBuilder;

	public TourDirector(TourPlanBuilder tourPlanBuilder) {
		this.tourPlanBuilder = tourPlanBuilder;
	}

	public TourPlan cancunTrip() {
		return tourPlanBuilder.title("칸쿤 여행")
			.nightsAndDays(2, 3)
			.startDate(LocalDate.of(2022, 6, 30))
			.whereToStay("리조트")
			.addPlan(0, "체크인 이후 짐풀기")
			.addPlan(0, "저녁 식사")
			.addPlan(1, "조식 부페에서 식사")
			.addPlan(1, "해변가 산책")
			.addPlan(1, "점심은 수영장 근처 음식점에서 먹기")
			.addPlan(1, "리조트 수영장에서 놀기")
			.addPlan(1, "저녁은 BBQ 식당에서 스테이크")
			.addPlan(2, "조식 부페에서 식사")
			.addPlan(2, "체크아웃")
			.getPlan();
	}

	public TourPlan longBeachTrip() {
		return tourPlanBuilder.title("롱비치")
			.startDate(LocalDate.of(2022, 7, 5))
			.getPlan();
	}
}
