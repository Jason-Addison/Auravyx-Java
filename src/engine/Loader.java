package engine;

import engine.game.Game;
import engine.graphics.model.Model;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import tools.OBJMaterial;
import tools.OBJReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2/12/2017.
 */
public class Loader
{
    public static List<Integer> vaos = new ArrayList<Integer>();
    public static List<Integer> vbos = new ArrayList<Integer>();
    private static List<Integer> textures = new ArrayList<Integer>();

    public static Model load2DModel(float[] positions)
    {
        int vao = createVAO();
        storeDataInAttributeList(0, 2, positions);
        GL30.glBindVertexArray(0);
        return new Model(vao, positions.length);
    }
    public static Model load2DModel(float[] positions, float[] textureCoords)
    {
        int vao = createVAO();
        storeDataInAttributeList(0, 2, positions);
        if(textureCoords != null)
        {
            storeDataInAttributeList(1, 2, textureCoords);
        }
        GL30.glBindVertexArray(0);
        return new Model(vao, positions.length);
    }
    public static Model load3DModel(float[] positions, float[] normals)
    {
        int vao = createVAO();
        Model model = new Model(vao, positions.length);

        if(positions != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(0, 3, positions));
        }
        if(normals != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(1, 3, normals));
        }
        GL30.glBindVertexArray(0);
        return model;
    }
    public static Model load3DModel(float[] positions, float[] colours, float[] normals)
    {
        int vao = createVAO();
        Model model = new Model(vao, positions.length);
        if(positions != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(0, 3, positions));
        }
        if(normals != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(1, 3, normals));
        }
        if(colours != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(2, 3, colours));
        }
        GL30.glBindVertexArray(0);
        return model;
    }
    public static Model load3DModel(float[] positions, float[] colours, float[] normals, float[] textureCoords)
    {
        int vao = createVAO();
        Model model = new Model(vao, positions.length);
        if(positions != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(0, 3, positions));
        }
        if(normals != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(1, 3, normals));
        }
        if(colours != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(2, 3, colours));
        }
        if(textureCoords != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(3, 2, textureCoords));
        }
        GL30.glBindVertexArray(0);
        return model;
    }
    public static Model load3DModel(float[] positions, float[] colours, float[] normals, float[] textureCoords, ArrayList<OBJMaterial> materials)
    {
        int vao = createVAO();
        Model model = new Model(vao, positions.length);
        if(positions != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(0, 3, positions));
        }
        if(normals != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(1, 3, normals));
        }
        if(colours != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(2, 3, colours));
        }
        if(textureCoords != null)
        {
            model.linkVertexAttribute(storeDataInAttributeList(3, 2, textureCoords));
        }
        model.setMaterials(materials);
        GL30.glBindVertexArray(0);
        return model;
    }

    /*public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices, float[] colours)
    {
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, textureCoords);
        storeDataInAttributeList(2, 3, normals);
        storeDataInAttributeList(3, 3, colours);
        unbindVAO();
        RawModel model = new RawModel(vaoID, indices.length);
        return model;
    }
    public RawModel loadToVAO(float[] positions, float[] colours)
    {
        int vaoID = createVAO();
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 4, colours);
        unbindVAO();
        RawModel model = new RawModel(vaoID, positions.length / 3);
        return model;
    }

    public RawModel loadToVAO(float[] positions, float[] normals, float[] colours)
    {
        int vaoID = createVAO();
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(2, 3, normals);
        storeDataInAttributeList(3, 4, colours);
        unbindVAO();
        RawModel model = new RawModel(vaoID, positions.length / 3);
        return model;
    }
    public RawModel loadToVao(float[] positions, float[] colours)
    {
        int vaoID = createVAO();
        this.storeDataInAttributeList(0, 2, positions);
        this.storeDataInAttributeList(1, 3, colours);
        unbindVAO();
        return new RawModel(vaoID, positions.length / 3);
    }
    public RawModel loadToVao(float[] positions)
    {
        int vaoID = createVAO();
        this.storeDataInAttributeList(0, 2, positions);
        unbindVAO();
        return new RawModel(vaoID, positions.length / 2);
    }

    public RawModel loadToVAOWithID(float[] positions, float[] normals, float[] colours, float[] geomipmap, int vboID, int vboNormalID, int vboPositionID, int vboGMMID, int vaoID)
    {
        GL30.glBindVertexArray(vaoID);
        storeDataInAttributeListWithID(0, 3, positions, vboPositionID);
        storeDataInAttributeListWithID(2, 3, normals, vboNormalID);
        storeDataInAttributeListWithID(3, 3, colours, vboID);
        storeDataInAttributeListWithID(4, 1, geomipmap, vboGMMID);
        unbindVAO();
        RawModel model = new RawModel(vaoID, positions.length / 3);
        positions = null;
        normals = null;
        colours = null;
        return model;
    }

    public RawModel loadToVAOWithID(float[] positions, float[] ajVertex1, float[] ajVertex2, float[] colours, float[] depth,
                                    int vboID, int vboPositionID, int vboDepthID, int vboAdj1ID, int vboAdj2ID, int vaoID)
    {
        GL30.glBindVertexArray(vaoID);
        storeDataInAttributeListWithID(0, 3, positions, vboPositionID);
        storeDataInAttributeListWithID(2, 1, depth, vboDepthID);
        storeDataInAttributeListWithID(3, 3, colours, vboID);
        storeDataInAttributeListWithID(4, 3, ajVertex1, vboAdj1ID);
        storeDataInAttributeListWithID(5, 3, ajVertex2, vboAdj2ID);
        unbindVAO();
        RawModel model = new RawModel(vaoID, positions.length / 3);
        positions = null;
        ajVertex1 = null;
        ajVertex2 = null;
        colours = null;
        depth = null;
        return model;
    }*/

    public static Texture loadTexture(String fileName)
    {
        Texture texture = null;
        try
        {
            //FileInputStream fis = new FileInputStream("res/" + fileName+".png");
            FileInputStream fis = new FileInputStream(fileName);
            texture = TextureLoader.getTexture("PNG", fis);
            fis.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        int textureID = texture.getTextureID();
        textures.add(textureID);

        return texture;
    }
    public int loadFont(String fileName)
    {
        Texture texture = null;
        try
        {
            FileInputStream fis = new FileInputStream(Game.GAME_DIR + "/source/font/" + fileName + ".png");
            texture = TextureLoader.getTexture("PNG", fis);
            fis.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        int textureID = texture.getTextureID();
        textures.add(textureID);

        return textureID;
    }

    public static void cleanUp()
    {
        for(int vao : vaos)
        {
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo : vbos)
        {
            GL15.glDeleteBuffers(vbo);
        }
        //for(int texture : textures)
        {
            //WGL11.glDeleteTextures(texture);
        }
    }

    public static void deleteVAO(int id)
    {
        GL30.glDeleteVertexArrays(id);
    }
    public static void deleteVBO(int id)
    {
        GL15.glDeleteBuffers(id);
    }

    public static int createVAO()
    {
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }
    public int createVAOWithID(int id)
    {
        GL30.glBindVertexArray(id);
        return id;
    }
    private static int storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data)
    {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        buffer = null;
        return vboID;
    }
    private void storeDataInAttributeListWithID(int attributeNumber, int coordinateSize, float[] data, int vboID)
    {
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        buffer = null;
    }
    private void unbindVAO()
    {
        GL30.glBindVertexArray(0);
    }
    private IntBuffer storeDataInIntBuffer(int[] data)
    {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    private void bindIndicesBuffer(int[] indices)
    {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STREAM_DRAW);
    }

    private static FloatBuffer storeDataInFloatBuffer(float[] data)
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    public static float[] generateNormals(float[] vertices)
    {
        float[] normals = new float[vertices.length];
        for(int i = 0; i < vertices.length / 9; i++)
        {
            Vector3f vec1 = new Vector3f(vertices[i * 9 + 0], vertices[i * 9 + 1], vertices[i * 9 + 2]);
            Vector3f vec2 = new Vector3f(vertices[i * 9 + 3], vertices[i * 9 + 4], vertices[i * 9 + 5]);
            Vector3f vec3 = new Vector3f(vertices[i * 9 + 6], vertices[i * 9 + 7], vertices[i * 9 + 8]);

            Vector3f newNormals = vectorToNormal(vec1, vec2, vec3);

            newNormals.normalise();
            normals[i * 9 + 0] = newNormals.x;
            normals[i * 9 + 1] = newNormals.y;
            normals[i * 9 + 2] = newNormals.z;
            normals[i * 9 + 3] = newNormals.x;
            normals[i * 9 + 4] = newNormals.y;
            normals[i * 9 + 5] = newNormals.z;
            normals[i * 9 + 6] = newNormals.x;
            normals[i * 9 + 7] = newNormals.y;
            normals[i * 9 + 8] = newNormals.z;
        }
        return normals;
    }
    public static Vector3f vectorToNormal(Vector3f ver1, Vector3f ver2, Vector3f ver3)
    {
        Vector3f normal = new Vector3f();
        Vector3f v = new Vector3f(ver2.x - ver1.x, ver2.y - ver1.y, ver2.z - ver1.z);
        Vector3f w = new Vector3f(ver3.x - ver1.x, ver3.y - ver1.y, ver3.z - ver1.z);

        Vector3f n = new Vector3f();

        n.x = (v.y * w.z) - (v.z * w.y);
        n.y = (v.z * w.x) - (v.x * w.z);
        n.z = (v.x * w.y) - (v.y * w.x);


        normal.x = n.x / (Math.abs(n.x) + (Math.abs(n.y) + Math.abs(n.z)));
        normal.y = n.y / (Math.abs(n.x) + (Math.abs(n.y) + Math.abs(n.z)));
        normal.z = n.z / (Math.abs(n.x) + (Math.abs(n.y) + Math.abs(n.z)));
        return normal;
    }
    public static void loadAllModels()
    {
        ArrayList<String> textures = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();
        File assetDir = new File(Game.GAME_DIR + "assests\\");

        File[] allFiles = assetDir.listFiles();
        files.addAll(directoryCheck(allFiles, "blp"));
        for(File file : files)
        {
            String name = file.getName().substring(0, file.getName().length() - 4);
            Game.addModel(OBJReader.loadModel(file.getAbsolutePath()), name);
        }
    }
    public static void loadAllTextures()
    {
        ArrayList<String> textures = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();
        File assetDir = new File(Game.GAME_DIR + "assests\\");

        File[] allFiles = assetDir.listFiles();
        files.addAll(directoryCheck(allFiles, "png"));
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        for(File file : files)
        {
            Game.textures.put(file.getName().substring(0, file.getName().length() - 4), loadTexture(file.getAbsolutePath()));
            textures.add(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4));
        }
    }
    public static ArrayList<File> directoryCheck(File[] allFiles, String fileType)
    {
        ArrayList<File> textures = new ArrayList<>();
        for(File file : allFiles)
        {
            if(file.isDirectory())
            {
                File[] filesWithin = file.listFiles();
                textures.addAll(directoryCheck(filesWithin, fileType));
            }
            if(file.getName().endsWith("." + fileType))
            {
                textures.add(file);
            }
        }
        return textures;
    }
}
