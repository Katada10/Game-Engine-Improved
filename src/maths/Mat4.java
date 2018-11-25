package maths;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import render.Camera;

public class Mat4 {

	public float[][] m = new float[4][4];
	int counter = 0;
	
	public Mat4() {
		identity();
	}

	public float sin(float angle)
	{
		return (float)Math.sin(Math.toRadians(angle));
	}
	
	public float cos(float angle)
	{
		return (float)Math.cos(Math.toRadians(angle));
	}
	
	public void rotate(float x, float y, float z)
	{
		m[0][0] = (cos(y)*cos(z));
		m[1][0] = cos(y) * -sin(z);
		m[2][0] = -sin(y);
		m[0][1] = (-sin(x)*sin(y)*cos(z)) + (cos(x)*sin(z));
		m[1][1] = (-sin(x)*sin(y)*(-sin(z)))+(cos(x)*cos(z));
		m[2][1] = -sin(x)*cos(y);
		m[0][2] = (cos(x)*sin(y)*cos(z))+(sin(x)*sin(z));
		m[1][2] = (cos(x)*sin(y)*-sin(z))+(sin(x)*cos(z));
		m[2][2] = cos(x) * cos(y);
		m[0][3] = 0;
		m[1][3] = 0;
		m[2][3] = 0;
		m[3][3] = 1;
	}
	
	public void scale(float x, float y, float z)
	{
		m[0][0] *= x;
		m[1][1] *= y;
		m[2][2] *= z;
	}
	
	public void translate(float x, float y, float z)
	{
		m[3][0] = x;
		m[3][1] = y;
		m[3][2] = z;
	}
	
	public void identity() {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = 0;
			}
		}

		m[0][0] = 1;
		m[1][1] = 1;
		m[2][2] = 1;
		m[3][3] = 1;
	}

	public Mat4 perspective(float fov, float aspect, float near, float far) {
		float x = (float) Math.tan(fov / 2);
		m[0][0] = (float) (1 / (aspect * x));
		m[1][1] = (float) (1 / x);
		m[2][2] = -((far + near) / (far - near));
		m[2][3] = -1;
		m[3][3] = 0;
		m[3][2] = ((-2 * far * near) / (far - near));
		return this;
	}

	public FloatBuffer get() {
		FloatBuffer fb = BufferUtils.createFloatBuffer(16);
		float[] n = new float[16];

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if(counter != 16)
				{
					n[counter] = m[i][j];
					counter++;
				}
			}
		}
		
		fb.put(n);
		fb.flip();
		
		return fb;
	}
}
