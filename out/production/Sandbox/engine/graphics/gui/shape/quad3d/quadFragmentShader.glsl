#version 400 core

//in float visibility;

out vec4 out_Color;
in vec2 textureCoords;
//layout (location = 0) out uvec4 out_Color;
/*layout (location = 1) out vec4 out_Bright;
layout (location = 2) out vec4 out_Position;
layout (location = 3) out vec4 out_Depth;
layout (location = 4) out vec4 out_toCamera;*/
//uniform vec3 lightColour;
//uniform float shineDamper;
//uniform float reflectivity;
//uniform vec3 skyColour;
uniform sampler2D texture2d;

void main(void)
{

    /*vec3 lightColour = vec3(1, 1, 1);
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

	out_Color = mix(vec4(skyColour, 1.0), out_Color, visibility);
    //out_Color = mix(vec4(diffuse, 1.0), vec4(1, 0, 0, 1), brightness);//(vec4(diffuse, 1.0) + vec4(1, 0, 0, 1)) / 2;
    bright = (bright + 1f) / 2;
    //out_Color = vec4(1, 0, 0.0f, 1) * (bright * 1f) + vec4(finalSpecular, 0.1f);
    vec4 surfaceColour = vec4(surfaceNormal / 2 + 0.5f, 1.0);
    surfaceColour = vec4(normalize(surfaceNormal.x), normalize(surfaceNormal.y), normalize(surfaceNormal.z), 1);
    out_Bright = surfaceColour;
    out_Depth = out_Color;
    out_Position = vec4((positionWorldPass - cameraPass) / farPlanePass + 0.5f, 1.0);
    out_toCamera = vec4(positionWorldPass / farPlanePass, 1.0);
    int e;
    float ver = vertexID;
    //float vec = ver / 21474836;//2147483647;
    float test =ver / entityCountPass;
    e = 1055500000;
    out_Color = uvec4(1059500000, 0, 0, 1);
    out_toCamera = vec4(0, 1, 0, 1);*/
    out_Color = texture(texture2d, textureCoords);
}