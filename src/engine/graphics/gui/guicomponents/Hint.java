package engine.graphics.gui.guicomponents;

/**
 * Created by Owner on 3/31/2017.
 */
public class Hint
{

    float r, g, b, a;
    float bR, bG, bB, bA;
    String hintText = "NoHintFound!";
    public Hint(String hint, float r, float g, float b, float a, float bR, float bG, float bB, float bA)
    {
        /*
            Normal RGBA values represent text colour
            RGBA with b as a prefix represent the background colour of the hint box
         */
        this.hintText = hint;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.bR = bR;
        this.bG = bG;
        this.bB = bB;
        this.bA = bA;
    }

}
