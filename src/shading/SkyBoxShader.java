package shading;

import static org.lwjgl.opengl.GL30.*;

import core.GameEngine;
import core.Loader;
import render.ShaderUtils;
import render.VAO;
import structures.Texture;

public class SkyBoxShader extends Shader{

	private int vbo, tbo;
	private String[] fileNames;
	
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
	
	public SkyBoxShader(String vertPath, String fragPath, String[] fileNames)
	{
		super(vertPath, fragPath);
		this.fileNames = fileNames;
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
	
	public void render(GameEngine e)
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
