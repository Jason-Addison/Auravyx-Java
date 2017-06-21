package engine.graphics.postprocess;

import engine.graphics.gui.font.FontRenderer;
import engine.graphics.gui.image.ImageRenderer;
import engine.graphics.gui.shape.model.ModelRenderer;
import engine.graphics.gui.shape.quad3d.QuadRenderer;
import engine.graphics.gui.shape.rectangle.RectangleRenderer;
import engine.graphics.gui.shape.texturedquad.TexturedQuadRenderer;
import engine.graphics.particle.ParticleRenderer;
import engine.graphics.particle.onecolourpass.OneColourPassRenderer;
import engine.graphics.postprocess.combine.CombineRenderer;
import engine.graphics.postprocess.deferred.DeferredRenderer;
import engine.graphics.postprocess.depth.DepthRenderer;
import engine.graphics.postprocess.depthrender.DepthRenderRenderer;
import engine.graphics.postprocess.distance.DistanceRenderer;
import engine.graphics.postprocess.hBlur.HBlurRenderer;
import engine.graphics.postprocess.invert.InvertRenderer;
import engine.graphics.postprocess.vBlur.VBlurRenderer;

/**
 * Created by Owner on 2/16/2017.
 */
public class PostProcess
{
    public static InvertRenderer invertRenderer = new InvertRenderer();
    public static DepthRenderer depthRenderer = new DepthRenderer();
    public static HBlurRenderer hBlurRenderer = new HBlurRenderer();
    public static VBlurRenderer vBlurRenderer = new VBlurRenderer();
    public static ImageRenderer imageRenderer = new ImageRenderer();
    public static RectangleRenderer rectangleRenderer = new RectangleRenderer();
    public static FontRenderer fontRenderer = new FontRenderer();
    public static DistanceRenderer distanceRenderer = new DistanceRenderer();
    public static QuadRenderer quadRenderer = new QuadRenderer();
    public static ModelRenderer modelRenderer = new ModelRenderer();
    public static TexturedQuadRenderer texturedQuadRenderer = new TexturedQuadRenderer();
    public static DeferredRenderer deferredRenderer = new DeferredRenderer();
    public static CombineRenderer combineRenderer = new CombineRenderer();
    public static OneColourPassRenderer oneColourPassRenderer = new OneColourPassRenderer();
    public static DepthRenderRenderer depthRenderRenderer = new DepthRenderRenderer();
    public static ParticleRenderer particleRenderer = new ParticleRenderer();
}
