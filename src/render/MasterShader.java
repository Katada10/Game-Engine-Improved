package render;

import java.nio.FloatBuffer;

import java.util.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import structures.GameObject;

public class MasterShader {

	private MainShader mainShader;
	public static int progId;
	
	public MasterShader() {
		mainShader = new MainShader();
		progId = mainShader.progId;
	}

	public void render(List<GameObject> objects) {
		mainShader.render();
		
		for(GameObject o : objects)
		{
			mainShader.model(o);
			VAO.render(o.getModel());
		}
	}
	
	public static void uploadUni(String name, float val) {
		int location = GL30.glGetUniformLocation(progId, name);

		GL30.glUniform1f(location, val);
	}

	public static void uploadMat(String name, Matrix4f mat) {
		int location = GL30.glGetUniformLocation(progId, name);
		FloatBuffer fb = BufferUtils.createFloatBuffer(16);

		mat.get(fb);

		GL30.glUniformMatrix4fv(location, false, fb);
	}

	public static void uploadVec3(String name, Vector3f vec) {
		int location = GL30.glGetUniformLocation(progId, name);

		float[] arr = { vec.x, vec.y, vec.z };
		GL30.glUniform3fv(location, arr);
	}
}
