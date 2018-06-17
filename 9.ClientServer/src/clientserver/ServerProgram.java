/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

import java.io.IOException;

/**
 *
 * Starts a server
 */
public class ServerProgram {
    public static void main(String[] args) throws InterruptedException {
        CustomServer myServer = new CustomServer();
        
        int port = 8080;
	
        try
	{
            myServer.run( port ); //runs server listening on a port (waiting for clients)
            System.out.println( "Server started." );

	}
	catch ( IOException ex )
	{
            System.out.println( "Server starting problem. Check port availability." );
            return;
	}
    }
}
