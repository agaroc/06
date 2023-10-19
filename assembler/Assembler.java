package assembler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
public class Assembler 
{
    private File code;
    private BufferedWriter write;
    private Code encoder;
    private SymbolTable table;

    public Assembler(File source, File target) throws IOException
    {
        this.code = source;
        FileWriter fileWrite = new FileWriter(target);
        this.write = new BufferedWriter(fileWrite);
        this.encoder = new Code();
        this.table = new SymbolTable();
    }
    
    public void translate() throws IOException
    {
        this.recordLabel();
        this.parse();
    }

    private void recordLabel() throws IOException
    {
        Parser parser = new Parser(this.code);
        while(parser.moreCommands())
        {
            parser.advance();
            CommandType command = parser.commandType();
            if(command.equals(CommandType.L_COMMAND))
            {
                String symbol = parser.symbol();
                int address = this.table.getPAddress();
                this.table.addEntry(symbol, address);
            }
            else
            {
                this.table.incrementPAddress();
            }
        }
        parser.close();
    }

    private void parse() throws IOException
    {
        Parser parser = new Parser(this.code);
        while(parser.moreCommands())
        {
            parser.advance();
            CommandType command = parser.commandType();
            String instruction = null;

            if(command.equals(CommandType.A_COMMAND))
            {
                String symbol = parser.symbol();
                Character first = symbol.charAt(0);
                boolean isSymbol = (!Character.isDigit(first));

                String address = "";

                if(isSymbol)
                {
                    boolean symbolInTable = this.table.contains(symbol);
                    if(!symbolInTable)
                    {
                        int dAddress = this.table.getDAddress();
                        this.table.addEntry(symbol, dAddress);
                        this.table.incrementDAddress();
                    }

                    address = Integer.toString(this.table.GetAddress(symbol));
                }
                else
                {
                    address = symbol;
                }
                instruction = this.formatA(address);
            }
            else if(command.equals(CommandType.C_COMMAND))
            {
                String comp = parser.comp();
                String dest = parser.dest();
                String jump = parser.jump();
                instruction = this.formatC(comp, dest, jump);
            }

            if(!command.equals(CommandType.L_COMMAND))
            {
                this.write.write(instruction);
                this.write.newLine();
            }
        }
        parser.close();
        this.write.flush();
        this.write.close();
    }  
    private String formatA(String address)
    {
        String formatNum = this.encoder.toBinary(address);
        return "0" + formatNum;
    }
    
    private String formatC(String comp, String dest, String jump)
    {
        StringWriter instruction = new StringWriter();
        instruction.append("111");
        instruction.append(this.encoder.comp(comp));
        instruction.append(this.encoder.dest(dest));
        instruction.append(this.encoder.jump(jump));
        return instruction.toString();
    }
}
