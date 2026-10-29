/*-------------------------------------------------------*
* Name: Michelle Shannon                                 *
* Date created: April 15th, 2015                         *
* HW#: 3                                                 *
* This program is a 80's style text based adventure      *
* game. You will play as a character who has amnesia.    *
* To reach the end you must go through several locations *
* getting items along the way. Have fun!                 *
*--------------------------------------------------------*/
import java.util.*;
import javax.swing.JOptionPane;

public class AmnesiaGame {
	public static Scanner kb = new Scanner(System.in);

	static final int NUMBER_OF_ROOMS = 8;
	static final int NUMBER_OF_ATTRIBUTES = 4;

	static final int NUMBER_OF_ITEMS = 8;
	static final int NUMBER_OF_PROPERTIES = 3;

	public static String[][] rooms = new String[NUMBER_OF_ROOMS][NUMBER_OF_ATTRIBUTES];

	public static String[][] items = new String[NUMBER_OF_ITEMS][NUMBER_OF_PROPERTIES];

	public static String[][] inventory = new String[NUMBER_OF_ROOMS][NUMBER_OF_PROPERTIES];

	static final int NAME = 0;
	static final int DESCRIPTION = 1;
	static final int EXITS = 2;
	static final int ITEMS = 3;

	static final int NOTE1 = 0;
	static final int DOLLAR = 1;
	static final int NOTE2 = 2;
	static final int NOTE3 = 4;
	static final int CLIPBOARD = 7;
	static final int COFFEE = 6;

	static final int ROOM = 0;
	static final int TRAINSTATION = 1;
	static final int TRAIN = 2;
	static final int TUNNEL = 3;
	static final int BOUTIQUE = 4;
	static final int OUTSIDE = 5;
	static final int WAKEUP = 6;
	static final int ESCAPE = 7;

	static String currentRoom = "room";

	static boolean gameOver = false;
	static int index = 0;
	static String introduction = "You're standing in front of a beautiful metal framed "
			+ "antique mirror with a note stuck\ninto the top right corner.\nYou look into the mirror, "
			+ "only to find a face that did not belong to you.\nA sense of hysteria and confusion overwhelms"
			+ " you. You realize that you can't remember anything.\nYou scream as loud as humanly possible, hoping"
			+ " for a glimpse of hope, fully aware of\nsomebody elses presence in the room.\nType \"Look\" to look"
			+ " around to see exits and items in your current location.\n";

	public static void main(String[] args) {
		createMap();
		System.out.println(introduction);

		while (!gameOver) {
			getCommand();
		}
	}

	private static void getCommand() {
		System.out.print("> ");
		String command = kb.nextLine();
		if (command.contains(" ")) {
			String[] input = command.split(" ");
			command = input[0];
			String target = input[1];

			doCommand(command, target);
		} else {
			doCommand(command);
		}
	}

	private static void createMap() {
    //Gives string information about each room for other methods. The exits restrict where player can go.
		rooms[ROOM][NAME] = "room";
		rooms[ROOM][DESCRIPTION] = "You are in a large brightly lit room with pure white walls "
				+ "and carpet, the mirror is in\nthe very center. You can hear the sound of a train passing by."
				+ "\nMaybe if you read the note, some clarity will be provided.\n*Type \"Get <item name>\" to get"
				+ " and read the item.";
		rooms[ROOM][EXITS] = "Station";
		rooms[ROOM][ITEMS] = "NOTE1";

		rooms[TRAINSTATION][NAME] = "Station";
		rooms[TRAINSTATION][DESCRIPTION] = "A train is waiting at the platform, though the train looks too"
				+ " old and run down to function,\nit honks its horn to signal you to get on. This place triggers a "
				+ "memory buried deep inside\nof you, causing a sense of comfort even as you struggle to fully"
				+ " remember where you are.\nHm, maybe the answer to all this is on the train.\n"
				+ "Before you get on the train, you notice a dollar on the floor that might come in handy later.\n";
		rooms[TRAINSTATION][EXITS] = "Room, Train";
		rooms[TRAINSTATION][ITEMS] = "Dollar";

		rooms[TRAIN][NAME] = "Train";
		rooms[TRAIN][DESCRIPTION] = "Now you remember, this is the train you used to ride to get home after"
				+ " work. Watching the\ntrees pass by as rain droplets danced, racing each other down the window as"
				+ " the motion of\nthe train rocked you to sleep after a long and stressful day. You ignore the strange"
				+ " event\nthat just happened and go to find your usual window seat to sit down. You see someone sitting"
				+ "\nin the seat, a chill goes down your spine and your body freezes as you realize who this is.\nThe "
				+ "person in the seat is you. The train stops suddenly and the other you gets off the train\nrapidly,"
				+ " leaving a note on the seat. You yell after them, but nothing happens.\nAll you see outside the train"
				+ " door is a tunnel.\n";
		rooms[TRAIN][EXITS] = "Tunnel";
		rooms[TRAIN][ITEMS] = "NOTE2";

		rooms[TUNNEL][NAME] = "Tunnel";
		rooms[TUNNEL][DESCRIPTION] = "The train door opens to a long dark tunnel, you can see a faint light at"
				+ " the end.\nA sickly sweet, yet familiar smell invades your nostrils with every inhale.\nYou can hear"
				+ " footsteps running down the tunnel, the other you must be trying to get away.\nThe sound of the train"
				+ " departing as you walk down the tunnel concerns you.";
		rooms[TUNNEL][EXITS] = "Boutique";
		rooms[TUNNEL][ITEMS] = "";

		rooms[BOUTIQUE][NAME] = "Boutique";
		rooms[BOUTIQUE][DESCRIPTION] = "So this is what was causing that aroma. You remember that your"
				+ " apartment was right next to\na boutique, everyday you held your breath as you walked through a perfume cloud."
				+ "\nThe shop owner smiles and waves at you, asking why you are back so soon.\nYou ignore their question,"
				+ " making them feel awkward.\nThe owner leaves a note on the counter and walks into the dark tunnel.\n"
				+ "You want to follow the shop owner, but they already feel awkward. Better go outside and put\n"
				+ "the dollar from earlier in the tip jar before you leave, its the least you could do\n"
				+ "*To put the dollar in the tip jar type \"Give dollar\"";
		rooms[BOUTIQUE][EXITS] = "Outside";
		rooms[BOUTIQUE][ITEMS] = "NOTE3";

		rooms[OUTSIDE][NAME] = "Outside";
		rooms[OUTSIDE][DESCRIPTION] = "The fresh air relaxes you a bit. You watch an elderly couple walk"
				+ " their dog across the street.\nThis is the first time so far where everything seemed normal.\nYou "
				+ "look at the 3 notes...they have to mean something.\nAs you look up you jump, terrified. The other "
				+ "you is standing less than a foot away.\n\"You must never find out\""
				+ " they said in a slightly disapointed tone.\nThis made you feel like a small child who did something wrong."
				+ "\n*To escape, type \"Go escape\"\n";
		rooms[OUTSIDE][EXITS] = "Escape";
		rooms[OUTSIDE][ITEMS] = "";

		rooms[ESCAPE][NAME] = "Escape";
		rooms[ESCAPE][DESCRIPTION] = "You run back into the Boutique, terrified and out of breath.\n\"What can't I find out?\""
				+ " you whisper under your breath. You hear a chuckle from behind you.\nIt was the shop owner."
				+ "\n\"I can help you if you trust me\" he stated nonchalantly. I have no choice.\n"
				+ "I nod as I put the notes down in front of me. He hands me a cup of coffee.\n*To drink, type \"Drink coffee\"\n";
		rooms[ESCAPE][EXITS] = "Wakeup";
		rooms[ESCAPE][ITEMS] = "Coffee";

		rooms[WAKEUP][NAME] = "Wakeup";
		rooms[WAKEUP][DESCRIPTION] = "You hear a voice yelling to wake up. Hyperventilating, you jump"
				+ " into an upright\nposition and open your eyes, squinting due to the extreme brightness of the lights.\n"
				+ "You appear to be in a hospital room. The voice belongs to your close friend who is sitting\nalongside "
				+ "you, \"I can't believe you woke up! The doctor said otherwise.\"\nYou see your friend, filled with joy and "
				+ "relief.\n\"What the hell\" you wonder. You see your reflection in the mirror on the wall.\nEyes bloodshot, "
				+ "skin pale, almost ghostly, but the reflection does belong to you.\nThe clipboard must have what happened...\n";
		rooms[WAKEUP][EXITS] = "";
		rooms[WAKEUP][ITEMS] = "Clipboard";

		items[NOTE1][NAME] = "Note1";
		items[NOTE1][DESCRIPTION] = "The note makes no sense, just has the letters \"UP\" on it.\nYou better"
				+ " check out the station outside for some clues.\n*To exit a location, type \"Go <exit name>\"";

		items[DOLLAR][NAME] = "Dollar";
		items[DOLLAR][DESCRIPTION] = "A wrinkly old dollar bill, but hey, free money.";

		items[NOTE2][NAME] = "Note2";
		items[NOTE2][DESCRIPTION] = "The note has the letters \"KE\" on it, \"Are these greek letters?\""
				+ " you wonder,\nstruggling to understand your surroundings.";

        items[3][NAME] = "";
        items[3][DESCRIPTION] = "";
		
        items[NOTE3][NAME] = "Note3";
		items[NOTE3][DESCRIPTION] = "The letters \"WA\" are on it. Now you are frustrated.\n\"Why are these"
				+ " notes being left? Why is my memory fuzzy?\"";
                
        items[5][NAME] = "";
        items[5][DESCRIPTION] = "";

		items[COFFEE][NAME] = "Coffee";
		items[COFFEE][DESCRIPTION] = "You take a sip of the hot coffee.\nThe bitterness takes over your taste buds "
				+ "as the coffee goes down your throat, making you gag slightly.\nYou give a noticeably forced \"Thank you\"\n"
				+ "He returns a warm, kind smile. Almost making you forget about whats going on.\n\"You won't have to worry "
				+ "anymore\" he says, maintaining the warm smile.\nPanic and confusion return as your vision starts to blur.\n"
				+ "You look out the window, the other you staring back.\nYour eyes dart down to the notes, together they read "
				+ "\"WAKE UP\".\nYou hear the shop owner chuckling as your vision fades to black"
				+ "\n*To wake up, type \"Go wakeup\"";
                
		items[CLIPBOARD][NAME] = "Clipboard";
		items[CLIPBOARD][DESCRIPTION] = "\"Patient arrived in critical condition after passing out on the train.\n"
				+ "Currently in coma like state. Vitals are showing signs of the patient experiencing nightmares.\"\nYou now"
				+ " remember everything, including being at work feeling light headed, and everything fading to black.\n"
				+ "What a relief, that was all just a horrific nightmare.";
        //All items have a description and name that add to the story, thus have their own array
		inventory[0][NAME] = "";
		inventory[0][DESCRIPTION] = "";

		inventory[1][NAME] = "";
		inventory[1][DESCRIPTION] = "";

		inventory[2][NAME] = "";
		inventory[2][DESCRIPTION] = "";

		inventory[3][NAME] = "";
		inventory[3][DESCRIPTION] = "";

		inventory[4][NAME] = "";
		inventory[4][DESCRIPTION] = "";

		inventory[5][NAME] = "";
		inventory[5][DESCRIPTION] = "";

		inventory[6][NAME] = "";
		inventory[6][DESCRIPTION] = "";

		inventory[7][NAME] = "";
		inventory[7][DESCRIPTION] = "";
        //There is a space for an item in players inventory for each room in the map. 
        //For certain locations if your inventory at the location is empty, you can't leave the location.
	}

	private static void doCommand(String command) {
		doCommand(command, "");
	}

	public static void doCommand(String command, String target) {
		command = command.toLowerCase();
		target = target.toLowerCase();

		switch (command) {
		case "look":
			if (target.length() > 0) {
				look(target);
			} else {
				look();
			}
			break;
		case "go":
			if (target.length() > 0) {
				move(target);
			} else {
				System.out.println("You must specify where to go. Please try again.");
			}
			break;
		case "get":
			if (target.length() > 0) {
				get(target);
			} else {
				System.out.println("You must specify what to get. Please try again.");
			}
			break;
		case "give":
			if (target.length() > 0) {
				give(target);
			} else {
				System.out.println("You must specify what to give. Please try again.");
			}
			break;
		case "drink":
			if (target.length() > 0) {
				drink(target);
			} else {
				System.out.println("You must specify what to drink. Please try again.");
			}
			break;

		default:
			System.out.println("Sorry, that isnt a command.");
		}
	}

	public static void move(String destination) {
		boolean isItem = true;
		boolean isFound = false;

		if (rooms[index][EXITS].toLowerCase().contains(destination)) {
			if (destination.equals("station") && inventory[0][NAME].equals("")) {
				JOptionPane.showMessageDialog(null,"You forgot to get the note!");
				isItem = false;
			}
			if (destination.equals("train") && inventory[1][NAME].equals("")) {
				JOptionPane.showMessageDialog(null,"You forgot to get the dollar!");
				isItem = false;
			}
			if (destination.equals("tunnel") && inventory[2][NAME].equals("")) {
				JOptionPane.showMessageDialog(null,"You forgot to get the note!");
				isItem = false;
			}
			if (destination.equals("outside") && inventory[4][NAME].equals("")) {
				JOptionPane.showMessageDialog(null,"You forgot to get the note!");
				isItem = false;
			}
			if (destination.equals("outside") && !inventory[1][NAME].equals("")) {
				JOptionPane.showMessageDialog(null,"Come on, give up the dollar.");
				isItem = false;
			}
			if (destination.equals("wakeup")&& rooms[ESCAPE][ITEMS].equals("Coffee")) {
				JOptionPane.showMessageDialog(null,"Don't make him feel awkward again, drink the coffee.");
				isItem = false;
                //Restricts user from moving forward if they did not complete item related task at location.
                //Pop up tells what they need to do.
			} else if (isItem) {
				for (int i = 0; i < rooms.length; i++) {
					if (rooms[i][EXITS].toLowerCase().contains(destination)) {
						currentRoom = destination;
						isItem = true;
						isFound = true;
						look();
					}
				}
			}
		}
		if (!isFound && isItem) {
			JOptionPane.showMessageDialog(null,"Sorry, you can not go to the \"" + destination + "\"");
		}
	}

	public static void look(String item) {
    //Checks if item player is trying to look at is in their inventory. If not, a pop up comes up.
        boolean isLookable = false;
        for(int	itemNum	= 0; itemNum < items.length; itemNum++){
            if(item.equalsIgnoreCase(inventory[itemNum][NAME])){
                System.out.println(items[itemNum][DESCRIPTION]);
                System.out.println();
                isLookable = true;
            }
        }
        if(!isLookable) {
            JOptionPane.showMessageDialog(null, "You do not have a \"" + item +"\"");
        }
    }

	public static int getRoomNumber(String destination) {
        for(int i = 0; i < NUMBER_OF_ROOMS; i++) {
            if(rooms[i][NAME].toLowerCase().equals(destination)){
                index = i;
            }  
        }
		return index;
	}

	public static void look() {
		int CURRENT_ROOM_INDEX = getRoomNumber(currentRoom);
		System.out.printf("%s", rooms[CURRENT_ROOM_INDEX][1]);
		System.out.println();
		System.out.printf("Exits: %s", rooms[CURRENT_ROOM_INDEX][2]);
		System.out.println();
		System.out.printf("Items: %s", rooms[CURRENT_ROOM_INDEX][3]);
		System.out.println("\n");

	}

	public static void get(String item) {
    //Checks if the item exist in the current location and assigns the inventory slot the items info.
      if (rooms[index][ITEMS].toLowerCase().equals(item)) {
			if (!item.equalsIgnoreCase("clipboard")) {
				System.out.println("*Added " + item
						+ " to your inventory. Type \"Look " + item
						+ "\" to see again\n");
				inventory[index][NAME] = items[index][NAME];
				inventory[index][DESCRIPTION] = items[index][DESCRIPTION];
				rooms[index][ITEMS] = "";
                look(item);
			} else {
                inventory[CLIPBOARD][NAME] = items[CLIPBOARD][NAME];
				look(item);
				gameOver = true;
				JOptionPane.showMessageDialog(null, "Game over...or is it?");
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"There is not a \"" + item.toLowerCase()
							+ "\" in this location.");
		}
	}

	public static void give(String item) {
    //The dollar is the only thing that can be removed from inventory, so it is needed
    //to check whether or not it is the dollar the player wants to give. They can only give
    //the dollar at one specified location. Also empties the inventory where the dollar was.
		boolean isFound = false;
		boolean isDollar = false;
		for (int i = 0; i <= index; i++) {
			if (inventory[i][NAME].toLowerCase().equals(item)) {
				isFound = true;
				if (item.equals("dollar") && currentRoom.equals("boutique")) {
					System.out.println("Removed \"" + item.toLowerCase()
							+ "\" from your inventory.\n");
					inventory[1][NAME] = "";
					inventory[1][DESCRIPTION] = "";
					isDollar = true;
					isFound = true;
					break;
				}
				else {
					JOptionPane.showMessageDialog(null, "You need to keep the "
							+ item.toLowerCase()
							+ " in your inventory, you will need this later.");
					isFound = true;
					break;
				}
			}
		}
		if (!isFound && !isDollar) {
			JOptionPane.showMessageDialog(null, "You do not currently have \""
					+ item.toLowerCase()+"\"");
		}
	}

	public static void drink(String item) {
    //Only item that player can drink is coffee, the if statement rules out all other items.
    //Then it is checked if you are in the room that you can drink cofee
		if (rooms[ESCAPE][ITEMS].equals("Coffee")) {
			System.out.println(items[COFFEE][DESCRIPTION]);
			items[COFFEE][NAME] = "";
			items[COFFEE][DESCRIPTION] = "";
			rooms[ESCAPE][ITEMS] = "";
		} else {
			JOptionPane.showMessageDialog(null, "You don't want to drink "
					+ item);
		}
	}

}
