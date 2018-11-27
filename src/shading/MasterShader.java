package shading;

import java.util.*;
import org.lwjgl.opengl.GL20;
import render.VAO;
import structures.GameObject;

public class MasterShader {

	private MainShader mainShader;
	private SkyBoxShader skyBoxShader;
	private List<GameObject> objects;
	
	public MasterShader(List<GameObject> objects) {
		this.objects = objects;
		VAO.init();
		
		mainShader = new MainShader(objects);
		skyBoxShader = new SkyBoxShader();
	}
	
	public void render() {
		
		GL20.glUseProgram(mainShader.getProgId());
		mainShader.render(objects);
		
		GL20.glUseProgram(skyBoxShader.progId);
		skyBoxShader.render();
	}
	

	public void cleanUp()
	{
		mainShader.clean();
	}
	
}
