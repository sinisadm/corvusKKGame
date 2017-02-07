package KKGame;

public class Player {

    public final String Name;
    
    public final Integer Wins;
    public final Integer Lose;
    public final Integer Draw;


    public Player(String name)
	{
    	this.Name = name;
	    this.Wins = 0;
	    this.Lose = 0;
	    this.Draw = 0;
	}


    public String toString() {
        return this.Name;
    }


	

}
