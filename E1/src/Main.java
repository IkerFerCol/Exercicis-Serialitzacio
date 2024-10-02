import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Assignatura[] assignaturas = new Assignatura[6];
        Scanner scanner = new Scanner(System.in);
        String[] nombresassignaturas = {"Programacion", "Lenguaje", "Entornos", "Base Datos", "Sistemas", "Fol"};

        for (int i = 0; i < assignaturas.length; i++) {
            assignaturas[i] = new Assignatura(nombresassignaturas[i]);
            System.out.print("Introduce la nota de la asignatura " + nombresassignaturas[i] + ": ");
            double nota = scanner.nextDouble();
            if (nota >= 0 && nota <= 10) {
                assignaturas[i].setNota(nota);
            } else {
                System.out.println("La nota no es valida");
                break;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Assignaturas.txt"))) {
            oos.writeObject(assignaturas);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Assignaturas.txt"))){
            Assignatura[] deserializado = (Assignatura[]) ois.readObject();
            double suma = 0;
            for (Assignatura assignatura : deserializado) {
                System.out.println(assignatura.toString());
                suma += assignatura.getNota();
            }
            double media = suma / deserializado.length;
            System.out.println("La media es: " + media);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
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
