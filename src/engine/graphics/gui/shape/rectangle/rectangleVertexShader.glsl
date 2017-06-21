#version 400

in vec2 position;

out vec4 colourPass;

uniform mat4 transformationMatrix;
uniform float width;
uniform float height;
uniform float xOffset;
uniform float yOffset;
uniform vec4 colour;

void main(void)
{
	gl_Position = transformationMatrix * vec4(position.x, position.y, 0.0, 1.0);
    gl_Position.x += (xOffset) / (width / 2);
    gl_Position.y += (yOffset) / (height / 2);
    gl_Position.y = -gl_Position.y;

    colourPass = colour;
}