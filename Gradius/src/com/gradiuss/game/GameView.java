package com.gradiuss.game;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gradiuss.game.models.Projectile;
import com.gradiuss.game.models.SpaceShip;
import com.gradiuss.game.models.TypeOneProjectile;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	
	private static final String TAG = GameView.class.getSimpleName();
	GameLoopThread gameLoop;
	
	// :::::::::::::::::::::::::::::::::::::::::::::: Fields ::::::::::::::::::::::::::::::::::::::::::::::
	
	// Game time
	//long totalGameTime;
	
	// GameObjects
	// SpaceShip
	public SpaceShip spaceShip;
	// Projectiles
	public List<Projectile> projectiles;
	int projectileType = 0;
	long fireTime;
	long previousFireTime = 0;
	// Enemies
	
	public GameView(Context context, AttributeSet attributes) {
		super(context, attributes);
		initGameView();
	}
	
	// :::::::::::::::::::::::::::::::::::::::::::::: Initializing ::::::::::::::::::::::::::::::::::::::::::::::
	
	// Loading resources like images, music etc... and starting the game loop!
	public void surfaceCreated(SurfaceHolder holder) {
		
		// Loading level (Resources)
		initGameObjects();
		
		// Starting game loop
		gameLoop.setRunning(true);
		gameLoop.start();
		//totalGameTime = System.currentTimeMillis();
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO: Fixa till senare!
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				gameLoop.join();
				retry = false;
			} catch (InterruptedException e) {
				// Try again, shutting down the thread(game loop)
			}
		}
		
	}
	
	private void initGameView() {
		getHolder().addCallback(this);
		gameLoop = new GameLoopThread(getHolder(), this);
		setFocusable(true);
	}

	 
	public void initGameObjects() {
		initSpaceShip();
		initProjectiles();
		initEnemies();
	}

	private void initSpaceShip() {
		// SpaceShip
		Bitmap spaceShipBitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.spaceship);
		spaceShip = new SpaceShip(spaceShipBitmap, getWidth()/2, getHeight()-spaceShipBitmap.getHeight(), 5, 5);
		spaceShip.setVx(10);
		spaceShip.setVy(10);
		spaceShip.setVisible(true);
	}
	
	private void initProjectiles() {
		// Projectiles
		
		projectiles = new ArrayList<Projectile>();
		//fireTime = 1;
	}
	
	private void initEnemies() {
		// TODO: Enemies
	}
	
	// :::::::::::::::::::::::::::::::::::::::::::::: Updating ::::::::::::::::::::::::::::::::::::::::::::::
	
	// Updating the states for all the game objects
	public void updateState() {
		// Update SpaceShip
		updateSpaceShip();
		updateProjectiles();
	}
	
	public void updateProjectiles() {
		// Shooting projectiles
		if (spaceShip.isShooting()/* && totalGameTime - previousFireTime > fireTime*/) {
			//previousFireTime = totalGameTime;
			addProjectile(spaceShip.getX(), spaceShip.getY());
		}
		
		for (int i = projectiles.size() - 1; i >= 0; i--) {
			projectiles.get(i).updateState();
			if (!projectiles.get(i).isVisible()) {
				projectiles.remove(i);
			}
		}
	}
	
	private void updateSpaceShip() {
		// Collisions with the Edges
		if (spaceShip.getX() + spaceShip.getBitmap().getWidth()/2 >= getWidth()) {
			spaceShip.setMoveRight(false);
		}
		if (spaceShip.getX() - spaceShip.getBitmap().getWidth()/2 <= 0) {
			spaceShip.setMoveLeft(false);
		}
		if (spaceShip.getY() - spaceShip.getBitmap().getHeight()/2 <= 0) {
			spaceShip.setMoveUp(false);
		}
		if (spaceShip.getY() + spaceShip.getBitmap().getHeight()/2 >= getHeight()) {
			spaceShip.setMoveDown(false);
		}
		spaceShip.updateState();
	}
	
	public void addProjectile(float x, float y) {
		// Adding projectiles
		Projectile projectile;
		switch (projectileType) {
		case 0:
			projectile = new TypeOneProjectile(BitmapFactory.decodeResource(getResources(), R.drawable.projectile1), x, y - spaceShip.getBitmap().getHeight()/2);
			projectile.setVisible(true);
			projectile.setMoveUp(true);
			projectile.setVy(50);
			projectiles.add(projectile);
			break;
		case 1:
			projectile = new TypeOneProjectile(BitmapFactory.decodeResource(getResources(), R.drawable.projectile2), x, y - spaceShip.getBitmap().getHeight()/2);
			projectile.setVisible(true);
			projectile.setMoveUp(true);
			projectile.setVy(50);
			projectiles.add(projectile);
			break;
		}
		
	}
	
	// :::::::::::::::::::::::::::::::::::::::::::::: Rendering ::::::::::::::::::::::::::::::::::::::::::::::
	
	// Rendering the game state
	public void renderState(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		renderSpaceShip(canvas);
		renderProjectiles(canvas);
	}
	
	public void renderSpaceShip(Canvas canvas) {
		spaceShip.draw(canvas);
	}
	
	public void renderProjectiles(Canvas canvas) {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(canvas);
		}
	}
	
}