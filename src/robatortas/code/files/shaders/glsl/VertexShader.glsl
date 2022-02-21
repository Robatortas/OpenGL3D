#version 400 core

// OpenGL auto identifies that the positions are the
// positions stored in the VAO thanks to the vec3 callout
in vec3 position;
// UV coords of the texture
in vec2 textureCoords;
in vec3 normal;

// Passes textureCoords to the fragment shader
out vec2 passTextureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 color;

out vec3 cameraPosition;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPosition;

void main(void){

	vec4 worldPosition = transformationMatrix * vec4(position, 1.0);

	// Tells OpenGL the positions of each vertex in the VAO
	// A vector is a 1 by 4 matrix too btw
	gl_Position = projectionMatrix * viewMatrix * worldPosition; //transformationMatrix*
	// passTextureCoords is equal to textureCoords
	passTextureCoords = textureCoords;

	surfaceNormal = (transformationMatrix * vec4(normal, 0.0)).xyz;
	toLightVector = lightPosition - worldPosition.xyz;

	cameraPosition = (inverse(viewMatrix) * vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;

	// Fill
	color = vec3(position.x+0.5, position.y+0.5, position.z+0.5);

}
