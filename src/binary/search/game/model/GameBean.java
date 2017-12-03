package binary.search.game.model;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import binary.search.game.exceptions.ExcessGuessesException;

@Named(value = "gameBean")
@SessionScoped
public class GameBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private NumberGuesser ng = null;
	String nextPage;
	int guess;
	int count;
	
	public GameBean() {
	}
	
	public void start() {
		ng = new NumberGuesser();
		nextPage = "guessPage";
		try {
			guess = ng.firstGuess();
			count = ng.getGuessCount();
		} catch (ExcessGuessesException e) {
			gameLost();
		}
	}
	
	private void gameLost() {
		setNextPage("errorPage");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesMessage facesMessage = new FacesMessage("Did you change your number mid-game"
				+ " or click the wrong button on a move?");
		FacesContext.getCurrentInstance().addMessage("GameLost",facesMessage);
	}

	private void setNextPage(String string) {
		// TODO Auto-generated method stub
		
	}
}

