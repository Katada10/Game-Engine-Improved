package render;

import java.util.*;

import core.Loader;
import maths.Vec3;
import structures.GameObject;

public class Renderer {

	private List<GameObject> objects;
	private ShaderManager manager;
	private static int progId;

	private List<Light> lights;

	
	public Renderer(List<GameObject> objects) {
		this.objects = objects;
		progId = Loader.loadShaders("vert", "frag");
		VAO.init();
		bind();
		lights = new ArrayList<>();
		
		lights.add(new Light(new Vec3(-5, 5, 1), new Vec3(1, 1, 1)));
		manager = new ShaderManager(lights, lights.size());
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
		manager.render();
	
		for(GameObject o : objects)
		{
			manager.model(o);
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
