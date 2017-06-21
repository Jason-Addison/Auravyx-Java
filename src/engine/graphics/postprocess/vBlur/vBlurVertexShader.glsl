#version 400

in vec2 position;

out vec2 textureCoordsKernel[11];
out vec2 textureCoords;
uniform float height;
void main(void)
{
	gl_Position = vec4(position, 0.0, 1.0);
    vec2 centerOfTexture = position * 0.5 + 0.5;
    float pixelSpace = 1.0 / height;

    for(int i = 0 -5; i < 5; i++)
    {
        textureCoordsKernel[i+5] = centerOfTexture + vec2(0.0, pixelSpace * i);
        /*if(textureCoordsKernel[i + 5].y >= 1)
        {
           textureCoordsKernel[i + 5].y = 1;
        }
        if(textureCoordsKernel[i + 5].y <= 0)
        {
            textureCoordsKernel[i + 5].y = 0;
        }
        if(textureCoordsKernel[i + 5].x >= 1)
        {
           textureCoordsKernel[i + 5].x = 1;
        }
        if(textureCoordsKernel[i + 5].x <= 0)
        {
            textureCoordsKernel[i + 5].x = 0;
        }*/
    }
}