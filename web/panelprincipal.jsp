<%-- 
    Document   : panelprincipal
    Created on : 28-nov-2016, 0:41:42
    Author     : alfas
--%>

<%@page import="jpa.AndlunRegistry"%>
<%@page import="jpa.AndlunUserGame"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css"/>
        <script type="text/javascript" src="js/funcionsEvents.js"></script>
        <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
        <title></title>
    </head>
    <body>
        <div class="contenedor">            
            <%String stnick = (String) request.getAttribute("nombreuser");%>
            <%List<AndlunRegistry> scoreP = (List) request.getAttribute("scoresPers");%>
            <form action="/3.4.3ServletJPA/ServletLogout" method="post">
                <input id="blogout" type="submit" value="Logout">
            </form>
            <p style="float: right;">Cuenta de <%=stnick%></p>
            <div class="titulo">Park The Spacecraft</div>
            <div id="pestanas">
                <ul id=lista>
                    <li id="pestana1"><a href='javascript:cambiarPestanna(pestanas,pestana1);'>Inicio</a></li>
                    <li id="pestana2"><a href='javascript:cambiarPestanna(pestanas,pestana2);'>Score Personal</a></li>
                    <li id="pestana3"><a href='javascript:cambiarPestanna(pestanas,pestana3);'>Score Mundial</a></li>
                </ul>
            </div>

            <body onload="javascript:cambiarPestanna(pestanas, pestana1);">

                <div id="contenidopestanas">
                    <div id="cpestana1">
                        <form action="/3.4.3ServletJPA/Game" method="get">
                            <center><input id="binicio" type="submit" value="Iniciar Partida"></center>
                        </form> 
                    </div>
                    <div id="cpestana2">                        
                        <center><table style="text-align: center">
                                <tr>
                                    <th colspan="3">Top 50 de <%=stnick%> </th>
                                </tr>
                                <%for (int i = 0; i < scoreP.size(); i++) {

                                %>
                                <tr>
                                    <td><%=i + 1%></td>
                                    <td>------------------------------------------------------------------</td>
                                    <td><%=scoreP.get(i).getSpeed()%></td>
                                </tr>
                                <%}%>
                            </table></center>
                    </div>
                    <div id="cpestana3">
                        top mundial
                    </div>
                </div>
        </div>
    </body>
</html>
