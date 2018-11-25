package render;

import static org.lwjgl.glfw.GLFW.*;

import core.Window;
import maths.Vec3;

public class Camera {
	public Vec3 position;
	
	public Camera()
	{
		position = new Vec3(0, 3, 10);
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
			position.setX(position.getX() - 0.1f);
		}if(getKey(GLFW_KEY_S))
		{
			position.setZ(position.getZ() + 0.1f);
		}
		if(getKey(GLFW_KEY_D))
		{
			position.setX(position.getX() + 0.1f);
		}
		if(getKey(GLFW_KEY_W))
		{
			position.setZ(position.getZ() - 0.1f);
		}
		
	}

}
