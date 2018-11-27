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
	public static Matrix4f projection;
	public static Matrix4f view;
	public static Matrix4f model;

	private static Camera cam;
	private static List<Light> lights;
	
	private static int progId;
	
	
	public int getProgId()
	{
		return progId;
	}
	
	public MainShader()
	{	
		progId = Loader.loadShaders("main/vert", "main/frag");
		VAO.init();
		cam = new Camera();
		model = new Matrix4f();
		view = new Matrix4f();
		projection = new Matrix4f();
		
		lights = new ArrayList<>();
		lights.add(new Light(new Vector3f(-5, 5, 1), new Vector3f(1, 1, 1)));
	}
	
	public void render()
	{
		createViewProjection(progId);
		light(lights);
	}


	public static void project(int progId)
	{
		projection.identity().perspective((float) Math.toRadians(60), 900 / 720, 0.1f, 100f);
		MasterShader.uploadMat("projection", projection, progId);
	}
	
	public static void createViewProjection(int progId) {
		
		project(progId);
		cam.move();
		view.identity().translate(-cam.position.x, -cam.position.y, -cam.position.z).rotate(cam.rotation.y, new Vector3f(0, 1, 0))
				.rotate(cam.rotation.z, new Vector3f(0, 0, 1));
				

		// uploadVec3("camPos", cam.position);
		
		MasterShader.uploadMat("view", view, progId);
	}

	public void model(GameObject o) {
		model.identity().translate(o.position).rotateX(o.rotation.x).rotateY(o.rotation.y).rotateZ(o.rotation.z)
				.scale(o.scale);

		MasterShader.uploadMat("model", model, progId);
	}
	
	private void light(List<Light> lights) {

		for (int i = 0; i < lights.size(); i++) {
			MasterShader.uploadVec3("light_pos[" + i + "]", lights.get(i).position, progId);
		}
		MasterShader.uploadUni("spec_Coef", 0f, progId);
		MasterShader.uploadUni("shine", 0f, progId);
		MasterShader.uploadVec3("light_color", lights.get(0).getColor(), progId);
	}
}
