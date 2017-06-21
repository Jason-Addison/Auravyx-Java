#version 420 core

layout (location = 0) in vec3 position;

out vec4 colour;
out vec2 textureCoordsPass;

uniform vec3 camera;
uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelView;


void main(void)
{
    textureCoordsPass.x = (position.x + 1) / 2;
    textureCoordsPass.y = (position.y + 1) / 2;
    vec3 newPosition = vec3(position.x, position.y, position.z);

    vec4 worldPosition = transformationMatrix * vec4(newPosition,1.0);
    vec4 positionRelativeToCam = viewMatrix * worldPosition;
    gl_Position = projectionMatrix * modelView * vec4(position, 1.0);
	colour = vec4(1, 0.54, 0, 0.999f);
}