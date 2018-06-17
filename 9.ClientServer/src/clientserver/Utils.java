package clientserver;

import java.io.Closeable;
import java.io.IOException;

public class Utils //Some utils useful for correct therads closing/joining/waiting. etc. Copied from the example
{
	public static boolean close( Closeable object ) //Closes anythig is possible to close. Can be used for both threds as streams
	{
		try
		{
                    object.close();
			
                    return true;
		}
		catch ( IOException e )
		{
                    return false;
		}
	}
	
	public static boolean join( Thread thread ) //joins threads (makes them wait for each other)
	{
		try
		{
                    thread.join();
			
                    return true;
		}
		catch ( InterruptedException e )
		{
                    return false;
		}
	}
	
	public static boolean join( Thread thread, long milliseconds ) //same, with a time period specified
	{
		try
		{
                    thread.join( milliseconds );
			
                    return true;
		}
		catch ( InterruptedException e )
		{
                    return false;
		}
	}
	
	public static boolean sleep( long milliseconds ) //sleep for time
	{
		try
		{
                    Thread.sleep( milliseconds );
			
                    return true;
		}
		catch ( InterruptedException e )
		{
                    return false;
		}
	}
	
}
