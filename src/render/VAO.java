package render;

import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import structures.GameObject;
import structures.Model;
import structures.Texture;

public class VAO {
	private static int vaoId;
	public static void init() {
		vaoId = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoId);
	}

	public static void destroy()
	{
		GL30.glDeleteVertexArrays(vaoId);
	}
	
	public static int loadFloat(float[] data, int index, int size) {
		int vbo = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, data, GL30.GL_STATIC_DRAW);
		pointer(index, size, GL30.GL_FLOAT);
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
		return vbo;
	}

	public static void pointer(int index, int size, int type) {
		GL30.glVertexAttribPointer(index, size, type, false, 0, 0);
	}

	public static int loadInt(int[] data) {
		int vbo = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, vbo);
		GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, data, GL30.GL_STATIC_DRAW);
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, 0);
		return vbo;
	}

	public static void render(int vbo, float[] data, int arrayIndex)
	{
		GL30.glEnableVertexAttribArray(arrayIndex);
		
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
		GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, data.length / 3);

		GL30.glDisableVertexAttribArray(arrayIndex);
	}
	
	public static void render(Model o)
	{
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		GL30.glEnableVertexAttribArray(2);
		
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, o.ebo);
		GL30.glDrawElements(GL30.GL_TRIANGLES, o.getInd().length, GL11.GL_UNSIGNED_INT, 0);

		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		GL30.glDisableVertexAttribArray(2);
	}

	public static void clean(GameObject o)
	{
		GL30.glDeleteBuffers(o.getModel().vbo);
		GL30.glDeleteBuffers(o.getModel().tbo);
		GL30.glDeleteBuffers(o.getModel().nbo);
		GL30.glDeleteBuffers(o.getModel().ebo);
	}
	
	public static void bindRender(GameObject o) {
		o.getModel().vbo = loadFloat(o.getModel().getVerts(), 0, 3);
		o.getModel().tbo = loadFloat(o.getModel().getTexCoords(), 1, 2);
		o.getModel().nbo = loadFloat(o.getModel().getNorms(), 2, 3);
		o.getModel().ebo = loadInt(o.getModel().getInd());
	}
	
	public static int createTexId(int target)
	{
		int id = glGenTextures();
		glBindTexture(target, id);
		return id;
	}
	
	public static void bindTexture(int type, Texture texture)
	{
		glTexImage2D(type, 0, GL30.GL_RGBA, 
				texture.getWidth(), texture.getHeight(), 0, GL30.GL_RGBA, 
				GL_UNSIGNED_BYTE, texture.getData());
	}
}
