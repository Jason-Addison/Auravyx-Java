#version 400 core

//in float visibility;

out vec4 out_Color;
in vec2 textureCoords;
uniform sampler2D texture2d;

void main(void)
{

    out_Color = texture(texture2d, textureCoords);
}