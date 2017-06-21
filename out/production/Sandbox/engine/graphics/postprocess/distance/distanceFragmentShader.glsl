#version 400

in vec2 textureCoords;

layout (location = 0) out vec4 out_Color;

uniform sampler2D texture2d;
uniform sampler2D depthTexture;
void main(void)
{
	//out_Color = texture(texture2d, vec2(textureCoords.x, textureCoords.y));
	//out_Color /= 2;
	//out_Color += texture(depthTexture, vec2(textureCoords.x, textureCoords.y)) / 2;

	vec4 depthColour = texture(depthTexture, vec2(textureCoords.x, textureCoords.y));

	out_Color = texture(texture2d, vec2(textureCoords.x, textureCoords.y)) * depthColour;
	out_Color += ((1 - depthColour) * vec4(0, 0, 1, 1));
	out_Color = texture(depthTexture, vec2(textureCoords.x, textureCoords.y));
	//out_Color.xyz = 1 - out_Color.xyz;
	float distance = length(depthColour * 50);
    float visibility = exp(-pow((distance * 1), 1));
    visibility = clamp(visibility, 0.0, 1.0);
    out_Color *= visibility * depthColour;
    out_Color.w = 1;
}