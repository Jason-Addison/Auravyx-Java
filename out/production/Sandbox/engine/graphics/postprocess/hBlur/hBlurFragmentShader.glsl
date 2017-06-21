#version 140

in vec2 textureCoords;
in vec2 blurTextureCoords[11];
out vec4 out_Color;

uniform sampler2D texture2d;
uniform float width;
void main(void)
{
	    out_Color = vec4(0.0);
    	out_Color += texture(texture2d, blurTextureCoords[0]) * 0.0093;
        out_Color += texture(texture2d, blurTextureCoords[1]) * 0.028002;
        out_Color += texture(texture2d, blurTextureCoords[2]) * 0.065984;
        out_Color += texture(texture2d, blurTextureCoords[3]) * 0.121703;
        out_Color += texture(texture2d, blurTextureCoords[4]) * 0.175713;
        out_Color += texture(texture2d, blurTextureCoords[5]) * 0.198596;
        out_Color += texture(texture2d, blurTextureCoords[6]) * 0.175713;
        out_Color += texture(texture2d, blurTextureCoords[7]) * 0.121703;
        out_Color += texture(texture2d, blurTextureCoords[8]) * 0.065984;
        out_Color += texture(texture2d, blurTextureCoords[9]) * 0.028002;
        out_Color += texture(texture2d, blurTextureCoords[10]) * 0.0093;
}