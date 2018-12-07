package render;

import java.util.*;
import org.lwjgl.opengl.GL20;

import core.GameEngine;
import shading.MainShader;
import shading.Shader;
import shading.SkyBoxShader;
import structures.GameObject;
import structures.Light;

public class MasterShader {

	private List<Shader> shaders;
	private LightManager lightManager;
	

	public MasterShader(List<GameObject> objects, List<Light> lights, String[] skyBoxNames, boolean useSkyBox) {
		shaders = new ArrayList<>();
		lightManager = new LightManager(lights);
		
		VAO.init();

		addShader(new MainShader(objects, lightManager, "main/vert", "main/frag"));

		if (useSkyBox) {
			addShader(new SkyBoxShader("skybox/vert", "skybox/frag", skyBoxNames));
		}
	}

	public void addShader(Shader shader) {
		shaders.add(shader);
	}

	public void render(GameEngine gameEngine) {
		for (Shader shader : shaders) {
			GL20.glUseProgram(shader.progId);
			shader.render(gameEngine);
		}
	}

	public void cleanUp() {
		for (Shader shader : shaders) {
			shader.clean();
		}
		VAO.destroy();
	}

}
