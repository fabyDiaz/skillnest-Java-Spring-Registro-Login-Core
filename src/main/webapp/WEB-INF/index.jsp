<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registro e Inicio de Sesión</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <div class="container">
            <!-- FORMULARIO DE REGISTRO -->
            <div class="form-contenedor">
                <form:form action="/procesa/registro" method="POST" modelAttribute="usuario">
                    <h2>Registrarse</h2>

                    <div class="form-grupo">
                        <label for="nombreUsuario">Nombre de Usuario:</label>
                        <div class="input-error">
                            <form:input path="nombreUsuario" type="text" id="nombreUsuario"/>
                            <form:errors path="nombreUsuario" class="error-inline"/>
                        </div>
                    </div>

                    <div class="form-grupo">
                        <label for="nombre">Nombre:</label>
                        <div class="input-error">
                            <form:input path="nombre" type="text" id="nombre"/>
                            <form:errors path="nombre" class="error-inline"/>
                        </div>
                    </div>

                    <div class="form-grupo">
                        <label for="apellido">Apellido:</label>
                        <div class="input-error">
                            <form:input path="apellido" type="text" id="apellido"/>
                            <form:errors path="apellido" class="error-inline"/>
                        </div>
                    </div>

                    <div class="form-grupo">
                        <label for="correo">Correo Electrónico:</label>
                        <div class="input-error">
                            <form:input path="correo" type="email" id="correo"/>
                            <form:errors path="correo" class="error-inline"/>
                        </div>
                    </div>

                    <div class="form-grupo">
                        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                        <div class="input-error">
                            <form:input path="fechaNacimiento" type="date" id="fechaNacimiento"/>
                            <form:errors path="fechaNacimiento" class="error-inline"/>
                        </div>
                    </div>

                    <div class="form-grupo">
                        <label for="contrasena">Contraseña:</label>
                        <div class="input-error">
                            <form:input path="contrasena" type="password" id="contrasena"/>
                            <form:errors path="contrasena" class="error-inline"/>
                        </div>
                    </div>

                    <div class="form-grupo">
                        <label for="confirmacionContrasena">Confirmar Contraseña:</label>
                        <div class="input-error">
                            <form:input path="confirmacionContrasena" type="password" id="confirmacionContrasena"/>
                            <form:errors path="confirmacionContrasena" class="error-inline"/>
                        </div>
                    </div>

                    <input type="submit" value="Registrarse" class="btn">
                </form:form>
            </div>
            
            <!-- FORMULARIO DE LOGIN -->
            <div class="form-contenedor">
                <form:form action="/procesa/login" method="POST" modelAttribute="usuarioLogin">
                    <h2>Iniciar Sesión</h2>

                    <div class="form-grupo">
                        <label for="usuarioLogin">Nombre de Usuario:</label>
                        <form:input path="usuarioLogin" type="text" id="usuarioLogin"/>
                    </div>

                    <div class="form-grupo">
                        <label for="contrasenaLogin">Contraseña:</label>
                        <div class="input-error">
                            <form:input path="contrasenaLogin" type="password" id="contrasenaLogin"/>
                            <form:errors path="contrasenaLogin" class="error-inline"/>
                        </div>
                    </div>

                    <input type="submit" value="Iniciar Sesión" class="btn">
                </form:form>
            </div>
        </div>
    </body>
</html>