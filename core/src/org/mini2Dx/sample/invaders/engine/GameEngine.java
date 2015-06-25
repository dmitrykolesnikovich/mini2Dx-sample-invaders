/**
 * Copyright (c) 2015, mini2Dx Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the mini2Dx nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mini2Dx.sample.invaders.engine;

import java.util.ArrayList;
import java.util.List;

import org.mini2Dx.core.collisions.RegionQuad;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;

/**
 *
 * @author Thomas Cashman
 */
public class GameEngine {
	private List<GameObject> gameObjects;
	private CollisionTracker collisionTracker;
	private GameState state;
	
	public GameEngine(GameContainer gc) {
		gameObjects = new ArrayList<GameObject>();
		collisionTracker = new CollisionTracker(gc);
	}
	
	public void reset() {
		//TODO: Spawn player, asteroids and invaders
		state = GameState.PLAYING;
	}
	
	public void update(GameContainer gc, float delta) {
		collisionTracker.preUpdate();
		
		for(int i = gameObjects.size() - 1; i >= 0; i--) {
			gameObjects.get(i).update(gc, delta);
		}
		
		collisionTracker.postUpdate();
		
		if(collisionTracker.isPlayerDestroyed()) {
			state = GameState.LOSS;
		}
	}
	
	public void interpolate(GameContainer gc, float alpha) {
		for(int i = gameObjects.size() - 1; i >= 0; i--) {
			gameObjects.get(i).interpolate(gc, alpha);
		}
	}
	
	public void render(GameContainer gc, Graphics g) {
		for(int i = gameObjects.size() - 1; i >= 0; i--) {
			gameObjects.get(i).render(g);
		}
	}

	public GameState getState() {
		return state;
	}
}
