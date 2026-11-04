package com.stockfacil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buscar")
public class BusquedaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        if (nombre == null || nombre.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/productos");
            return;
        }
        Inventario inventario = (Inventario) getServletContext().getAttribute("inventario");
        request.setAttribute("productos", inventario.buscarPorNombre(nombre));
        request.setAttribute("total", inventario.contarProductos());
        request.setAttribute("busqueda", nombre);
        try {
            request.getRequestDispatcher("/WEB-INF/vistas/inventario.jsp").forward(request, response);
        } catch (jakarta.servlet.ServletException ex) {
            throw new IOException(ex);
        }
    }
}
