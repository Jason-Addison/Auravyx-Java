#version 400 core

in vec3 position;
//layout(location = 3) in vec4 newColour;
in vec3 normal;
in vec3 colour;
in vec2 textureCoordsIn;
out vec3 surfaceNormal;

out vec3 toLightVector;
out vec3 cameraPass;
out float farPlanePass;
//out vec3 toCameraVector;
//out float visibility;
//out vec4 colour;
out vec3 positionPass;
out vec3 positionWorldPass;
out vec3 colourPass;
out vec2 textureCoords;

uniform vec3 camera;
uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform float farPlane;
//uniform vec3 lightPosition;

//uniform float density;
//uniform float gradient;

//const float amplitude = 0.1;
//const float PI = 3.14159265359;

void main(void)
{
    textureCoords = vec2(position.x, position.z);
    surfaceNormal = normal;
    vec3 newPosition = vec3(position.x, position.y, position.z);

    vec4 worldPosition = vec4(newPosition,1.0);
    vec4 positionRelativeToCam = viewMatrix * worldPosition;
    gl_Position = projectionMatrix * positionRelativeToCam;
    positionPass = position.xyz;
    positionWorldPass = worldPosition.xyz;
    cameraPass = camera;
    farPlanePass = farPlane;
    colourPass = colour;
}