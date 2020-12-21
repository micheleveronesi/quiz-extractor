package view;

import java.io.*;

public class Input {

	private final InputStreamReader input;
	private final BufferedReader keyboard;
	private static Input instance = null;

	private Input() {
		input = new InputStreamReader(System.in);
		keyboard = new BufferedReader(input);
	}

	public static Input getInstance() {
		if (instance == null) {
			instance = new Input();
		}
		return instance;
	}

	public int readInt (String testo)
	{
		Integer n = null;
		boolean error;
		
		do 
		{
			error=false;
			try 
			{
				System.out.print(testo);
				n = Integer.valueOf(keyboard.readLine());
			} 
			catch (Exception e) 
			{
				System.out.println("Errore in lettura");
				error=true;
			}
		} while (error);
		
		return n;
	}

	public String readString (String testo)
	{
		String s = null;
		boolean error;
		
		do 
		{
			error=false;
			try 
			{
				System.out.print(testo);
				s = keyboard.readLine();
			} 
			catch (Exception e)
			{
				System.out.println("Errore in lettura");
				error=true;
			}
		} while (error);
		
		return s;
	}
	
}
