package engine.graphics.gui.font;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 2/12/2017.
 */
public class Character
{
    public static Map<String, Character> characters = new HashMap<String, Character>();
    private char character;
    private float yOffset;
    private float width;
    private float x;
    private float y;
    private float xOffset = 0;
    public Character(char c, float yOffset, float width, float x, float y)
    {
        character = c;
        this.yOffset = yOffset;
        this.width = width;
        this.x = x;
        this.y = y;
    }
    public Character(char c, float xOffset, float yOffset, float width, float x, float y)
    {
        character = c;
        this.yOffset = yOffset;
        this.width = width;
        this.x = x;
        this.y = y;
        this.xOffset = xOffset;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public float getWidth()
    {
        return width;
    }
    public float getYOffset()
    {
        return yOffset;
    }
    public char getCharacterAsChar()
    {
        return character;
    }
    public String getCharacterAsString()
    {
        return character + "";
    }
    public float getXOffset()
    {
        return xOffset;
    }
    public static void init()
    {
        characters.put(" ", new Character(' ', 0, 0.06f, 0, 12));
        characters.put("A", new Character('A', 0, 0.06f, 0, 0));
        characters.put("B", new Character('B', 0, 0.06f, 1, 0));
        characters.put("C", new Character('C', 0, 0.06f, 2, 0));
        characters.put("D", new Character('D', 0, 0.06f, 3, 0));
        characters.put("E", new Character('E', 0, 0.06f, 4, 0));
        characters.put("F", new Character('F', 0, 0.06f, 5, 0));
        characters.put("G", new Character('G', 0, 0.06f, 6, 0));
        characters.put("H", new Character('H', 0, 0.06f, 7, 0));
        characters.put("I", new Character('I', 0, 0.06f, 8, 0));
        characters.put("J", new Character('J', 0, 0.06f, 9, 0));
        characters.put("K", new Character('K', 0, 0.06f, 10, 0));
        characters.put("L", new Character('L', 0, 0.06f, 11, 0));
        characters.put("M", new Character('M', 0, 0.06f, 12, 0));
        characters.put("N", new Character('N', 0, 0.06f, 13, 0));
        characters.put("O", new Character('O', 0, 0.06f, 14, 0));
        characters.put("P", new Character('P', 0, 0.06f, 15, 0));
        characters.put("Q", new Character('Q', 0, 0.06f, 16, 0));
        characters.put("R", new Character('R', 0, 0.06f, 17, 0));
        characters.put("S", new Character('S', 0, 0.06f, 18, 0));
        characters.put("T", new Character('T', 0, 0.06f, 19, 0));
        characters.put("U", new Character('U', 0, 0.06f, 20, 0));
        characters.put("V", new Character('V', 0, 0.06f, 21, 0));
        characters.put("W", new Character('W', 0, 0.06f, 22, 0));
        characters.put("X", new Character('X', 0, 0.06f, 23, 0));
        characters.put("Y", new Character('Y', 0, 0.06f, 24, 0));
        characters.put("Z", new Character('Z', 0, 0.06f, 25, 0));

        characters.put("a", new Character('a', 0, 0.055f, 0, 2));
        characters.put("b", new Character('b', 0, 0.06f, 1, 2));
        characters.put("c", new Character('c', 0, 0.06f, 2, 2));
        characters.put("d", new Character('d', 0, 0.06f, 3, 2));
        characters.put("e", new Character('e', 0, 0.06f, 4, 2));
        characters.put("f", new Character('f', 0, 0.06f, 5, 2));
        characters.put("g", new Character('g', 0.7f, 0.06f, 6, 2));
        characters.put("h", new Character('h', 0, 0.06f, 7, 2));
        characters.put("i", new Character('i', 0.02f, 0, 0.01f, 8, 2));
        characters.put("j", new Character('j', 0, 0.06f, 9, 2));
        characters.put("k", new Character('k', 0, 0.06f, 10, 2));
        characters.put("l", new Character('l', 0.02f, 0, 0.015f, 11, 2));
        characters.put("m", new Character('m', 0, 0.06f, 12, 2));
        characters.put("n", new Character('n', 0, 0.06f, 13, 2));
        characters.put("o", new Character('o', 0, 0.06f, 14, 2));
        characters.put("p", new Character('p', -0.01f, 0.8f, 0.07f, 15, 2));
        characters.put("q", new Character('q', 0, 0.06f, 16, 2));
        characters.put("r", new Character('r', 0, 0.06f, 17, 2));
        characters.put("s", new Character('s', 0, 0.06f, 18, 2));
        characters.put("t", new Character('t', 0, 0.05f, 19, 2));
        characters.put("u", new Character('u', 0, 0.06f, 20, 2));
        characters.put("v", new Character('v', 0, 0.06f, 21, 2));
        characters.put("w", new Character('w', 0, 0.06f, 22, 2));
        characters.put("x", new Character('x', 0, 0.06f, 23, 2));
        characters.put("y", new Character('y', 0.00f, 0.75f, 0.06f, 24, 2));
        characters.put("z", new Character('z', 0, 0.06f, 25, 2));

        characters.put("0", new Character('0', 0, 0.06f, 0, 4));
        characters.put("1", new Character('1', 0, 0.06f, 1, 4));
        characters.put("2", new Character('2', 0, 0.06f, 2, 4));
        characters.put("3", new Character('3', 0, 0.06f, 3, 4));
        characters.put("4", new Character('4', 0, 0.06f, 4, 4));
        characters.put("5", new Character('5', 0, 0.06f, 5, 4));
        characters.put("6", new Character('6', 0, 0.06f, 6, 4));
        characters.put("7", new Character('7', 0, 0.06f, 7, 4));
        characters.put("8", new Character('8', 0, 0.06f, 8, 4));
        characters.put("9", new Character('9', 0, 0.06f, 9, 4));

        characters.put(".", new Character('.', 0, 0.02f, 0, 6));
        characters.put("!", new Character('!', 0, 0.02f, 1, 6));
        characters.put("#", new Character('#', 0, 0.02f, 2, 6));
        characters.put("$", new Character('$', 0, 0.02f, 3, 6));
        characters.put("%", new Character('%', 0, 0.02f, 4, 6));
        characters.put("^", new Character('^', 0, 0.02f, 5, 6));
        characters.put("&", new Character('&', 0, 0.02f, 6, 6));
        characters.put("*", new Character('*', 0, 0.02f, 7, 6));
        characters.put("(", new Character('(', 0, 0.02f, 8, 6));
        characters.put(")", new Character(')', 0, 0.02f, 9, 6));
        characters.put("-", new Character('-', 0, 0.06f, 10, 6));
        characters.put("+", new Character('+', 0, 0.02f, 11, 6));

        characters.put("/", new Character('/', 0, 0.02f, 0, 8));
        characters.put("\\", new Character('\\', 0, 0.02f, 0, 8));
        characters.put("|", new Character('|', 0, 0.02f, 2, 8));
        characters.put(":", new Character(':', 0, 0.02f, 3, 8));
        characters.put(";", new Character(';', 0, 0.02f, 4, 8));
        characters.put("'", new Character('\'', 0, 0.02f, 5, 8));

        characters.put("[", new Character('[', 0, 0.02f, 0, 10));
        characters.put("]", new Character(']', 0, 0.02f, 1, 10));
        characters.put("{", new Character('{', 0, 0.02f, 2, 10));
        characters.put("}", new Character('}', 0, 0.02f, 3, 10));
    }
}
