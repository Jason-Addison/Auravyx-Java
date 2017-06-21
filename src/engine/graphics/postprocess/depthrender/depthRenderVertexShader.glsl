#version 400

in vec2 position;

out vec2 textureCoords;

uniform float width;
uniform float height;
uniform float xOffset;
uniform float yOffset;
uniform mat4 transformationMatrix;

void main(void)
{

	gl_Position = transformationMatrix * vec4(position.x, position.y, 0.0, 1.0);
    gl_Position.x += (xOffset) / (width / 2) - 1;
    gl_Position.y += (yOffset) / (height / 2) - 1;
    gl_Position.y = gl_Position.y;


    textureCoords = vec2(position.x * 1, position.y * 1);

    if(textureCoords.x < 0)
    {
        textureCoords.x = 0;
    }
    if(textureCoords.x > 1)
    {
        textureCoords.x = 1;
    }
    if(textureCoords.y < 0)
    {
        textureCoords.y = 0;
    }
    if(textureCoords.y > 1)
    {
        textureCoords.y = 1;
    }
    textureCoords = clamp(textureCoords, 0, 1f);

    if(gl_Position.x < -1)
    {
        //gl_Position.x = -1;
    }
    if(gl_Position.x > 1)
    {
        //gl_Position.x = 1;
    }
    if(gl_Position.y < -1)
    {
        //gl_Position.y = -1;
    }
    if(gl_Position.y > 1)
    {
        //gl_Position.y = 1;
    }
}