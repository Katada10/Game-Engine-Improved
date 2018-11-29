package core;
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
import shading.MasterShader;
import structures.GameObject;

public class Main {

	List<GameObject> objects = new ArrayList<>();
	
	public void run()
	{
		GameObject o1 = Loader.loadModel("cube", "texture.jpg");
		GameObject o2 = Loader.loadModel("cube", "texture.jpg");
		
		o1.position.x = 2;
		objects.add(o1);
		objects.add(o2);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		MasterShader r = new MasterShader(objects);
		GL30.glEnable(GL30.GL_CULL_FACE);
		GL30.glEnable(GL30.GL_DEPTH_TEST);
		while ( !glfwWindowShouldClose(Window.window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			GL30.glCullFace(GL30.GL_BACK);
		
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
