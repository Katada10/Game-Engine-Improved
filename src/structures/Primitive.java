package structures;

import org.joml.Vector3f;

import render.VAO;

public class Primitive {

	public int vbo, texId, tbo;
	
	public float[] verts;
	public float[] texCoords;
	
	public Vector3f position, scale, rotation;

	public Primitive(float[] verts, float[] texCoords) {
		this.verts = verts;
		this.position = new Vector3f();
		this.scale = new Vector3f(1, 1, 1);
		this.rotation = new Vector3f();
		
		vbo = VAO.loadFloat(verts, 0, 3);
		tbo = VAO.loadFloat(texCoords, 1, 2);
	}
	
}
