#version 420 core

in vec3 position;
//layout(location = 3) in vec4 newColour;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
//uniform vec3 lightPosition;

//uniform float density;
//uniform float gradient;

//const float amplitude = 0.1;
//const float PI = 3.14159265359;
out vec2 textureCoords;

void main(void)
{
	gl_Position = projectionMatrix * transformationMatrix * viewMatrix * vec4(position, 1.0);
	textureCoords = vec2(position.x / 2 + 0.5f, position.y / 2 + 0.5f);
}