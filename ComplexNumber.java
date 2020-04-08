
public class ComplexNumber
{

    private int real;
    private int imaginary;

    public ComplexNumber()
    {
        setReal(0);
        setImaginary(0);
    }

    public ComplexNumber(int real, int imaginary)
    {
        setReal(real);
        setImaginary(imaginary);
    }

    public void setReal(int real)
    {
        this.real = real;
    }

    public void setImaginary(int imaginary)
    {
        this.imaginary = imaginary;
    }

    public int getReal()
    {
        return real;
    }

    public int getImaginary()
    {
        return imaginary;
    }

    @Override
    public String toString()
    {
        if(Integer.toString(this.getImaginary()).contains("-"))
            return "(" + this.getReal() + this.getImaginary() + "i)";
        else
            return "(" + this.getReal() + "+" + this.getImaginary() + "i)";
    }

}