package assembler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class Main 
{
    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            System.err.println("No File");
            System.exit(1);
        }
        File source = new File(args[0].trim());
		if (!source.exists()) {
			System.err.println("Specified source file could not be found.");
			System.exit(2);
		}
        String path = source.getAbsolutePath();
        String file = source.getName();
        int fileExtendIndex = file.lastIndexOf(".");
        String fileNoExtend = file.substring(0, fileExtendIndex);
        int fileNameIndex = source.getAbsolutePath().indexOf(source.getName());
        String sourceDirect = path.substring(0, fileNameIndex);
        String outputPath = sourceDirect + fileNoExtend + ".hack";
        File output = new File(outputPath);
        try 
        {
            if(output.exists())
            {
                output.delete();
            }
            output.createNewFile();

            Assembler assembler = new Assembler(source, output);
            assembler.translate();

            StringWriter status = new StringWriter();
            status.append("Success on");
            status.append(path);
            status.append(" to ");
            status.append(outputPath);
            System.out.println(status.toString());
        } catch (IOException e)
        {
            System.err.println("Error Occured");
            System.exit(3);
        }
    }
}
