import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class PerformanceTest
{
	public int iterations = 1; // How often do you want to parse each File?
	public long elapsedTime = 0;
	public long startTime = 0;
	public PrintWriter pw;
	public String s;
	public char[] c;
	public Process p;
	public String[] command = {"cmd"};
	ArrayList<Long> average;
	public long totalCspmf;
	public long totalCspmj;

	public PerformanceTest(String searchLoc, String resultLoc) throws Exception
	{
		totalCspmj = 0;
		totalCspmf = 0;
		pw = new PrintWriter(new File(resultLoc+"/PerformanceResult.txt"));
		s = "File                                    cspmf time (s)           cspmj time (s)\n\n";
		File folder = new File(searchLoc);
		elapsedTime = 0;
		startTime = 0;	
		average = new ArrayList<Long>();
		System.out.println("Generating performance results. Please wait...");
		generatePerformanceComparison(folder);
		String tcf = String.valueOf(((double)totalCspmf/1000000000.0)).substring(0,5);
		String tcj = String.valueOf(((double)totalCspmj/1000000000.0)).substring(0,5);
		s += "TOTAL                                   "+tcf+"                   "+tcj;
		pw.write(s);
		pw.close();
		System.out.println("Your performance results have been created succesfully!");
	}
	
	public void generatePerformanceComparison(File folder) throws Exception
	{
		for (File fileEntry : folder.listFiles()) 
		{		
			if (fileEntry.isDirectory())
			{
				generatePerformanceComparison(fileEntry);
			} 
			else if(getExtension(fileEntry.toString()).equals("csp"))
			{	
				cspmfCompile(fileEntry.toString());		
				totalCspmf += elapsedTime;
				c = Paths.get(fileEntry.toString()).getFileName().toString().toCharArray(); //get file name and convert to char
				for(int i=0;i<40;i++)
				{
					if(i<c.length){s+=c[i];}
					else{s+=" ";}
				}
				c = String.valueOf(((double)elapsedTime/1000000000.0)).substring(0,5).toCharArray();
				for(int i=0;i<25;i++)
				{
					if(i<c.length){s+=c[i];}
					else{s+=" ";}
				}				
				
				cspmjCompile(fileEntry.toString());
				totalCspmj += elapsedTime;
				s += String.valueOf(((double)elapsedTime/1000000000.0)).substring(0,5)+"\r\n";	
			}

		}			
	}
	
	public void cspmfCompile(String filepath) throws Exception
	{
			for(int i=0;i<iterations;i++)
			{	
				startTime = System.nanoTime(); 
				p = Runtime.getRuntime().exec("src\\test\\java\\cspmf.exe translate "+filepath+" --prologOut=src\\test\\java\\cspmfOUT.temp");				
				p.waitFor();
				elapsedTime = System.nanoTime() - startTime;
				average.add(elapsedTime);
				p.destroy();	
			}
			elapsedTime = getAverage();
			Files.delete(Paths.get("src\\test\\java\\cspmfOUT.temp"));		
	}
	
	public void cspmjCompile(String filepath) throws Exception
	{	
		for(int i=0;i<iterations;i++)
		{	
			startTime = System.nanoTime(); 
			p = Runtime.getRuntime().exec("java -jar build\\libs\\cspmj.jar -parse "+filepath+" --prologOut=build\\libs\\cspmjOUT.temp");
			p.waitFor();
			elapsedTime = System.nanoTime() - startTime;
			average.add(elapsedTime);
			p.destroy();
		}
			elapsedTime = getAverage();
			Files.delete(Paths.get("build\\libs\\cspmjOUT.temp.pl"));
	}	
	
	public String getPath()
	{
		
		File temp = new File("temp.temp");
		String absolutePath = temp.getAbsolutePath();				
		String filePath = absolutePath.
		substring(0,absolutePath.lastIndexOf(File.separator));						
		return filePath;
	}	
	
	public String getExtension(String filename)
	{
		if (filename == null) 
		{
			return null;
		}
		int extensionPos = filename.lastIndexOf('.');
		int lastUnixPos = filename.lastIndexOf('/');
		int lastWindowsPos = filename.lastIndexOf('\\');
		int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
		int index = lastSeparator > extensionPos ? -1 : extensionPos;
		if (index == -1) 
		{
			return "";
		} 
		else 
		{
			return filename.substring(index + 1);
		}
	}	
	
	public long getAverage()
	{
		long a = 0;
		for(int i=0;i<average.size();i++)
		{
			a += average.get(i);
		}
		a = a/average.size();
		average.clear();
		return a;
	}
	
	
	public static void main(String[] args)
	{
		try
		{
			PerformanceTest pt = new PerformanceTest(args[0],args[1]);
		}
		catch(Exception e)
		{
			System.out.println("An Exception was thrown. "+e.getMessage());
		}
	}
}