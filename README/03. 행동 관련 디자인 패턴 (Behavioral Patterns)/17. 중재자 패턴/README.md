# 중재자 (Mediator) 패턴
여러 객체들이 소통하는 방법을 캡슐화하는 패턴
- 여러 컴포넌트간의 결합도를 중재자를 통해 낮출 수 있다.

## 적용 전
CleaningService
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.before;

public class CleaningService {

	public void clean(Gym gym) {
		System.out.println("clean " + gym);
	}

	public void getTower(Guest guest, int numberOfTower) {
		System.out.println(numberOfTower + " towers to " + guest);
	}

	public void clean(Restaurant restaurant) {
		System.out.println("clean " + restaurant);
	}

}
```
Restaurant
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.before;

public class Restaurant {

	private CleaningService cleaningService = new CleaningService();

	public void dinner(Guest guest) {
		System.out.println("dinner " + guest);
	}

	public void clean() {
		cleaningService.clean(this);
	}
}
```
Gym
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.before;

public class Gym {

	private CleaningService cleaningService;

	public void clean() {
		cleaningService.clean(this);
	}
}
```
Guest
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.before;

public class Guest {

	private Restaurant restaurant = new Restaurant();
	private CleaningService cleaningService = new CleaningService();

	public void dinner() {
		restaurant.dinner(this);
	}

	public void getTower(int numberOfTower) {
		cleaningService.getTower(this, numberOfTower);
	}

}
```
Hotel
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.before;

public class Hotel {
	public static void main(String[] args) {
		Guest guest = new Guest();
		guest.getTower(3);
		guest.dinner();

		Restaurant restaurant = new Restaurant();
		restaurant.clean();
	}
}
```

## 적용 후
CleaningService
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.after;

public class CleaningService {

	private FrontDesk frontDesk = new FrontDesk();

	public void getTowers(Integer guestId, int numberOfTowers) {
		String roomNumber = this.frontDesk.getRoomNumberFor(guestId);
		System.out.println("provide " + numberOfTowers + " to " + roomNumber);
	}
}
```
Restaurant
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.after;

import java.time.LocalDateTime;

public class Restaurant {
	public void dinner(Integer id, LocalDateTime dateTime) {
		System.out.println("#" + id + " dinner in" + dateTime);
	}
}
```
FrontDesk
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.after;

import java.time.LocalDateTime;

public class FrontDesk {

	private CleaningService cleaningService = new CleaningService();

	private Restaurant restaurant = new Restaurant();

	public void getTowers(Guest guest, int numberOfTowers) {
		cleaningService.getTowers(guest.getId(), numberOfTowers);
	}

	public String getRoomNumberFor(Integer guestId) {
		return "#" + guestId;
	}

	public void dinner(Guest guest, LocalDateTime dateTime) {
		restaurant.dinner(guest.getId(), dateTime);
	}
}
```
Guest
```java
package com.jikim.designpatterns._03_behavioral_patterns._17_mediator.after;

import java.time.LocalDateTime;

public class Guest {

	private Integer id;

	private FrontDesk frontDesk = new FrontDesk();

	public void getTowers(int numberOfTowers) {
		this.frontDesk.getTowers(this, numberOfTowers);
	}

	public void dinner(LocalDateTime dateTime) {
		this.frontDesk.dinner(this, dateTime);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FrontDesk getFrontDesk() {
		return frontDesk;
	}

	public void setFrontDesk(FrontDesk frontDesk) {
		this.frontDesk = frontDesk;
	}
}
```
