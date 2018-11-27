package render;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import structures.Light;

public class LightManager {

	private static List<Light> lights;
	private float specular, shine;
	
	public LightManager(float specular, float shine) {
	
		lights = new ArrayList<>();
		lights.add(new Light(new Vector3f(-5, 3, 5), new Vector3f(1, 1, 1)));
	
		this.specular = specular;
		this.shine = shine;
	}
	
	public void uploadLights(int progId)
	{
		for (int i = 0; i < lights.size(); i++) {
			ShaderUtils.uploadVec3("light_pos[" + i + "]", lights.get(i).position, progId);
		}
		ShaderUtils.uploadUni("spec_Coef", specular, progId);
		ShaderUtils.uploadUni("shine", shine, progId);
		ShaderUtils.uploadVec3("light_color", lights.get(0).getColor(), progId);
	}
}
