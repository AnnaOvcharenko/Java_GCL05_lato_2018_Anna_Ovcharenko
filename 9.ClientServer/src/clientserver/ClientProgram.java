
package clientserver;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * Program starts a client which tries to connect to a server, throws an IOException if fails
 */
public class ClientProgram {
    public static void main(String[] args) throws UnknownHostException {
        CustomClient myClient = new CustomClient();
        
        String address ="127.0.0.1"; //localhost
	int port = 8080;
        try
	{
            myClient.connect( address, port ); //tries to connect
            System.out.println( "Client connected." );
	}
	catch ( IOException ex )
	{
            System.out.println( "Client connection problem. Check address, port or server status." );
            return;
	}
    }
}
