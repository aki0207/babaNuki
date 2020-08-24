package babaNuki;

public class Disp {

	public static void throwAwayCard(Card firstCard, Card secondCard) {
		StringBuilder message = new StringBuilder();
		message.append(firstCard.toString());
		message.append(" ");
		message.append(secondCard.toString());
		message.append(" ");
		message.append("を捨てました");
		System.out.println(message);
	}
}
