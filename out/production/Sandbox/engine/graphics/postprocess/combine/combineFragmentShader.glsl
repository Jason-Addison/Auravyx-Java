#version 400

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D texture1;
uniform sampler2D texture2;
uniform float transparency;

void main()
{
	vec4 tex1Colour = texture2D(texture1, vec2(textureCoords.x, textureCoords.y));
	vec4 tex2Colour = texture2D(texture2, vec2(textureCoords.x, textureCoords.y));
    if(textureCoords.y > 1 || textureCoords.y < 0)
    {
        discard;
    }

    float visibility = tex2Colour.w;
    out_Color = vec4(tex1Colour.x * (1 - visibility), tex1Colour.y * (1 - visibility), tex1Colour.z * (1 - visibility), 1);
    out_Color.x = out_Color.x + (tex2Colour.x * visibility);
    out_Color.y = out_Color.y + (tex2Colour.y * visibility);
    out_Color.z = out_Color.z + (tex2Colour.z * visibility);
    if(visibility < 0.9)
    {
        out_Color = vec4(1, 0, 0, 1);
    }
}