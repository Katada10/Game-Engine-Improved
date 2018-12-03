package shading;

import core.GameEngine;
import core.Loader;

public abstract class Shader {
	
	public int progId;
	
	public Shader(String vert, String frag)
	{
		progId = Loader.loadShaders(vert, frag);
	}
	
	public abstract void render(GameEngine gameEngine);
	public abstract void clean();
}
