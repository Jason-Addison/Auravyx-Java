package tools;

import engine.Loader;
import engine.game.Game;
import engine.graphics.model.Model;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Owner on 4/16/2017.
 */
public class OBJReader
{

    public static Model loadModel(String modelDir)
    {
        String[] splitOBJ = modelDir.split("\\\\");
        String objName = splitOBJ[splitOBJ.length - 1].split("\\.")[0];
        modelDir = modelDir.substring(0, modelDir.length() - 4) + ".obj";
        FileReader fis = null;
        try
        {
            fis = new FileReader(modelDir);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fis);
        Model model = null;

        //String[] data = Utilities.readStringFromFile(modelDir).split("\n");


        ArrayList<Vector3f> rawVertices = new ArrayList<>();
        ArrayList<Vector2f> rawTextureCoords = new ArrayList<>();
        ArrayList<Vector3f> rawNormals = new ArrayList<>();
        ArrayList<Float> verticesArray = new ArrayList<>();
        ArrayList<Float> texArray = new ArrayList<>();
        ArrayList<Float> normalsArray = new ArrayList<>();

        String mtlFile = modelDir.substring(0, modelDir.length() - (objName + ".obj").length() - 1) + "\\" + objName + ".mtl";
        ArrayList<Texture> textures = readMTLFile(mtlFile);
        ArrayList<OBJMaterial> objMaterials = new ArrayList<>();

        int length = 0;
        //Go through vertex data//
        int index = 0;

        try
        {
            while(true)
            {
                String line = reader.readLine();
                if(line == null)
                {
                    break;
                }
                if(line.startsWith("v "))
                {
                    //Add vertex
                    String[] vertexData = line.split(" ");
                    rawVertices.add(new Vector3f(Float.parseFloat(vertexData[1]), Float.parseFloat(vertexData[2]), Float.parseFloat(vertexData[3])));
                }

                //Go through texture data//
                if(line.startsWith("vt"))
                {
                    //Add tex coordinate
                    String[] texCoord = line.split(" ");
                    rawTextureCoords.add(new Vector2f(Float.parseFloat(texCoord[1]), Float.parseFloat(texCoord[2])));
                }

                //Go through normals data//

                if(line.startsWith("vn"))
                {
                    //Add normal
                    String[] normal = line.split(" ");
                    rawNormals.add(new Vector3f(Float.parseFloat(normal[1]), Float.parseFloat(normal[2]), Float.parseFloat(normal[3])));
                }
                if(line.startsWith("usemtl"))
                {
                    if(length != 0)
                    {
                        objMaterials.add(new OBJMaterial(textures.get(index).getTextureID(), textures.get(index).hasAlpha(), length));
                        length = 0;
                        index++;
                    }
                    String[] split = line.split(" ");
                }
                if(line.startsWith("f"))
                {
                    //Go through indices

                    String[] splitVertices = line.split(" ");
                    String[] vertex1 = splitVertices[1].split("/");
                    int v1v = Integer.parseInt(vertex1[0]);
                    int v1t = Integer.parseInt(vertex1[1]);
                    int v1n = Integer.parseInt(vertex1[2]);
                    String[] vertex2 = splitVertices[2].split("/");
                    int v2v = Integer.parseInt(vertex2[0]);
                    int v2t = Integer.parseInt(vertex2[1]);
                    int v2n = Integer.parseInt(vertex2[2]);
                    String[] vertex3 = splitVertices[3].split("/");
                    int v3v = Integer.parseInt(vertex3[0]);
                    int v3t = Integer.parseInt(vertex3[1]);
                    int v3n = Integer.parseInt(vertex3[2]);
                    verticesArray.add(rawVertices.get(v1v - 1).getX());
                    verticesArray.add(rawVertices.get(v1v - 1).getY());
                    verticesArray.add(rawVertices.get(v1v - 1).getZ());
                    verticesArray.add(rawVertices.get(v2v - 1).getX());
                    verticesArray.add(rawVertices.get(v2v - 1).getY());
                    verticesArray.add(rawVertices.get(v2v - 1).getZ());
                    verticesArray.add(rawVertices.get(v3v - 1).getX());
                    verticesArray.add(rawVertices.get(v3v - 1).getY());
                    verticesArray.add(rawVertices.get(v3v - 1).getZ());

                    texArray.add(rawTextureCoords.get(v1t - 1).getX());
                    texArray.add(rawTextureCoords.get(v1t - 1).getY());
                    texArray.add(rawTextureCoords.get(v2t - 1).getX());
                    texArray.add(rawTextureCoords.get(v2t - 1).getY());
                    texArray.add(rawTextureCoords.get(v3t - 1).getX());
                    texArray.add(rawTextureCoords.get(v3t - 1).getY());

                    normalsArray.add(rawNormals.get(v1n - 1).getX());
                    normalsArray.add(rawNormals.get(v1n - 1).getY());
                    normalsArray.add(rawNormals.get(v1n - 1).getZ());
                    normalsArray.add(rawNormals.get(v2n - 1).getX());
                    normalsArray.add(rawNormals.get(v2n - 1).getY());
                    normalsArray.add(rawNormals.get(v2n - 1).getZ());
                    normalsArray.add(rawNormals.get(v3n - 1).getX());
                    normalsArray.add(rawNormals.get(v3n - 1).getY());
                    normalsArray.add(rawNormals.get(v3n - 1).getZ());
                    length += 3;
                }
            }
        }


        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(length != 0 && textures.size() != 0)
        {
            objMaterials.add(new OBJMaterial(textures.get(index).getTextureID(), textures.get(index).hasAlpha(), length));
            length = 0;
            index++;
        }
        float[] vertices = new float[rawVertices.size()];
        float[] textureCoords = new float[rawTextureCoords.size()];
        float[] normals = new float[rawNormals.size()];

        return Loader.load3DModel(Utilities.floatListToArray(verticesArray), null, Utilities.floatListToArray(normalsArray), Utilities.floatListToArray(texArray), objMaterials);
    }

    private static ArrayList<Texture> readMTLFile(String dir)
    {
        FileReader fis = null;
        try
        {
            fis = new FileReader(dir);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        BufferedReader mtlReader = new BufferedReader(fis);
        ArrayList<Texture> materials = new ArrayList<>();
        try
        {
            while(true)
            {
                String line = mtlReader.readLine();
                if(line == null)
                {
                    break;
                }
                if(line.startsWith("map"))
                {
                    /*String[] split = line.split(" ");
                    for(int i = 0; i < 7; i++)
                    {
                        mtlReader.readLine();
                    }
                    String[] splitDir = mtlReader.readLine().split(" ");
                    String[] splitData = splitDir[1].split("\\\\");*/
                    String[] splitDir = line.split(" ");
                    String[] splitData = splitDir[1].split("\\\\");
                    Texture mat = Game.getFullTexture(splitData[splitData.length - 1].substring(0, splitData[splitData.length - 1].length() - 4));
                    materials.add(mat);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return materials;
    }


}
