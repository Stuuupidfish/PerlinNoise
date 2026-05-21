# Perlin Noise Generator

Hi guys this project is just me wanting to learn about procedural generation.
Procedural generation is used a lot in games and game development and Perlin Noise specifically is used a lot for things like terrain generation and textures so it was something I was curious about.

<img width="587" height="417" alt="Screenshot 2026-05-19 224947" src="https://github.com/user-attachments/assets/cb7d2f9f-cc73-479d-9f6b-5c4c2828393c" />

## What is Perlin Noise?
Perlin noise is a pseudo-random procedural texture. Its basically a function that takes in some inputs and spits out some output(s).

My Perlin Noise generator implements 2D scalar noise $f : \mathbb{R}^2 \rightarrow \mathbb{R}$, so for a pixel at coordinate $(x_i,y_i)$ the program spits out a scalar output. For applications such as textures and terrain generation, this scalar can be used as a height map where $z = f(x,y)$. To produce RGB values I just mapped the scalar to color. In this program, outputs go from $[-1,1]$ so something like $-1$ can map to black and $1$ can map to white and any value inbetween will be some shade of grey.

To make Perlin Noise, you create a grid and assign a random unit vector to every vertex. Then, for each pixel, you find which grid square it falls into. You calculate the displacement vector from that pixel to each corner of the square, and take the dot product of each displacement vector with its corresponding gradient vector. These dot products are then interpolated to get the final noise value.

For a smoother look/output, a smoothstep function is used as something like linear interpolation will create a squarish look which is something we want to avoid if we want to simulate natural phenomena. For this program, the four resulting dot products are interpolated using bicubic interpolation along both the $x$ and $y$ axes.

For a more defined look you can stack layers of Perlin Noise which are defined as octaves. The less octaves there are the blurrier the image output and vice versa. At every subsequent octave, the frequency is doubled and the amplitude is halved. 

## Sources / Tech Stack
I watched [Zipped's perlin noise tutorial](https://www.youtube.com/watch?v=kCIaHqb60Cw) to make this and created a Java version of his C++ code (I left out contrast). His code can be found [here](https://pastebin.com/gXEYsmw8).

For the graphics rendering I used Swing and AWT and refered to this [Stack Overflow thread](https://stackoverflow.com/questions/14859593/java-basic-plotting-drawing-a-point-dot-pixel) for the setup. 

Also check out [Studio Reborne's fantastic video](https://youtu.be/VK6BjNI6PFc?si=Y5LLjGAqZKjHBZUV) on how Minecraft infinite worlds are generated.

