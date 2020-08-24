package babaNuki;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Hand {

	private List<Card> hand = new ArrayList<>();

	public void addCard(Card card) {
		hand.add(card);
	}

	public void throwAwayCard(int index) {
		Card card = hand.remove(index);
		System.out.println(card + "を捨てました");
	}

	public void throwAwaySameCard() {
		boolean isResetCount = false;
		for (int i = 0; i < hand.size(); i++) {
			if (isResetCount) {
				i = 0;
				isResetCount = false;
			}
			Card card = hand.get(i);
			int searchResult = findSameCard(card, i);
			if (searchResult != -1) {
				throwAwayCard(searchResult);
				throwAwayCard(i);
				isResetCount = true;
			}
		}
	}

	public int findSameCard(Card card) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).equals(card)) {
				return i;
			}
		}
		return -1;
	}

	public int findSameCard() {
		Card drawCard = hand.get(hand.size() -1);
		for (int i = 0; i < hand.size() - 1; i++) {
			if (hand.get(i).equals(drawCard)) {
				return i;
			}
		}
		return -1;
	}

	public int findSameCard(Card card, int ignoreIndex) {

		for (int i = 0; i < this.hand.size(); i++) {
			if (ignoreIndex == i) {
				continue;
			}
			if (this.hand.get(i).equals(card)) {
				return i;
			}
		}
		return -1;
	}

	public List<Card> getHand() {
		return this.hand;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(" ");
		hand.forEach(s -> sj.add(s.toString()));
		return sj.toString();
	}
}
