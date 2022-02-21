#version 400 core

// The input of the Fragment Shader
// (The output of the Vertex Shader basically)
in vec3 color;
in vec2 passTextureCoords;

// Vector pointing straight out of the surface vector
in vec3 surfaceNormal;
// Vector pointing at light source
in vec3 toLightVector;

// The color that will be outputted
out vec4 out_Color;

uniform sampler2D textureSampler;

uniform vec3 lightColor;

void main(void) {

	// Makes the normals to 1
	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitLightVector = normalize(toLightVector);

	// Dot product calculation
	float nDot1 = dot(unitNormal, unitLightVector);

	// Sets the max brightness to be 1
	float brightness = max(nDot1, 0.2);
	vec3 diffuse = brightness * lightColor;

	// Special GLSL method, takes in text we want to sample and takes
	//in the coordinates for the point on the texture that wants to be sampled

	// Returns color of the pixel on the texture at the coords that we give it
	out_Color = vec4(diffuse, 1.0) * texture(textureSampler, passTextureCoords); // vec4(color,1.0);

	// Fill
//	out_Color = vec4(color, 1.0);

}
