package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import airbend.Airbend;

import processing.core.PImage;

public class MouseControl implements IController, MouseMotionListener{
	
	Airbend app;
	
	float mousePosX;
	float mousePosY;
	
	public MouseControl(Airbend app) {
		this.app = app;
	}

	@Override
	public float getX() {
		return app.getWidth()/2;
	}

	@Override
	public float getY() {
		return app.getHeight()/2;
	}


	@Override
	public void mouseMoved(MouseEvent me) {
		mousePosX = ((float)me.getPoint().x);
		mousePosY = ((float)me.getPoint().y);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNewUser(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void kinectUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getChoice1X() {
		return mousePosX;
	}

	@Override
	public float getChoice1Y() {
		return mousePosY;
	}

	@Override
	public float getChoice2X() {
		return getChoice1X()+50;
	}

	@Override
	public float getChoice2Y() {
		return getChoice1Y()+50;
	}

}
