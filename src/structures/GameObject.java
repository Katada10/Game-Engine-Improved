package structures;

import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;

import org.lwjgl.opengl.GL30;

import maths.Vec3;

public class GameObject {
	private Model model;
	public Vec3 position, rotation, scale;
	
	public Model getModel() {
		return model;
	}

	private void bindTexture()
	{
		model.tbo = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, model.tbo);
		
		GL30.glPixelStorei(GL30.GL_UNPACK_ALIGNMENT, 1);

		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);

		
		glTexImage2D(GL_TEXTURE_2D, 0, GL30.GL_RGBA, 
				texture.getWidth(), texture.getHeight(), 0, GL30.GL_RGBA, 
				GL_UNSIGNED_BYTE, texture.getData());
	}
	public Texture getTexture() {
		return texture;
	}

	private Texture texture;
	
	public GameObject(Model m, Texture t) {
		this.model = m;
		this.texture = t;
		bindTexture();
		position = new Vec3(0 , 0, 0);
		rotation = new Vec3(0 , 0, 0);
		scale = new Vec3(1 , 1, 1);
	}
	
}
