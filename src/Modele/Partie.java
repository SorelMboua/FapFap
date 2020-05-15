package Modele;

import java.util.*;

import Modele.Carte.colors;
import Modele.Carte.types;

public class Partie {

	private int nbreJoueurs;
	private LinkedList<Joueur> joueurs;
	private LinkedList<Carte> table;
	private LinkedList<Carte> deck;
	private Joueur leader;

	public Joueur getLeader() {
		return leader;
	}

	public void setLeader(Joueur leader) {
		this.leader = leader;
	}

	// Constructor
	public Partie(int nbreJoueurs, LinkedList<Joueur> joueurs, LinkedList<Carte> table, LinkedList<Carte> deck) {
		super();
		this.nbreJoueurs = nbreJoueurs;
		this.joueurs = joueurs;
		this.table = table;
		this.deck = deck;
	}

	// getters and setters
	public int getNbreJoueurs() {
		return nbreJoueurs;
	}

	public void setNbreJoueurs(int nbreJoueurs) {
		this.nbreJoueurs = nbreJoueurs;
	}

	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(LinkedList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public LinkedList<Carte> getTable() {
		return table;
	}

	public void setTable(LinkedList<Carte> table) {
		this.table = table;
	}

	public LinkedList<Carte> getDeck() {
		return deck;
	}

	public void setDeck(LinkedList<Carte> deck) {
		this.deck = deck;
	}

	// Create players
	public LinkedList<Joueur> createPlayers(int nbreJ) {
		System.out.println("===================== Start creating players =========================");
		LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
		int i = 1;
		while (i <= nbreJ) {
			Scanner myObj = new Scanner(System.in);
			LinkedList<Carte> deckJ = new LinkedList<Carte>();
			System.out.println("Name the player number " + String.valueOf(i));
			String name = myObj.nextLine();
			Joueur j = new Joueur(i, name, deckJ);
			joueurs.add(j);
			i++;
		}
		System.out.println("===================== End creating players =========================");
		return joueurs;
	}

	// create Deck of cards
	public LinkedList<Carte> createDeck() {
		System.out.println("===================== Start creating cards =========================");
		LinkedList<Carte> deckCards = new LinkedList<Carte>();
		int n = 0;
		while (n < 4) {
			for (int i = 3; i < 11; i++) {
				if (n == 0) {
					Carte c = new Carte(String.valueOf(i), colors.red, types.heart);
					try {
						deckCards.add(c);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
				if (n == 1) {
					Carte c = new Carte(String.valueOf(i), colors.red, types.square);
					try {
						deckCards.add(c);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
				if (n == 2) {
					Carte c = new Carte(String.valueOf(i), colors.black, types.spade);
					try {
						deckCards.add(c);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
				if (n == 3) {
					Carte c = new Carte(String.valueOf(i), colors.black, types.club);
					try {
						deckCards.add(c);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			n++;
		}
		System.out.println("=============== Finished creating " + deckCards.size() + " cards ====================");
		return deckCards;
	}

	// Distribute cards
	public void distributeCards(LinkedList<Carte> deckCards, int nbreJ, LinkedList<Joueur> joueurs) {
		System.out.println("===================== Start distributing cards =========================");
		Collections.shuffle(deckCards);
		// First round of dealing (3 cards each)
		for (Joueur j : joueurs) {
			j.getDeckJoueur().add(deckCards.pop());
			j.getDeckJoueur().add(deckCards.pop());
			j.getDeckJoueur().add(deckCards.pop());
		}
		// Other rounds of dealing (2 cards each)
		while (deckCards.size() >= nbreJ * 2 && joueurs.getLast().getDeckJoueur().size()<5) {
			int i = 0;
			while (i < nbreJ) {
				try {
					joueurs.get(i).getDeckJoueur().add(deckCards.pop());
					joueurs.get(i).getDeckJoueur().add(deckCards.pop());
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				i++;
			}
		}
		System.out.println("===================== End distributing cards =========================");
	}

	// Run the game
	public void runPartie(int nbreJ) {
		System.out.println("===================== Starting game =========================");
		this.setNbreJoueurs(nbreJ);
		this.setJoueurs(createPlayers(this.getNbreJoueurs()));
		this.setDeck(createDeck());
		distributeCards(this.getDeck(), this.getNbreJoueurs(), this.getJoueurs());
		String winner = null;
		int round = 1;
		this.setLeader(this.getJoueurs().getFirst());
		while (winner == null) {
			if (this.getLeader() != this.getJoueurs().getFirst()) {
				for (Joueur j : this.getJoueurs()) {
					if (j == this.getLeader()) {
						this.getJoueurs().remove(j);
						this.getJoueurs().addFirst(j);
						break;
					}
				}
			}
			System.out.println("===================== Round " + round + " =========================");
			for (Joueur j : this.getJoueurs()) {
				System.out.println("============== " + j.getName() + ", it's your turn =================");
				int choice = j.play();
				if (choice == 1) {
					j.seeCards();
					this.setTable(j.playCard(this));
					System.out.println("========= " + this.getLeader().getName() + " has the hand ===========");
				} else if (choice == 2) {
					this.setTable(j.playCard(this));
					System.out.println("========= " + this.getLeader().getName() + " has the hand ===========");
				}
			}
			if (this.getJoueurs().getLast().getDeckJoueur().isEmpty()) {
				winner = this.getLeader().getName();
			}
			round++;
			this.getTable().clear();
		}
		System.out.println("=============== End of the game, Congrats to: "+ winner +" ===================");
	}

	public static void main(String[] args) {
		int nbreJ = 0;
		while (nbreJ < 2) {
			try {
				Scanner myObj = new Scanner(System.in);
				System.out.println("How many players ?");
				nbreJ = myObj.nextInt();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
		LinkedList<Carte> table = new LinkedList<Carte>();
		LinkedList<Carte> deck = new LinkedList<Carte>();
		Partie p = new Partie(nbreJ, joueurs, table, deck);
		p.runPartie(p.getNbreJoueurs());
	}

}
