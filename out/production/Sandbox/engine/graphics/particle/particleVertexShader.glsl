#version 420 core

layout (location = 0 ) in vec3 position;

out vec3 surfaceNormal;

out vec3 toLightVector;
out vec3 cameraPass;
out float farPlanePass;
out float vertexID;
out vec2 textureCoordsPass;
//out vec3 toCameraVector;
//out float visibility;
//out vec4 colour;
out vec3 positionPass;
out vec3 positionWorldPass;
out vec3 toCameraVector;

uniform vec3 camera;
uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform float farPlane;
uniform mat4 modelView;


void main(void)
{
    textureCoordsPass.x = (position.x + 1) / 2;
    textureCoordsPass.y = (position.y + 1) / 2;
	vec3 newPosition = vec3(position.x, position.y, position.z);

	vec4 worldPosition = transformationMatrix * vec4(newPosition,1.0);
	vec4 positionRelativeToCam = viewMatrix * worldPosition;
	gl_Position = projectionMatrix * modelView * vec4(position, 1.0);// positionRelativeToCam;
	gl_Position = vec4(gl_Position.x, gl_Position.y, gl_Position.z, gl_Position.w);

	toCameraVector = (inverse(viewMatrix) * vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;

	positionPass = position.xyz;
	positionWorldPass = worldPosition.xyz;
	cameraPass = camera;
	farPlanePass = farPlane;
}