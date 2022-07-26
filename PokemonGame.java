import java.util.Scanner;

public class PokemonGame {
	private Player player1;
	private Player player2;

	public PokemonGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	Character playerOneCharacter;
	Character playerTwoCharacter;
	public void start() {

		playerOneCharacter = this.player1.getNextCharacter();
		playerTwoCharacter = this.player2.getNextCharacter();

		Scanner scan = new Scanner(System.in);
		
		

		while (this.player1.isAlive() && this.player2.isAlive() ) {
			String attackcommandStr;

			if (playerOneCharacter.name.equals(playerTwoCharacter.name)) {
				playerTwoCharacter = this.player2.getNextCharacter();
			}

			if (player1.getHealth() < 22 && playerOneCharacter instanceof Boostable) {
				((Boostable) playerOneCharacter).boost();
				playerOneCharacter.setHasBoostFalse();
			}
			
			System.out.println();
			System.out.println("First player turn");
			System.out.println("press 1 for Attack");
			System.out.println(playerOneCharacter.getSpecialAmount() == 0 ? "":"press 2 for Special");
			System.out.println(playerOneCharacter.getHealAmount() < 3 ? "" : "press 3 for Heal"); // ternary if
			
			attackcommandStr = scan.next();
			while(!attackcommandStr.equals("1") && !attackcommandStr.equals("2") && !attackcommandStr.equals("3")) {
				System.out.println("Please enter a valid command!");
				attackcommandStr = scan.next();
			}
			int attackcommand = Integer.parseInt(attackcommandStr);  //type cast

			if (attackcommand == 1) {
				playerOneCharacter.hit(this.playerTwoCharacter);
				if (!this.player2.isAlive()) {
					break;
				}
			}

			if (player2.getHealth() < 22 && playerTwoCharacter instanceof Boostable) {
				((Boostable) playerTwoCharacter).boost();
				playerTwoCharacter.setHasBoostFalse();
			}

			if (attackcommand == 2 && playerOneCharacter.getSpecialAmount() != 0) {
				((Boostable) playerOneCharacter).special();
				playerOneCharacter.decreaseSpecialAmount();
			}
			
			if (attackcommand == 3 && playerOneCharacter.getHealAmount() >= 3) {
				player1.setHealth(player1.getHealth() + playerOneCharacter.getHealAmount());
				playerOneCharacter.decreaseHealAmount();
			}
			
			
			
			System.out.println();
			System.out.println("Second player turn");
			System.out.println("press 1 for attack");
			System.out.println(playerTwoCharacter.getSpecialAmount() == 0 ? "":"press 2 for Special");
			System.out.println(playerTwoCharacter.getHealAmount() < 3 ? "" : "press 3 for Heal");


			attackcommandStr = scan.next();
			while(!attackcommandStr.equals("1") && !attackcommandStr.equals("2") && !attackcommandStr.equals("3")) {
				System.out.println("Please enter a valid command!");
				attackcommandStr = scan.next();
			}
			attackcommand = Integer.parseInt(attackcommandStr);

			if (attackcommand == 1) {
				playerTwoCharacter.hit(this.player1);
				this.printTurnResult();

			}

			if (attackcommand == 2 && playerTwoCharacter.getSpecialAmount() != 0) {		
				((Boostable) playerTwoCharacter).special();
				playerTwoCharacter.decreaseSpecialAmount();
				this.printTurnResult();
			}
			
			if (attackcommand == 3 && playerTwoCharacter.getHealAmount() >= 3) {
				player2.setHealth(player2.getHealth() + playerTwoCharacter.getHealAmount());
				playerTwoCharacter.decreaseHealAmount();
				this.printTurnResult();
			}

			
		}

		if (this.player1.isAlive()) {
			System.out.println("Player1 Won");
		} else {
			System.out.println("Player2 Won");
		}

	}

	public void printTurnResult() {
		System.out.println("Player1 Name : " + player1.getName());
		System.out.println("Player2 Name : " + player2.getName());
		System.out.println("Player1 Health :" + playerOneCharacter.getHealth());
		System.out.println("Player2 Health :" + playerTwoCharacter.getHealth());
	}

	 
}