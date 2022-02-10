#version 400 core

// The input of the Fragment Shader
// (The output of the Vertex Shader basically)
in vec2 passTextureCoords;

// The color that will be outputted
out vec4 out_Color;

uniform sampler2D textureSampler;

void main(void) {
	// Special GLSL method, takes in text we want to sample and takes
	//in the coordinates for the point on the texture that wants to be sampled

	// Returns color of the pixel on the texture at the coords that we give it
	out_Color = texture(textureSampler, passTextureCoords);
}
