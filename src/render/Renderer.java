package render;

import java.util.*;

import structures.GameObject;

public class Renderer {

	private List<GameObject> objects;
	private MasterShader manager;
	
	public Renderer(List<GameObject> objects) {
		this.objects = objects;
		
		manager = new MasterShader();
		bind();
		
	}
	
	private void bind()
	{
		manager.bind(objects);
	}
	
	public void render()
	{	
		manager.render(objects);
	}
	
	public void cleanUp()
	{
		manager.clean(objects);
	}
}
