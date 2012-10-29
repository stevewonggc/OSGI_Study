package org.stevesolution.osgistudy.gettingstart.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.stevesolution.osgistudy.gettingstart.ExampleService;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl implements ExampleService
{
    // implementation methods go here...
    
    private ServerSocket ss = null;

    public ExampleServiceImpl(int port)
    {
        try
        {
            ss = new ServerSocket(port);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Socket socket = ss.accept();
                new CreateServerThread(socket);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private class CreateServerThread extends Thread
    {
        private BufferedReader in;
        private PrintWriter out;
        public CreateServerThread(Socket socket)
        {
            try
            {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            out.println("=================================================");
            out.println("==============Welcome to SteveSolution===========");
            out.println("=================================================");
            this.start();
        }

        @Override
        public void run()
        {
            String line = null;
            try
            {
                line = in.readLine();
                while(line.equalsIgnoreCase("bye"))
                {
                    out.println(line);
                    line = in.readLine();
                }
            } 
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    ss.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

