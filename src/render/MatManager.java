package render;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import core.Window;
import maths.Mat4;
import structures.GameObject;

public class MatManager {

	private Mat4 projection;
	private Mat4 view;
	private Mat4 model;
	
	private Camera cam;
	
	public MatManager()
	{
		cam = new Camera();
	}
	
	public void project()
	{
		createViewProjection();
		uploadMat("projection", projection);
		uploadMat("view", view);
	}
	
	public void model(GameObject o)
	{
		model = new Mat4();
		model.translate(o.position.getX(), o.position.getY(), o.position.getZ());
		model.rotate(o.rotation.getX(), o.rotation.getY(), o.rotation.getZ());
		model.scale(o.scale.getX(), o.scale.getY(), o.scale.getZ());
		
		uploadMat("model", model);
	}
	
	private void uploadMat(String name, Mat4 mat)
	{
		int location = GL30.glGetUniformLocation(Renderer.getProgId(), name);
		FloatBuffer fb = mat.get();

		GL30.glUniformMatrix4fv(location, false, fb);
	}
	
	
	private void createViewProjection()
	{
		projection = new Mat4();
		view = new Mat4();
		
		projection.perspective((float)Math.toRadians(60), (Window.WIDTH / Window.HEIGHT), 0.1f, 100f);
		
		view.view(cam);
	}
}
