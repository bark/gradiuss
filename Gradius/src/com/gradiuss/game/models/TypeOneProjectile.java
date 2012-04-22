package com.gradiuss.game.models;

import android.graphics.Bitmap;

public class TypeOneProjectile extends Projectile {

	public TypeOneProjectile(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
	}

	@Override
	public void updateState() {
		
		// Movement upwards
		if (moveUp) {	
			setY((int) (getY() - (getVy() * getDirectionY()) ));
		}
		
		// Destroy if touches upper screen boundary
		if (getY() < 0) {
			setVisible(false);
		}
		
		// Calls the superclass method that updates the rectangle automatically.
		super.updateState(); 
	}

}
