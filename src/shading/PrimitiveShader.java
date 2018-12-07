package shading;

import core.GameEngine;
import render.ShaderUtils;
import render.VAO;
import structures.Primitive;
import structures.Quad;

import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP;
import static org.lwjgl.opengl.GL30.*;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

public class PrimitiveShader extends Shader {
	
	private static List<Primitive> primitives;
	private Matrix4f model;
	
	public PrimitiveShader(String vert, String frag) {
		super(vert, frag);
		model = new Matrix4f();
		primitives = new ArrayList<>();
		
		primitives.add(new Quad());
	}
	
	
	private void model(Primitive quad)
	{
		model.identity().translate(quad.position).rotateX(quad.rotation.x).rotateY(quad.rotation.y).rotateZ(quad.rotation.z)
		.scale(quad.scale);

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
