import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Polynomial {

	double coff[];
	int exp[];
	public Polynomial()
	{
		this.coff= new double[]{0.0};
		this.exp= new int[] {0};
	}
	public Polynomial(double c[], int e[])
	{
		if (c==null)
		{
			this.coff= new double[]{0.0};
			this.exp= new int[] {0};
		}
		this.coff=c;
		this.exp=e;
	}
	public Polynomial(File f)
	{
		String line;
		List<Double>splitcoff=new ArrayList<>();
		List<Integer>splitexp=new ArrayList<>();
		double cf;
		int e;
		try (BufferedReader br = new BufferedReader(new FileReader(f)))
		{
		   line = br.readLine();
		}
		catch(IOException err)
		{
			this.coff= new double[]{0.0};
			this.exp= new int[]{0};
			return;
		}
		if (line==null)
		{
			this.coff= new double[]{0.0};
			this.exp= new int[]{0};
			return;
		}
		String[] edit = line.replace("-", "+-").split("\\+");


		for (int i=0; i<edit.length; i++)
		{
			if(edit[i].isEmpty())
			{
				continue;
			}
			int xindx=edit[i].indexOf('x');
			if(xindx==-1)
			{
				cf=Double.parseDouble(edit[i]);
				e=0;
			}
			else
			{
				String cstr= edit[i].substring(0,xindx);
				String estr= edit[i].substring(xindx+1);
				if (cstr.isEmpty()||cstr=="+")
				{
					cf=1.0;
				}
				else if (cstr=="-")
				{
					cf=-1.0;
				}
				else
				{
					cf = Double.parseDouble(cstr);
				}
				if (estr.isEmpty())
				{
					e=1;
				}
				else
				{
					e=Integer.parseInt(estr);
				}
			}
			splitcoff.add(cf);
			splitexp.add(e);
		}
		this.coff=new double[splitcoff.size()];
		this.exp=new int[splitexp.size()];
		for (int i=0; i<splitcoff.size(); i++)
		{
			this.coff[i]=splitcoff.get(i);
			this.exp[i]=splitexp.get(i);
		}

	}
	public int check(int i, int [] e)
	{
		for (i=0; i<e.length; i++)
		{
			if (e[i]==i)
			{
				return i;
			}
		}
		return -1;
	}
	public Polynomial add(Polynomial addp)
	{
		int shortl=0;
	    if (addp==null|| addp.coff==null||addp.exp==null)
	    {
	    	return this;
	    }
	    if (addp.coff.length>this.coff.length)
	    {
	    	shortl=this.coff.length;
	    }

		for (int i=0; i<addp.coff.length; i++)
		{
			// check if this element is already in set, if so go to its index and add the coff
			int indx=check(addp.exp[i],this.exp);
			if (check(addp.exp[i],this.exp)>0)
			{
				this.coff[i]+=addp.coff[i];
			}
			// if not, add to end of array
			else
			{
				this.coff= Arrays.copyOf(this.coff,this.coff.length+1);
				this.exp= Arrays.copyOf(this.exp,this.exp.length+1);
				this.coff[this.coff.length-1]=addp.coff[i];
				this.exp[this.exp.length-1]=addp.exp[i];
			}
		}
		return this;
	}
	public double evaluate(double x)
	{
		double sum=0;
		double tempsum;
		for (int i=0; i<coff.length; i++)
		{
			tempsum=1.0;
			for (int j=0; j<exp[i]; j++)
			{
				tempsum=tempsum*x;
			}
			sum+=(double)coff[i]*tempsum;

		}
		return sum;
	}
	public Polynomial multiply(Polynomial mult)
	{
		// multiply element by og set and add to a temp set, add temp set variables to current set
		Polynomial temp = new Polynomial();

		double tempc;
		int tempe;
		double [] ogc=this.coff;
		int [] oge=this.exp;
		for (int i=0; i<mult.coff.length; i++)
		{
			for (int j=0; j<ogc.length; j++)
			{
				tempc=this.coff[j]*ogc[i];
				tempe=this.exp[j]+oge[i];

				temp.coff= Arrays.copyOf(temp.coff,temp.coff.length+1);
				temp.exp= Arrays.copyOf(temp.exp,temp.exp.length+1);
				temp.coff[temp.coff.length-1]=tempc;
				temp.exp[temp.exp.length-1]=tempe;
			}
			add(temp);
			temp.coff= Arrays.copyOf(temp.coff,1);
			temp.exp= Arrays.copyOf(temp.exp,1);
			temp.coff[temp.coff.length-1]=0.0;
			temp.exp[temp.exp.length-1]=0;
		}
		return this;

	}
	public void saveToFile(String filename) {
	    try (java.io.PrintStream pstream=new java.io.PrintStream(new java.io.FileOutputStream(filename), true, "UTF-8"))
	    {
	        for (int i=0; i<this.coff.length; i++)
	        {
	            if (i!=0&&this.coff[i]>=0.0)
	            {
	                pstream.print("+");
	            }
	            pstream.print(this.coff[i]);
	            if (this.exp[i]>0)
	            {
	                pstream.print("x");
	                if (this.exp[i]>1)
	                {
	                    pstream.print(this.exp[i]);
	                }
	            }
	        }
	    }
	    catch (java.io.IOException err)
	    {
	        return;
	    }
	}


	public boolean hasRoot(double x)
	{
		return evaluate(x)==0.0;
	}
}
