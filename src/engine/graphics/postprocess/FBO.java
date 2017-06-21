package engine.graphics.postprocess;

import engine.graphics.Gfx;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Owner on 2/14/2017.
 */
public class FBO
{

    private int width = 0;
    private int height = 0;


    private int frameBuffer;
    private int texture;
    private int depthBuffer;
    private int depthTexture;
    private int colourBuffer1;
    private int colourBuffer2;
    private int colourBuffer3;
    private int colourBuffer4;
    private int colourBuffer5;
    private int colourBuffer6;
    private int colourBuffer7;
    private int colourBuffer8;
    private int colourFormat = GL11.GL_RGBA16;

    public FBO(int width, int height)
    {
        this.width = width;
        this.height = height;
        init();
    }
    public FBO(int width, int height, int colourFormat)
    {
        this.width = width;
        this.height = height;
        this.colourFormat = colourFormat;

        init();
    }

    public void destroy()
    {
        GL30.glDeleteFramebuffers(frameBuffer);
        GL11.glDeleteTextures(texture);
        GL11.glDeleteTextures(depthTexture);
        GL30.glDeleteRenderbuffers(depthBuffer);
        GL30.glDeleteRenderbuffers(texture);
        GL30.glDeleteRenderbuffers(depthTexture);
        GL30.glDeleteRenderbuffers(colourBuffer1);
        GL30.glDeleteRenderbuffers(colourBuffer2);
        GL30.glDeleteRenderbuffers(colourBuffer3);
        GL30.glDeleteRenderbuffers(colourBuffer4);
        GL30.glDeleteRenderbuffers(colourBuffer5);
        GL30.glDeleteRenderbuffers(colourBuffer6);
        GL30.glDeleteRenderbuffers(colourBuffer7);
        GL30.glDeleteRenderbuffers(colourBuffer8);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public void bind()
    {
        bindFrameBuffer(frameBuffer, width, height);
    }

    public void unbind()
    {
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
    }

    public int getTexture()
    {
        return texture;
    }
    public int getDepthBuffer()
    {
        return depthBuffer;
    }
    public int getDepthTexture()
    {
        return depthTexture;
    }
    public void resolve(int readBuffer, FBO output)
    {
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, output.frameBuffer);
        GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, this.frameBuffer);
        GL11.glReadBuffer(readBuffer);
        GL30.glBlitFramebuffer(0, 0, width, height, 0, 0, output.width, output.height, GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT, GL11.GL_NEAREST);
        unbind();
    }

    private void init()
    {
        frameBuffer = createFrameBuffer();

        colourBuffer1 = createUIColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT0);
        colourBuffer2 = createUIColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT1);
        colourBuffer3 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT2);
        colourBuffer4 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT3);
        colourBuffer5 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT4);
        colourBuffer6 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT5);
        colourBuffer7 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT6);
        colourBuffer8 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT7);

        texture = createTextureAttachment(width, height);
        depthBuffer = createDepthBufferAttachment(width, height);
        depthTexture = createDepthTextureAttachment(width, height);
        unbind();
    }

    private void bindFrameBuffer(int frameBuffer, int width, int height)
    {
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
        GL11.glViewport(0, 0, width, height);
    }

    private int createFrameBuffer()
    {
        int frameBuffer = GL30.glGenFramebuffers();
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
        IntBuffer buffers = BufferUtils.createIntBuffer(8);
        buffers.put(GL30.GL_COLOR_ATTACHMENT0);
        buffers.put(GL30.GL_COLOR_ATTACHMENT1);
        buffers.put(GL30.GL_COLOR_ATTACHMENT2);
        buffers.put(GL30.GL_COLOR_ATTACHMENT3);
        buffers.put(GL30.GL_COLOR_ATTACHMENT4);
        buffers.put(GL30.GL_COLOR_ATTACHMENT5);
        buffers.put(GL30.GL_COLOR_ATTACHMENT6);
        buffers.put(GL30.GL_COLOR_ATTACHMENT7);
        buffers.flip();
        GL20.glDrawBuffers(buffers);
        return frameBuffer;
    }

    private int createTextureAttachment(int width, int height)
    {
        int texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, colourFormat, width, height, 0, GL11.GL_RGBA, GL11.GL_INT, (IntBuffer) null);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, texture, 0);
        return texture;
    }

    private int createDepthTextureAttachment(int width, int height)
    {
        int texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT32, width, height,0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (FloatBuffer) null);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, texture, 0);
        return texture;
    }

    private int createDepthBufferAttachment(int width, int height)
    {
        int depthBuffer = GL30.glGenRenderbuffers();
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, depthBuffer);
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, width, height);
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER, depthBuffer);
        return depthBuffer;
    }

    private int createColourBufferAttachment(int attachment)
    {
        int colourBuffer = GL30.glGenRenderbuffers();
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, colourBuffer);
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_RGBA16, width, height);
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment, GL30.GL_RENDERBUFFER, colourBuffer);
        return colourBuffer;
    }
    private int createUIColourBufferAttachment(int attachment)
    {
        int colourBuffer = GL30.glGenRenderbuffers();
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, colourBuffer);
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_RGBA, width, height);
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment, GL30.GL_RENDERBUFFER, colourBuffer);
        return colourBuffer;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
    /*@Deprecated public void update()
    {

        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
        texture = createTextureAttachment(width, height);
        depthBuffer = createDepthBufferAttachment(width, height);
        depthTexture = createDepthTextureAttachment(width, height);
        colourBuffer1 = createUIColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT0);
        colourBuffer2 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT1);
        colourBuffer3 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT2);
        colourBuffer4 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT3);
        colourBuffer5 = createColourBufferAttachment(GL30.GL_COLOR_ATTACHMENT4);
        unbind();
    }*/
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public int getFrameBuffer()
    {
        return frameBuffer;
    }
    public void invertColours()
    {
        PostProcess.invertRenderer.render(this.getTexture(), this);
    }
    public void visualizeDepth()
    {
        PostProcess.depthRenderer.render(this.getDepthTexture(), this);
    }
    public void fadeDistance()
    {
        PostProcess.distanceRenderer.render(this.getTexture(), Gfx.TO_CAMERA_FBO.getTexture(), this);
    }

    static FBO blur = new FBO(Display.getWidth() / 4, Display.getHeight() / 4);
    static FBO blur2 = new FBO(Display.getWidth() / 32, Display.getHeight() / 32);
    static float blurAmount;
    public void blur(float amount)
    {
        if(amount != blurAmount)
        {
            blurAmount = amount;
            blur.destroy();
            blur = new FBO((int) (Gfx.WIDTH / (amount / 8)), (int) (Gfx.HEIGHT / (amount / 8)));
            blur2.destroy();
            blur2 = new FBO((int) (Gfx.WIDTH / (amount)), (int) (Gfx.HEIGHT / (amount)));
        }
        blur.bind();
        clear();
        PostProcess.imageRenderer.render(this.getTexture());
        blur.unbind();
        blur.hBlur();
        blur.vBlur();
        blur2.bind();
        clear();
        PostProcess.imageRenderer.render(blur.getTexture());
        blur2.unbind();
        blur2.hBlur();
        blur2.vBlur();
        blur.bind();
        PostProcess.imageRenderer.render(blur2.getTexture());
        blur.unbind();
        blur.hBlur();
        blur.vBlur();
        bind();
        PostProcess.imageRenderer.render(blur.getTexture());
        unbind();
    }
    public void hBlur()
    {
        PostProcess.hBlurRenderer.render(this.getTexture(), this);
    }
    public void vBlur()
    {
        PostProcess.vBlurRenderer.render(this.getTexture(), this);
    }
    public void render()
    {
        PostProcess.imageRenderer.render(0, 0, Gfx.WIDTH, Gfx.HEIGHT, this.getTexture());
    }
    public void render(float x, float y, float width, float height)
    {
        PostProcess.imageRenderer.render(x, Gfx.HEIGHT - height - y, width / 1000, height / 1000, texture);
    }
    public void updateFBO()
    {
        //if(Display.wasResized() || width != Gfx.WIDTH || height != Gfx.WIDTH)
        {
            width = Display.getWidth();
            height = Display.getHeight();
            /*blur.destroy();
            blur = new FBO(Display.getWidth() / 4, Display.getHeight() / 4);
            blur2.destroy();
            blur2 = new FBO(Display.getWidth() / 8, Display.getHeight() / 8);*/
            destroy();
            init();
        }
    }
    public void updateFBO(int xScale, int yScale)
    {
        if(Display.wasResized() || width != (int) (Gfx.WIDTH / xScale) || height != (int) (Gfx.HEIGHT / yScale))
        {
            width = (int) (Gfx.WIDTH / xScale);
            height = (int) (Gfx.HEIGHT / yScale);
            blur.destroy();
            blur = new FBO((int) (Gfx.WIDTH / (blurAmount / 2)), (int) (Gfx.HEIGHT / (blurAmount / 2)));
            blur2.destroy();
            blur2 = new FBO((int) (Gfx.WIDTH / (blurAmount / 1)), (int) (Gfx.HEIGHT / (blurAmount / 1)));
            destroy();
            init();
        }
    }
    public Vector3f getColour(int x, int y)
    {
        bind();
        GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0);
        FloatBuffer pix = BufferUtils.createFloatBuffer(3 *65);
        GL11.glReadPixels(x, y, 1, 1, GL11.GL_RGBA, GL11.GL_FLOAT, pix);
        float r = pix.get(0);
        float g = pix.get(1);
        float b = pix.get(2);
        pix.flip();
        pix = null;
        return new Vector3f(r, g, b);
    }
    public int getColourAsInt(int x, int y)
    {
        bind();
        GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0);
        IntBuffer pix = BufferUtils.createIntBuffer(3 * 64);
        GL11.glReadPixels(x, y, 1, 1, GL30.GL_RGBA_INTEGER, GL11.GL_UNSIGNED_INT, pix);
        int r = pix.get(0);
        int g = pix.get(1);
        int b = pix.get(2);
        pix.flip();
        pix = null;
        unbind();
        return r;
    }
    public void clear()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0, 0, 0, 0);
    }
    public void setDepthInfo(int depthTexture, int depthBuffer)
    {
        this.depthTexture = depthTexture;
        this.depthBuffer = depthBuffer;
    }
}
