<%-- 
    Document   : panelprincipal
    Created on : 28-nov-2016, 0:41:42
    Author     : alfas
--%>

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
            <form action="/3.4.3ServletJPA/ServletLogout" method="post">
                <input type="submit" name="Logout" value="Logout">
            </form>
            <div class="titulo">Park The Spacecraft</div>
            <div id="pestanas">
                <ul id=lista>
                    <li id="pestana1"><a href='javascript:cambiarPestanna(pestanas,pestana1);'>Inicio</a></li>
                    <li id="pestana2"><a href='javascript:cambiarPestanna(pestanas,pestana2);'>Score Personal</a></li>
                    <li id="pestana3"><a href='javascript:cambiarPestanna(pestanas,pestana3);'>Score Mundial</a></li>
                </ul>
            </div>
            
            <body onload="javascript:cambiarPestanna(pestanas,pestana1);">
       
            <div id="contenidopestanas">
                <div id="cpestana1">
                   Boton Game 
                </div>
                <div id="cpestana2">
                    Top 50 de puntuacion propia.
                </div>
                <div id="cpestana3">
                    top 100 de puntuacion global.
                </div>
            </div>
        </div>
    </body>
</html>
