#version 400
#extension GL_ARB_explicit_uniform_location : enable
in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D texture2d;
uniform float transparency;
void main()
{
	out_Color = texture2D(texture2d, vec2(textureCoords.x, textureCoords.y));
    if(out_Color.w == 0 || textureCoords.y > 1 || textureCoords.y < 0)
    {
        discard;
    }
}