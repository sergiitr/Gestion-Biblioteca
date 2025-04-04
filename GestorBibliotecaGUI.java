import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GestorBibliotecaGUI extends JFrame {

    private ArrayList<Libro> libros = new ArrayList<>();
    private JTextField txtTitulo, txtAutor, txtAnio;
    private JTextArea areaLibros;

    public GestorBibliotecaGUI() {
        setTitle("Gestor de Biblioteca");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Panel superior con campos de entrada
        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del Libro"));

        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtAnio = new JTextField();

        panelDatos.add(new JLabel("Título:"));
        panelDatos.add(txtTitulo);
        panelDatos.add(new JLabel("Autor:"));
        panelDatos.add(txtAutor);
        panelDatos.add(new JLabel("Año:"));
        panelDatos.add(txtAnio);

        JButton btnAgregar = new JButton("Añadir libro");
        panelDatos.add(btnAgregar);

        JButton btnMostrar = new JButton("Mostrar libros");
        panelDatos.add(btnMostrar);

        panel.add(panelDatos, BorderLayout.NORTH);

        // Área de resultados
        areaLibros = new JTextArea();
        areaLibros.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaLibros);
        panel.add(scroll, BorderLayout.CENTER);

        // Panel inferior con búsqueda y eliminación
        JPanel panelInferior = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField txtBuscar = new JTextField();
        JButton btnBuscar = new JButton("Buscar por título");
        JTextField txtEliminar = new JTextField();
        JButton btnEliminar = new JButton("Eliminar por título");

        panelInferior.setBorder(BorderFactory.createTitledBorder("Buscar / Eliminar"));
        panelInferior.add(txtBuscar);
        panelInferior.add(btnBuscar);
        panelInferior.add(txtEliminar);
        panelInferior.add(btnEliminar);

        panel.add(panelInferior, BorderLayout.SOUTH);

        add(panel);

        // === ACCIONES ===

        btnAgregar.addActionListener(e -> {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int anio;

            try {
                anio = Integer.parseInt(txtAnio.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Año inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            libros.add(new Libro(titulo, autor, anio));
            JOptionPane.showMessageDialog(this, "Libro añadido.");
            txtTitulo.setText("");
            txtAutor.setText("");
            txtAnio.setText("");
        });

        btnMostrar.addActionListener(e -> {
            areaLibros.setText("");
            if (libros.isEmpty()) {
                areaLibros.setText("No hay libros registrados.");
                return;
            }

            for (Libro l : libros) {
                areaLibros.append("Título: " + l.getTitulo() + ", Autor: " + l.getAutor() + ", Año: " + l.getAnio() + "\n");
            }
        });

        btnBuscar.addActionListener(e -> {
            String tituloBuscar = txtBuscar.getText();
            boolean encontrado = false;
            areaLibros.setText("");
            for (Libro l : libros) {
                if (l.getTitulo().equalsIgnoreCase(tituloBuscar)) {
                    areaLibros.append("Título: " + l.getTitulo() + ", Autor: " + l.getAutor() + ", Año: " + l.getAnio() + "\n");
                    encontrado = true;
                }
            }
            if (!encontrado) {
                areaLibros.setText("Libro no encontrado.");
            }
        });

        btnEliminar.addActionListener(e -> {
            String tituloEliminar = txtEliminar.getText();
            boolean eliminado = libros.removeIf(lib -> lib.getTitulo().equalsIgnoreCase(tituloEliminar));
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Libro eliminado.");
            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestorBibliotecaGUI().setVisible(true));
    }
}
