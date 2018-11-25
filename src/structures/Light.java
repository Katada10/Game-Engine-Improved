package structures;

import org.joml.Vector3f;

public class Light {
	public Vector3f position;
	private Vector3f color;
	
	public Light(Vector3f pos, Vector3f color)
	{
		this.position = pos;
		this.color = color;
	}

	public Vector3f getColor()
	{
		return color;
	}
}
