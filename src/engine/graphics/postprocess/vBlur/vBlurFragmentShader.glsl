#version 140

in vec2 textureCoordsKernel[11];
out vec4 out_Color;

uniform sampler2D texture2d;
void main(void)
{
	out_Color = vec4(0.0);
    out_Color += texture(texture2d, textureCoordsKernel[0]) * 0.0093;
    out_Color += texture(texture2d, textureCoordsKernel[1]) * 0.028002;
    out_Color += texture(texture2d, textureCoordsKernel[2]) * 0.065984;
    out_Color += texture(texture2d, textureCoordsKernel[3]) * 0.121703;
    out_Color += texture(texture2d, textureCoordsKernel[4]) * 0.175713;
    out_Color += texture(texture2d, textureCoordsKernel[5]) * 0.198596;
    out_Color += texture(texture2d, textureCoordsKernel[6]) * 0.175713;
    out_Color += texture(texture2d, textureCoordsKernel[7]) * 0.121703;
    out_Color += texture(texture2d, textureCoordsKernel[8]) * 0.065984;
    out_Color += texture(texture2d, textureCoordsKernel[9]) * 0.028002;
    out_Color += texture(texture2d, textureCoordsKernel[10]) * 0.0093;
}