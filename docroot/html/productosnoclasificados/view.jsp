<%@include file="../init.jspf" %>
<% JSONArray productos=(JSONArray) request.getAttribute("productosNoClasificados"); %>
<div class="box">
   <div class="box-header">
     <h3 class="box-title">Productos no clasificados</h3>
   </div>
   <!-- /.box-header -->
   <div class="box-body no-padding">
     <table class="table table-striped">
       <tr>
         <th>Nombre producto</th>
         <th>Tipo producto</th>
       </tr>
      <%for(int i=0;i<productos.length();i++){ %>
       <tr>
       		
         <td><%=productos.getJSONObject(i).getString("n_nombre") %></td>
         <td><span class="badge bg-red"><%=productos.getJSONObject(i).getString("c_tipo_producto") %></span></td>
       </tr>
       <%} %>
     </table>
   </div>
   <!-- /.box-body -->
 </div>
 <!-- /.box -->
