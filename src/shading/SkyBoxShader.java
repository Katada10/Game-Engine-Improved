package shading;

import static org.lwjgl.opengl.GL30.*;

import core.GameEngine;
import core.Loader;
import render.VAO;
import structures.Texture;

public class SkyBoxShader extends Shader{

	private int vbo, tbo;
	private String[] fileNames = {"0", "1", "2", "3", "4", "5"};
	
	private float[] verts = {
			-50.0f,  50.0f, -50.0f,
		    -50.0f, -50.0f, -50.0f,
		     50.0f, -50.0f, -50.0f,
		     50.0f, -50.0f, -50.0f,
		     50.0f,  50.0f, -50.0f,
		    -50.0f,  50.0f, -50.0f,

		    -50.0f, -50.0f,  50.0f,
		    -50.0f, -50.0f, -50.0f,
		    -50.0f,  50.0f, -50.0f,
		    -50.0f,  50.0f, -50.0f,
		    -50.0f,  50.0f,  50.0f,
		    -50.0f, -50.0f,  50.0f,

		     50.0f, -50.0f, -50.0f,
		     50.0f, -50.0f,  50.0f,
		     50.0f,  50.0f,  50.0f,
		     50.0f,  50.0f,  50.0f,
		     50.0f,  50.0f, -50.0f,
		     50.0f, -50.0f, -50.0f,

		    -50.0f, -50.0f,  50.0f,
		    -50.0f,  50.0f,  50.0f,
		     50.0f,  50.0f,  50.0f,
		     50.0f,  50.0f,  50.0f,
		     50.0f, -50.0f,  50.0f,
		    -50.0f, -50.0f,  50.0f,

		    -50.0f,  50.0f, -50.0f,
		     50.0f,  50.0f, -50.0f,
		     50.0f,  50.0f,  50.0f,
		     50.0f,  50.0f,  50.0f,
		    -50.0f,  50.0f,  50.0f,
		    -50.0f,  50.0f, -50.0f,

		    -50.0f, -50.0f, -50.0f,
		    -50.0f, -50.0f,  50.0f,
		     50.0f, -50.0f, -50.0f,
		     50.0f, -50.0f, -50.0f,
		    -50.0f, -50.0f,  50.0f,
		     50.0f, -50.0f,  50.0f
	};
	
	public SkyBoxShader(String vertPath, String fragPath)
	{
		super(vertPath, fragPath);
		vbo = VAO.loadFloat(verts, 3, 3);
		loadCubeMap();
	}
	
	public void loadCubeMap()
	{
		Texture[] textures = new Texture[6];
		tbo = VAO.createTexId(GL_TEXTURE_CUBE_MAP);
		
		for (int i = 0; i < textures.length; i++) {
			textures[i] = Loader.loadData(fileNames[i] +".png");
			VAO.bindTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, textures[i]);
		}
		
		glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	}
	
	public void render()
	{
		glDisable(GL_CULL_FACE);
		ShaderUtils.project(progId);
		ShaderUtils.skyView(progId);
		glBindTexture(GL_TEXTURE_CUBE_MAP, tbo);
		VAO.render(vbo, verts, 3);
	}

	@Override
	public void clean() {
		glDeleteBuffers(vbo);
		glDeleteBuffers(tbo);
	}
}
