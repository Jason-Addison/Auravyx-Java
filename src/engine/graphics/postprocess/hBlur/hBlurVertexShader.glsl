#version 140

in vec2 position;

out vec2 textureCoords;
out vec2 blurTextureCoords[11];

uniform float width;
void main(void)
{
	gl_Position = vec4(position, 0.0, 1.0);
    vec2 centerTexCoords = position * 0.5 + 0.5;
    float pixelSize = 1.0 / width;
    textureCoords = vec2((position.x + 1.0) / 2.0, 1 - (position.y + 1.0) / 2.0);
    textureCoords.y = 1 - textureCoords.y;

    for(int i = 0 -5; i < 5; i++)
    {
        blurTextureCoords[i+5] = centerTexCoords + vec2(pixelSize * i, 0.0);
        /*if(blurTextureCoords[i+5].x > 1)
        {
            blurTextureCoords[i+5].x = 1 - (blurTextureCoords[i+5].x - 1);
        }
        if(blurTextureCoords[i + 5].x < 0)
        {
            blurTextureCoords[i + 5].x = 0 - (blurTextureCoords[i+5].x);
        }*/
    }
}