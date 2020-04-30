public class RealComponent 
{

    private int real;

    public RealComponent()
    {
        setReal(0);
    }

    public RealComponent(int real)
    {
        setReal(real);
    }
    
    /** 
     * @param real
     */
    public void setReal(int real)
    {
        this.real = real;
    }
    
    /** 
     * @return int
     */
    public int getReal()
    {
        return real;
    }

}