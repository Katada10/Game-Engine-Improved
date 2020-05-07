package shading;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL30;

import core.GameEngine;
import render.VAO;
import structures.GameObject;
import structures.Light;

public class MainShader extends Shader {

	public MainShader(String vert, String frag) {
		super(vert, frag);

		bind();
	}

	private void uploadLights(int progId) {
		int i = 0;
		for (GameObject o : GameEngine.objects) {
			if (o.type == 1) {
				Light l = (Light) o;
				ShaderUtils.uploadVec3("light_pos[" + i + "]", l.position, progId);
				ShaderUtils.uploadUni("spec_Coef[" + i + "]", l.specular, progId);
				ShaderUtils.uploadUni("shine[" + i + "]", l.shine, progId);
			}
			i++;
		}
		
	}

	public void render() {
		GL30.glEnable(GL_CULL_FACE);
		ShaderUtils.project(progId);
		ShaderUtils.view(progId);

		uploadLights(progId);

		for (GameObject o : GameEngine.objects) {
			if (o.type == 0) {
				ShaderUtils.uploadMat("model", o.getTransformMatrix(), progId);
				VAO.render(o.getModel());
			}
		}
	}

	public void clean() {
		for (GameObject o : GameEngine.objects) {
			if (o.type == 0) {
				VAO.clean(o);
			}
		}
	}

	private void bind() {
		for (GameObject o : GameEngine.objects) {
			if (o.type == 0) {
				VAO.bindRender(o);
			}
		}

	}
}
