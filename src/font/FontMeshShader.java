package font;

import core.GameEngine;
import render.ShaderUtils;
import render.VAO;
import shading.Shader;
import structures.Primitive;
import structures.Quad;

import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP;
import static org.lwjgl.opengl.GL30.*;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

public class FontMeshShader extends Shader {
	
	private static List<Primitive> primitives;
	private Matrix4f model;
	
	public FontMeshShader(String vert, String frag) {
		super(vert, frag);
		model = new Matrix4f();
		primitives = new ArrayList<>();
	}
	
	private void model(Primitive prim)
	{
		model.identity().translate(prim.position).rotateX(prim.rotation.x).rotateY(prim.rotation.y).rotateZ(prim.rotation.z)
		.scale(prim.scale);

		ShaderUtils.uploadMat("model", model, progId);
	}
	
	@Override
	public void render(GameEngine gameEngine) {
		glEnable(GL_CULL_FACE);
		ShaderUtils.project(progId);
		ShaderUtils.view(progId);
		
		for (Primitive prim : primitives)
		{
			model(prim);
			VAO.render(prim.vbo, prim.verts, 0);
		}
	}

	@Override
	public void clean() {
		for (Primitive prim : primitives)
		{
			glDeleteBuffers(prim.vbo);
		}
	}
	
	public static void addQuad(Primitive q)
	{
		primitives.add(q);
	}
	
}
