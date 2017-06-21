#version 140

in vec2 position;

out vec2 textureCoords;
out vec4 colourPass;
out vec4 backgroundcolourPass;

uniform mat4 transformationMatrix;
uniform vec2 offset;
uniform vec4 colour;
uniform float width;
uniform float height;
uniform float xOffset;
uniform float yOffset;
uniform vec4 backgroundcolour;

void main(void)
{
	textureCoords = vec2(position.x - 1, position.y - 1);
	textureCoords.y = position.y;
	gl_Position = transformationMatrix * vec4(position.x, position.y, 0.0, 1.0);
    gl_Position.x += (xOffset) / (width / 2);
    gl_Position.y += (yOffset) / (height / 2);
    gl_Position.y = -gl_Position.y;
	textureCoords = vec2((textureCoords.x + offset.x), textureCoords.y + offset.y);
	textureCoords /= 64;
	colourPass = colour;
	backgroundcolourPass = backgroundcolour;
}