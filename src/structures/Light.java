package structures;

import org.joml.Vector3f;

public class Light {
	public Vector3f position;
	public float shine, specular;
	
	
	public Light(Vector3f position,  float shine, float specular) {
		this.position = position;
		this.shine = shine;
		this.specular = specular;
	}
}
