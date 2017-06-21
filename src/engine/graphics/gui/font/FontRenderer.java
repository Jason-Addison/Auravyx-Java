package engine.graphics.gui.font;

import engine.Loader;
import engine.graphics.Gfx;
import engine.graphics.model.Model;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import tools.Maths;

/**
 * Created by Owner on 2/12/2017.
 */
public class FontRenderer
{
    private final Model quad;
    private static FontShader shader;
    public static int texID = 0;

    public FontRenderer()
    {
        float[] SQUARE_ARRAY = {0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0};
        float[] positions = {0, 0, 0, 2, 2, 0, 2, 2, 0, 2, 2, 0};
        quad = Loader.load2DModel(positions);
        shader = new FontShader();
    }
    //DrawableString string = new DrawableString("", 0, 0, 0.03f);
    /*public static ArrayList<DrawableString> stringsToDraw = new ArrayList<DrawableString>();
    public void render()
    {
        shader.start();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        for(int j = 0; j < stringsToDraw.size(); j++)
        {
            string.setString(stringsToDraw.get(j).getString());
            string.setSize(stringsToDraw.get(j).getSize());
            string.setPosition(stringsToDraw.get(j).getX(), stringsToDraw.get(j).getY());
            string.setFitToScreen(stringsToDraw.get(j).isFitToScreen());
            shader.loadColour(stringsToDraw.get(j).getR(), stringsToDraw.get(j).getG(),
                    stringsToDraw.get(j).getB(), stringsToDraw.get(j).getA());
            shader.loadBackgroundColour(stringsToDraw.get(j).getbR(), stringsToDraw.get(j).getbG(),
                    stringsToDraw.get(j).getbB(), stringsToDraw.get(j).getbA());
            for(int i = 0; i < string.getString().length(); i++)
            {
                String characterFromString = "" + string.getString().charAt(i);
                Character character = Character.characters.get(characterFromString);
                GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                GL13.glActiveTexture(GL13.GL_TEXTURE0);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);

                String beforeString = string.getString().substring(0, i + 0);
                float widthBefore = 0;

                for(int k = 0; k < beforeString.length(); k++)
                {
                    widthBefore += Character.characters.get(beforeString.charAt(k) + "").getWidth();
                }
                widthBefore -= character.getXOffset();
                Matrix4f matrix = null;
                if(string.getX() < 0)
                {
                    matrix = Maths.createTransformationMatrix(new Vector2f((float) ((float) Display.getWidth() / (string.getSize() * 1000))
                                    + string.getX() + (widthBefore * 30), -string.getY() + (float) (float) Display.getHeight() / (string.getSize() * 1000)
                                    - (character.getYOffset())),
                            new Vector2f(string.getSize() / (float) Display.getWidth() * 1000, string.getSize() / (float) Display.getHeight() * 1000));
                }
                else
                {
                    matrix = Maths.createTransformationMatrix(new Vector2f((float) ((float) -Display.getWidth() / (string.getSize() * 1000))
                                    + 0 + (widthBefore * 30), -0 + (float) (float) Display.getHeight() / (string.getSize() * 1000)
                                    - (character.getYOffset())),
                            new Vector2f(string.getSize() / (float) Display.getWidth() * 1000, string.getSize() / (float) Display.getHeight() * 1000));
                }
                Vector2f position = new Vector2f((-Gfx.WIDTH / (string.getSize() * 1000f)) + (widthBefore * 30), (-Gfx.HEIGHT / (string.getSize() * 1000f)) + character.getYOffset());
                matrix = Maths.createTransformationMatrix(position,
                        new Vector2f(string.getSize() / Gfx.WIDTH * 1000f, string.getSize() / Gfx.HEIGHT * 1000f));
                shader.loadTransformation(matrix);
                shader.loadOffset(character.getX() * 2 + 1,character.getY() * 2);
                shader.loadScreenDimensions((float) Display.getWidth(), (float) Display.getHeight());
                shader.loadOffsets(string.getX(), string.getY());
                GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
            }
        }
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
        stringsToDraw.clear();
    }*/
    public void render(DrawableString string)
    {
        shader.start();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        shader.loadScreenDimensions((float) Display.getWidth(), (float) Display.getHeight());
        shader.loadColour(string.getR(), string.getG(), string.getB(), string.getA());
        shader.loadOffsets(string.getX(), string.getY());
        for(int i = 0; i < string.getString().length(); i++)
        {
            String characterFromString = "" + string.getString().charAt(i);
            Character character = Character.characters.get(characterFromString);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            String beforeString = string.getString().substring(0, i + 0);
            float widthBefore = 0;

            for(int k = 0; k < beforeString.length(); k++)
            {
                widthBefore += Character.characters.get(beforeString.charAt(k) + "").getWidth();
            }
            widthBefore -= character.getXOffset();
            Matrix4f matrix = null;

            if(string.getX() < 0)
            {
                matrix = Maths.createTransformationMatrix(new Vector2f((float) Display.getWidth() / (string.getSize() * 1000)
                                + string.getX() + (widthBefore * 30), -string.getY() + (float) Display.getHeight() / (string.getSize() * 1000)
                                - (character.getYOffset())),
                        new Vector2f((int) (string.getSize() / (float) Display.getWidth() * 1000), (int) (string.getSize() / (float) Display.getHeight() * 1000)));
            }
            else
            {
                matrix = Maths.createTransformationMatrix(new Vector2f((float) -Display.getWidth() / (string.getSize() * 1000)
                                + 0 + (widthBefore * 30), -0 + (float) Display.getHeight() / (string.getSize() * 1000)
                                - (character.getYOffset())),
                        new Vector2f(string.getSize() / (float) Display.getWidth() * 1000, string.getSize() / (float) Display.getHeight() * 1000));
            }
            Vector2f position = new Vector2f((-Gfx.WIDTH / (string.getSize() * 1000f)) + (widthBefore * 30), (-Gfx.HEIGHT / (string.getSize() * 1000f)) + character.getYOffset());
            matrix = Maths.createTransformationMatrix(position,
                    new Vector2f( (string.getSize() / Gfx.WIDTH * 1000f), (string.getSize() / Gfx.HEIGHT * 1000f)));
            shader.loadTransformation(matrix);
            shader.loadOffset(character.getX() * 2 + 1,character.getY() * 2);
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
        }
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }
    public void getOffset()
    {

    }


    public void cleanUp()
    {
        shader.cleanUp();
    }
}
