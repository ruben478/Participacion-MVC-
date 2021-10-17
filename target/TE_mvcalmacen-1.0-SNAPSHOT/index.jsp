<!--PARTICIPACION- MVC-->
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Producto"%>
<%
    ArrayList<Producto> lista = (ArrayList<Producto>)session.getAttribute("listaproducto");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LISTADO DE PRODUCTOS</h1>
        <a href="MainController?opcion=nuevo">Registrar Nuevo Producto</a>
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>DESCRIPCION</th>
                <th>CANTIDAD</th>
                <th>PRECIO</th>
                <th></th>
                <th></th>
            </tr>
            <%
              if (lista != null) {
                  for (Producto i:lista) {
            %>
            <tr>
                <td><%= i.getId()%></td>
                <td><%= i.getDescripcion()%></td>
                <td><%= i.getCantidad()%></td>
                <td><%= i.getPrecio()%></td> 
                <td><a href="MainController?opcion=editar&id=<%=i.getId() %>">Modificar Registro</a> </td>
                <td><a href="MainController?opcion=eliminar&id=<%=i.getId() %>" onclick="return confirm('Esta seguro de eliminar el registro?');">Eliminar Registro</a> </td>
            </tr>
            <%
                }
             }
            %>
        </table>
    </body>
</html>
