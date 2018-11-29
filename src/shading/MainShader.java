package shading;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

import core.Loader;
import core.Window;
import game.Physics;
import render.LightManager;
import render.ShaderUtils;
import render.VAO;
import structures.Camera;
import structures.GameObject;
import structures.Light;

public class MainShader{
	private static Matrix4f model;
	
	private static int progId;
	private List<GameObject> objects;
	private LightManager lightManager;
	
	public int getProgId()
	{
		return progId;
	}
	
	public MainShader(List<GameObject> objects)
	{	
		this.objects = objects;
		lightManager = new LightManager(2f, 10f);
		progId = Loader.loadShaders("main/vert", "main/frag");
	//	physics = new Physics(objects);
		model = new Matrix4f();
		bind();
	}
	
	public void render(List<GameObject> objects)
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
