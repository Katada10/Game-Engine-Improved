package core;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.opengl.GL30;

import java.util.*;
import shading.MasterShader;
import structures.GameObject;
import structures.Light;

public interface GameEngine {

	List<GameObject> objects = new ArrayList<>();
	List<Light> lights = new ArrayList<>();
	String[] names = new String[6];
	
	public void addStuff(List<GameObject> objects, List<Light> lights, String[] skyBoxTexNames);
	public void addShaders(MasterShader r);
	public void transform(GameObject object);
	
	default void run(boolean useSkyBox) {
		addStuff(objects, lights, names);

		MasterShader r = new MasterShader(objects, lights, names, useSkyBox);

		addShaders(r);
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	
		GL30.glEnable(GL30.GL_CULL_FACE);
		GL30.glEnable(GL30.GL_DEPTH_TEST);
		
		while (!glfwWindowShouldClose(Window.window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			GL30.glCullFace(GL30.GL_BACK);

			
			r.render(this);

			glfwSwapBuffers(Window.window);

			glfwPollEvents();
		}
		r.cleanUp();
	}
	
	
	default void start(int width, int height, String title, boolean useSkyBox) {
		Window.create(width, height, title);
		run(useSkyBox);
		Window.destroy();
	}

}
