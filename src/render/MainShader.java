package render;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

import core.Loader;
import core.Window;
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
	private float specular, shine;
	
	public int getProgId()
	{
		return progId;
	}
	
	public MainShader(float specular, float shine)
	{	
		progId = Loader.loadShaders("main/vert", "main/frag");
		VAO.init();
		cam = new Camera();
		model = new Matrix4f();
		view = new Matrix4f();
		projection = new Matrix4f();
		
		lights = new ArrayList<>();
		lights.add(new Light(new Vector3f(-5, 3, 5), new Vector3f(1, 1, 1)));
	
		this.specular = specular;
		this.shine = shine;
	}
	
	public void render()
	{

		GL30.glEnable(GL_CULL_FACE);
		createViewProjection(progId);
		light(lights);
	}


	public static void project(int progId)
	{
		projection.identity().perspective((float) Math.toRadians(60), Window.WIDTH / Window.HEIGHT, 0.1f, 100f);
		MasterShader.uploadMat("projection", projection, progId);
	}
	
	public static void createViewProjection(int progId) {
		
		project(progId);
		cam.move();
		view.identity().translate(-cam.position.x, -cam.position.y, -cam.position.z).rotate(cam.rotation.y, new Vector3f(0, 1, 0))
				.rotate(cam.rotation.z, new Vector3f(0, 0, 1));
				

		MasterShader.uploadVec3("camPos", cam.position, progId);
		
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
		MasterShader.uploadUni("spec_Coef", specular, progId);
		MasterShader.uploadUni("shine", shine, progId);
		MasterShader.uploadVec3("light_color", lights.get(0).getColor(), progId);
	}
}
