package structures;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

import render.VAO;

public class GameObject {
	private Model model;
	public Vector3f position, rotation, scale;
	
	public Model getModel() {
		return model;
	}
	
	public GameObject(Model m, Texture t) {
		this.model = m;
		model.tbo = VAO.createTexId(GL30.GL_TEXTURE_2D); 
		
		GL30.glPixelStorei(GL30.GL_UNPACK_ALIGNMENT, 1);

		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);

		VAO.bindTexture(GL30.GL_TEXTURE_2D, t);
		position = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1, 1, 1);
	}
	
	public GameObject(Model m) {
		this.model = m;
		position = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1, 1, 1);
	}
	
}
