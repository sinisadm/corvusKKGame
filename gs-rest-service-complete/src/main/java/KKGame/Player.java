package KKGame;

public class Player {

    public final String Name;
    
    public Integer Wins;
    public Integer Lose;
    public Integer Draw;


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


	public void Winn() {
		this.Wins++;
		
	}

	public void Lose() {
		this.Lose++;
		
	}

	public void Draw() {
		this.Draw++;
		
	}


	

}
