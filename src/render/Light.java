package render;

import maths.Vec3;

public class Light {
	public Vec3 position;
	private Vec3 color;
	private float strength;
	
	public Light(Vec3 pos, Vec3 color, float strength)
	{
		this.position = pos;
		this.color = color;
		this.strength = strength;
	}

	public Vec3 getColor()
	{
		return color;
	}
	
	public float getStrength()
	{
		return strength;
	}
}
