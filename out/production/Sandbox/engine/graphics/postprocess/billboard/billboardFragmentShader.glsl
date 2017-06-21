#version 400 core

#extension GL_EXT_gpu_shader4 : require
#extension GL_ARB_explicit_uniform_location : enable

in vec2 textureCoordsPass;

layout (location = 0) out vec4 out_Color;
layout (location = 7) out vec4 out_Settings;

layout(location = 0) uniform sampler2D billboardTexture;

void main(void)
{
    out_Color = vec4(1, 0, 0, 1);
    out_Settings = vec4(1, 0, 0, 1);
}