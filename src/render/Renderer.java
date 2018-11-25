package render;

import java.util.*;

import org.joml.Vector3f;

import core.Loader;
import structures.GameObject;
import structures.Light;

public class Renderer {

	private List<GameObject> objects;
	private MasterShader manager;
	
	public Renderer(List<GameObject> objects) {
		this.objects = objects;
		
		VAO.init();
		bind();
		
		manager = new MasterShader();
	}
	
	private void bind()
	{
		for(GameObject o : objects)
		{
			VAO.bindRender(o);
		}
	}
	
	public void render()
	{	
		manager.render(objects);
	}
	
	public void cleanUp()
	{
		for(GameObject o : objects)
		{
			VAO.clean(o);
		}
	}
}
