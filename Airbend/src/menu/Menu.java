package menu;

import java.awt.Color;

import airbend.Airbend;
import airbend.Instrument;


public class Menu {
	boolean active = true;
	Airbend game;
	Button b1;
	Button b2;
	Button b3;

	float activationtime = 1500; //ms

	public Menu(Airbend game) {
		this.game=game;
		
		b1=new Button(game, 0.0f, 0.0f, 0.05f, 0.05f);
		b1.setColor(Color.BLUE);
		b1.setActivationTime(activationtime);
		
		b2=new Button(game, 0.0f, 0.0f, 0.05f, 0.05f);
		b2.setColor(Color.YELLOW);
		b2.setActivationTime(activationtime);
		
		b3=new Button(game, 0.0f, 0.0f, 0.05f, 0.05f);
		b3.setColor(Color.GREEN);
		b3.setActivationTime(activationtime);

	}
	
	public void update(float x1, float y1, float x2, float y2) {
		if(active){
			b1.setPosition(game.controller.getX()/game.width, game.controller.getY()/game.height-0.05f);
			b2.setPosition(game.controller.getX()/game.width-0.1f, game.controller.getY()/game.height);
			b3.setPosition(game.controller.getX()/game.width+0.1f, game.controller.getY()/game.height);

			
			b1.update(x1, y1, x2, y2);
			b2.update(x1, y1, x2, y2);
			b3.update(x1, y1, x2, y2);

			b1.draw();
			b2.draw();
			b3.draw();

	
			if(b1.isActivated()){
				game.instrument = Instrument.drums;
			}
			if(b2.isActivated()){
				game.instrument = Instrument.keyboard;
			}
			if(b3.isActivated()){
				game.instrument = Instrument.guitar;
			}
	

		}

	}
	

}
