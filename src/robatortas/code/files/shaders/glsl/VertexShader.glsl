#version 400 core

// OpenGL auto identifies that the positions are the
// positions stored in the VAO thanks to the vec3 callout
in vec3 position;

// Color of the vertices
out vec3 color;

void main(void){
	// Tells OpenGL the positions of each vertex in the VAO
	gl_Position = vec4(position.x, position.y, position.z, 1.0);
	// Color of each vertex
	color = vec3(position.x+0.5, 0.4, position.y+0.5);
}
