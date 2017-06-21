#version 420 core

layout (location = 0 ) in vec3 position;


uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelView;


void main(void)
{
	gl_Position = projectionMatrix * modelView * vec4(position, 1.0);
}