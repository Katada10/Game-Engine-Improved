package core;

import java.util.List;

import org.joml.Vector3f;

import shading.MasterShader;
import structures.GameObject;
import structures.Light;

public class Main implements GameEngine{

	@Override
	public void addStuff(List<GameObject> objects, List<Light> lights, String[] skyBoxTexNames) {
		// TODO Auto-generated method stub
		objects.add(Loader.loadModel("monkey", "texture.jpg"));
		lights.add(new Light(new Vector3f(0, 2, 3), 50, 20));
	}

	@Override
	public void addShaders(MasterShader r) {
		
	}

	@Override
	public void transform(GameObject object) {
		object.rotation.y += Math.sin(System.currentTimeMillis() / 1000) / 10;
	}
	

	public static void main(String[] args)
	{
		new Main().start(1920, 1080, "My Nice Game", false);
	}
}
