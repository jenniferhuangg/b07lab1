

public class Polynomial {

	double coff[];
	public Polynomial()
	{
		this.coff= new double[]{0.0};
	}
	public Polynomial(double c[])
	{
		if (c==null)
		{
			this.coff= new double[]{0.0};
		}
		this.coff=c;
	}
	public Polynomial add(Polynomial addp)
	{
		int shortl=0;
	    if (addp == null || addp.coff == null)
	    {
	    	return this;
	    }
	    if (addp.coff.length>this.coff.length)
	    {
	    	shortl=this.coff.length;
	    }
		for (int i=0; i<shortl; i++)
		{
			addp.coff[i]+=coff[i];
		}
		if (this.coff.length > addp.coff.length) {
	        double[] resized = new double[this.coff.length];
	        System.arraycopy(addp.coff, 0, resized, 0, addp.coff.length);
	        for (int i = addp.coff.length; i < this.coff.length; i++) {
	            resized[i] = this.coff[i];
	        }
	        addp.coff = resized;
	    }
		return addp;
	}
	public double evaluate(double x)
	{
		int power=0;
		double sum=0;
		double tempsum;
		for (int i=0; i<coff.length; i++)
		{
			tempsum=1.0;
			for (int j=0; j<power; j++)
			{
				tempsum=tempsum*x;
			}
			sum+=(double)coff[i]*tempsum;
			power++;

		}
		return sum;
	}
	public boolean hasRoot(double x)
	{
		return evaluate(x)==0.0;
	}
}
