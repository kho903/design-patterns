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

## 적용 후
- TourPlanBuilder 인터페이스 생성
```java
public interface TourPlanBuilder {

	TourPlanBuilder title(String title);

	TourPlanBuilder nightsAndDays(int nights, int days);

	TourPlanBuilder startDate(LocalDate startDate);

	TourPlanBuilder whereToStay(String whereToStay);

	TourPlanBuilder addPlan(int day, String plan);

	TourPlan getPlan();

}
```
- DefaultTourBuilder에서 TourPlanBuilder 인터페이스 구현
```java

public class DefaultTourBuilder implements TourPlanBuilder {

	private String title;

	private int nights;

	private int days;

	private LocalDate startDate;

	private String whereToStay;

	private List<DetailPlan> plans;

	@Override
	public TourPlanBuilder title(String title) {
		this.title = title;
		return this;
	}

	@Override
	public TourPlanBuilder nightsAndDays(int nights, int days) {
		this.nights = nights;
		this.days = days;
		return this;
	}

	@Override
	public TourPlanBuilder startDate(LocalDate startDate) {
		this.startDate = startDate;
		return this;
	}

	@Override
	public TourPlanBuilder whereToStay(String whereToStay) {
		this.whereToStay = whereToStay;
		return this;
	}

	@Override
	public TourPlanBuilder addPlan(int day, String plan) {
		if (this.plans == null) {
			this.plans = new ArrayList<>();
		}
		this.plans.add(new DetailPlan(day, plan));
		return this;
	}

	@Override
	public TourPlan getPlan() {
		return new TourPlan(title, nights, days, startDate, whereToStay, plans);
	}
}

```
- 실행 코드
```java

public class App {

	public static void main(String[] args) {
		TourPlanBuilder builder = new DefaultTourBuilder();
		TourPlan cancun = builder.title("칸쿤 여행")
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
		TourPlan cancunTrip = director.cancunTrip();

		System.out.println(cancunTrip);

		TourPlan longBeachTrip = builder.title("롱비치")
			.startDate(LocalDate.of(2022, 7, 5))
			.getPlan();
		System.out.println(longBeachTrip);
	}
}
```
- TourDirector 클래스를 만들어 미리 만들어 놓은 TourPlan을 제공해 줄 수 있다.
```java
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
```
```java

public class App {

	public static void main(String[] args) {
		TourDirector director = new TourDirector(new DefaultTourBuilder());
		TourPlan cancunTrip = director.cancunTrip();
		System.out.println(cancunTrip);

		TourPlan longBeachTrip = director.longBeachTrip();
		System.out.println(longBeachTrip);
	}
}

```

## 장점과 단점
### 장점
- 만들기 복잡한 객체를 순차적으로 만들 수 있다.
- 복잡한 객체를 만드는 구체적인 과정을 숨길 수 있다.
- 동일한 프로세스를 통해 각기 다르게 구성된 객체를 만들 수도 있다.
- 불완전한 객체를 사용하지 못하도록 방지할 수 있다.

### 단점
- 원하는 객체를 만들려면 빌더부터 만들어야 한다.
- 구조가 복잡해 진다. (트레이드 오프)

## 실무에서 어떻게 쓰일까?
- 자바 8 Stram.Builder API
- StringBuilder는 빌더 패턴일까?
  - StringBuilder 는 빌더 패턴과 유사하지만 GoF에서 말하는 빌더 패턴과는 차이가 있다.
  - 100% 일치한다고 볼 수는 없지만 빌더 패턴이라고 본다.
  - https://softwareengineering.stackexchange.com/questions/305504/is-stringbuilder-an-application-of-the-builder-design-pattern
- 롬복의 @Builder
  - https://projectlombok.org/features/Builder
- 스프링
  - UriComponentsBuilder
  - MockMvcWebClientBuilder
  - ...Builder
