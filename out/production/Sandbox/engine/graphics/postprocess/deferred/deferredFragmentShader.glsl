#version 400
#extension GL_ARB_explicit_uniform_location : enable

in vec2 textureCoords;
in vec3 sunVector;

out vec4 out_Color;

layout(location = 0) uniform sampler2D albedo;
layout(location = 1) uniform sampler2D normal;
layout(location = 2) uniform sampler2D specular;
layout(location = 3) uniform sampler2D glow;
layout(location = 4) uniform sampler2D position;
layout(location = 5) uniform sampler2D depth;
layout(location = 7) uniform sampler2D settings;

float sunLighting(vec4 surfaceNormal)
{
    return dot(normalize(surfaceNormal.xyz), (sunVector));
}
float scale(float value, float minCurrent, float maxCurrent, float min, float max)
{
    return ((max - min) * (value - minCurrent) / (maxCurrent - minCurrent)) + min;
}
void main(void)
{
    vec4 albedoColour = texture(albedo, textureCoords);
    vec4 normalColour = texture(normal, textureCoords);
    vec4 settingsColour = texture(settings, textureCoords);
    vec4 depth = texture(depth, textureCoords);
    vec4 positionColour = texture(position, textureCoords);
    vec4 unnormalized = (normalColour * 2) - 1;
    vec4 sunColour = vec4(0, 0, 0, 1);
    float dottedNormal = 1 - sunLighting(unnormalized);
    if(settingsColour.x != 1 && unnormalized.x != 0 && unnormalized.y != 0 && unnormalized.z != 0 && depth.x != 0)
    {
        float sunBrightness = scale(dottedNormal, 0, 1, 0.45f, 0.8f);
        sunColour *= dottedNormal;
    	out_Color = vec4(albedoColour.x * (sunBrightness) + sunColour.x, albedoColour.y * (sunBrightness) + sunColour.y, albedoColour.z * (sunBrightness) + sunColour.z, 1);
    }
    else
    {
        out_Color = albedoColour;
    }
    if(length(positionColour.xyz) < 0.1f)
    {
       // out_Color = vec4(1, 0, 0, 1);
    }
}