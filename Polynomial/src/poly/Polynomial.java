package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author: Mohammad Sohail / mms458@scarletmail.rutgers.edu / mms458 runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) 
	{
		Node eq1 = poly1;
        Node eq2 = poly2;
        Node polySum = null;

        while (eq1 != null && eq2 != null) {
            int eq1Degree = eq1.term.degree;
            int eq2Degree = eq2.term.degree;
            if (eq1Degree > eq2Degree) 
			{
                Node cookie = new Node(eq2.term.coeff, eq2.term.degree, polySum);
                polySum = cookie;
                eq2 = eq2.next;
            }
            if (eq1Degree < eq2Degree) 
			{
                Node cookie = new Node(eq1.term.coeff, eq1.term.degree, polySum);
                polySum = cookie;
                eq1 = eq1.next;
            }
            if (eq1Degree == eq2Degree) 
			{
				float massab = eq1.term.coeff +eq2.term.coeff;
				if (massab != 0)
				{
                Node cookie = new Node(massab, eq1.term.degree, polySum);
				polySum = cookie;
				}
                eq1 = eq1.next;
                eq2 = eq2.next;

            }
        }

        while (eq1 == null && eq2 != null)
		{
            Node cookie = new Node(eq2.term.coeff, eq2.term.degree, polySum);
            polySum = cookie;
            eq2 = eq2.next;
            //printList(polySum);
        }

        while (eq2 == null && eq1 != null) 
		{
            Node cookie = new Node(eq1.term.coeff, eq1.term.degree, polySum);
            polySum = cookie;
            eq1 = eq1.next;
            //printList(polySum);
        }

        return reverseList (polySum);
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
	}
	public static void  printList(Node list)
    {
         System.out.println(toString(list));
    }
    public static Node reverseList (Node head) 
    {
        Node prev = null;
        Node origHead = head;
        Node forward = null;
            while (origHead != null) 
			{
                forward = origHead.next;
                origHead.next = prev;
                prev = origHead;
                origHead = forward;
            }
            head = prev;
            return prev;
        }
	
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) 
	{
		Node polyProduct = null;
        Node eq2 = poly2;

        while (poly1 != null) {
            Node polySum = null;
            poly2 = eq2;


            while (poly2 != null) {
                Node cookie = new Node((poly1.term.coeff) * (poly2.term.coeff), (poly1.term.degree) + (poly2.term.degree), polySum);
                polySum = cookie;
                poly2 = poly2.next;

            }
            polyProduct = add(reverseList(polySum), polyProduct);
            poly1 = poly1.next;

        }
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return polyProduct;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) 
	{
		int cookie = 0;

        while (poly != null) 
		{
            cookie =  cookie +((int) poly.term.coeff * (int) Math.pow(x, poly.term.degree));
            poly = poly.next;
		}
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return (float) cookie;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
