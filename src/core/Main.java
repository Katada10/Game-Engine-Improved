package core;

import java.util.List;

import org.joml.Vector3f;

import shading.MasterShader;
import structures.GameObject;
import structures.Light;

public class Main implements GameEngine{

	public static void main(String[] args) {
		new Main().start(1280, 720, "Game Engine");
	}

	@Override
	public void addStuff(List<GameObject> objects, List<Light> lights, String[] names) {
		for (int i = 0; i < names.length; i++) {
			names[i] = i + "";
		}
		
		objects.add(Loader.loadModel("monkey", "texture.jpg"));
		lights.add(new Light(new Vector3f(5, 5, 5), 0, 0));
	}

	@Override
	public void addShaders(MasterShader r) {
		
	}

}
