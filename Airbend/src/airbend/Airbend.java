package airbend;

import java.awt.Color;
import java.awt.event.MouseMotionListener;

import menu.Menu;
import processing.core.PImage;
import controller.IController;
import controller.KinecControl;
import controller.MouseControl;

public class Airbend extends processing.core.PApplet{
	private static final long serialVersionUID = 1L;

	public IController controller;
	private Menu menu;
	public Instrument instrument;
	
	boolean active = true; 
	public void onNewUser(int userId) {
		controller.onNewUser(userId);
	}

	@Override
	public void setup() {	
		if(controller == null){
			controller = new KinecControl(this);
			//controller = new MouseControl(this);
			addMouseMotionListener((MouseMotionListener) controller);
		}
		
		menu = new Menu(this);

		size(640, 480, P2D);	

		background(0);

	}

	@Override
	public void draw() {		
		fill(255,255,255);
		if(controller!=null){
			PImage image = controller.getImage();
			if(image!=null){
				image(controller.getImage(),0,0);
			}else{
				background(0);
			}
			controller.kinectUpdate();
		}else{
			background(0);
		}
	
		// visualizing position of head, left hand, right hand
		ellipse(controller.getX(), controller.getY(), 10 ,10);
		ellipse(controller.getChoice1X(), controller.getChoice1Y(), 10,10);
		ellipse(controller.getChoice2X(), controller.getChoice2Y(), 10,10);
		
		// draws instrument
		if(instrument == Instrument.drums){
			fill(0,0,255);
			rect((controller.getChoice1X()+controller.getChoice2X())/2,(controller.getChoice1Y()+controller.getChoice2Y())/2,20,20);
		}
		if(instrument == Instrument.keyboard){
			fill(255, 255, 0);
			rect((controller.getChoice1X()+controller.getChoice2X())/2,(controller.getChoice1Y()+controller.getChoice2Y())/2,20,20);
		}
		if(instrument == Instrument.guitar){
			fill(0, 255, 0);
			rect((controller.getChoice1X()+controller.getChoice2X())/2,(controller.getChoice1Y()+controller.getChoice2Y())/2,20,20);
		}
		
		// don't show menu if an instrument is chosen
		if(menu != null && instrument == null){
			menu.update(controller.getChoice1X()/getWidth(), controller.getChoice1Y()/getHeight(), controller.getChoice2X()/getWidth(), controller.getChoice2Y()/getHeight());
			
		}


	}


}
