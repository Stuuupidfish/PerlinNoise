public class Perlin {
    
    public double perlin(double x, double y)
    {
        //corners
        int x0 = (int) Math.floor(x);
        int x1 = x0 + 1;
        int y0 = (int) Math.floor(y);
        int y1 = y0 + 1;

        //gradients at corners
        double[] x0y0 = gradient(x0, y0);
        double[] x1y0 = gradient(x1, y0);
        double[] x0y1 = gradient(x0, y1);
        double[] x1y1 = gradient(x1, y1);

        //dot products between gradients and distance vectors
        double x0y0dotProd = x0y0[0] * (x - x0) + x0y0[1] * (y - y0);
        double x1y0dotProd = x1y0[0] * (x - x1) + x1y0[1] * (y - y0);
        double x0y1dotProd = x0y1[0] * (x - x0) + x0y1[1] * (y - y1);
        double x1y1dotProd = x1y1[0] * (x - x1) + x1y1[1] * (y - y1);

        //interpolate between the dot products
        //to handle 4 corners, do it in stages: bicubic interpolation
        double x0interp = interpolate(x0y0dotProd, x1y0dotProd, x - x0);
        double x1interp = interpolate(x0y1dotProd, x1y1dotProd, x - x0);
        return interpolate(x0interp, x1interp, y - y0);
    }

    //hashing code from: https://pastebin.com/gXEYsmw8 translated from c++ to java
    private double[] gradient(int x, int y)
    {
        // No precomputed gradients mean this works for any number of grid coordinates
        long a = x;
        long b = y;
        a *= 3284157443L;
        
        b ^= (a << 16) | (a >>> 16);
        b *= 1911520717L;
        
        a ^= (b << 16) | (b >>> 16);
        a *= 2048419325L;
        
        // Convert hash to angle in [0, 2π]
        double angle = ((a & 0xFFFFFFFFL) * Math.PI) / 0x80000000L;
        
        // Create the vector from the angle
        return new double[] { Math.cos(angle), Math.sin(angle) };
    }

    //a0 = value at (x0, y0)
    //a1 = value at (x1, y0)
    //w = (x - x0) / (x1 - x0)
    double interpolate (double a0, double a1, double w)
    {
        return (a1 - a0) * (3.0 - w * 2.0) * w * w + a0;
    }
}
