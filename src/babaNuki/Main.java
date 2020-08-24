package babaNuki;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		Player p1 = new Player("hoge");
		Player p2 = new Player("hogehoge");
		Player p3 = new Player("muno");
		Facilitator facilitator = Facilitator.valueOf(Arrays.asList(p1, p2, p3));

		facilitator.shuffleCard();
		facilitator.giveOutCard();
		System.out.println("各プレイヤーに配られた手札");
		facilitator.getPlayerList().forEach(Player::remainingHand);
		System.out.println("同じ数字のカードを捨ててください");
		facilitator.getPlayerList().forEach(s -> s.throwAwaySameCard());
		System.out.println("ゲーム開始前の各プレイヤーの手札");
		facilitator.getPlayerList().forEach(Player::remainingHand);

		System.out.println("ババ抜きを始めるよん");

		for (;;) {
			Player drawingPlayer = facilitator.getDrawingPlayer();
			Player drawnPlayer = facilitator.getDrawnPlayer();
			System.out.println("現ターンのプレイヤーの手札");
			drawingPlayer.remainingHand();
			System.out.println("カードを引かれるプレイヤーの手札");
			drawnPlayer.remainingHand();

			Card drawCard = drawnPlayer.drawCard();
			drawingPlayer.addCard(drawCard);
			System.out.println(drawingPlayer + "が" + drawnPlayer + "から" + drawCard + "を引きました");
			if (drawnPlayer.getNumberOfSheet() == 0) {
				facilitator.except(drawnPlayer);
			} else {
				System.out.println("カードを引かれたプレイヤーの残り手札");
				drawnPlayer.remainingHand();
			}

			int searchResult = drawingPlayer.findSameCard();
			if (searchResult != -1) {
				// 加えたカードと、見つけた同じカードを捨てる
				System.out.println("手札に同じカード数字のカードが見つかったため捨てます");
				drawingPlayer.throwAwayCard(drawingPlayer.getHand().size() -1);
				drawingPlayer.throwAwayCard(searchResult);
			}

			if (drawingPlayer.getNumberOfSheet() == 0) {
				facilitator.except(drawingPlayer);
			} else {
				System.out.println("カードを引いたプレイヤーの残り手札");
				drawingPlayer.remainingHand();
			}

			if (facilitator.isFinish()) {
				facilitator.finish();
				System.exit(0);
			}
		}
	}
}
