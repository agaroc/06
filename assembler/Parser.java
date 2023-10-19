package assembler;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parser
{
    private String currentLine;
    private String nextLine;
    private Scanner buff;

    public Parser(File file) throws IOException
    {
        if(file == null)
        {
            throw new IOException("invalid file");
        }
        this.buff = new Scanner(file);
        this.currentLine = null;
        this.nextLine = this.getNext();
    }

    private String getNext() throws IOException
    {
        String nextLine = "";
        do
        {
            nextLine = this.buff.nextLine();
            if(nextLine == null)
            {
                return null;
            }
        } while(nextLine.trim().isEmpty() || this.commentFlag(nextLine));

        int commentIndex = nextLine.indexOf("//");
        if(commentIndex != -1)
        {
            nextLine = nextLine.substring(0, commentIndex-1);
        }
        return nextLine;
    }

    private boolean commentFlag(String str)
    {
        return str.trim().startsWith("//");
    }

    @Override
    public void finalize()
    {
        this.close();
    }

    public void close()
    {
        this.buff.close();
    }

    public boolean moreCommands()
    {
        return (this.nextLine != null);
    }
    
    public void advance() throws IOException
    {
        this.currentLine = this.nextLine;
        this.nextLine = this.getNext();
    }

    public CommandType commandType()
    {
        String line = this.currentLine.trim();
        if(line.startsWith("(") && line.endsWith(")"))
        {
            return
        } 
    }
}