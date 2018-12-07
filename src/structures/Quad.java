package structures;

import org.joml.Vector3f;

public class Quad extends Primitive{

	private static float[] verts = {
			-0.5f,  0.5f, -0.5f,
		    -0.5f, -0.5f, -0.5f,
		     0.5f, -0.5f, -0.5f,
		     0.5f, -0.5f, -0.5f,
		     0.5f,  0.5f, -0.5f,
		    -0.5f,  0.5f, -0.5f
	};
	
	public Vector3f position, scale, rotation;
	
	public Quad() {
		super(verts);
	}

}
