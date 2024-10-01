import java.io.*;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Asignatura[] asignaturas = new Asignatura[6];
        Scanner scanner = new Scanner(System.in);
        String[] nombres = {"Programacion", "Base de datos", "Lenguaje", "Entornos", "Sistemas", "Fol"};

        for (int i = 0; i < nombres.length; i++) {
            asignaturas[i] = new Asignatura(nombres[i]);
            System.out.print("Introduce la nota de " + nombres[i] + ": ");
            double nota = scanner.nextDouble();
            asignaturas[i].setNota(nota);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("notasDAM.ser"))) {
            oos.writeObject(asignaturas);
            System.out.println("Las notas se han guardado.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream("notasDAM.ser"))) {
            Asignatura[] notasdeserializadas = new Asignatura[6];
            double suma = 0;
            for (Asignatura asignatura : asignaturas) {
                System.out.println(asignatura);
                suma += asignatura.getNota();
            }
            double media = suma / asignaturas.length;
            System.out.println("Media: " + media);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
    }
}

class Asignatura implements Serializable {
    private String nombre;
    private double nota;

    public Asignatura(String nombre) {
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
        if (nota >= 0 && nota <= 10) {
            this.nota = nota;
        } else {
            System.out.println("Nota no válida");
        }
    }

    @Override
    public String toString() {
        return "Asignatura: " + nombre + ", Nota: " + nota;
    }
}