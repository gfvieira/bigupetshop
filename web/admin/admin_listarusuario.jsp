<%@page import="src.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%ArrayList<Usuario> list = (ArrayList<Usuario>) request.getAttribute("lista");%>
<%Usuario usuario = (Usuario) session.getAttribute("usuario");%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">

        <title>SIG-BASE</title>

        <!-- Bootstrap core CSS -->
        <!-- Bootstrap core CSS -->
        <link href="../assets/css/bootstrap.css" rel="stylesheet">
        <!--external css-->
        <link href="../assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="../assets/css/zabuto_calendar.css">
        <link rel="stylesheet" type="text/css" href="../assets/js/gritter/css/jquery.gritter.css" />
        <link rel="stylesheet" type="text/css" href="../assets/lineicons/style.css">    

        <!-- Custom styles for this template -->
        <link href="../assets/css/style.css" rel="stylesheet">
        <link href="../assets/css/style-responsive.css" rel="stylesheet">

        <script src="../assets/js/chart-master/Chart.js"></script>
        <script language=javascript type="text/javascript">
            dayName = new Array("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
            monName = new Array("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro");
            now = new Date;
        </script>
        <script src="../assets/js/time_session.js" type="text/javascript"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body onload="time()">

        <section id="container" >
            <!-- **********************************************************************************************************************************************************
            TOP BAR CONTENT & NOTIFICATIONS
            *********************************************************************************************************************************************************** -->
            <!--header start-->
            <header class="header black-bg">
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Clique Aqui Para Abrir/Fechar Menu"></div>
                </div>
                <!--logo start-->
                <a href="#" class="logo"><b>SIG-BASE || <%out.print(usuario.getTitulo());%></b></a>
                <!--logo end-->
                <div class="top-menu">
                    <ul class="nav pull-right top-menu">
                        <li>
                            <div style="margin-top: 22px; margin-right: 20px; color: white">
                                <div id="timeout"></div>
                            </div>
                        </li>
                        <li>
                            <div class="btn-group" style="margin-top: 15px">
                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fa fa-cog"></i> <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><a href="#">Perfil</a></li>
                                    <li><a href="#">Alterar Senha</a></li>
                                        <%if (usuario.getAdmin() == 1) {%>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="/sigbase/admin/admin_cadastro.jsp">Cadastrar Usuario</a></li>
                                    <li><a href="/sigbase/admin/ListarUsuario.jsp?type=99">Listar Usuarios</a></li>
                                        <%}%>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="/sigbase/LogOff.jsp">Sair</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </header>
            <!--header end-->
            <!-- **********************************************************************************************************************************************************
            MAIN SIDEBAR MENU
            *********************************************************************************************************************************************************** -->
            <!--sidebar start-->
            <aside>
                <div id="sidebar"  class="nav-collapse ">
                    <!-- sidebar menu start-->
                    <ul class="sidebar-menu" id="nav-accordion">
                        <h5 class="centered"><%out.print(usuario.getGraduacaoNome());%>-<%out.print(usuario.getGuerra());%></h5>
                        <h5 class="centered"> 
                            <script language=javascript type="text/javascript">
                                document.write(dayName[now.getDay() ] + ", " + now.getDate() + " de " + monName [now.getMonth() ] + " de " + now.getFullYear());
                            </script>
                        </h5>
                        <h5 class="centered"><div id="txt"></div></h5>
                        <li class="mt">
                            <a class="active" href="../<%out.print(usuario.getHome());%>">
                                <i class="fa fa-dashboard"></i>
                                <span>Principal <%out.print(usuario.getSetor());%></span>
                            </a>
                        </li>
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-bar-chart-o"></i>
                                <span>Listar</span>
                            </a>
                            <ul class="sub">
                                <li><a href="ListarUsuario.jsp?type=99">Todos</a></li>
                                <li><a href="ListarUsuario.jsp?type=999">Desativados</a></li>
                                <li><a href="ListarUsuario.jsp?type=1">Comando</a></li>
                                <li><a href="ListarUsuario.jsp?type=2">Assessorias</a></li>
                                <li><a href="ListarUsuario.jsp?type=3">Chefe de Departamento</a></li>
                                <li><a href="ListarUsuario.jsp?type=4">Encarregado</a></li>
                                <li><a href="ListarUsuario.jsp?type=5">Sargenteante</a></li>
                                <li><a href="ListarUsuario.jsp?type=10">BH-10</a></li>
                                <li><a href="ListarUsuario.jsp?type=20">BH-20</a></li>
                                <li><a href="ListarUsuario.jsp?type=30">BH-30</a></li>
                                <li><a href="ListarUsuario.jsp?type=40">BH-40</a></li>
                                <li><a href="ListarUsuario.jsp?type=50">BH-50</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </aside>
            <!--sidebar end-->
            <!-- **********************************************************************************************************************************************************
            MAIN CONTENT
            *********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h2 class="thin text-center"><%out.print(list.size());%> Usuario(s) Encontrado(s):</h2>
                                <table class="table table-striped table-advance table-hover">
                                    <hr>
                                    <thead>
                                        <tr>
                                            <th><i class="fa fa-angle-double-right"></i> Nip</th>
                                            <th><i class="fa fa-angle-double-right"></i> Usuario</th>                                 
                                            <th><i class="fa fa-angle-double-right"></i> Ramal</th>
                                            <th><i class="fa fa-angle-double-right"></i> Tipo de acesso</th>
                                            <th><i class="fa fa-angle-double-right"></i> Status</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for (Usuario user : list) {%>
                                        <tr>
                                            <td><%out.print(user.getNip());%></td>
                                            <td><%out.print(user.getGraduacaoNome());%>-<%out.print(user.getGuerra());%></td>
                                            <td><%out.print(user.getRamal());%></td>
                                            <td><%out.print(user.getAcessoNome());%></td>
                                            <%if (user.getAtivo() == 1 && (user.getNip().equals("12345678") || user.getNip().equals("09096418"))) {%>
                                            <td><span class="label label-primary label-mini">TESTE</span></td>
                                            <%} else if (user.getAtivo() == 1) {%>
                                            <td><span class="label label-success label-mini">ATIVO</span></td>
                                            <%} else {%>
                                            <td><span class="label label-danger label-mini">DESATIVADO</span></td>
                                            <%}%>
                                            <td>
                                                <form class="col-sm-2 control-label" action="ExibirUsuario.jsp" method="post">      
                                                    <button type="submit" title="EXIBIR USUARIO" class="btn btn-success btn-xs"><i class="fa fa-check"></i></button>
                                                    <input type="hidden" class="form-control" id="nip" name="nip" value="<% out.print(user.getNip()); %>" > 
                                                </form>
                                            </td>
                                        </tr>
                                        <% }%>


                                    </tbody>
                                </table>
                            </div><!--/form-panel-->
                        </div><!--/col-lg-12-->
                    </div><!--/row-->
                </section>
            </section>
        </section>

        <!-- js placed at the end of the document so the pages load faster -->
        <script src="assets2/js/jquery.js"></script>
        <script src="assets2/js/jquery-1.8.3.min.js"></script>
        <script src="assets2/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="assets2/js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="assets2/js/jquery.scrollTo.min.js"></script>
        <script src="assets2/js/jquery.nicescroll.js" type="text/javascript"></script>
        <script src="assets2/js/jquery.sparkline.js"></script>
        <!--common script for all pages-->
        <script src="assets2/js/common-scripts.js"></script>
        <script type="text/javascript" src="assets2/js/gritter/js/jquery.gritter.js"></script>
        <script type="text/javascript" src="assets2/js/gritter-conf.js"></script>
    </body>
</html>
