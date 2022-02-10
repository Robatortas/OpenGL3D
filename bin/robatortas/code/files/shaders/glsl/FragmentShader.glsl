#version 400 core

// The input of the Fragment Shader
// (The output of the Vertex Shader basically)
in vec3 color;

// The color that will be outputted
out vec4 out_Color;

void main(void) {
	// Sets input color to the output color
	out_Color = vec4(color,1.0)*2;
}
