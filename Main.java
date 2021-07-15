import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// System objects
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();

		// Game variables
		String[] enemyNames = { "Goblin", "Troll", "Giant Snake", "Swarm of Bugs" };
		int maxEnemyHealth = 50;
		int enemyAttackDamage = 10;

		// Player variables
		int playerHealth = 100;
		int playerAttackDamage = 25;

		int numHealthPotions = 2;
		int healthPotionHealAmount = 20;
		int healthPotionDropChance = 20; // Percent chance

		boolean running = true;

		System.out.println("");
		System.out.println("You are an adventurer exploring a dungeon full of monsters" + "\nBe careful!");

		Game: while (running) {
			System.out.println("----------------------------------------------------------" + "\n");

			int enemyHealth = rand.nextInt(maxEnemyHealth);
			String enemy = enemyNames[rand.nextInt(enemyNames.length)];
			System.out.println("\t* " + enemy + " appeared! *\n");

			while (enemyHealth > 0) {
				System.out.println("\tPlayer HP: " + playerHealth);
				System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
				System.out.println("\n\tSelect your move:");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Use Potion");
				System.out.println("\t3. Run");

				// Player move selection
				String input = scan.nextLine();
				if (input.equals("1")) {
					int damageDealt = rand.nextInt(playerAttackDamage);
					int damageTaken = rand.nextInt(enemyAttackDamage);

					enemyHealth -= damageDealt;
					playerHealth -= damageTaken;

					System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage!");
					System.out.println("\t> You receive " + damageTaken + " in retaliation!");

					if (playerHealth < 1) {
						System.out.println("\t> You have taken too much damage and have been knocked unconcious!");
						break;
					}

				} else if (input.equals("2")) {
					if (numHealthPotions > 0) {
						playerHealth += numHealthPotions;
						numHealthPotions--;
						System.out.println("\t> You drink a health potion, heaaling for " + healthPotionHealAmount + "."
								+ "\n\t> You now have " + playerHealth + " HP." + "\n\t> You have " + numHealthPotions
								+ " health potions left.\n");
					} else {
						System.out.println("\t> You have no health potions left!");
					}

				} else if (input.equals("3")) {
					System.out.println("\tYou ran away from " + enemy + "!");
					continue Game; // this returns the loop back to the start, not during the initial combat

				} else {
					System.out.println("** Invalid Command **");
				}

			}

			if (playerHealth < 1) {
				System.out.println("You have been knocked out, a wandering traveler rescued you.");
				break;
			}

			System.out.println("--------------------------------------------------------" + "\n");
			System.out.println("* " + enemy + " was defeated! *");
			System.out.println("* You have " + playerHealth + " hp left *");
			if (rand.nextInt(100) < healthPotionDropChance) {
				numHealthPotions++;
				System.out.println("* The " + enemy + " dropped a health potion! *");
				System.out.println("* You have " + numHealthPotions + " health potion(s) *" + "\n");
			}
			System.out.println("--------------------------------------------------------" + "\n");
			System.out.println("What would you like to do now?");
			System.out.println("1. Continue fighting");
			System.out.println("2. Exit Dungeon");

			String input = scan.nextLine();

			while (!input.equals("1") && !input.equals("2")) {
				System.out.println("Invalid Command!");
				input = scan.nextLine();
			}

			if (input.equals("1")) {
				System.out.println("You continue deeper into the dungeon...");
			} else if (input.equals("2")) {
				System.out.println("You exit the dungeon successfully!" + "\n");
				break;
			}
		}

		System.out.println("**********************");
		System.out.println("Thank You for playing!");
		System.out.println("**********************");

	}
}
