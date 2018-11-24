package structures;

import java.nio.ByteBuffer;

public class Texture {

	private int width, height;
	private ByteBuffer data;
	
	public Texture(int width, int height, ByteBuffer data) {
		super();
		this.width = width;
		this.height = height;
		this.data = data;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ByteBuffer getData() {
		return data;
	}
	
}
