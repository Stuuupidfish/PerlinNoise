public class Screen 
{
    private int width, height;  
    public int[] pixels;
    private Perlin perlin;
    private double frequency;
    private double amplitude; 
    private int gridSize; //pixles per grid cell-- changes how zoomed in 
    private int octaves; //# of layers
    //private double contrast; //how much to exaggerate the difference between light and dark areas

    public Screen(int width, int height, double frequency, double amplitude, int gridSize, int octaves) 
    {
        this.width = width;
        this.height = height;
        this.frequency = frequency;
        this.amplitude = amplitude;
        this.gridSize = gridSize;
        this.octaves = octaves;
        //this.contrast = contrast;
        this.perlin = new Perlin();
        pixels = new int[width * height];
    }
    public Screen(int width, int height) 
    {
        this(width, height, 1, 1, 40, 5);
    }
    public void setFrequency(double frequency) 
    {
        this.frequency = frequency;
    }
    public void setAmplitude(double amplitude) 
    {
        this.amplitude = amplitude;
    }
    public void setGridSize(int gridSize) 
    {
        this.gridSize = gridSize;
    }
    public void setOctaves(int octaves) 
    {
        this.octaves = octaves;
    }

    public void render() 
    {
        for(int y = 0; y < height; y++) 
        {
            for(int x = 0; x < width; x++) 
            {
                double val = 0;
                double freq = frequency;
                double amp = amplitude;
                for(int o = 0; o < octaves; o++) 
                {
                    val += perlin.perlin(x * freq /gridSize, y * freq / gridSize) * amp;
                    freq *= 2;
                    amp /= 2;
                }

                int color = (int) ((val + 1) / 2 * 255); //normalize to [0, 255]
                color = (color << 16) | (color << 8) | color; //grayscale
                pixels[y * width + x] = color;
            }
        }
    }

    public void clear() 
    {
            for(int i = 0; i < pixels.length; i++) 
            {
                    pixels[i] = 0; //make every pixel black
            }
    }

}