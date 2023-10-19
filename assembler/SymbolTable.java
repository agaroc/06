package assembler;

import java.util.Hashtable;


public class SymbolTable 
{
    private static final int DATA_STARTING_ADDRESS = 16;
    private static final int DATA_ENDING_ADDRESS = 16384;
    private static final int PROGRAM_STARTING_ADDRESS = 0;
    private static final int PROGRAM_ENDING_ADDRESS = 32767;

    private Hashtable<String, Integer> symbolMap;
    private int pAddress;
    private int dAddress;
    public SymbolTable()
    {
        this.initialize();
        this.pAddress = PROGRAM_STARTING_ADDRESS;
        this.dAddress = DATA_STARTING_ADDRESS;
    }

    private void initialize()
    {
        this.symbolMap = new Hashtable<String, Integer>();
		this.symbolMap.put("SP", Integer.valueOf(0));
		this.symbolMap.put("LCL", Integer.valueOf(1));
		this.symbolMap.put("ARG", Integer.valueOf(2));
		this.symbolMap.put("THIS", Integer.valueOf(3));
		this.symbolMap.put("THAT", Integer.valueOf(4));
		this.symbolMap.put("R0", Integer.valueOf(0));
		this.symbolMap.put("R1", Integer.valueOf(1));
		this.symbolMap.put("R2", Integer.valueOf(2));
		this.symbolMap.put("R3", Integer.valueOf(3));
		this.symbolMap.put("R4", Integer.valueOf(4));
		this.symbolMap.put("R5", Integer.valueOf(5));
		this.symbolMap.put("R6", Integer.valueOf(6));
		this.symbolMap.put("R7", Integer.valueOf(7));
		this.symbolMap.put("R8", Integer.valueOf(8));
		this.symbolMap.put("R9", Integer.valueOf(9));
		this.symbolMap.put("R10", Integer.valueOf(10));
		this.symbolMap.put("R11", Integer.valueOf(11));
		this.symbolMap.put("R12", Integer.valueOf(12));
		this.symbolMap.put("R13", Integer.valueOf(13));
		this.symbolMap.put("R14", Integer.valueOf(14));
		this.symbolMap.put("R15", Integer.valueOf(15));
		this.symbolMap.put("SCREEN", Integer.valueOf(16384));
		this.symbolMap.put("KBD", Integer.valueOf(24576));
    }

    public void addEntry(String symbol, int address)
    {
        this.symbolMap.put(symbol, Integer.valueOf(address));
    }

    public Boolean contains(String symbol)
    {
        return this.symbolMap.containsKey(symbol);
    }

    public int GetAddress(String symbol)
    {
        return this.symbolMap.get(symbol);
    }

    public void incrementDAddress()
    {
        this.dAddress++;
        if(this.dAddress > DATA_ENDING_ADDRESS)
        {
            throw new RuntimeException();
        }
    }

    public void incrementPAddress()
    {
        this.pAddress++;
        if(this.pAddress > PROGRAM_ENDING_ADDRESS)
        {
            throw new RuntimeException();
        }
    }

    public int getDAddress()
    {
        return this.dAddress;
    }

    public int getPAddress()
    {
        return this.pAddress;
    }
}
