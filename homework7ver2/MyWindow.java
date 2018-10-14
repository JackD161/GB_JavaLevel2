package homework7ver2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MyWindow extends JFrame {
    private JTextField jtf ;
    private JTextArea jta ;
    private final String SERVER_ADDR = "localhost" ;
    private final int SERVER_PORT = 8189 ;
    private Socket socket ;
    private DataInputStream in ;
    private DataOutputStream out;
    public MyWindow() {
        try {
            socket = new Socket ( "localhost" , 8189 );
            in = new DataInputStream( socket . getInputStream ());
            out = new DataOutputStream( socket . getOutputStream ());
            setAuthorized ( false );
            Thread t = new Thread (() -> {
                try {
                    while ( true ) {
                        String str = in . readUTF ();
                        if ( str . startsWith ( "/authok" )) {
                            setAuthorized ( true );
                            break ;
                        }
                        jta.append( str + "\n" );
                    }
                    while ( true ) {
                        String str = in . readUTF ();
                        if ( str . equals ( "/end" )) {
                            break ;
                        }
                        jta . append ( str + "\n" );
                    }
                } catch ( IOException e ) {
                    e . printStackTrace ();
                } finally {
                    try {
                        socket . close ();
                    } catch ( IOException e ) {
                        e . printStackTrace ();
                    }
                    setAuthorized ( false );
                }
            });
            t . setDaemon ( true );
            t . start ();
        } catch ( IOException e ) {
            e . printStackTrace ();
        }
        setBounds ( 600 , 300 , 500 , 500 );
        setTitle ( "Client" );
        setDefaultCloseOperation ( WindowConstants . EXIT_ON_CLOSE );
        jta = new JTextArea ();
        jta . setEditable ( false );
        jta . setLineWrap ( true );
        JScrollPane jsp = new JScrollPane ( jta );
        add ( jsp , BorderLayout. CENTER );
        JPanel bottomPanel = new JPanel ( new BorderLayout ());
        add ( bottomPanel , BorderLayout . SOUTH );
        JButton jbSend = new JButton ( "SEND" );
        bottomPanel . add ( jbSend , BorderLayout . EAST );
        jtf = new JTextField ();
        bottomPanel . add ( jtf , BorderLayout . CENTER );
        jbSend . addActionListener ( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                if (! jtf . getText (). trim (). isEmpty ()) {
                    sendMsg (jtf . getText ());
                    jtf . grabFocus ();
                }
            }
        });
        jtf . addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                sendMsg (jtf . getText ());
            }
        });
        new Thread ( new Runnable () {
            @Override
            public void run () {
                try {
                    while ( true ) {
                        if ( in.readBoolean()) {
                            String w = in.readUTF();
                            if ( w . equalsIgnoreCase ( "end session" )) break ;
                            jta . append ( w );
                            jta . append ( "\n" );
                        }
                    }
                } catch ( Exception e ) {
                }
            }
        }). start ();
        addWindowListener ( new WindowAdapter() {
            @Override
            public void windowClosing ( WindowEvent e ) {
                super . windowClosing ( e );
                try {
                    out . writeUTF ( "end" );
                    out . flush ();
                    socket . close ();
                    out . close ();
                    in . close ();
                } catch ( IOException exc ) {
                }
            }
        });
        setVisible ( true );
    }

    private void setAuthorized(boolean b) {

    }

    public void sendMsg ( String msg ) {
        try {
            out . writeUTF ( msg );
        } catch ( IOException e ) {
            e . printStackTrace ();
        }
    }
    public static void main(String[] args) {
        new MyWindow();
    }
}
