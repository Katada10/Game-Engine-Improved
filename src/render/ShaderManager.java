package render;

import java.nio.FloatBuffer;

import java.util.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import core.Window;
import maths.Mat4;
import maths.Vec3;
import structures.GameObject;

public class ShaderManager {

	private Matrix4f projection;
	private Matrix4f view;
	private Matrix4f model;

	private Camera cam;

	private int numOfLights;
	private List<Light> lights;

	public ShaderManager(List<Light> lights, int numOfLights) {
		this.lights = lights;
		this.numOfLights = numOfLights;
		cam = new Camera();
		model = new Matrix4f();
		view = new Matrix4f();
		projection = new Matrix4f();
	}

	public void render() {
		createViewProjection();
		light(lights);
	}

	public void model(GameObject o) {
		model.identity().translate(o.position).rotateX(o.rotation.x).rotateY(o.rotation.y).rotateZ(o.rotation.z)
				.scale(o.scale);

		uploadMat("model", model);
	}

	private void uploadUni(String name, float val) {
		int location = GL30.glGetUniformLocation(Renderer.getProgId(), name);

		GL30.glUniform1f(location, val);
	}

	private void uploadMat(String name, Matrix4f mat) {
		int location = GL30.glGetUniformLocation(Renderer.getProgId(), name);
		FloatBuffer fb = BufferUtils.createFloatBuffer(16);

		mat.get(fb);

		GL30.glUniformMatrix4fv(location, false, fb);
	}

	private void uploadVec3(String name, Vec3 vec) {
		int location = GL30.glGetUniformLocation(Renderer.getProgId(), name);

		float[] arr = { vec.getX(), vec.getY(), vec.getZ() };
		GL30.glUniform3fv(location, arr);
	}

	public void createViewProjection() {
		projection.identity().perspective((float) Math.toRadians(60), 900 / 720, 0.1f, 100f);

		cam.move();
		view.identity().translate(-cam.position.getX(), -cam.position.getY(), -cam.position.getZ()).rotate(cam.rotation.getY(), new Vector3f(0, 1, 0))
				.rotate(cam.rotation.getZ(), new Vector3f(0, 0, 1));
				

		// uploadVec3("camPos", cam.position);
		uploadMat("projection", projection);
		uploadMat("view", view);
	}

	public void light(List<Light> lights) {

		for (int i = 0; i < numOfLights; i++) {
			uploadVec3("light_pos[" + i + "]", lights.get(i).position);
		}
		uploadUni("spec_Coef", 0f);
		uploadUni("shine", 0f);
		uploadVec3("light_color", lights.get(0).getColor());
	}
}
