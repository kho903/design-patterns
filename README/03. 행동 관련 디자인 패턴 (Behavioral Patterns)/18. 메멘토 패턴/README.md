# 메멘토 (Memento) 패턴
캡슐화를 유지하면서 객체 내부 상태를 외부에 저장하는 방법
- 객체 상태를 외부에 저장했다가 해당 상태로 다시 복구할 수 있다.

## 적용 전
Game
```java
package com.jikim.designpatterns._03_behavioral_patterns._18_memento.before;

public class Game {

	private int redTeamScore;
	private int blueTeamScore;

	public int getRedTeamScore() {
		return redTeamScore;
	}

	public void setRedTeamScore(int redTeamScore) {
		this.redTeamScore = redTeamScore;
	}

	public int getBlueTeamScore() {
		return blueTeamScore;
	}

	public void setBlueTeamScore(int blueTeamScore) {
		this.blueTeamScore = blueTeamScore;
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._18_memento.before;

public class Client {
	public static void main(String[] args) {
		Game game = new Game();
		game.setRedTeamScore(10);
		game.setBlueTeamScore(20);

		int redTeamScore = game.getRedTeamScore();
		int blueTeamScore = game.getBlueTeamScore();

		Game restoredGame = new Game();
		restoredGame.setRedTeamScore(redTeamScore);
		restoredGame.setBlueTeamScore(blueTeamScore);
	}
}
```

## 적용 후
GameSave
```java
package com.jikim.designpatterns._03_behavioral_patterns._18_memento.after;

public final class GameSave {

	private final int blueTeamScore;

	private final int redTeamScore;

	public GameSave(int blueTeamScore, int redTeamScore) {
		this.blueTeamScore = blueTeamScore;
		this.redTeamScore = redTeamScore;
	}

	public int getBlueTeamScore() {
		return blueTeamScore;
	}

	public int getRedTeamScore() {
		return redTeamScore;
	}
}
```
Game
```java
package com.jikim.designpatterns._03_behavioral_patterns._18_memento.after;

public class Game {

	private int redTeamScore;
	private int blueTeamScore;

	public int getRedTeamScore() {
		return redTeamScore;
	}

	public void setRedTeamScore(int redTeamScore) {
		this.redTeamScore = redTeamScore;
	}

	public int getBlueTeamScore() {
		return blueTeamScore;
	}

	public void setBlueTeamScore(int blueTeamScore) {
		this.blueTeamScore = blueTeamScore;
	}

	public GameSave save() {
		return new GameSave(this.blueTeamScore, this.redTeamScore);
	}

	public void restore(GameSave gameSave) {
		this.redTeamScore = gameSave.getRedTeamScore();
		this.blueTeamScore = gameSave.getBlueTeamScore();
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._18_memento.after;

public class Client {

	public static void main(String[] args) {
		Game game = new Game();
		game.setBlueTeamScore(10);
		game.setRedTeamScore(20);

		GameSave save = game.save();

		game.setBlueTeamScore(12);
		game.setRedTeamScore(22);

		game.restore(save);

		System.out.println(game.getBlueTeamScore());
		System.out.println(game.getRedTeamScore());
	}
}
```

## 장점과 단점
### 장점
- 캡슐화를 지키면서 상태 객체 상태 스냅샷을 만들 수 있다.
- 객체 상태 저장하고 또는 복원하는 역할을 CareTaker에게 위임할 수 있다.
- 객체 상태가 바뀌어도 클라이언트 코드는 변경되지 않는다.

### 단점
- 많은 정보를 저장하는 Mementor를 자주 생성하는 경우 메모리 사용량에 많은 영향을 줄 수 있다.

## 자바에서 찾아보는 패턴
- 객체 직렬화, java.io.Serializable
- java.util.Date
