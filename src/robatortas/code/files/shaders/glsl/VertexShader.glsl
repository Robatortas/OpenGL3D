#version 400 core

// OpenGL auto identifies that the positions are the
// positions stored in the VAO thanks to the vec3 callout
in vec3 position;
// UV coords of the texture
in vec2 textureCoords;

// Passes textureCoords to the fragment shader
out vec2 passTextureCoords;

out vec3 color;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;

uniform mat4 viewMatrix;

void main(void){
	// Tells OpenGL the positions of each vertex in the VAO
	// A vector is a 1 by 4 matrix too btw
	gl_Position = viewMatrix * projectionMatrix * transformationMatrix * vec4(position, 1.5); //transformationMatrix*
	// passTextureCoords is equal to textureCoords
	passTextureCoords = textureCoords;

	// Fill
	color = vec3(position.x+0.5, position.y+0.5, position.z+0.5);

}
