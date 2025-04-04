import java.util.ArrayList;
import java.util.Scanner;

public class GestorBiblioteca {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Libro> libros = new ArrayList<>();
        int opcion;

        do {
            System.out.println("\n==== GESTOR DE BIBLIOTECA ====");
            System.out.println("1. Añadir libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Buscar libro por título");
            System.out.println("4. Mostrar todos los libros");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    // Añadir libro
                    String titulo,autor;
                    int anio;
                    System.out.print("Introduce nombre del libro: ");
                    titulo=sc.nextLine();
                    System.out.print("Introduce autor: ");
                    autor=sc.nextLine();
                    System.out.println("Introduce anio lanzamiento: ");
                    anio=sc.nextInt();
                    Libro lib = new Libro(titulo, autor, anio);

                    libros.add(lib);
                    break;
                case 2:
                    // Eliminar libro por título
                    String tit;
                    System.out.println("¿Que libro deseas eliminar?");
                    tit=sc.nextLine();

                    for (int i = 0; i < libros.size(); i++) {
                        if (libros.get(i).getTitulo().equals(tit)) {
                            libros.remove(i);
                        }
                    }
                    break;
                case 3:
                    // Buscar por título
                    String _tit;
                    System.out.println("¿Que libro deseas buscar?");
                    _tit=sc.nextLine();

                    for (int i = 0; i < libros.size(); i++)
                        if (libros.get(i).getTitulo().equals(_tit))
                            libros.get(i).mostrarInformacion();
                        
                    break;
                case 4:
                    // Mostrar todos los libros
                    System.out.println(libros);
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}
