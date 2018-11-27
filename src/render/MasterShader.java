package render;

import java.nio.FloatBuffer;

import java.util.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import structures.GameObject;

public class MasterShader {

	private MainShader mainShader;
	private SkyBoxShader skyBoxShader;
	
	private int mainProgId;
	private int skyBoxId;
	
	public MasterShader() {
		mainShader = new MainShader(1f, 10f);
		skyBoxShader = new SkyBoxShader();
		mainProgId = mainShader.getProgId();
		skyBoxId = skyBoxShader.progId;
	}

	private void use(int id)
	{
		GL20.glUseProgram(id);
	}
	
	public void bind(List<GameObject> objects)
	{
		for(GameObject o : objects)
		{
			VAO.bindRender(o);
		}
	}
	
	
	public void render(List<GameObject> objects) {
		
		use(mainProgId);
		mainShader.render();
		
		for(GameObject o : objects)
		{
			o.rotation.y -= 0.01f;
			mainShader.model(o);
			VAO.render(o.getModel());
		}
		
		use(skyBoxId);
		skyBoxShader.render();
	}
	
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

	public void clean(List<GameObject> objects) {
		for(GameObject o : objects)
		{
			VAO.clean(o);
		}
		
	}
}
