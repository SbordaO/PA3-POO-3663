package com.stockfacil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Inventario {
    private final ArrayList<Producto> productos = new ArrayList<>();
    private int siguienteId = 1;

    public synchronized Producto agregarProducto(String nombre, double precio, int cantidad) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.trim().length() > 100)
            throw new IllegalArgumentException("El nombre debe tener entre 1 y 100 caracteres.");
        if (!Double.isFinite(precio) || precio <= 0 || precio > 100000000)
            throw new IllegalArgumentException("El precio debe ser mayor a cero y no superar 100000000.");
        if (cantidad < 0 || cantidad > 100000)
            throw new IllegalArgumentException("La cantidad debe estar entre 0 y 100000.");
        Producto producto = new Producto(siguienteId++, nombre.trim(), precio, cantidad);
        productos.add(producto);
        return producto;
    }

    public synchronized List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public synchronized Producto buscarPorId(int id) {
        for (Producto p : productos) if (p.getId() == id) return p;
        return null;
    }

    public synchronized List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        if (nombre == null || nombre.trim().isEmpty()) return encontrados;
        String texto = nombre.trim().toLowerCase(Locale.ROOT);
        for (Producto p : productos)
            if (p.getNombre().toLowerCase(Locale.ROOT).contains(texto)) encontrados.add(p);
        return encontrados;
    }

    public synchronized boolean eliminarProducto(int id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

    public synchronized List<Producto> ordenarPorPrecio() {
        List<Producto> ordenados = new ArrayList<>(productos);
        Collections.sort(ordenados, Comparator.comparingDouble(Producto::getPrecio));
        return ordenados;
    }

    public synchronized List<Producto> ordenarPorNombre() {
        List<Producto> ordenados = new ArrayList<>(productos);
        Collections.sort(ordenados, Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER));
        return ordenados;
    }

    public synchronized int contarProductos() { return productos.size(); }
}
