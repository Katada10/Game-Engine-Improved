package core;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

import render.Renderer;
import shading.SkyBoxShader;

import java.util.*;

import structures.GameObject;
import structures.Light;

public interface GameEngine {

	public static List<GameObject> objects = new ArrayList<>();
	
	private static void addStuff() {
		objects.add(new Light(new Vector3f(1, 10, 3), 10, 1));
		
		objects.add(Loader.loadModel("cube", "texture.jpg", "Monkey"));
	}

	
	private static void run() {
		addStuff();

		Renderer r = new Renderer(true);

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	
		GL30.glEnable(GL30.GL_CULL_FACE);
		GL30.glEnable(GL30.GL_DEPTH_TEST);
		
		while (!glfwWindowShouldClose(Window.window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			GL30.glCullFace(GL30.GL_BACK);

			
			r.render();

			glfwSwapBuffers(Window.window);

			glfwPollEvents();
		}
		r.cleanUp();
	}
	
	
	public static void start(int width, int height, String title) {
		Window.create(width, height, title);
		run();
		Window.destroy();
	}

}
