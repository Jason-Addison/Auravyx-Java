#version 140

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D texture2d;
void main(void)
{
	out_Color = texture(texture2d, vec2(textureCoords.x, textureCoords.y));
	out_Color.xyz = 1 - out_Color.xyz;
}