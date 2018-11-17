/*
 * Clase ManejoArchivos
 */

package utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author excz010715
 * @param <T>
 */
public class ManejoArchivos<T> {
    
    private final Class<T> entidad;
    public ManejoArchivos(final Class<T> entidad){
        this.entidad = entidad;
    }

    public boolean registrar(final T t) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean resultado = false;
        try {
            File file = new File (t.getClass().getSimpleName()+".txt");
            fos = new FileOutputStream(file, true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.close();
            resultado = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    
    public ArrayList<T> obtenerListado() {
        ArrayList<T> listado = new ArrayList<>();
        ObjectInputStream ois = null;
        int indice = 0;
        File file = new File(entidad.getSimpleName()+".txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            while (true) {                
                ois = new ObjectInputStream(fis);
                T objeto = (T) ois.readObject();
                listado.add(indice, objeto);
                indice++;                
            }
        } catch (IOException ex) {
            System.err.println("Final de archivo");
        } catch (ClassNotFoundException ex) {
            System.err.println("Clase no encontrada");
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                System.err.println("No se puede cerrar archivo, porque no existe");
            }
        }
        return listado;
    }
    
    public boolean deleteArchivo(final T t){
        File file = new File (t.getClass().getSimpleName()+".txt");
        return file.delete();
    }
    
}
