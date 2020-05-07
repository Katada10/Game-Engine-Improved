package structures;

public class Model {

	public int tbo, ebo, nbo, vbo;
	private float[] verts, texCoords, norms;
	
	public Model(float[] verts, float[] texCoords, float[] norms, int[] ind) {
		this.verts = verts;
		this.texCoords = texCoords;
		this.norms = norms;
		this.ind = ind;
	}
	private int[] ind;
	
	
	public float[] getVerts() {
		return verts;
	}
	public float[] getTexCoords() {
		return texCoords;
	}
	public float[] getNorms() {
		return norms;
	}
	public int[] getInd() {
		return ind;
	}
	
}
