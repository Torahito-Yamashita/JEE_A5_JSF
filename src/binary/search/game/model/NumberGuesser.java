package binary.search.game.model;

import binary.search.game.exceptions.GameLostException;

public class NumberGuesser {
	// current status of this game
	private int minValue = 0;
	private int maxValue = 0;
	private int maxGuessCount = 0;
	// two fields must be available to client code 
	private int lastGuess = 0;
	private int guessCount = 0;
	
	// client creates an instance for each game
	public NumberGuesser() {
		super();
		setMinValue( GameConstants.RANGE_BOTTOM );
		setMaxValue( GameConstants.RANGE_TOP );
		setMaxGuessCount( GameConstants.MAXGUESSES );
		// guessCount set to zero by default
	}

	private void setGuessCount(int guessCount) throws GameLostException {
		if ( guessCount > getMaxGuessCount() ) {
			throw new GameLostException ("I lost, which should never happen.");
		}
		this.guessCount = guessCount;
	}

	// getter for current guess count required by client code
	public int getGuessCount() {
		return guessCount;
	}
	
	// called by client code to start a game
	public int firstGuess() throws GameLostException {
		return getNextGuess();
	}
	
	// called by client when last guess to low
	public int guessHigher() throws GameLostException {		
		minValue = lastGuess;
		return getNextGuess();
	}
	
	// called by client when last guess too high
	public int guessLower() throws GameLostException {
		maxValue = lastGuess;
		return getNextGuess();
	}
	
	// apply binary search algorithm to at each guess
	private int getNextGuess() throws GameLostException {
	    setGuessCount( getGuessCount() +1 );
		int newGuess = ( getMinValue() + getMaxValue() ) / 2;
		setLastGuess( newGuess);		
		return newGuess;
	}
	
	// get and set methods for all fields	
	private int getMinValue() {
		return minValue;
	}

	private void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	private int getMaxValue() {
		return maxValue;
	}

	private void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	// value use by client code
	public int getMaxGuessCount() {
		return maxGuessCount;
	}

	private void setMaxGuessCount(int maxGuessCount) {
		this.maxGuessCount = maxGuessCount;
	}

	private void setLastGuess(int lastGuess) {
		this.lastGuess = lastGuess;
	}
	
	// getter for last guess required by client code	
	public int getLastGuess() {
		return lastGuess;
	}

}
