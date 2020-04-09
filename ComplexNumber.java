
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

    @Override
    public boolean equals(Object o)
    {
        if(o == this)
            return true;

        if(!(o instanceof ComplexNumber))
            return false;

        ComplexNumber c = (ComplexNumber) o;

        return this.getReal() == c.getImaginary() && this.getImaginary() == c.getImaginary();
    }

    public static ComplexNumber complexAddition(ComplexNumber cNum1, ComplexNumber cNum2)
    {
        ComplexNumber output = new ComplexNumber();
        output.setReal(cNum1.getReal() + cNum2.getReal());
        output.setImaginary(cNum1.getImaginary() + cNum2.getImaginary());
        return output;
    }

    public static ComplexNumber complexSubtraction(ComplexNumber cNum1, ComplexNumber cNum2)
    {
        ComplexNumber output = new ComplexNumber();
        output.setReal(cNum1.getReal() - cNum2.getReal());
        output.setImaginary(cNum1.getImaginary() - cNum2.getImaginary());
        return output;
    }

    public static ComplexNumber complexMultiplication(ComplexNumber cNum1, ComplexNumber cNum2)
    {
        ComplexNumber output = new ComplexNumber();
        output.setReal((cNum1.getReal() * cNum2.getReal()) + (cNum1.getImaginary() * cNum2.getImaginary() * -1));
        output.setImaginary((cNum1.getReal() * cNum2.getImaginary()) + (cNum2.getReal() * cNum1.getImaginary()));
        return output;
    }
    
    //Division Method if I ever find a good way to incorporate it
    // private static ComplexNumber complexDivision(ComplexNumber cNum1, ComplexNumber cNum2)
    // {
    //     //An annonymous object is be instantiated in second argument because in complex division you multiply by the inverse denominator
    //     //Not assigning to complexNumber output because that would only assign the adress which would result in numerator and demoninator being the same value
    //     ComplexNumber numerator = new ComplexNumber(complexMultiplication(cNum1, new ComplexNumber(cNum2.getReal(), cNum2.getImaginary() * -1)).getReal() 
    //                                               , complexMultiplication(cNum1, new ComplexNumber(cNum2.getReal(), cNum2.getImaginary() * -1)).getImaginary());
    //     double denominator = (cNum2.getReal() * cNum2.getReal()) + (cNum2.getImaginary() * cNum2.getImaginary() * -1);

    //     ComplexNumber output = new ComplexNumber(numerator.getReal() / denominator, numerator.getImaginary() / denominator);
    //     return output;
    // }

}