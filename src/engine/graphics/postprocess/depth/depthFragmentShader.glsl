#version 140

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D texture2d;
uniform float zFar;
void main(void)
{
	out_Color = texture(texture2d, vec2(textureCoords.x, textureCoords.y));
	out_Color.x = zFar - (out_Color.x * zFar);
	//out_Color.x = 1 - out_Color.x;
    out_Color = vec4(out_Color.x, out_Color.x, out_Color.x, 1.0);
}