package com.stockfacil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productos")
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Inventario inventario = (Inventario) getServletContext().getAttribute("inventario");
        String orden = request.getParameter("orden");
        if ("precio".equals(orden)) request.setAttribute("productos", inventario.ordenarPorPrecio());
        else if ("nombre".equals(orden)) request.setAttribute("productos", inventario.ordenarPorNombre());
        else request.setAttribute("productos", inventario.listarProductos());
        request.setAttribute("total", inventario.contarProductos());
        request.setAttribute("orden", orden);
        request.getRequestDispatcher("/WEB-INF/vistas/inventario.jsp").forward(request, response);
    }
}
