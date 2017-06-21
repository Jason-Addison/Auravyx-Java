#version 400 core

#extension GL_EXT_gpu_shader4 : require
#extension GL_ARB_explicit_uniform_location : enable

in vec3 positionWorldPass;
in vec3 positionPass;
in vec3 cameraPass;
in float farPlanePass;
//in vec4 colour;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float vertexID;
in float entityCountPass;
in vec2 textureCoordsPass;
//in float visibility;

layout (location = 0) out vec4 out_Color;
layout (location = 1) out vec4 out_Normals;
layout (location = 2) out vec4 out_Position;
layout (location = 3) out vec4 out_Depth;
layout (location = 4) out vec4 out_toCamera;
layout (location = 7) out vec4 out_Settings;
//uniform vec3 lightColour;
//uniform float shineDamper;
//uniform float reflectivity;
//uniform vec3 skyColour;
layout(location = 4) uniform sampler2D entityTexture;
layout(location = 1) uniform sampler2D normalMap;
layout(location = 2) uniform sampler2D specular;
layout(location = 3) uniform sampler2D glow;

layout(location = 10) uniform sampler2D screen;

void main(void)
{

    vec3 lightColour = vec3(1, 1, 1);
	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitLightVector = normalize(toLightVector);

	float nDot1 = dot(unitNormal, unitLightVector);
	float brightness = max(nDot1, 0.0);
	vec3 diffuse = brightness * lightColour;
    float bright = dot(toLightVector, surfaceNormal);
    bright = mix(bright, bright * 0.1, step(0.0, -bright));

    float shineDamper = 100;
    float reflectivity = 1;

	vec3 unitVectorToCamera = normalize(toCameraVector);
	vec3 lightDirection = -unitLightVector;
	vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);

	float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
	specularFactor = max(specularFactor, 0.0);
	float dampedFactor = pow(specularFactor, shineDamper);
	vec3 finalSpecular = dampedFactor * lightColour * reflectivity;
	/*diffuse = vec3(diffuse.x + 0.5, diffuse.y + 0.5, diffuse.z + 0.5);

	out_Color = vec4(diffuse, 1.0) * vec4(colour) + vec4(finalSpecular, 0.1);

	out_Color = mix(vec4(skyColour, 1.0), out_Color, visibility);*/
    //out_Color = mix(vec4(diffuse, 1.0), vec4(1, 0, 0, 1), brightness);//(vec4(diffuse, 1.0) + vec4(1, 0, 0, 1)) / 2;
    bright = (bright + 1f) / 2;
    //out_Color = vec4(1, 0, 0.0f, 1) * (bright * 1f) + vec4(finalSpecular, 0.1f);
    vec3 normal = surfaceNormal;
    vec3 normalTexture = texture(normalMap, textureCoordsPass).xyz;
    //normal = vec3(normal.x * (normalTexture.x * 2 - 1), normal.y * (normalTexture.y * 2 - 1), normal.z * (normalTexture.z * 2 - 1));
    out_Normals = vec4((normal.x + 1) / 2, (normal.y + 1) / 2, (normal.z + 1) / 2, 1);
    //out_Normals.xyz *= texture(normalMap, textureCoordsPass).xyz;
    out_Depth = out_Color;
    out_Position = vec4((positionWorldPass - cameraPass) / farPlanePass + 0.5f, 1.0);
    out_toCamera = vec4(positionWorldPass / farPlanePass, 1.0);
    vec4 screenTexture = texture(screen, gl_FragCoord.xy);
    vec4 entityColour = texture(entityTexture, textureCoordsPass);

    out_Color = entityColour;
    out_Settings = vec4(0, 0, 0, 1);
    if(entityColour.w < 1)
    {
        discard;
    }
    if(entityColour.w != 1)
    {
        //out_Color = vec4(1, 0, 0, 0.5);
    }
    if(entityColour.w != 1)
    {
        out_Settings.x = 1;
    }

    out_toCamera = vec4(0, 1, 0, 1);
}