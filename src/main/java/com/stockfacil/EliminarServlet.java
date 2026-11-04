package com.stockfacil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/eliminar")
public class EliminarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mensaje;
        String tipo;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Inventario inventario = (Inventario) getServletContext().getAttribute("inventario");
            boolean eliminado = inventario.eliminarProducto(id);
            tipo = eliminado ? "mensaje" : "error";
            mensaje = eliminado ? "Producto eliminado correctamente." : "No se encontró el producto.";
        } catch (NumberFormatException ex) {
            tipo = "error";
            mensaje = "ID de producto inválido.";
        }
        response.sendRedirect(request.getContextPath() + "/productos?" + tipo + "=" + URLEncoder.encode(mensaje, StandardCharsets.UTF_8));
    }
}
