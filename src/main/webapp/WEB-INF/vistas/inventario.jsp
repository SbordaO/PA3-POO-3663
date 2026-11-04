<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.stockfacil.Producto" %>
<%!
    public String html(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Integer total = (Integer) request.getAttribute("total");
    String busqueda = (String) request.getAttribute("busqueda");
    String mensaje = request.getParameter("mensaje");
    String error = request.getParameter("error");
    String contexto = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StockFácil - Inventario</title>
    <style>
        * { box-sizing: border-box; }
        body { margin: 0; font-family: Arial, sans-serif; background: #f1f5f9; color: #1e293b; }
        main { max-width: 1000px; margin: 36px auto; padding: 0 16px; }
        h1 { color: #2563eb; margin-bottom: 4px; }
        .subtitulo { color: #64748b; margin-top: 0; }
        .panel { background: white; border-radius: 12px; padding: 22px; margin: 20px 0; box-shadow: 0 4px 16px #0000000d; }
        .campos { display: grid; grid-template-columns: 2fr 1fr 1fr auto; gap: 12px; align-items: end; }
        label { display: block; font-weight: bold; font-size: 14px; margin-bottom: 7px; }
        input, select { width: 100%; padding: 11px; border: 1px solid #cbd5e1; border-radius: 7px; font-size: 15px; }
        button, .enlace { padding: 11px 16px; border: 0; border-radius: 7px; background: #2563eb; color: white; cursor: pointer; text-decoration: none; display: inline-block; font-size: 14px; }
        button:hover, .enlace:hover { background: #1d4ed8; }
        .eliminar { background: #dc2626; }
        .eliminar:hover { background: #b91c1c; }
        .herramientas { display: flex; gap: 10px; flex-wrap: wrap; align-items: end; }
        .herramientas form { display: flex; gap: 8px; align-items: end; }
        .aviso { padding: 12px; border-radius: 7px; background: #dcfce7; color: #166534; }
        .aviso.error { background: #fee2e2; color: #991b1b; }
        .tabla { overflow-x: auto; }
        table { width: 100%; border-collapse: collapse; }
        th, td { text-align: left; padding: 12px; border-bottom: 1px solid #e2e8f0; }
        th { color: #475569; }
        .vacio { color: #64748b; }
        @media(max-width: 720px) { .campos { grid-template-columns: 1fr; } .herramientas form { width: 100%; } }
    </style>
</head>
<body>
<main>
    <h1>StockFácil</h1>
    <p class="subtitulo">Gestión de productos en memoria con ArrayList y Collections</p>
    <% if (mensaje != null) { %><p class="aviso"><%= html(mensaje) %></p><% } %>
    <% if (error != null) { %><p class="aviso error"><%= html(error) %></p><% } %>
    <section class="panel">
        <h2>Registrar producto</h2>
        <form action="<%= contexto %>/registrar" method="post" accept-charset="UTF-8" class="campos">
            <div><label for="nombre">Nombre</label><input id="nombre" name="nombre" maxlength="100" required placeholder="Ej.: Teclado"></div>
            <div><label for="precio">Precio (S/)</label><input id="precio" name="precio" type="number" min="0.01" max="100000000" step="0.01" required></div>
            <div><label for="cantidad">Cantidad</label><input id="cantidad" name="cantidad" type="number" min="0" max="100000" step="1" required></div>
            <button type="submit">Agregar</button>
        </form>
    </section>
    <section class="panel">
        <h2>Inventario (<%= total == null ? 0 : total %> productos)</h2>
        <div class="herramientas">
            <form action="<%= contexto %>/buscar" method="get">
                <div><label for="buscar">Buscar por nombre</label><input id="buscar" name="nombre" value="<%= html(busqueda) %>" placeholder="Nombre del producto"></div>
                <button type="submit">Buscar</button>
            </form>
            <form action="<%= contexto %>/productos" method="get">
                <div><label for="orden">Ordenar</label><select id="orden" name="orden"><option value="">Registro</option><option value="nombre">Nombre</option><option value="precio">Precio</option></select></div>
                <button type="submit">Ordenar</button>
            </form>
            <a class="enlace" href="<%= contexto %>/productos">Ver todos</a>
        </div>
        <div class="tabla">
            <table>
                <thead><tr><th>ID</th><th>Producto</th><th>Precio</th><th>Cantidad</th><th>Acción</th></tr></thead>
                <tbody>
                <% if (productos != null && !productos.isEmpty()) {
                    for (Producto p : productos) { %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= html(p.getNombre()) %></td>
                        <td>S/ <%= String.format(Locale.US, "%.2f", p.getPrecio()) %></td>
                        <td><%= p.getCantidad() %></td>
                        <td><form action="<%= contexto %>/eliminar" method="post" onsubmit="return confirm('¿Eliminar este producto?');">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button class="eliminar" type="submit">Eliminar</button>
                        </form></td>
                    </tr>
                <% } } else { %>
                    <tr><td colspan="5" class="vacio">No hay productos para mostrar.</td></tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>
