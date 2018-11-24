package render;

import java.util.*;

import core.Loader;
import structures.GameObject;

public class Renderer {

	private List<GameObject> objects;
	private MatManager mat;
	private static int progId;

	public Renderer(List<GameObject> objects) {
		this.objects = objects;
		progId = Loader.loadShaders("vert", "frag");
		VAO.init();
		bind();
		mat = new MatManager();
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
		mat.project();
		for(GameObject o : objects)
		{
			o.rotation.setY(o.rotation.getY() + 1);
			mat.model(o);
			VAO.render(o);
		}
	}
	
	public void cleanUp()
	{
		for(GameObject o : objects)
		{
			VAO.clean(o);
		}
	}
	
	public static int getProgId()
	{
		return progId;
	}
}
