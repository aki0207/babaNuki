package babaNuki;

import static babaNuki.Card.Suit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Facilitator {

	private List<Player> playerList;
	private int turnIndex = 0;
	private List<Card> trump;

	private Facilitator(List<Player> playerList) {
		this.playerList = playerList;
		createTrump();
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	private void createTrump() {
		trump =  new ArrayList<>();
		for(int i = 1; i <= 13; i++) {
			trump.add(Card.valueOf(SPADE, i));
			trump.add(Card.valueOf(HEART, i));
			trump.add(Card.valueOf(DIAMOND, i));
			trump.add(Card.valueOf(CLUB, i));
		}

		trump.add(Card.valueOf(JOKER, -1));
	}

	public void shuffleCard() {
		for (int i = 0; i < 100; i++) {
			int dest = ThreadLocalRandom.current().nextInt(53);
			int source = ThreadLocalRandom.current().nextInt(53);

			Card tmp = trump.get(dest);
			trump.set(dest, trump.get(source));
			trump.set(source, tmp);
		}
	}

	public Player getDrawingPlayer() {
		if (turnIndex >= playerList.size()) {
			turnIndex = 0;
		}
		Player player = playerList.get(turnIndex);
		System.out.println(player + "のターンです");
		return player;
	}

	public Player getDrawnPlayer() {
		if (turnIndex + 1 >= playerList.size()) {
			turnIndex = 0;
		} else {
			turnIndex++;
		}
		Player player = playerList.get(turnIndex);
		System.out.println(player + "からカードを引いてください");
		return player;
	}

	public void giveOutCard() {
		System.out.println("カードを配ります");
		int playerIndex = 0;
		for (int i = 0; i < trump.size(); i++) {
			Player player = playerList.get(playerIndex);
			Card card = trump.get(i);
			player.addCard(card);

			if (playerIndex + 1 == playerList.size()) {
				playerIndex = 0;
			} else {
				playerIndex++;
			}
		}
	}

	public void except(Player player) {
		System.out.println(player + "が上がりました");
		playerList = playerList.stream()
				.filter(s -> !s.getName().equals(player.getName()))
				.collect(Collectors.toList());

	}

	public boolean isFinish() {
		return this.playerList.size() == 1;
	}

	public void finish() {
		Player losePlayer = this.playerList.get(0);
		System.out.println(losePlayer + "の負けです");
		System.out.println("ゲームを終了します");
	}

	public static Facilitator valueOf(List<Player> playerList) {
		return new Facilitator(playerList);
	}
}
