#version 400 core

// OpenGL auto identifies that the positions are the
// positions stored in the VAO thanks to the vec3 callout
in vec3 position;
// UV coords of the texture
in vec2 textureCoords;

// Passes textureCoords to the fragment shader
out vec2 passTextureCoords;

out vec3 color;

void main(void){
	// Tells OpenGL the positions of each vertex in the VAO
	gl_Position = vec4(position, 1.0);
	// passTextureCoords is equal to textureCoords
	passTextureCoords = textureCoords;

	// Fill
//	color = vec3(0, 1, 1);

}
