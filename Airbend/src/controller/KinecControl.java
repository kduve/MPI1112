package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import processing.core.PImage;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;
import airbend.Airbend;

public class KinecControl implements IController, MouseMotionListener {

	private static final long serialVersionUID = 1L;  
	
	private SimpleOpenNI soni;

	private int numberOfUsers = 0;
	
	float leftHandXPos, leftHandYPos, rightHandXPos, rightHandYPos, headXPos, headYPos;
	private Airbend app;
	
	public KinecControl(Airbend app) {
		this.app=app;
		soni = new SimpleOpenNI(app);
		soni.enableDepth();
		soni.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL);
		soni.setMirror(true);
		soni.enableRGB();
	}

	public PImage getImage(){
		if(soni!=null){
			soni.update();

			return soni.rgbImage();
		}else{
			return null;
		}
	}
	
	public void kinectUpdate(){
		int p1UserId = 1;
		int p2UserId = 2;

		// Tracking of player 1 position
		if (soni.isTrackingSkeleton(p1UserId)) {
			
			// tracking of left hand
			PVector p1LeftHand3d = new PVector();
			PVector p1LeftHand2d = new PVector();
			float confidenceP1LeftHand = soni.getJointPositionSkeleton(p1UserId, SimpleOpenNI.SKEL_LEFT_HAND, p1LeftHand3d);
			soni.convertRealWorldToProjective(p1LeftHand3d, p1LeftHand3d);
			leftHandXPos = ((float)p1LeftHand3d.x);
			leftHandYPos = ((float)p1LeftHand3d.y);
			
			// tracking of right hand
			PVector p1RightHand3d = new PVector();
			PVector p1RightHand2d = new PVector();
			float confidenceP1RightHand = soni.getJointPositionSkeleton(p1UserId, SimpleOpenNI.SKEL_RIGHT_HAND, p1RightHand3d);
			soni.convertRealWorldToProjective(p1RightHand3d, p1RightHand3d);
			rightHandXPos = ((float)p1RightHand3d.x);
			rightHandYPos = ((float)p1RightHand3d.y);
			
			// tracking of head
			PVector p1Head3d = new PVector();
			PVector p1Head2d = new PVector();
			float confidenceP1Head = soni.getJointPositionSkeleton(p1UserId, SimpleOpenNI.SKEL_HEAD, p1Head3d);
			soni.convertRealWorldToProjective(p1Head3d, p1Head3d);
			headXPos = ((float)p1Head3d.x);
			headYPos = ((float)p1Head3d.y);

	
		}
		
		// Tracking of player 2 position
		//if(soni.isTrackingSkeleton(p2UserId)){
		//	PVector p2RightHand3d = new PVector();
		//	PVector p2RightHand2d = new PVector();
			
		//	float confidence2 = soni.getJointPositionSkeleton(p2UserId, SimpleOpenNI.SKEL_RIGHT_HAND, p2RightHand3d);
		//	soni.convertRealWorldToProjective(p2RightHand3d, p2RightHand3d);

		//}
		
	
	}

	public void onNewUser(int userId) {
		if(soni.loadCalibrationDataSkeleton(userId, "UserCalibration.bin")){
			soni.startTrackingSkeleton(userId);
			System.out.println("Calibration file loaded");
		}
		else
			System.out.println("Calibration file failed to load");

	}
	


	public float getY() {
		return headYPos;
	}

	@Override
	public float getX() {
		return headXPos;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getChoice1X() {
		return leftHandXPos;
	}

	@Override
	public float getChoice1Y() {
		return leftHandYPos;
	}

	@Override
	public float getChoice2X() {
		return rightHandXPos;
	}

	@Override
	public float getChoice2Y() {
		return rightHandYPos;
	}



	

}
