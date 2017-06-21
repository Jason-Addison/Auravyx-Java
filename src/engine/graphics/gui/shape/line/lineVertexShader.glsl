#version 400

in vec3 position;

out vec4 colourPass;

uniform mat4 transformationMatrix;
uniform vec4 colour;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(void)
{
	vec4 worldPosition = transformationMatrix * vec4(position, 1.0);
    vec4 positionRelativeToCam = viewMatrix * worldPosition;
    gl_Position = projectionMatrix * positionRelativeToCam;
    gl_Position = vec4(gl_Position.x, gl_Position.y, gl_Position.z, gl_Position.w);
    colourPass = colour;
}