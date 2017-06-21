#version 400
#extension GL_ARB_explicit_uniform_location : enable
in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D texture2d;
uniform layout(location = 1) sampler2D depth;
uniform layout(location = 2) sampler2D scene;
uniform float transparency;
void main()
{
	vec4 depthTexture = texture2D(depth, vec2(textureCoords.x, textureCoords.y));
	vec4 sceneDepth = texture2D(scene, vec2(textureCoords.x, textureCoords.y));
    if(length(depthTexture.xyz) < length(sceneDepth.xyz))
    {
        discard;
    }
    out_Color = texture2D(texture2d, textureCoords);
    if(out_Color.w == 0 || textureCoords.y > 1 || textureCoords.y < 0)
    {
        discard;
    }
}