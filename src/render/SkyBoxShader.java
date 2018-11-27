package render;

import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.opengl.GL30;

import core.Loader;
import structures.Texture;

public class SkyBoxShader {

	private int vbo, tbo;
	private float[] verts = {
			-30.0f,  30.0f, -30.0f,
		    -30.0f, -30.0f, -30.0f,
		     30.0f, -30.0f, -30.0f,
		     30.0f, -30.0f, -30.0f,
		     30.0f,  30.0f, -30.0f,
		    -30.0f,  30.0f, -30.0f,

		    -30.0f, -30.0f,  30.0f,
		    -30.0f, -30.0f, -30.0f,
		    -30.0f,  30.0f, -30.0f,
		    -30.0f,  30.0f, -30.0f,
		    -30.0f,  30.0f,  30.0f,
		    -30.0f, -30.0f,  30.0f,

		     30.0f, -30.0f, -30.0f,
		     30.0f, -30.0f,  30.0f,
		     30.0f,  30.0f,  30.0f,
		     30.0f,  30.0f,  30.0f,
		     30.0f,  30.0f, -30.0f,
		     30.0f, -30.0f, -30.0f,

		    -30.0f, -30.0f,  30.0f,
		    -30.0f,  30.0f,  30.0f,
		     30.0f,  30.0f,  30.0f,
		     30.0f,  30.0f,  30.0f,
		     30.0f, -30.0f,  30.0f,
		    -30.0f, -30.0f,  30.0f,

		    -30.0f,  30.0f, -30.0f,
		     30.0f,  30.0f, -30.0f,
		     30.0f,  30.0f,  30.0f,
		     30.0f,  30.0f,  30.0f,
		    -30.0f,  30.0f,  30.0f,
		    -30.0f,  30.0f, -30.0f,

		    -30.0f, -30.0f, -30.0f,
		    -30.0f, -30.0f,  30.0f,
		     30.0f, -30.0f, -30.0f,
		     30.0f, -30.0f, -30.0f,
		    -30.0f, -30.0f,  30.0f,
		     30.0f, -30.0f,  30.0f
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
			VAO.bindTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, textures[i]);
		
		}
		
		glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	}
	
	public void render()
	{
		glDisable(GL_CULL_FACE);
		MainShader.project(progId);
		GL30.glBindTexture(GL30.GL_TEXTURE_CUBE_MAP, tbo);
		VAO.render(vbo, verts);
	}
}
