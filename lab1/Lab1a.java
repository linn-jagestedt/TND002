package lab1;

class Lab1a 
{
    public static void main(String[] args) 
    {
        double[] v = { 1.0, 2.0, 3.0 };
        PrintVector(v);
    }

    public static void PrintVector(double[] v) 
    {
        if (v.length != 3) {
            return;
        }

        System.out.println(
            "Vector = (" + 
            String.format("%.2f", v[0]) + ", " + 
            String.format("%.2f", v[1]) + ", " + 
            String.format("%.2f", v[2]) + ")"
        );
    }

    public static double[] AddVectors(double[] a, double[] b) 
    {
        if (a.length != b.length) {
            return null;
        }
        
        double[] result = new double[3];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
    
        return result;
    }

    public static double[] SubtractVectors(double[] a, double[] b) 
    {
        if (a.length != b.length) {
            return null;
        }
        
        double[] result = new double[3];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
    
        return result;
    }
}