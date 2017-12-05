package binary.search.game.model;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import binary.search.game.exceptions.GameLostException;

@ManagedBean
@Named(value = "gameBean")
@SessionScoped
public class GameBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private NumberGuesser ng = null;
	String player;
	String nextPage;
	double currentBetAmount;
	double totalBetAmount;
	int guess;
	int count;
	
	// Create no-arg constructor
	public GameBean() {
	}
	
	// Define getter and setter methods
	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public double getCurrentBetAmount() {
		return currentBetAmount;
	}

	public void setCurrentBetAmount(double currentBetAmount) {
		this.currentBetAmount = currentBetAmount;
	}

	public double getTotalBetAmount() {
		return totalBetAmount;
	}

	public void setTotalBetAmount(double totalBetAmount) {
		this.totalBetAmount = totalBetAmount;
	}

	public int getGuess() {
		return guess;
	}

	public void setGuess(int guess) {
		this.guess = guess;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	// Start method
	public void start() {
		ng = new NumberGuesser();
		nextPage = "main";
		try {
			guess = ng.firstGuess();
			count = ng.getGuessCount();
		} catch (GameLostException e) {
			gameLost();
		}
	}

	// Game lost method
	private void gameLost() {
		setNextPage("error");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesMessage facesMessage = new FacesMessage("Did you change your number mid-game"
				+ " or click the wrong button on a move?");
		FacesContext.getCurrentInstance().addMessage("GameLost",facesMessage);
	}

	// Correct method
	public void correct() {
		setNextPage("correct");
		getTotalBetAmount();
		getCurrentBetAmount();
		totalBetAmount = totalBetAmount + currentBetAmount;
		setTotalBetAmount(totalBetAmount);
	}

	// Too high method
	public void tooHigh() throws GameLostException {
		ng = new NumberGuesser();
		guess = ng.guessLower();
		setGuess(guess);
		count = ng.getGuessCount();
		setCount(count);
	}
	
	// Too low method
	public void tooLow() throws GameLostException {
		ng = new NumberGuesser();
		guess = ng.guessHigher();
		setGuess(guess);
		count = ng.getGuessCount();
		setCount(count);
	}
}

