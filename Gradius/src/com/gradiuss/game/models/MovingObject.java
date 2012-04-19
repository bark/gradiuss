package com.gradiuss.game.models;

import android.graphics.Bitmap;


public abstract class MovingObject extends GameObject {

	public static final int DIRECTION_LEFT	= -1;
	public static final int DIRECTION_RIGHT	= 1;
	public static final int DIRECTION_UP	= -1;
	public static final int DIRECTION_DOWN	= 1;
	
	private float vx;
	private float vy;
	
	private int directionX = DIRECTION_RIGHT;
	private int directionY = DIRECTION_DOWN;
	
	protected boolean moveLeft;
	protected boolean moveRight;
	protected boolean moveUp;
	protected boolean moveDown;
	
	public MovingObject(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
	}
	
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}
	
	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}
	
	public boolean isMovingLeft() {
		return moveLeft;
	}
	
	public boolean isMovingRight() {
		return moveRight;
	}
	
	public boolean isMovingUp() {
		return moveUp;
	}
	
	public boolean isMovingDown() {
		return moveDown;
	}
	
	public void setVx(float vx) {
		this.vx = vx;
	}
	
	public float getVx() {
		return vx;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}
	
	public float getVy() {
		return vy;
	}

	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}
	
	public int getDirectionX() {
		return directionX;
	}
	
	public void setDirectionY(int yDirection) {
		this.directionY = yDirection;
	}

	public int getDirectionY() {
		return directionY;
	}

}