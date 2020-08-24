package babaNuki;

public class Card {

	private final Suit suit;
	private final int num;

	private Card (Suit suit, int num) {
		this.suit = suit;
		this.num = num;
	}

	enum Suit {

		SPADE("S"),
		HEART("H"),
		DIAMOND("D"),
		CLUB("C"),
		JOKER("JK");

		private String value;

		private Suit(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {

		if (suit == Suit.JOKER) {
			return suit.value;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(this.suit.getValue());

		switch (num) {
			case 1:
				sb.append("A");
				break;
			case 10:
				sb.append("T");
				break;
			case 11:
				sb.append("J");
				break;
			case 12:
				sb.append("Q");
				break;
			case 13:
				sb.append("K");
				break;
			default:
				sb.append(num);
		}
		return sb.toString();
	}

	public static Card valueOf(Suit suit, int num) {
		return new Card(suit, num);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (num != other.num)
			return false;
		return true;
	}
}
