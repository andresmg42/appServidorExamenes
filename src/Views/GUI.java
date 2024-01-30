package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controllers.Controlador;

import Models.ModeloServidor.Multicast;

public class GUI extends JFrame {
    JPanel pestaña1, pestaña2, pestaña3, pCargar, pCargarNombre, pContenedor1, pContenedor2, pContenedor3;
    JTabbedPane pestañas;
    public JTextField tRuta, tNombre, tTiempo;
    JLabel lNombre, lTiempo, lRuta, lInfo;
    JButton bCrear, bHExamen, bCargarExamen;
    JTextArea areaInfo, areaInfo2;
    private Multicast multi;
    JFileChooser fcEscojerExamen;

    public GUI() {

        multi = new Multicast();
        setTitle("QUIZLET");
        setSize(800, 600);
        iniciarComponentes();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Multicast getMulti() {
        return multi;
    }

    public void iniciarComponentes() {
        pestañas = new JTabbedPane();
        iniciarPestaña1();
        iniciarPestaña2();

        add(pestañas);

        GestionEventos gestion = new GestionEventos();
        bCrear.addActionListener(gestion);
        bHExamen.addActionListener(gestion);
        bCargarExamen.addActionListener(gestion);

    }

    public void iniciarPestaña1() {
        pestaña1 = new JPanel(new BorderLayout());
        // pCargar = new JPanel(new GridLayout(3, 2));
        pCargar = new JPanel();
        pCargar.setLayout(null);

        // pCargarNombre = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));

        pContenedor1 = new JPanel();
        pContenedor1.setLayout(new BoxLayout(pContenedor1, BoxLayout.PAGE_AXIS));
        // tRuta = new JTextField(20);
        // tRuta.setText("path...");
        tNombre = new JTextField(20);
        tNombre.setSize(150, 20);
        tNombre.setLocation(120, 50);

        lRuta = new JLabel();
        lRuta.setSize(150, 20);
        lRuta.setLocation(120, 10);

        lNombre = new JLabel("nombre Examen:");
        lNombre.setSize(100, 20);
        lNombre.setLocation(10, 50);

        bCargarExamen = new JButton("Cargar");
        bCargarExamen.setSize(80, 20);
        bCargarExamen.setLocation(10, 10);

        // falta esto: //prueva para git

        lTiempo = new JLabel("tiempo:");
        lTiempo.setSize(100, 20);
        lTiempo.setLocation(10, 90);

        tTiempo = new JTextField(20);
        tTiempo.setSize(150, 20);
        tTiempo.setLocation(120, 90);

        bCrear = new JButton("Crear");
        bCrear.setSize(80, 20);
        bCrear.setLocation(10, 130);

        fcEscojerExamen = new JFileChooser();
        fcEscojerExamen.setCurrentDirectory(new File("src\\assets"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivoss de Texto (.txt)", "txt", "text");
        fcEscojerExamen.setFileFilter(filter);

        lInfo = new JLabel("informacion");

        areaInfo = new JTextArea(5, 20);

        pCargar.add(bCargarExamen);
        pCargar.add(lRuta);
        pCargar.add(lNombre);
        pCargar.add(tNombre);
        pCargar.add(lTiempo);
        pCargar.add(tTiempo);
        pCargar.add(bCrear);
        // pCargar.add(tRuta);
        // pCargarNombre.add(lNombre);
        // pCargarNombre.add(tNombre);
        // pCargarRuta.add(lTiempo);
        // pCargarRuta.add(tTiempo);

        pContenedor1.add(pCargar);
        // pContenedor1.add(pCargarNombre);
        // pContenedor1.add(bCargarExamen);
        // pContenedor1.add(bCrear);
        pContenedor1.add(new JScrollPane(areaInfo));
        pestaña1.add(pContenedor1, BorderLayout.CENTER);
        pestañas.addTab("Cargar Examen", pestaña1);

    }

    public void iniciarPestaña2() {
        pestaña2 = new JPanel();
        pContenedor2 = new JPanel(new FlowLayout());
        pContenedor3 = new JPanel(new FlowLayout());
        areaInfo2 = new JTextArea(10, 10);
        pContenedor3.add(areaInfo2);
        pContenedor2.add(pContenedor3);
        pestaña2.add(pContenedor2, BorderLayout.CENTER);
        pestañas.addTab("Hacer Examen", pestaña2);
        bHExamen = new JButton("hacer Ex");
        pContenedor2.add(bHExamen);

    }

    public JFileChooser getFcEscojerExamen() {
        return fcEscojerExamen;
    }

    public JLabel getlInfo() {
        return lInfo;
    }

    public void setlInfo(String info) {
        lInfo.setText(info);
    }

    public void mostrarMensaje(String mensaje) {
        areaInfo2.append(mensaje);
    }

    public class GestionEventos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bCrear) {

                if (!lRuta.getText().isEmpty() && !fcEscojerExamen.getSelectedFile().getPath().isEmpty()) {
                    Controlador.crearExamen();
                    int ultimoIndice = Controlador.Examenes.size() - 1;
                    System.out.println(Controlador.Examenes.get(0));
                    areaInfo.setText(
                            "examen creado con exito\n" + Controlador.Examenes.get(ultimoIndice).mostrarDatos());

                }

            }
            if (e.getSource() == bHExamen) {
                System.out.println(multi.enviarMensajeMulticast(Controlador.Examenes.get(0).getContenido()));
                System.out.println(Controlador.Examenes.get(0).getContenido());

            }
            if (e.getSource() == bCargarExamen) {
                if (fcEscojerExamen.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (fcEscojerExamen.getSelectedFile().exists())
                        lRuta.setText(fcEscojerExamen.getSelectedFile().getName());
                }
            }

        }

    }

}
