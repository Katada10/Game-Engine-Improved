package shading;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL30;

import render.LightManager;
import render.ShaderUtils;
import render.VAO;
import structures.GameObject;

public class MainShader extends Shader{
	private static Matrix4f model;
	
	private List<GameObject> objects;
	private LightManager lightManager;
	
	public MainShader(List<GameObject> objects, LightManager lightManager, String vert, String frag)
	{
		super(vert, frag);
		this.objects = objects;
		this.lightManager = lightManager;
		model = new Matrix4f();
		bind();
	}
	
	public void render()
	{
		GL30.glEnable(GL_CULL_FACE);
		ShaderUtils.project(progId);
		ShaderUtils.view(progId);
		lightManager.uploadLights(progId);
		for(GameObject o : objects)
		{
			model(o);
			VAO.render(o.getModel());
		}
	}
	
	public void clean()
	{
		for(GameObject o : objects)
		{
			VAO.clean(o);
		}
	}

	private void model(GameObject o) {
		model.identity().translate(o.position).rotateX(o.rotation.x).rotateY(o.rotation.y).rotateZ(o.rotation.z)
				.scale(o.scale);

		ShaderUtils.uploadMat("model", model, progId);
	}

	private void bind() {
		for(GameObject o : objects)
		{
			VAO.bindRender(o);
		}
		
	}
}
