package structures;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector3f;

import core.Window;

public class Camera {
	public Vector3f position;
	public Vector3f rotation;
	
	public Camera()
	{
		position = new Vector3f(0, 0, 5);
		rotation = new Vector3f(0, 0, 0);
	}
	
	public boolean getKey(int key)
	{
		if(glfwGetKey(Window.window, key) != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void move()
	{
		if(getKey(GLFW_KEY_A))
		{
			position.x  -= 0.1f;
		}if(getKey(GLFW_KEY_S))
		{
			position.z += 0.1f;
		}
		if(getKey(GLFW_KEY_D))
		{
			position.x += 0.1f;
		}
		if(getKey(GLFW_KEY_W))
		{
			position.z -= 0.1f;
		}
		if(getKey(GLFW_KEY_Z))
		{
			rotation.y -= 0.05f;
		}
		if(getKey(GLFW_KEY_C))
		{
			rotation.y += 0.05f;
		}
	}

}
