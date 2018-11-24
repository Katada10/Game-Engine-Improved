package core;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;

import org.lwjgl.system.MemoryStack;

import maths.Vec2;
import maths.Vec3;
import structures.GameObject;
import structures.Model;
import structures.Texture;

public class Loader {
	private static List<Vec3> verts = new ArrayList<>(), norms = new ArrayList<>();
	private static List<Vec2> tex = new ArrayList<>();
	private static List<Integer> indices = new ArrayList<>();
	
	private static String read(String name) {
		String src = "";

		try {
			FileReader reader = new FileReader(new File("shaders/" + name + ".txt"));
			int i;
			while ((i = reader.read()) != -1) {
				src += (char) i;
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return src;
	}

	private static int create(String name, int type) {
		int id = glCreateShader(type);
		String src = read(name);
		glShaderSource(id, src);
		glCompileShader(id);
		return id;
	}

	public static int loadShaders(String vert, String frag) {
		int vertId = create(vert, GL_VERTEX_SHADER);
		int fragId = create(frag, GL_FRAGMENT_SHADER);
		int progId = glCreateProgram();

		glAttachShader(progId, vertId);
		glAttachShader(progId, fragId);
		glLinkProgram(progId);

		glDetachShader(progId, vertId);
		glDetachShader(progId, fragId);

		glDeleteShader(vertId);
		glDeleteShader(fragId);

		return progId;
	}

	public static Texture loadData(String name) {
		ByteBuffer image;
		int width, height;
		try (MemoryStack stack = MemoryStack.stackPush()) {
			/* Prepare image buffers */
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);

			/* Load image */
			stbi_set_flip_vertically_on_load(true);
			image = stbi_load("res/" + name, w, h, comp, 4);
			if (image == null) {
				throw new RuntimeException("Failed to load a texture file!\n" + stbi_failure_reason());
			}

			/* Get width and height of image */
			width = w.get();
			height = h.get();
		}

		return new Texture(width, height, image);
	}

	private static float[] toArr2f(List<Vec2> list)
	{
		List<Float> f = new ArrayList<>();
		
		for (Vec2 v : list) {
			f.add(v.getX());
			f.add(v.getY());
		}
		Float[] arr = new Float[f.size()];
		arr = f.toArray(arr);
		
		float[] ret = new float[f.size()];
		for (int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}
	
	private static float[] toArr(List<Vec3> list) {
		List<Float> f = new ArrayList<>();
		
		for (Vec3 v : list) {
			f.add(v.getX());
			f.add(v.getY());
			f.add(v.getZ());
		}
		Float[] arr = new Float[f.size()];
		arr = f.toArray(arr);
		
		float[] ret = new float[f.size()];
		for (int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}

	private static int[] toIArr(List<Integer> list) {
		Integer[] f = new Integer[list.size()];
		f = list.toArray(f);
		int[] ret = new int[f.length];
		for (int i = 0; i < f.length; i++) {
			ret[i] = f[i];
		}
		return ret;
	}

	public static GameObject loadModel(String name, String texName) {
		BufferedReader br;
		String line;
		String[] c;
		int first = 0;
		
		List<Vec2> textures = new ArrayList<>();
		List<Vec3> vertices = new ArrayList<>(), normals = new ArrayList<>();
		List<String> combinations = new ArrayList<>();
		String combo = "";
		
		try {
			br = new BufferedReader(new FileReader(new File("res/" + name + ".obj")));
			while ((line = br.readLine()) != null) {
				if (line.startsWith("v ")) {
					line = line.replace("v ", "");
					c = line.split(" ");

					for (String s : c) {
						if (s != null && !s.isEmpty()) {
							combo += s + ",";
					}}
					
					String[] arr = combo.split(",");
					Vec3 v = new Vec3(Float.parseFloat(arr[0]), Float.parseFloat(arr[1]), Float.parseFloat(arr[2]));
					verts.add(v);
					
					combo = "";
				} else if (line.startsWith("vt")) {
					combo = "";
					line = line.replace("vt", "");
					c = line.split(" ");

					for (String s : c) {
						
						if (s != null && !s.isEmpty())
							combo += s + ",";
					}
					
					String[] arr = combo.split(",");
					Vec2 v = new Vec2(Float.parseFloat(arr[0]), Float.parseFloat(arr[1]));
					tex.add(v);
				} else if (line.startsWith("vn")) {
					combo = "";
					line = line.replace("vn", "");
					c = line.split(" ");

					for (String s : c) {
						if (s != null && !s.isEmpty())
							combo += s + ",";
					}
					
					String[] arr = combo.split(",");
					Vec3 v = new Vec3(Float.parseFloat(arr[0]), Float.parseFloat(arr[1]), Float.parseFloat(arr[2]));
					norms.add(v);
				} else if (line.startsWith("f")) {
					line = line.replace("f", "");
					c = line.split(" ");

					for (String s : c) {
						if (s != null && !s.isEmpty()) {
							String[] arr = s.split("/");
							if (!combinations.contains(s)) {
								combinations.add(s);
								
								vertices.add(verts.get(Integer.parseInt(arr[0]) - 1));
								textures.add(tex.get(Integer.parseInt(arr[1]) - 1));
								normals.add(norms.get(Integer.parseInt(arr[2]) - 1));
								indices.add(first);
								first++;
							}
							else
							{
								indices.add(combinations.indexOf(s));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		float[] v = toArr(vertices), t = toArr2f(textures), n = toArr(normals);
		int[] ind = toIArr(indices);
		
		return new GameObject(new Model(v, t, n, ind), loadData(texName));
	}
}
