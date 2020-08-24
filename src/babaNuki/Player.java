package babaNuki;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

	private final String name;
	private Hand hand = new Hand();

	public Player(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand.getHand();
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}



	public String getName() {
		return this.name;
	}

	public Player out() {
		return this;
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public void throwAwaySameCard() {
		System.out.println(name);
		hand.throwAwaySameCard();
	}

	public void throwAwayCard(int index) {
		System.out.println(name);
		hand.throwAwayCard(index);
	}

	public Card drawCard() {
		int numberOfSheets = getHand().size();
		int randomDrawIndex = ThreadLocalRandom.current().nextInt(numberOfSheets);

		Card drawedCard = getHand().get(randomDrawIndex);

		getHand().remove(randomDrawIndex);
		return drawedCard;
	}

	public int findSameCard(Card card, int ignoreIndex) {
		return hand.findSameCard(card, ignoreIndex);
	}

	public int findSameCard(Card card) {
		return hand.findSameCard(card);
	}

	public int findSameCard() {
		return hand.findSameCard();
	}

	public int getNumberOfSheet() {
		return getHand().size();
	}

	public void remainingHand() {
		System.out.println(name + "の手札:" + hand);
	}

	@Override
	public String toString() {
		return name;
	}
}
