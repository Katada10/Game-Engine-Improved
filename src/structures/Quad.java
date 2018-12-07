package structures;

import org.joml.Vector3f;

public class Quad extends Primitive{

	private static float[] verts = {
			-1f,  1f, -1f,
		    -1f, -1f, -1f,
		     1f, -1f, -1f,
		     1f, -1f, -1f,
		     1f,  1f, -1f,
		    -1f,  1f, -1f
	};
	
	private static float[] texCoords = {
			-1f,  1f, 
		    -1f, -1f, 
		     1f, -1f, 
		     1f, -1f, 
		     1f,  1f,
		    -1f,  1f
	};
	
	public Vector3f position, scale, rotation;
	
	public Quad() {
		super(verts, texCoords);
	}

}
