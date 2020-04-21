//Extends Real Component because a complex number is a real + imaginary

//TODO revise equals method to work if comparing fraction complexnumber and whole complex number

public class ComplexNumber extends RealComponent
{

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


    public void setImaginary(int imaginary)
    {
        this.imaginary = imaginary;
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
    
    public static ComplexNumber complexDivision(ComplexNumber cNum1, ComplexNumber cNum2)
    {
        //Not assigning to complexMultiplication output directly because that would only assign the memory adress
        // ComplexNumber numerator = new ComplexNumber(complexMultiplication(cNum1, new ComplexNumber(cNum2.getReal(), cNum2.getImaginary() * -1)).getReal() 
        //                                           , complexMultiplication(cNum1, new ComplexNumber(cNum2.getReal(), cNum2.getImaginary() * -1)).getImaginary());
        ComplexNumber numerator = complexMultiplication(cNum1, new ComplexNumber(cNum2.getReal(), cNum2.getImaginary() * -1));
        
        double denominator = Math.pow(cNum2.getReal(), 2) + Math.pow(cNum2.getImaginary(), 2);

        System.out.println(numerator.getReal() / denominator);
        System.out.println(numerator.getImaginary() / denominator);

        ComplexNumber output = new ComplexNumber( (int) Math.round(numerator.getReal() / denominator), (int) Math.round(numerator.getImaginary() / denominator));
        return output;
    }

}