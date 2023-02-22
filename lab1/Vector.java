package lab1;

public class Vector 
{
    public double x;
    public double y; 
    public double z;

    public static Vector vdef = new Vector(0.0, 0.0, 0.0);
    
    Vector() {
        SetToDefault();
    }

    Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void SetDefault(Vector v) {
        vdef.x = v.x;
        vdef.y = v.y;
        vdef.z = v.z;
    }

    public void SetToDefault() {
        x = vdef.x;
        y = vdef.y;
        z = vdef.z;
    }

    public static Vector plus(Vector a, Vector b) 
    {        
        return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public Vector minus(Vector v) 
    {
        Vector result = new Vector(x, y, z);

        result.x -= v.x;
        result.y -= v.y;
        result.z -= v.z;

        return result;
    }

    public Vector mult(double d) 
    {
        Vector result = new Vector(x, y, z);

        result.x *= d;
        result.y *= d;
        result.z *= d;

        return result;
    }

    public double mult(Vector v) 
    {
        return x * v.x + y * v.y + z * v.z;
    }

    public double length() 
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector matrixMult(double[][] matrix) 
    {
        if (matrix.length != 3 || matrix[0].length != 3) {
            return this;
        }

        return new Vector(
            matrix[0][0] * x + matrix[1][0] * y + matrix[2][0] * z,
            matrix[0][1] * x + matrix[1][1] * y + matrix[2][1] * z,
            matrix[0][2] * x + matrix[1][2] * y + matrix[2][2] * z
        );
    } 

    public void norm()  
    {
        double length = length();

        x /= length;
        y /= length;
        z /= length;
    }

    public int compareTo(Vector v) 
    {
        double dif = length() - v.length();

        if (dif < 0) {
            return -1;
        } else if (dif > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return  
            "Vector = (" + 
            String.format("%.2f", x) + ", " + 
            String.format("%.2f", y) + ", " + 
            String.format("%.2f", z) + ")";
    }
}
