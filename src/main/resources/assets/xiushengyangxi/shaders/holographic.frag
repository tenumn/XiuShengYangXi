#version 120

#define PI 3.141592653589793238462643383279

uniform sampler2D texture;
uniform float time;
uniform vec2 resolution;

varying vec3 position;
varying vec3 worldPos;
varying vec2 texCoord0;
varying float scale;
varying vec4 colorIn;



void main(void) {
    vec4 tex = texture2D(texture, texCoord0-vec2(.5));

    gl_FragColor = tex;
}