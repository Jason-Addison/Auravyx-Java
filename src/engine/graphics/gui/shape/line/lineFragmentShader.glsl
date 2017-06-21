#version 400

in vec4 colourPass;
out vec4 out_Color;

uniform float transparency;

void main()
{
	out_Color = colourPass;
}