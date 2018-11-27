package render;

import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.opengl.GL30;

import core.Loader;
import structures.Texture;

public class SkyBoxShader {

	private int vbo, tbo;
	private float[] verts = {
			-20.0f,  20.0f, -20.0f,
		    -20.0f, -20.0f, -20.0f,
		     20.0f, -20.0f, -20.0f,
		     20.0f, -20.0f, -20.0f,
		     20.0f,  20.0f, -20.0f,
		    -20.0f,  20.0f, -20.0f,

		    -20.0f, -20.0f,  20.0f,
		    -20.0f, -20.0f, -20.0f,
		    -20.0f,  20.0f, -20.0f,
		    -20.0f,  20.0f, -20.0f,
		    -20.0f,  20.0f,  20.0f,
		    -20.0f, -20.0f,  20.0f,

		     20.0f, -20.0f, -20.0f,
		     20.0f, -20.0f,  20.0f,
		     20.0f,  20.0f,  20.0f,
		     20.0f,  20.0f,  20.0f,
		     20.0f,  20.0f, -20.0f,
		     20.0f, -20.0f, -20.0f,

		    -20.0f, -20.0f,  20.0f,
		    -20.0f,  20.0f,  20.0f,
		     20.0f,  20.0f,  20.0f,
		     20.0f,  20.0f,  20.0f,
		     20.0f, -20.0f,  20.0f,
		    -20.0f, -20.0f,  20.0f,

		    -20.0f,  20.0f, -20.0f,
		     20.0f,  20.0f, -20.0f,
		     20.0f,  20.0f,  20.0f,
		     20.0f,  20.0f,  20.0f,
		    -20.0f,  20.0f,  20.0f,
		    -20.0f,  20.0f, -20.0f,

		    -20.0f, -20.0f, -20.0f,
		    -20.0f, -20.0f,  20.0f,
		     20.0f, -20.0f, -20.0f,
		     20.0f, -20.0f, -20.0f,
		    -20.0f, -20.0f,  20.0f,
		     20.0f, -20.0f,  20.0f
	};
	
	public int progId;
	
	public SkyBoxShader()
	{
		progId = Loader.loadShaders("skybox/vert", "skybox/frag");
		vbo = VAO.loadFloat(verts, 3, 3);
		loadCubeMap();
	}
	
	public void loadCubeMap()
	{
		Texture[] textures = new Texture[6];
		tbo = VAO.createTexId(GL_TEXTURE_CUBE_MAP);
		
		for (int i = 0; i < textures.length; i++) {
			textures[i] = Loader.loadData(i+".png");
		}
		
		VAO.bindTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_X, textures[0]);
		VAO.bindTexture(GL_TEXTURE_CUBE_MAP_NEGATIVE_X, textures[1]);
		VAO.bindTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_Y, textures[2]);
		VAO.bindTexture(GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, textures[3]);
		VAO.bindTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_Z, textures[4]);
		VAO.bindTexture(GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, textures[5]);
		
		
		
		glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
	    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
	    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
	}
	
	public void render()
	{
		glDisable(GL_CULL_FACE);
		MainShader.project(progId);
		GL30.glBindTexture(GL30.GL_TEXTURE_CUBE_MAP, tbo);
		VAO.render(vbo, verts);
	}
}
