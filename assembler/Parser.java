package assembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Parser
{
    private String currentLine;
    private String nextLine;
    private BufferedReader buff;

    public Parser(File file) throws IOException
    {
        if(file == null)
        {
            throw new IOException("invalid file");
        }
        if (!file.exists()) {
			throw new FileNotFoundException(file.getAbsolutePath());
		}
        this.buff = new BufferedReader(new FileReader(file));
        this.currentLine = null;
        this.nextLine = this.getNext();
    }

    private String getNext() throws IOException
    {
        String nextLine;
        do
        {
            nextLine = this.buff.readLine();
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
    public void finalize() throws IOException
    {
        this.close();
    }

    public void close() {
		try {
			this.buff.close();
		} catch (IOException e) {
		}
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
            return CommandType.L_COMMAND;
        } 
        else if(line.startsWith("@"))
        {
            return CommandType.A_COMMAND;
        }
        else
        {
            return CommandType.C_COMMAND;
        }
    }

    public String dest()
    {
        String line = this.currentLine.trim();
        int index = line.indexOf("=");
        if(index == -1)
        {
            return null;
        }
        else
        {
            return line.substring(0, index);
        }
    }

    public String symbol()
    {
        String line = this.currentLine.trim();
        CommandType command = this.commandType();
        if(command.equals(CommandType.L_COMMAND))
        {
            return line.substring(1, this.currentLine.length()-1);
        }
        else if(command.equals(CommandType.A_COMMAND))
        {
            return line.substring(1);
        }
        else
        {
            return null;
        }
    }

    public String comp()
    {
        String line = this.currentLine.trim();
        int index = line.indexOf("=");
        if(index != -1)
        {
            line = line.substring(index+1);
        }
        int compIndex = line.indexOf(";");
        if(compIndex == -1)
        {
            return line;
        }
        else
        {
            return line.substring(0, compIndex);
        }
    }

    public String jump()
    {
        String line = this.currentLine.trim();
        int compIndex = line.indexOf(";");
        if (compIndex == -1) {
			return null;
		} else {
			return line.substring(compIndex + 1);
		}
    }


}