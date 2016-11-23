<%-- 
    Document   : index
    Created on : 23-nov-2016, 4:09:04
    Author     : alfas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Game</title>
        <style type="text/css">
            h1{
                font-family: "Times New Roman", Times, serif;
            }
            body {
                background-color: lightblue;
                background-image: url("imagenes/orig_175039.jpg");
            }
  			form {
			    /* Sólo para centrar el formulario a la página */
			    margin: 0 auto;
			    width: 400px;
			    /* Para ver el borde del formulario */
			    padding: 1em;
			    border: 2px solid #CCC;
			    border-radius: 1em;
                            border-color: #ffffff; 
				}
			label {
			    /* Para asegurarse que todos los labels tienen el mismo tamaño y están alineados correctamente */
			    margin-top: 10px;
			    display: inline-block;
			    width: 90px;
			    text-align: left;
                            color: white;
			}
			
			input:focus{
			    /* Para dar un pequeño destaque en elementos activos*/
			    border-color: #000;
			}
		</style>
        <script type="text/javascript">
            function ventanaRegist() {
                document.getElementById('formlog').style.display = 'none';
                document.getElementById('formregist').style.display = 'block';
                $scope.greeting = 'Registro';
            }
            function ventanaLogin() {
                document.getElementById('formregist').style.display = 'none';
                document.getElementById('formlog').style.display = 'block';
            }
        </script>
    </head>
    <body>
        <div id="formlog">
            <h1 style="text-align: center;margin-top:50px;color: white">Park The Spacecraft</h1>
            <div  style="text-align: center;margin-top: 10%">
                <form action="/ServletJPA/index" method="post">
                    <label>Usuario:</label><input type="text" name="name"/><br>
                    <label>Contraseña:</label><input type="password" name="passwd"/><br><br>
                    <input type="submit" value="Entrar">
                    <input type="button" style="margin-left: 20px" onclick="ventanaRegist()" value="Registrarse" ></input>
                    
                </form>
            </div>
        </div>
        <div id="formregist" style="display:none;">
            <h1 style="text-align: center;margin-top:50px;color: white" >Pagina de Registro</h1>
            <div style="text-align: center;margin-top: 10%;">
                <form action="/3.4.3ServletJPA/registro" method="post">
                    <label>Usuario: </label><input type="text"  name="nick" required/><br>
                    <label>Contraseña: </label><input type="password" name="passwd1" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="La contraseña tendrá minimo 8 caracteres y tiene que contener minimo una letra mayuscula, minuscula y numero."required/><br>
                    <label>Repite la Contraseña: </label><input type="password" name="passwd2" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"  required/><br>
                    <label>Email: </label><input type="email" name="email" required/><br><br>
                    <input type="submit" value="Registrarse">
                    <input type="button" style="margin-left: 20px" onclick="ventanaLogin()" value="Pagina de Login" ></input>                    
                </form>
                
            </div>
        </div>
    </body>
</html>
