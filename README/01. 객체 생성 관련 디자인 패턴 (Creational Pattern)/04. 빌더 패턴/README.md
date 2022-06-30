# 빌더(Builder) 패턴
동일한 프로세스를 거쳐 다양한 구성의 인스턴스를 만드는 방법
- (복잡한) 객체를 만드는 프로세스를 독립적으로 분리할 수 있다.

## 적용 전
- TourPlan
  - Builder 패턴을 적용하기 전에는 이렇게 생성자가 많아 질 수 있다.
```java
public class TourPlan {
	private String title;

	private int nights;

	private int days;

	private LocalDate startDate;

	private String whereToStay;

	private List<DetailPlan> plans;

	public TourPlan() {
	}

	public TourPlan(String title, int nights, int days, LocalDate startDate, String whereToStay,
		List<DetailPlan> plans) {
		this.title = title;
		this.nights = nights;
		this.days = days;
		this.startDate = startDate;
		this.whereToStay = whereToStay;
		this.plans = plans;
	}
	
	public TourPlan(String title, LocalDate startDate, String whereToStay, List<DetailPlan> plans) {
		this.title = title;
		this.startDate = startDate;
		this.whereToStay = whereToStay;
		this.plans = plans;
	}

	public TourPlan(String title, LocalDate startDate) {
		this.title = title;
		this.startDate = startDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNights() {
		return nights;
	}

	public void setNights(int nights) {
		this.nights = nights;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getWhereToStay() {
		return whereToStay;
	}

	public void setWhereToStay(String whereToStay) {
		this.whereToStay = whereToStay;
	}

	public List<DetailPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<DetailPlan> plans) {
		this.plans = plans;
	}

	public void addPlan(int day, String plan) {
		if (this.plans == null)
			this.plans = new ArrayList<>();
		this.plans.add(new DetailPlan(day, plan));
	}

	@Override
	public String toString() {
		return "TourPlan{" +
			"title='" + title + '\'' +
			", nights=" + nights +
			", days=" + days +
			", startDate=" + startDate +
			", whereToStay='" + whereToStay + '\'' +
			", plans=" + plans +
			'}';
	}
}
```
- App (클라이언트 코드)
  - 장황해진다.
  - 불완전한 객체 사용을 방지하지 못한다.
```java

public class App {

	public static void main(String[] args) {
		TourPlan tourPlan = new TourPlan();
		tourPlan.setTitle("칸쿤 여행");
		tourPlan.setNights(2);
		tourPlan.setDays(3);
		tourPlan.setStartDate(LocalDate.of(2022, 6, 30));
		tourPlan.setWhereToStay("리조트");
		tourPlan.addPlan(0, "체크인 이후 짐풀기");
		tourPlan.addPlan(0, "저녁 식사");
		tourPlan.addPlan(1, "조식 부페에서 식사");
		tourPlan.addPlan(1, "해변가 산책");
		tourPlan.addPlan(1, "점심은 수영장 근처 음식점에서 먹기");
		tourPlan.addPlan(1, "리조트 수영장에서 놀기");
		tourPlan.addPlan(1, "저녁은 BBQ 식당에서 스테이크");
		tourPlan.addPlan(2, "조식 부페에서 식사");
		tourPlan.addPlan(2, "체크아웃");

		System.out.println(tourPlan);

		TourPlan shortTrip = new TourPlan();
		shortTrip.setTitle("오레곤 롱비치 여행");
		shortTrip.setStartDate(LocalDate.of(2022, 7, 5));
		System.out.println(shortTrip);

		TourPlan anotherPlan = new TourPlan();
	}
}
```
