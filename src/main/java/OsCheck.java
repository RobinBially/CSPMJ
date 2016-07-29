import java.lang.System;
import java.util.Locale;

public class OsCheck 
{
  public enum OSType 
  {
    Windows, MacOS, Linux64, Linux32, Other
  };

  protected static OSType detectedOS;

  public static OSType getOperatingSystemType() 
  {
    if (detectedOS == null) 
	{
	  String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
	  String osArch = System.getProperty("os.arch");

      if(osName.indexOf("mac") >= 0) 
	  {
        detectedOS = OSType.MacOS;
      } 
	  else if(osName.indexOf("win") >= 0) 
	  {
        detectedOS = OSType.Windows;
      }
	  else if (osName.indexOf("nux") >= 0 && osArch.endsWith("32")) 
	  {
        detectedOS = OSType.Linux32;
      } 	  
	  else if (osName.indexOf("nux") >= 0 && osArch.endsWith("64")) 
	  {
        detectedOS = OSType.Linux64;
      } 
	  else 
	  {
        detectedOS = OSType.Other;
      }
    }
	//System.out.println("OSNAME "+detectedOS);
    return detectedOS;
  }
}