package engine;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;


import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.*;
import core.*;
import render.Renderer;
import structures.GameObject;
import structures.Model;

public class Main {

	List<GameObject> objects = new ArrayList<>();
	
	public void run()
	{
		//Loader.loadModel("cube", "texture.jpg")
		objects.add(Loader.loadModel("monkey", "texture.jpg"));
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		Renderer r = new Renderer(objects);
		
		while ( !glfwWindowShouldClose(Window.window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			GL30.glEnable(GL30.GL_DEPTH_TEST);
		
			GL20.glUseProgram(Renderer.getProgId());
	
			r.render();
			
			glfwSwapBuffers(Window.window); 

			glfwPollEvents();
		}
		r.cleanUp();
	}
	
	public static void main(String[] args) {
		Window.create(1280, 720, "Game Engine");
		new Main().run();
		Window.destroy();
	}

}
