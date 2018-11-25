package render;

import java.util.*;

import structures.GameObject;

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
