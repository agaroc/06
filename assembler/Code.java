package assembler;

import java.util.Hashtable;

public class Code 
{
    private Hashtable<String, String> destMncs;
    private Hashtable<String, String> compMncs;
    private Hashtable<String, String> jmpMncs;
    public Code()
    {
        this.jmpMncs = new Hashtable<String, String>();
        this.fillJTable();
        this.compMncs = new Hashtable<String, String>();
        this.fillCTable();
        this.destMncs = new Hashtable<String, String>();
        this.fillDTable();
    }

    private void fillJTable()
    {
        this.jmpMncs.put("NULL", "000");
		this.jmpMncs.put("JGT", "001");
		this.jmpMncs.put("JEQ", "010");
		this.jmpMncs.put("JGE", "011");
		this.jmpMncs.put("JLT", "100");
		this.jmpMncs.put("JNE", "101");
		this.jmpMncs.put("JLE", "110");
		this.jmpMncs.put("JMP", "111");
    }

    private void fillCTable()
    {
        this.compMncs.put("0", "0101010");
		this.compMncs.put("1", "0111111");
		this.compMncs.put("-1", "0111010");
		this.compMncs.put("D", "0001100");
		this.compMncs.put("A", "0110000");
		this.compMncs.put("M", "1110000");
		this.compMncs.put("!D", "0001101");
		this.compMncs.put("!A", "0110001");
		this.compMncs.put("!M", "1110001");
		this.compMncs.put("-D", "0001111");
		this.compMncs.put("-A", "0110011");
		this.compMncs.put("-M", "1110011");
		this.compMncs.put("D+1", "0011111");
		this.compMncs.put("A+1", "0110111");
		this.compMncs.put("M+1", "1110111");
		this.compMncs.put("D-1", "0001110");
		this.compMncs.put("A-1", "0110010");
		this.compMncs.put("M-1", "1110010");
		this.compMncs.put("D+A", "0000010");
		this.compMncs.put("D+M", "1000010");
		this.compMncs.put("D-A", "0010011");
		this.compMncs.put("D-M", "1010011");
		this.compMncs.put("A-D", "0000111");
		this.compMncs.put("M-D", "1000111");
		this.compMncs.put("D&A", "0000000");
		this.compMncs.put("D&M", "1000000");
		this.compMncs.put("D|A", "0010101");
		this.compMncs.put("D|M", "1010101");
    }

    private void fillDTable()
    {
        this.destMncs.put("NULL", "000");
		this.destMncs.put("M", "001");
		this.destMncs.put("D", "010");
		this.destMncs.put("MD", "011");
		this.destMncs.put("A", "100");
		this.destMncs.put("AM", "101");
		this.destMncs.put("AD", "110");
		this.destMncs.put("AMD", "111");
    }

    public String dest(String input)
    {
        if(input == null || input.isEmpty())
        {
            input = "NULL";
        }
        return this.destMncs.get(input);
    }

    public String comp(String input)
    {
        return this.compMncs.get(input);
    }

    public String jump(String input)
    {
        if(input == null || input.isEmpty())
        {
            input = "NULL";
        }
        return this.jmpMncs.get(input);
    }

    public String toBinary(String number) 
    {
		int value = Integer.parseInt(number);
		String binaryNumber = Integer.toBinaryString(value);
		String binary = String.format("%15s", binaryNumber).replace(' ', '0');
		return binary;
	}
}
