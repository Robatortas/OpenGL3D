#version 400 core

// OpenGL auto identifies that the positions are the
// positions stored in the VAO thanks to the vec3 callout
in vec3 position;
in vec2 textureCoords;

out vec2 passTextureCoords;

void main(void){
	// Tells OpenGL the positions of each vertex in the VAO
	gl_Position = vec4(position, 1.0);
	passTextureCoords = textureCoords;

}
