package menu;

import java.awt.Color;
import airbend.Airbend;

public class Button {
	Airbend game;
	float x,y,w,h;
	boolean selected = false;
	boolean activated = false;
	float activationtime = 1500;
	long starttime = 0;
	long endtime = 0;
	float processtime = 0;
	Color c = Color.YELLOW;

	public Button(Airbend game,float x, float y, float w, float h){
		this.game=game;

		this.x=x-w/2;
		this.y=y-h/2;
		this.w=w;
		this.h=h;

	}

	public void setPosition(float x, float y){
		this.x=x;
		this.y=y;
	}

	public void update(float x1, float y1, float x2, float y2){	
		if(x1>x&&x1<(x+w)&&y1>y&&y1<(y+h) || x2>x&&x2<(x+w)&&y2>y&&y2<(y+h)){
			if(!selected){
				starttime=System.currentTimeMillis();
			}
			selected=true;
		}else{
			selected=false;
			activated=false;
			processtime=0;
			return;
		}

		if(!activated){
			endtime = starttime+(long)activationtime;
			processtime= ((float)(System.currentTimeMillis()-starttime))/activationtime;
			if(processtime>1){
				activated=true;
			}
		}	
	}

	public void draw(){
		float w = game.width * this.w;
		float h = game.height * this.h;
		float x = game.width * this.x;
		float y = game.height * this.y;

		if(processtime<1.0){
			game.fill(c.getRed(),c.getGreen(),c.getBlue(),64+195*processtime);
			game.rect(x, y, w, h+(game.height-h)*processtime);
			game.fill(255-255*processtime);

		}else{
			game.fill(c.getRed(),c.getGreen(),c.getBlue(),255);
			game.rect(0, y, game.width, h);
			game.fill(0);
		}
	}

	public boolean isSelected(){
		return selected;
	}

	public boolean isActivated(){
		if(activated){
			selected=false;
			activated=false;
			processtime=0;
			return true;
		}else{
			return false;
		}
	}

	public void setActivationTime(float t){
		activationtime=(long)t;
	}

	public void setColor(Color c){
		this.c=c;
	}


}
