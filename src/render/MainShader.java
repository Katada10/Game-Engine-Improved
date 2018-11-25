package render;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import core.Loader;
import structures.Camera;
import structures.GameObject;
import structures.Light;

public class MainShader{
	private Matrix4f projection;
	private Matrix4f view;
	private Matrix4f model;

	private Camera cam;
	private List<Light> lights;
	
	public int progId;
	
	public MainShader()
	{	
		progId = Loader.loadShaders("main/vert", "main/frag");
		cam = new Camera();
		model = new Matrix4f();
		view = new Matrix4f();
		projection = new Matrix4f();
		
		lights = new ArrayList<>();
		lights.add(new Light(new Vector3f(-5, 5, 1), new Vector3f(1, 1, 1)));
	}
	
	
	public void render()
	{
		createViewProjection();
		light(lights);
	}


	private void createViewProjection() {
		projection.identity().perspective((float) Math.toRadians(60), 900 / 720, 0.1f, 100f);

		cam.move();
		view.identity().translate(-cam.position.x, -cam.position.y, -cam.position.z).rotate(cam.rotation.y, new Vector3f(0, 1, 0))
				.rotate(cam.rotation.z, new Vector3f(0, 0, 1));
				

		// uploadVec3("camPos", cam.position);
		MasterShader.uploadMat("projection", projection);
		MasterShader.uploadMat("view", view);
	}

	public void model(GameObject o) {
		model.identity().translate(o.position).rotateX(o.rotation.x).rotateY(o.rotation.y).rotateZ(o.rotation.z)
				.scale(o.scale);

		MasterShader.uploadMat("model", model);
	}
	
	public void light(List<Light> lights) {

		for (int i = 0; i < lights.size(); i++) {
			MasterShader.uploadVec3("light_pos[" + i + "]", lights.get(i).position);
		}
		MasterShader.uploadUni("spec_Coef", 0f);
		MasterShader.uploadUni("shine", 0f);
		MasterShader.uploadVec3("light_color", lights.get(0).getColor());
	}
}
