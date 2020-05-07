package structures;

import org.joml.Vector3f;

public class Light extends GameObject{
	public float shine, specular;
	
	
	public Light(Vector3f position,  float shine, float specular) {
		super(position, 1);
		
		this.shine = shine;
		this.specular = specular;
	}
}
