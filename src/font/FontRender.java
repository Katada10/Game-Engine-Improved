package font;

import structures.Quad;

public class FontRender {
	private String text;
	private String bmpLoc;
	
	private final int MAX_LEN = 15;
	private Quad[] quads = new Quad[MAX_LEN];

	public FontRender(String text, String bmpLoc) {
		this.text = text;
		this.bmpLoc = bmpLoc;
	}
	
	
	/*THIS METHOD WILL EXTRACT THE LETTERS FROM BITMAP FONT,
	 * then bind each "texture" to the corresponding quad
	*
	*/
	public void createTextures()
	{
		
	}
	
	
	public void render()
	{
		
	}

}
