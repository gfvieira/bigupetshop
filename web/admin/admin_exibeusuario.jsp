<%@page import="src.modelo.Usuario"%>
<%@page import="java.io.*"%>
<%Usuario user = (Usuario) request.getAttribute("exibe");%>
<%@ page contentType="text/html; charset=UTF-8" %>
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
                                <h3 class="mb"><i class="fa fa-angle-double-right"></i> <b>Cadastro de Usuarios</b></h3><br><br>
                                ${mensagem}
                                <form class="form-horizontal style-form" action="AttUsuario.jsp" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Nome</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(user.getNome());%>" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">NIP</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" value="<%out.print(user.getNip());%>" disabled>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Nome de Guerra</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="guerra"  value="<%out.print(user.getGuerra());%>" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Posto/Graduação</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="grad">
                                                <option value="<%out.print(user.getGraduacao());%>"><%out.print(user.getGraduacaoNome());%></option>
                                                <option value="1">MN-RC</option>
                                                <option value="2">MN</option>
                                                <option value="3">MNE</option>
                                                <option value="4">CB</option>
                                                <option value="5">3ºSG</option>
                                                <option value="6">2ºSG</option>
                                                <option value="7">1ºSG</option>
                                                <option value="8">SO</option>
                                                <option value="9">GM</option>
                                                <option value="10">1ºT</option>
                                                <option value="11">2ºT</option>
                                                <option value="12">CT</option>
                                                <option value="13">CC</option>
                                                <option value="14">CF</option>
                                                <option value="15">CMG</option>
                                                <option value="16">CA</option>
                                                <option value="17">VA</option>
                                                <option value="18">AE</option>
                                                <option value="19">SC</option>
                                                <option value="20">FC</option>
                                                <option value="21">EST</option>
                                            </select>
                                        </div> 
                                        <label class="col-sm-1 col-sm-2 control-label">Ramal</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="ramal" maxlength="4" value="<%out.print(user.getRamal());%>" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Grupo</label>
                                        <div class="col-sm-3">
                                            <select class="form-control" size="1" id="grupo" name="grupo" required>
                                                <option value="<%out.print(user.getTipoAcesso());%>"><%out.print(user.getTipoAcessoNome());%></option>
                                                <option value="1">Comando</option>
                                                <option value="2">Assesoria</option>
                                                <option value="3">Chefe Departamento</option>
                                                <option value="4">Encarregado</option>
                                                <option value="5">Sargenteante</option>
                                                <option value="10">BH-10</option>
                                                <option value="20">BH-20</option>
                                                <option value="30">BH-30</option>
                                                <option value="40">BH-40</option>
                                                <option value="50">BH-50</option>
                                            </select>
                                        </div> 
                                        <label class="col-sm-2 col-sm-2 control-label">Tipo de acesso</label>
                                        <div class="col-sm-3">
                                            <select class="form-control" size="1" id="type" name="type">
                                                <option value="<%out.print(user.getAcesso());%>"><%out.print(user.getAcessoNome());%></option>
                                            </select>
                                        </div> 
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Reset de Senha?</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="reset" required>
                                                <option value="">Selecione!</option>
                                                <option value="1">SIM</option>
                                                <option value="0">NÃO</option>
                                            </select>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Ativo?</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="active" >
                                                <%if (user.getAtivo() == 1) {%>
                                                <option value="1">SIM</option>
                                                <option value="0">NÃO</option>
                                                <%} else {%>
                                                <option value="0">NÃO</option>
                                                <option value="1">SIM</option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Administrador?</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="active" >
                                                <%if (user.getAdmin() == 1) {%>
                                                <option value="1">SIM</option>
                                                <option value="0">NÃO</option>
                                                <%} else {%>
                                                <option value="0">NÃO</option>
                                                <option value="1">SIM</option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Setor</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="setor" value="<%out.print(user.getSetor());%>" required>
                                        </div>
                                    </div>
                                    <br>
                                    <input type="hidden" class="form-control" id="nip" name="nip" value="<%out.print(user.getNip());%>">
                                    <hr>
                                    <br>
                                    <div>
                                        <button type="submit" class="btn btn-theme">Editar</button>
                                        <input type="button"  class="btn btn-theme"  value="Cancelar" onClick="Voltar()">
                                        <input type="hidden" class="form-control" name="user" value="<%out.print(usuario.getNip());%>">
                                    </div>
                                    <br>
                                    <br>
                                </form>
                            </div>
                        </div>    
                    </div>
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

        <script type="text/javascript">
                                            $(document).ready(function () {
                                                $('#grupo').change(function (e) {
                                                    var option = '<option>Selecione</option>';
                                                    var grp = $('#grupo').val();
                                                    if (grp == 1) {
                                                        option += '<option value="11">BH-01</option>';
                                                        option += '<option value="12">BH-02</option>';
                                                        option += '<option value="25">BH-05</option>';
                                                    } else if (grp == 2) {
                                                        option += '<option value="28">BH-08</option>';
                                                    } else if (grp == 3) {
                                                        option += '<option value="100">BH-10 Chefe Departamento</option>';
                                                        option += '<option value="200">BH-20 Chefe Departamento</option>';
                                                        option += '<option value="300">BH-30 Chefe Departamento</option>';
                                                        option += '<option value="400">BH-40 Chefe Departamento</option>';
                                                        option += '<option value="500">BH-50 Chefe Departamento</option>';
                                                    } else if (grp == 4) {
                                                        option += '<option value="310">BH-31 Encarregado(a)</option>';
                                                        option += '<option value="320">BH-32 Encarregado(a)</option>';
                                                        option += '<option value="330">BH-33 Encarregado(a)</option>';
                                                        option += '<option value="340">BH-34 Encarregado(a)</option>';
                                                        option += '<option value="520">BH-52 Encarregado(a)</option>';
                                                        option += '<option value="530">BH-53 Encarregado(a)</option>';
                                                    } else if (grp == 6) {
                                                        option += '<option value="6">teste</option>';
                                                    } else if (grp == 10) {
                                                        option += '<option value="101">BH-10 Cartas Nacional</option>';
                                                        option += '<option value="102">BH-10 Cartas Estrangeira</option>';
                                                        option += '<option value="103">BH-10 Folha-N</option>';
                                                        option += '<option value="104">BH-10 Mercator</option>';
                                                    } else if (grp == 20) {
                                                        option += '<option value="201">BH-25 Municiamento</option>';
                                                    } else if (grp == 30) {
                                                        option += '<option value="301">BH-30 CHaPA</option>';
                                                        option += '<option value="311">BH-31 Pessoal</option>';
                                                        option += '<option value="341">BH-34 Supervisor</option>';
                                                        option += '<option value="342">BH-34 Hardware</option>';
                                                        option += '<option value="343">BH-34 Rede</option>';
                                                        option += '<option value="344">BH-34 Lotus-SiGDEM</option>';
                                                        option += '<option value="345">BH-34 Telefônia</option>';
                                                    } else if (grp == 40) {
                                                        option += '<option value="401">teste</option>';
                                                    } else if (grp == 50) {
                                                        option += '<option value="531">BH-50 Eletrica</option>';
                                                        option += '<option value="532">BH-50 Aguada/CAV</option>';
                                                        option += '<option value="533">BH-50 Refrigeração</option>';
                                                        option += '<option value="534">BH-50 Carpintaria</option>';
                                                        option += '<option value="535">BH-50 Metalurgia</option>';
                                                        option += '<option value="521">BH-50 Prefeitura</option>';
                                                        option += '<option value="522">BH-50 Garagem</option>';
                                                    } else {
                                                        Reset();
                                                    }
                                                    $('#type').html(option).show();
                                                })


                                                function Reset() {
                                                    $('#type').empty().append('<option>erro</option>>');
                                                }
                                            });
        </script>


    </body>
</html>
