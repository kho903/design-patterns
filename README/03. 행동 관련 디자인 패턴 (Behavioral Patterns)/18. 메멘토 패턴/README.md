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