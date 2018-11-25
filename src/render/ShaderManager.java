package render;

import java.nio.FloatBuffer;

import java.util.*;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import core.Window;
import maths.Mat4;
import maths.Vec3;
import structures.GameObject;

public class ShaderManager {

	private Mat4 projection;
	private Mat4 view;
	private Mat4 model;
	
	private Camera cam;
	
	private List<Light> lights;
	
	public ShaderManager(List<Light> lights)
	{
		this.lights = lights;
		cam = new Camera();
	}
	
	
	public void render()
	{
		createViewProjection();
		for (Light light : lights) {
			light(light);
		}
	}
	
	public void model(GameObject o)
	{
		model = new Mat4();
		model.translate(o.position.getX(), o.position.getY(), o.position.getZ());
		model.rotate(o.rotation.getX(), o.rotation.getY(), o.rotation.getZ());
		model.scale(o.scale.getX(), o.scale.getY(), o.scale.getZ());
		
		uploadMat("model", model);
	}
	
	private void uploadUni(String name, float val)
	{
		int location = GL30.glGetUniformLocation(Renderer.getProgId(), name);
		
		GL30.glUniform1f(location, val);
	}
	
	private void uploadMat(String name, Mat4 mat)
	{
		int location = GL30.glGetUniformLocation(Renderer.getProgId(), name);
		FloatBuffer fb = mat.get();

		GL30.glUniformMatrix4fv(location, false, fb);
	}
	
	private void uploadVec3(String name, Vec3 vec)
	{
		int location = GL30.glGetUniformLocation(Renderer.getProgId(), name);

		float[] arr = {vec.getX(), vec.getY(), vec.getZ()};
		GL30.glUniform3fv(location, arr);
	}
	
	
	public void createViewProjection()
	{
		projection = new Mat4();
		view = new Mat4();
		
		projection.perspective((float)Math.toRadians(60), (Window.WIDTH / Window.HEIGHT), 0.1f, 100f);
		
		cam.move();
		view.view(cam);
		
		uploadMat("projection", projection);
		uploadMat("view", view);
	}

	public void light(Light light) {
		uploadVec3("light_pos", light.position);
		uploadVec3("light_color", light.getColor());
		uploadUni("light_strength", light.getStrength());
	}
}
