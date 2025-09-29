
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

public class Driver {
	public static void main(String [] args) {
	Polynomial p = new Polynomial();
	System.out.println(p.evaluate(3));
	double [] c1 = {6,0,0,5};
	int [] c1e = {0,1,2,3,4};
	Polynomial p1 = new Polynomial(c1,c1e);
	double [] c2 = {0,-2,0,0,-9};
	int [] c2e = {0,1,2,3,4};
	Polynomial p2 = new Polynomial(c2,c2e);
	Polynomial s = p1.add(p2);
	System.out.println("s(0.1) = " + s.evaluate(0.1));
	if(s.hasRoot(1))
		System.out.println("1 is a root of s");
	else
		System.out.println("1 is not a root of s");
	// multiply
	Polynomial m =p1.multiply(p2);
	System.out.println("m(0.1) = "+m.evaluate(0.1));

	// save to files (part e)
	p1.saveToFile("p1.txt");
	p2.saveToFile("p2.txt");
	s.saveToFile("sum.txt");
	m.saveToFile("prod.txt");
	System.out.println("Polynomials saved to p1.txt, p2.txt, sum.txt, prod.txt");

	// load back from file (part d) and verify
	Polynomial sumFromFile  = new Polynomial(new File("sum.txt"));
	Polynomial prodFromFile = new Polynomial(new File("prod.txt"));
	System.out.println("sumFromFile(0.1)  = " + sumFromFile.evaluate(0.1));
	System.out.println("prodFromFile(0.1) = " + prodFromFile.evaluate(0.1));
	}
}
