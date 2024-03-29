/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import entidades.Producto;
import interfaces.IFachadaPersistencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.FachadaPersistencia;

/**
 * REST Web Service
 *
 * @author lv1013
 */
@Path("producto")
public class ProductoResource {

    IFachadaPersistencia fp;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductoResource
     */
    public ProductoResource() throws SQLException {
        fp = new FachadaPersistencia();
    }

    /**
     * Retrieves representation of an instance of ws.ProductoResource
     *
     * @return an instance of entidades.Producto
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductobyid(@PathParam("id") String id) {
        return Response.status(200).entity(fp.getProductoById(Integer.parseInt(id))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducto() {
        return Response.status(200).entity(fp.getProductos()).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProducto(@PathParam("id") int id) {
        try {
            fp.deleteProducto(id);
            return Response.status(200).build();
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(Producto producto) {
        return Response.status(200).entity(fp.actualizarProducto(producto)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Producto producto) {
        System.out.println(producto.getNombre());
        return Response.status(200).entity(fp.addProduct(producto)).build();
    }
}
