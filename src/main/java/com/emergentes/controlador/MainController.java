//PARTICIPACION- MVC
package com.emergentes.controlador;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Producto productouno=new Producto();
        if (sesion.getAttribute("listaproducto")==null) {
            ArrayList<Producto> listados = new ArrayList<Producto>();
            sesion.setAttribute("listaproducto", listados);
        }
        ArrayList<Producto> lista = (ArrayList<Producto>)sesion.getAttribute("listaproducto");
        String opciones = request.getParameter("opcion");
        String opcion =(opciones != null)? opciones:"view";
        int id,posicion;
        switch (opcion) {
            case "nuevo":
                request.setAttribute("producto", productouno);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id=Integer.parseInt(request.getParameter("id"));
                posicion = BuscarIndice(request,id);
                productouno=lista.get(posicion);
                request.setAttribute("producto", productouno);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                id=Integer.parseInt(request.getParameter("id"));
                posicion = BuscarIndice(request,id);
                lista.remove(posicion);
                sesion.setAttribute("listaproducto", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>)sesion.getAttribute("listaproducto");
        Producto productouno=new Producto();
        productouno.setId(Integer.parseInt(request.getParameter("id")));
        productouno.setDescripcion(request.getParameter("descripcion"));
        productouno.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        productouno.setPrecio(Float.parseFloat(request.getParameter("precio")));
        int idaux = productouno.getId();
        if (idaux==0) {
            int ultimoid = BuscarUltimo(request);
            productouno.setId(ultimoid);
            lista.add(productouno);
        }else{
            lista.set(BuscarIndice(request,idaux), productouno);
        }
        sesion.setAttribute("listaproducto", lista);
        response.sendRedirect("index.jsp");
    }
    private int BuscarIndice (HttpServletRequest request,int id){
        int indice=0;
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("listaproducto");
        if (lista.size()>0) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId()==id) {
                    indice=i;
                }
            }
        }
        return indice;
    }
    private int BuscarUltimo (HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("listaproducto");
        int idauxiliar=0;
        for (Producto i:lista) {
            idauxiliar=i.getId();
        }
        return idauxiliar+1;
    }  
}
