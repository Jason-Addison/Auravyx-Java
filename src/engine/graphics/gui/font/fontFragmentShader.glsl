#version 140

in vec2 textureCoords;
in vec4 colourPass;
in vec4 backgroundcolourPass;

out vec4 out_Color;

uniform sampler2D guiTexture;

void main(void)
{

	out_Color = texture(guiTexture, textureCoords);
	//out_Color = vec4(0, 1, 0, 1);
    if(out_Color.w > 0)
    {
        out_Color = colourPass;
    }
    else
    {
        //out_Color = vec4(0, 1, 0, 1);
    }
}