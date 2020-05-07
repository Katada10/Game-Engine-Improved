package structures;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

import render.VAO;

public class GameObject {
	private Model model;
	private Matrix4f transformMatrix;
	public Vector3f position, rotation, scale;
	public int type;
	
	//TYPES: 0 - OBJECT
	//       1 - LIGHT
	
	public GameObject(Vector3f position, int type) {
		this.position = position;
		this.rotation = new Vector3f();
		this.scale = new Vector3f(1, 1, 1);
		
		this.type = type;
	}
	
	public GameObject(Model m, Texture t) {
		this(m);
		model.tbo = VAO.createTexId(GL30.GL_TEXTURE_2D); 
		
		GL30.glPixelStorei(GL30.GL_UNPACK_ALIGNMENT, 1);

		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);

		VAO.bindTexture(GL30.GL_TEXTURE_2D, t);
		
	}
	
	
	public GameObject(Model m) {
		this.model = m;
		position = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1, 1, 1);
		
		
		transformMatrix = new Matrix4f();
		transformMatrix.identity().translate(position).rotateX(rotation.x).rotateY(rotation.y).rotateZ(rotation.z)
		.scale(scale);
		
		
		this.type = 0;
	}
	
	
	public Model getModel() {
		return model;
	}


	public Matrix4f getTransformMatrix() {
		return transformMatrix;
	}
}
