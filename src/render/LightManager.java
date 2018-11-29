package render;

import java.util.List;

import structures.Light;

public class LightManager {

	private List<Light> lights;
	public float specular, shine;
	
	public LightManager(List<Light> lights) {
		this.lights = lights;
	}
	
	public void uploadLights(int progId)
	{
		for (int i = 0; i < lights.size(); i++) {
			ShaderUtils.uploadVec3("light_pos[" + i + "]", lights.get(i).position, progId);
			ShaderUtils.uploadUni("spec_Coef["+i+"]", lights.get(i).specular, progId);
			ShaderUtils.uploadUni("shine["+i+"]", lights.get(i).shine, progId);
		}
	}
}
