package engine.graphics.postprocess.depthrender;

import engine.Loader;
import engine.graphics.Gfx;
import engine.graphics.model.Model;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import tools.Maths;

public class DepthRenderRenderer
{

	private Model quad;
	private static DepthRenderShader shader;


	public DepthRenderRenderer()
	{
		float[] positions = {0, 0, 0, 2, 2, 0, 2, 2, 0, 2, 2, 0};
		quad = Loader.load2DModel(positions);
		shader = new DepthRenderShader();
	}
	public void render(float x, float y, float width, float height, int textureID, int depthTexture, int sceneDepth, int mode)
	{
		shader.start();
		prepare(textureID, mode);
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, depthTexture);
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, sceneDepth);
		Vector2f position = new Vector2f(0, 0);//(-Gfx.WIDTH / (width * 1000f)), (-Gfx.HEIGHT / (height * 1000f))); /./////////////////////////////////////////////////////////////////////////
		Matrix4f matrix = Maths.createTransformationMatrix(position,
				new Vector2f(width / Gfx.WIDTH * 1f, height / Gfx.HEIGHT * 1f));
		shader.loadTransformation(matrix);
		shader.loadScreenDimensions((float) Display.getWidth(), (float) Display.getHeight());
		shader.loadOffsets(x, y);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
		end();
		shader.stop();
	}
	public void prepare(int texture, int mode)
	{
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, mode);

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	}

	public void end()
	{
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void cleanUp()
	{
		shader.cleanUp();
	}
}
