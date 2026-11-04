package com.stockfacil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/registrar")
public class RegistroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Inventario inventario = (Inventario) getServletContext().getAttribute("inventario");
            inventario.agregarProducto(nombre, precio, cantidad);
            response.sendRedirect(request.getContextPath() + "/productos?mensaje=" + codificar("Producto registrado correctamente."));
        } catch (IllegalArgumentException | NullPointerException ex) {
            String mensaje = ex instanceof NumberFormatException || ex instanceof NullPointerException
                    ? "Ingresá un precio y una cantidad válidos." : ex.getMessage();
            response.sendRedirect(request.getContextPath() + "/productos?error=" + codificar(mensaje));
        }
    }

    private String codificar(String valor) { return URLEncoder.encode(valor, StandardCharsets.UTF_8); }
}
