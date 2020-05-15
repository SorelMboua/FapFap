package Modele;

import java.util.*;

import Modele.Carte.colors;
import Modele.Carte.types;

public class Joueur {

	// Card played
	public class CardInput {
		private String nber;
		private String type;

		public CardInput(String nber, String type) {
			super();
			this.nber = nber;
			this.type = type;
		}

		public String getNber() {
			return nber;
		}

		public String getType() {
			return type;
		}

		public void setNber(String nber) {
			this.nber = nber;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	// Attributes of the class
	private int id;
	private String name;
	private LinkedList<Carte> deckJoueur;
	private boolean hasTheHand;

	// Constructor
	public Joueur(int id, String name, LinkedList<Carte> deckJoueur) {
		this.id = id;
		this.name = name;
		this.deckJoueur = deckJoueur;
		this.hasTheHand = false;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Carte> getDeckJoueur() {
		return deckJoueur;
	}

	public void setDeckJoueur(LinkedList<Carte> deckJoueur) {
		this.deckJoueur = deckJoueur;
	}

	public boolean getHasTheHand() {
		return hasTheHand;
	}

	public void setHasTheHand(Boolean hasTheHand) {
		this.hasTheHand = hasTheHand;
	}

	// Play a card
	public LinkedList<Carte> playCard(Partie p) {
		CardInput cardPlayed = new CardInput("test", "test");
		Scanner myObj = new Scanner(System.in);
		boolean found = false;
		LinkedList<String> listTypes = new LinkedList<String>();
		listTypes.add("spade");
		listTypes.add("square");
		listTypes.add("heart");
		listTypes.add("club");
		if (p.getTable().isEmpty()) {
			while (!listTypes.contains(cardPlayed.getType())) {
				System.out.println("Enter the card number");
				cardPlayed.setNber(myObj.nextLine());
				System.out.println("Enter the card type {spade, club, heart, square}");
				cardPlayed.setType(myObj.nextLine());
			}
			for (Carte c : this.getDeckJoueur()) {
				if (c.getNumero().equals(this.generateCardPlayed(cardPlayed, p.getTable()).getNumero())
						&& c.getType().equals(this.generateCardPlayed(cardPlayed, p.getTable()).getType())) {
					p.getTable().addLast(c);
					this.getDeckJoueur().remove(c);
					this.setHasTheHand(true);
					p.setLeader(this);
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println(this.getName() + ", you do not have this card try again");
				this.playCard(p);
			}
		} else {
			System.out.println("===== Card to beat is: " + p.getTable().getLast().getNumero() + " "
					+ p.getTable().getLast().getType() + " =====");
			boolean hasType = false;
			for (Carte c : this.getDeckJoueur()) {
				if (c.getType().toString().equals(p.getTable().getLast().getType().toString())) {
					hasType = true;
				}
			}
			if (hasType) {
				System.out.println("You have to play a card of type " + p.getTable().getLast().getType().toString());
				while (!p.getTable().getLast().getType().toString().contains(cardPlayed.getType())) {
					System.out.println("Enter the card number");
					cardPlayed.setNber(myObj.nextLine());
					cardPlayed.setType(p.getTable().getLast().getType().toString());
				}
				for (Carte c : this.getDeckJoueur()) {
					if (c.getNumero().equals(this.generateCardPlayed(cardPlayed, p.getTable()).getNumero())
							&& c.getType().equals(this.generateCardPlayed(cardPlayed, p.getTable()).getType())) {
						if (Integer.valueOf(c.getNumero()) > Integer.valueOf(p.getTable().getLast().getNumero())) {
							p.getTable().addLast(c);
							this.getDeckJoueur().remove(c);
							this.setHasTheHand(true);
							p.setLeader(this);
							found = true;
							break;
						} else {
							p.getTable().addFirst(c);
							this.getDeckJoueur().remove(c);
							this.setHasTheHand(false);
							found = true;
							break;
						}
					}
				}
				if (!found) {
					System.out.println("You do not posess this card try again");
					this.playCard(p);
				}
			} else {
				while (!listTypes.contains(cardPlayed.getType())) {
					System.out.println("Enter the card number");
					cardPlayed.setNber(myObj.nextLine());
					System.out.println("Enter the card type {spade, club, heart, square}");
					cardPlayed.setType(myObj.nextLine());
				}
				for (Carte c : this.getDeckJoueur()) {
					if (c.getNumero().equals(this.generateCardPlayed(cardPlayed, p.getTable()).getNumero())
							&& c.getType().equals(this.generateCardPlayed(cardPlayed, p.getTable()).getType())) {
						p.getTable().addFirst(c);
						this.getDeckJoueur().remove(c);
						this.setHasTheHand(false);
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println(this.getName() + ", you do not have this card try again");
					this.playCard(p);
				}
			}
		}
		return p.getTable();
	}

	// Generate real cards from inputs
	public Carte generateCardPlayed(CardInput cardPlayed, LinkedList<Carte> table) {
		if (cardPlayed.getType().equalsIgnoreCase("spade")) {
			Carte card = new Carte(cardPlayed.getNber(), colors.black, types.spade);
			return card;
		} else if (cardPlayed.getType().equalsIgnoreCase("heart")) {
			Carte card = new Carte(cardPlayed.getNber(), colors.red, types.heart);
			return card;
		} else if (cardPlayed.getType().equalsIgnoreCase("square")) {
			Carte card = new Carte(cardPlayed.getNber(), colors.red, types.square);
			return card;
		} else {
			Carte card = new Carte(cardPlayed.getNber(), colors.black, types.club);
			return card;
		}
	}

	// See your cards
	public void seeCards() {
		String cards = "[ ";
		for (Carte c : this.deckJoueur) {
			cards += c.getNumero() + " " + c.getType().toString() + "; ";
		}
		System.out.println(cards + "]");
	}

	// play
	public int play() {
		String newligne = System.getProperty("line.separator");
		int choice = 0;
		while (choice != 1 && choice != 2) {
			Scanner myObj = new Scanner(System.in);
			System.out.println(
					"What you wanna do?" + newligne + "1. Check your cards first" + newligne + "2. Play a card");
			choice = myObj.nextInt();
		}
		return choice;
	}

}
