package render;

import maths.Vec3;

public class Light {
	public Vec3 position;
	private Vec3 color;
	
	public Light(Vec3 pos, Vec3 color)
	{
		this.position = pos;
		this.color = color;
	}

	public Vec3 getColor()
	{
		return color;
	}
}
