package structures;

import org.joml.Vector3f;

import core.Loader;
import render.VAO;

public class Primitive {

	public int vbo;
	
	public float[] verts;
	
	public Vector3f position, scale, rotation;

	public Primitive(float[] verts) {
		this.verts = verts;
		this.position = new Vector3f();
		this.scale = new Vector3f(1, 1, 1);
		this.rotation = new Vector3f();
		
		vbo = VAO.loadFloat(verts, 0, 3);
	}
	
}
