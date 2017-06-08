<%@page import="src.modelo.Pedido_model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%ArrayList<Pedido_model> list = (ArrayList<Pedido_model>) request.getAttribute("oficinaarray");%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>= BHMN =</title>
        <link rel="shortcut icon" href="assets/images/gt_favicon.png">

        <!-- Bootstrap Core CSS -->
        <link href="../css/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../css/css/business-casual.css" rel="stylesheet">

        <!-- Fonts -->
        <link href="../css/fonts/font.css" rel="stylesheet" type="text/css">
        <link href="../css/fonts/font2.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <!--[endif]-->

    </head>

    <body>

        <div class="brand"><a href="http://www.bhmn.mb" style="margin-left: 0px; margin-right: 400px; margin-bottom: 0px"><img src="../css/img/logos/BHMN.png" class="" alt="" width="60"></a>
            BH-50
            <a href="http://www.mar.mb" style="margin-left:400px; margin-right:0px; margin-bottom: 0px" ><img src="../css/img/mb.png" class="" alt="bhmn" width="70" ></a></div> 

        <div class="address-bar">Departamento de Apoio</div>

        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                    <a class="navbar-brand" href="index3.html">Business Casual</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a class="page-scroll" href="../" style="color: black">Principal</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#" style="color: black">Acesso ao Folha-N</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="../loginSigbase.jsp" style="color: black">Acesso ao Sig-base</a>
                        </li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: black">Pesquisas <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="../pesquisa/ResultadoClimaOrg.jsp?perg=0">Clima Organizacional</a></li>
                            </ul>
                        </li>
                        <li>
                            <a class="page-scroll" href="#" style="color: black">Como Utilizar?</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                    <a class="navbar-brand" href="oficinabh50.html">Business Casual</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a class="page-scroll" href="oficinabh50.html" style="color: black">Pedido de Serviço</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="oficinabh50_viatura.html" style="color: black">Pedido de Viatura</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="oficinabh50_consulta.html" style="color: black">Consultar Pedido</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="OficinaBh50Consult.jsp?id=3" style="color: black">Listar Pedidos</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
        <!-- Header -->
        <!-- /Header -->

        <!-- Intro -->
        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <br> <br>
                    <%if (list.size() == 0) {%>
                    <h2 class="intro-text text-center">Nenhum Pedido encontrado!</h2>
                    <%} else {%>
                    <h2 class="intro-text text-center"><%out.println(list.size());%> Pedido(s) Encontrado(s):</h2>
                    <%}%>
                    <table class="table table-striped table-advance table-hover">
                        <hr>
                        <thead>
                            <tr>
                                <th><i class="fa fa-angle-double-right text-center"></i> OS</th>
                                <th><i class="fa fa-angle-double-right text-center"></i> Solicitante</th>
                                <th><i class="fa fa-angle-double-right text-center"></i> Nip</th>                                 
                                <th><i class="fa fa-angle-double-right text-center"></i> Oficina</th>
                                <th><i class="fa fa-angle-double-right text-center"></i> Solicitação</th>
                                <th><i class="fa fa-angle-double-right text-center"></i> Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Pedido_model oficina : list) {%>
                            <tr>
                                <td><%out.println(oficina.getOs());%></td>
                                <td><%out.println(oficina.getPost());%>-<%out.println(oficina.getNome());%></td>
                                <td><%out.println(oficina.getNip());%></td>
                                <td><%out.println(oficina.getAtendimento());%></td>
                                <td><%out.println(oficina.getSolicitacao());%></td>
                                <%switch (oficina.getStatus()) {
                                        case 1:%>
                                <td><span class="label label-danger label-mini"><%out.println(oficina.getStatus());%></span></td>
                                    <%break;
                                        case 2:%>
                                <td><span class="label label-warning label-mini"><%out.println(oficina.getStatus());%></span></td>
                                    <%break;
                                        case 3:%>
                                <td><span class="label label-info label-mini"><%out.println(oficina.getStatus());%></span></td>
                                    <%break;
                                    default:%>
                                <td><span class="label label-success label-mini"><%out.println(oficina.getStatus());%></span></td>
                                        <%}%>
                                <td>
                                    <form class="col-sm-2 control-label" action="Bh50ConsultOs.jsp" method="post">      
                                        <button type="submit" title="EXIBIR DADOS DA OS" class="btn btn-success btn-xs">Visualizar</button>
                                        <input type="hidden" class="form-control" id="os" name="os" maxlength="50" value="<% out.print(oficina.getOs()); %>" > 
                                    </form>
                                </td>
                            </tr>
                            <% }%>


                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <footer>
            <div class="container">
                <div class="row">
                    <br>
                    <div style="font-size:11px; padding-bottom: 30px" class="col-lg-12 text-center">
                        <p>Todos os Direitos Reservados © 2016 - Base de Hidrografia da Marinha em Niterói</p>
                        <p>Rua Barão de Jaceguai s/n - CEP: 24048-900 - Ponta da Armação - Niterói - RJ</p>
                        <p>Tel: 2189-3469 - Retelma: 8112-3469 - Fax: 2189-3467 </p>
                        <p>Tel: 2189-3186 - Retelma: 8112-3186 (Sala de Estado)</p>
                    </div>
                    <br>
                    <br>
                </div>
            </div>
        </footer>

        <!-- jQuery -->
        <script src="../css/js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../css/js/bootstrap.min.js"></script>

    </body>

</html>
