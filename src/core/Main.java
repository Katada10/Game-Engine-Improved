package core;

import java.util.List;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import render.MasterShader;
import shading.PrimitiveShader;
import structures.GameObject;
import structures.Light;

public class Main implements GameEngine{

	@Override
	public void addStuff(List<GameObject> objects, List<Light> lights, String[] skyBoxTexNames) {
		
		lights.add(new Light(new Vector3f(0, 2, 3), 50, 20));
	}

	@Override
	public void addShaders(MasterShader r) {
		r.addShader(new PrimitiveShader("plain/vert", "plain/frag"));
	}

	@Override
	public void transform(GameObject object) {
	
		
	}
	

	public static void main(String[] args)
	{
		new Main().start(1920, 1080, "My Game", false);
	}
}
