package render;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import core.Window;
import structures.Camera;

public class ShaderUtils {
	
	private static Camera cam = new Camera();
	
	public static Matrix4f projection = new Matrix4f();
	public static Matrix4f view = new Matrix4f();
	public static Matrix4f skyView = new Matrix4f();
	

	public static void uploadUni(String name, float val, int id) {
		int location = GL30.glGetUniformLocation(id, name);

		GL30.glUniform1f(location, val);
	}

	public static void uploadMat(String name, Matrix4f mat, int id) {
		int location = GL30.glGetUniformLocation(id, name);
		FloatBuffer fb = BufferUtils.createFloatBuffer(16);

		mat.get(fb);
		GL30.glUniformMatrix4fv(location, false, fb);
	}

	public static void uploadVec3(String name, Vector3f vec, int id) {
		int location = GL30.glGetUniformLocation(id, name);

		float[] arr = { vec.x, vec.y, vec.z };
		GL30.glUniform3fv(location, arr);
	}
	
	public static void project(int progId)
	{
		projection.identity().perspective((float) Math.toRadians(60), Window.WIDTH / Window.HEIGHT, 0.1f, 100f);
		ShaderUtils.uploadMat("projection", projection, progId);
	}
	
	public static void skyView(int progId)
	{
		cam.move();
		view.identity()
				.rotate(cam.rotation.y, new Vector3f(0, 1, 0));
		
		ShaderUtils.uploadMat("view", view, progId);
	}
	
	
	public static void view(int progId)
	{
		cam.move();
		view.identity().rotate(cam.rotation.x, new Vector3f(1, 0, 0))
				.rotate(cam.rotation.y, new Vector3f(0, 1, 0)).translate(-cam.position.x, -cam.position.y, -cam.position.z);
				

		ShaderUtils.uploadVec3("camPos", cam.position, progId);
		
		ShaderUtils.uploadMat("view", view, progId);
	}
	
}
