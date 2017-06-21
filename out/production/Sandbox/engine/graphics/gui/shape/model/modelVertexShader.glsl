#version 420 core

in vec3 position;
in vec3 colour;
out vec3 colourPass;
//layout(location = 3) in vec4 newColour;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
//uniform vec3 lightPosition;

//uniform float density;
//uniform float gradient;

//const float amplitude = 0.1;
//const float PI = 3.14159265359;

void main(void)
{
    //surfaceNormal = normal;
	//vec3 newPosition = vec3(position.x, position.y, position.z);

	//vec4 worldPosition = vec4(newPosition,1.0);
	//vec4 positionRelativeToCam = viewMatrix * worldPosition;
	//gl_Position = projectionMatrix * positionRelativeToCam;
	gl_Position = projectionMatrix * transformationMatrix * viewMatrix * vec4(position, 1.0);
	colourPass = colour;
	//gl_Position = vec4(gl_Position.x, gl_Position.y, gl_Position.z, gl_Position.w);
	//gl_Position = vec4(1, 1, 1, 1);
	/*//gl_Position = vec4(position.x, position.y, position.z, gl_Position.w);
	surfaceNormal = (transformationMatrix * vec4(normal,0.0)).xyz;
	vec3 lightPosition = vec3(1000, 453, 654);
	toLightVector = lightPosition - worldPosition.xyz;
	//gl_PointSize = 3;
	toCameraVector = (inverse(viewMatrix) * vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;

	float distance = length(positionRelativeToCam.xyz);
	visibility = exp(-pow((distance * density), gradient));
	visibility = clamp(visibility, 0.0, 1.0);
	colour = newColour;
	positionPass = position.xyz;
	positionWorldPass = worldPosition.xyz;
	cameraPass = camera;
	farPlanePass = farPlane;
	vertexID = id;
	entityCountPass = entityCount;*/
}