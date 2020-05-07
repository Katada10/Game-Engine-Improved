package render;

import java.util.*;
import org.lwjgl.opengl.GL20;

import core.GameEngine;
import shading.MainShader;
import shading.Shader;
import shading.SkyBoxShader;
import structures.GameObject;
import structures.Light;

public class Renderer {

	private List<Shader> shaders;
	

	public Renderer(boolean useSkyBox) {
		shaders = new ArrayList<>();
		
		VAO.init();

		addShader(new MainShader("main/vert", "main/frag"));

		if (useSkyBox) {
			addShader(new SkyBoxShader("skybox/vert", "skybox/frag"));
		}
	}

	public void addShader(Shader shader) {
		shaders.add(shader);
	}

	public void render() {
		for (Shader shader : shaders) {
			GL20.glUseProgram(shader.progId);
			shader.render();
		}
	}

	public void cleanUp() {
		for (Shader shader : shaders) {
			shader.clean();
		}
		VAO.destroy();
	}

}
