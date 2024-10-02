import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Double> lista = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String[] nombresassignaturas = {"Programacion", "Lenguaje", "Entornos", "Base Datos", "Sistemas", "Fol"};

        for (String asignatura : nombresassignaturas) {
            System.out.print("Introduce la nota de la asignatura " + asignatura + ": ");
            double nota = scanner.nextDouble();
            if (nota >= 0 && nota <= 10) {
                lista.put(asignatura, nota);
            } else {
                System.out.println("La nota no es valida.");
                break;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Assignaturas.txt"))) {
            oos.writeObject(lista);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Assignaturas.txt"))){
            HashMap<String, Double> deserializado = (HashMap<String, Double>) ois.readObject();
            double suma = 0;
            for (String assignatura : deserializado.keySet()) {
                double nota = deserializado.get(assignatura);
                System.out.println(assignatura + ", Nota: " + nota);
                suma += nota;
            }
            double media = suma / deserializado.size();
            System.out.println("La media es: " + media);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.close();

    }
}


class Assignatura implements Serializable {
    private String nombre;
    private double nota;

    public Assignatura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    public String toString() {
        return "Nombre: " + nombre + ", Nota: " + nota;
    }
}
