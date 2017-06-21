package engine.graphics.gui.shape.rectangle;

import engine.Loader;
import engine.graphics.Gfx;
import engine.graphics.model.Model;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import tools.Maths;

public class RectangleRenderer
{

	private Model quad;
	private static RectangleShader shader;


	public RectangleRenderer()
	{
		float[] positions = {0, 0, 0, 1, 2, 0, 2, 1, 0, 1, 2, 0};
		quad = Loader.load2DModel(positions);
		shader = new RectangleShader();
	}

	public void render(Rectangle2D rectangle)
	{
		shader.start();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		prepare();

		float xOff = Gfx.WIDTH / 1000;
		float pixSpace = Gfx.WIDTH / 1000;

		Vector2f position = new Vector2f((-Gfx.WIDTH / (rectangle.getXScale() * 1000f))
			, (-Gfx.HEIGHT / (rectangle.getYScale() * 1000f)));
		Matrix4f matrix = Maths.createTransformationMatrix(position,
				new Vector2f(rectangle.getXScale() / Gfx.WIDTH * 1000f, rectangle.getYScale() / Gfx.HEIGHT * 1000f));
		shader.loadTransformation(matrix);
		shader.loadScreenDimensions((float) Display.getWidth(), (float) Display.getHeight());
		shader.loadOffsets(rectangle.getX(), rectangle.getY());
		shader.loadColour(rectangle.getR(), rectangle.getG(), rectangle.getB(), rectangle.getA());
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
		shader.stop();
	}
	public void prepare()
	{
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	public void end()
	{
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void cleanUp()
	{
		shader.cleanUp();
	}
}
