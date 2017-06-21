#version 400 core

#extension GL_EXT_gpu_shader4 : require

in vec3 positionWorldPass;
in vec3 positionPass;
in vec3 cameraPass;
in float farPlanePass;
//in vec4 colour;
in vec3 surfaceNormal;
in vec3 toLightVector;
//in vec3 toCameraVector;
//in float visibility;

layout (location = 0) out vec4 out_Color;
layout (location = 1) out vec4 out_Bright;
layout (location = 2) out vec4 out_Position;
layout (location = 3) out vec4 out_Depth;

//uniform vec3 lightColour;
//uniform float shineDamper;
//uniform float reflectivity;
//uniform vec3 skyColour;
uniform sampler2D texture2d;

void main(void)
{

    vec3 lightColour = vec3(1, 1, 1);
    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDot1 = dot(unitNormal, unitLightVector);
    float brightness = max(nDot1, 0.0);
    vec3 diffuse = brightness * lightColour;
    float bright = dot(vec3(0, 1, 0), surfaceNormal);
    bright = mix(bright, bright * 0.1, step(0.0, -bright));
    //bright = clamp(bright, 0.0, 1.0);
	/*vec3 unitVectorToCamera = normalize(toCameraVector);
	vec3 lightDirection = -unitLightVector;
	vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);

	float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
	specularFactor = max(specularFactor, 0.0);
	float dampedFactor = pow(specularFactor, shineDamper);
	vec3 finalSpecular = dampedFactor * lightColour * reflectivity;
	diffuse = vec3(diffuse.x + 0.5, diffuse.y + 0.5, diffuse.z + 0.5);

	out_Color = vec4(diffuse, 1.0) * vec4(colour) + vec4(finalSpecular, 0.1);

	out_Color = mix(vec4(skyColour, 1.0), out_Color, visibility);*/
    //out_Color = mix(vec4(diffuse, 1.0), vec4(1, 0, 0, 1), brightness);//(vec4(diffuse, 1.0) + vec4(1, 0, 0, 1)) / 2;
    //out_Color = vec4(diffuse, 1.0) * vec4(1, 0, 0, 1);
    bright = (bright + 1f) / 2;
    out_Color = vec4(0.7f, 1, 0.05f, 1) * (bright * 1f);
    vec4 surfaceColour = vec4(surfaceNormal / 2 + 0.5f, 1.0);
    surfaceColour = (surfaceColour -0.5) * 2;
    out_Bright = surfaceColour;
    out_Depth = out_Color;
    out_Position = vec4((positionWorldPass - cameraPass) / farPlanePass + 0.5f, 1.0);
}