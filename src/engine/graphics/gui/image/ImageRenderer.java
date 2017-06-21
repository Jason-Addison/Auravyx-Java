package engine.graphics.gui.image;

import engine.Loader;
import engine.graphics.Gfx;
import engine.graphics.model.Model;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import tools.Maths;

public class ImageRenderer
{

	private Model quad;
	private static ImageShader shader;


	public ImageRenderer()
	{
		float[] positions = {0, 0, 0, 2, 2, 0, 2, 2, 0, 2, 2, 0};
		quad = Loader.load2DModel(positions);
		shader = new ImageShader();
	}
	
	public void render()
	{
		shader.start();
		for(Texture2D texture : Gfx.imageBatch)
		{
			shader.loadTransparency(1);//texture.getTransparency());
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, texture.getBlendMode());
			prepare(texture);
			Matrix4f matrix = Maths.createTransformationMatrix(new Vector2f(texture.getX(), texture.getY()),
					new Vector2f(texture.getXScale(), texture.getYScale()),
					new Vector2f(0, 0), 0);

			matrix = Maths.createTransformationMatrix(new Vector2f(texture.getX(), texture.getY()),
					new Vector2f(texture.getXScale(), texture.getYScale()),
					new Vector2f(0, 0), 0);
			Vector2f position = new Vector2f((-Gfx.WIDTH / (texture.getX() * 1000f)), (-Gfx.HEIGHT / (texture.getY() * 1000f)));
			matrix = Maths.createTransformationMatrix(position,
					new Vector2f(texture.getXScale() / Gfx.WIDTH * 1000f, texture.getYScale() / Gfx.HEIGHT * 1000f));
			shader.loadTransformation(matrix);
			shader.loadScreenDimensions((float) Display.getWidth(), (float) Display.getHeight());
			shader.loadOffsets(texture.getX(), texture.getY());
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
			end();
		}

		shader.stop();

	}
	float e;
	public void render(int tex)
	{
		float x = 0;
		float y = 0;
		float width = Gfx.WIDTH;
		float height = Gfx.HEIGHT;
		shader.start();
		shader.loadTransparency(1);//texture.getTransparency());
		prepare(tex);
		Vector2f position = new Vector2f((-Gfx.WIDTH / (width * 1000f))
				, (-Gfx.HEIGHT / (height * 1000f)));
		Matrix4f matrix = Maths.createTransformationMatrix(position,
				new Vector2f(width / Gfx.WIDTH * 1000f, height / Gfx.HEIGHT * 1000f));
		shader.loadTransformation(matrix);
		shader.loadScreenDimensions((float) Display.getWidth(), (float) Display.getHeight());
		shader.loadOffsets(x, y);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
		end();
		shader.stop();

	}
	public void render(float x, float y, float width, float height, int textureID)
	{
		shader.start();
		shader.loadTransparency(1);//texture.getTransparency());
		prepare(textureID);
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
	public void render(float x, float y, float width, float height, int textureID, int mode)
	{
		shader.start();
		shader.loadTransparency(1);//texture.getTransparency());
		prepare(textureID, mode);
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
	public void render(float x, float y, float width, float height, int textureID, int depthTexture, int mode)
	{
		shader.start();
		shader.loadTransparency(1);//texture.getTransparency());
		prepare(textureID, mode);
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, depthTexture);
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
	public void renderManual(int texture)
	{
		shader.start();
		shader.loadTransparency(1);//texture.getTransparency());
		prepare(texture);

		Matrix4f matrix = Maths.createTransformationMatrix(new Vector2f(0, 0),
				new Vector2f(1, 1),
				new Vector2f(0, 0), 0);
		shader.loadTransformation(matrix);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
		end();

		shader.stop();

	}
	public void prepare(Texture2D texture)
	{
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTexture());
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	}
	public void prepare(int texture)
	{
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
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
