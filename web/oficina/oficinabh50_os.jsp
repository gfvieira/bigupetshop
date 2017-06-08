<%@page import="src.modelo.Pedido_move"%>
<%@page import="src.modelo.Pedido_model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%Pedido_model oficina = (Pedido_model) request.getAttribute("oficina");%>
<%ArrayList<Pedido_move> list = (ArrayList<Pedido_move>) request.getAttribute("oficinamove");%>
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
                    <%if (oficina.getStatus().equals("FINALIZADO") && oficina.getFeedback() == 0) {%>
                    <div class="row">
                        <br> 
                        <br>
                        <br>
                        <div class="col-lg-12">
                            <div class="form-panel center-block">
                                <h2 class="intro-text text-center">Serviço de Atendimento das Oficina do BH-50<br><br></h2>
                                <form class="form-horizontal style-form" action="Feedback.jsp" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-6 col-sm-6 control-label">Qual a sua avaliação sobre o serviço executado?</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="satisfacao" required>
                                                <option value="">Selecione!</option>
                                                <option value="5">Excelente</option>
                                                <option value="4">Muito-Bom</option>
                                                <option value="3">Bom</option>
                                                <option value="2">Regular</option>
                                                <option value="1">Ruim</option>
                                            </select>
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="hidden"  class="form-control" id="os" name="os" value="<%out.println(oficina.getOs());%>" disabled>
                                            <button type="submit" class="btn btn-theme center-block">Enviar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%} else {%>

                    <%}%>
                    <h3 class="intro-text text-center"><b>Detalhes da OS: </b><i><%out.print(oficina.getOs());%></i></h3><br>
                    <h4 class="intro-text text-center"><b>Status: </b> <i><%out.print(oficina.getStatus());%></i> </h4><br>
                    <h4 class="intro-text text-center"><a href=javascript:print();>Clique aqui para imprimir a pagina</a></h4>
                    <br><br>
                    <br>
                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <form class="form-horizontal style-form" action="">
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Nome:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getPost());%>-<%out.println(oficina.getNome());%>" disabled>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">NIP:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getNip());%>" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Ramal:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getRamal());%>" disabled>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Setor:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getSetor());%>" disabled>
                                        </div>                                            
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Atendimento:</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getAtendimento());%>" disabled>
                                        </div> 
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Solicitação:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getSolicitacao());%>" disabled>
                                        </div>
                                    </div>
                                    <%if (oficina.getDescricao().equals("NAO DISPONIVEL")) {%>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Data do Evento:</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getDataevento());%>" disabled>
                                        </div>
                                        <label class="col-sm-2 col-sm-2 control-label">Pessoas a Bordo:</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getPob());%>" disabled>
                                        </div>
                                    </div>
                                        
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Saida BHMN:</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getHoraevento());%>" disabled>
                                        </div>
                                        <label class="col-sm-2 col-sm-2 control-label">Previsão de Retorno:</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getHorafim());%>" disabled>
                                        </div>
                                    </div>
                                    <%} else {%>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Descrição:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getDescricao());%>" disabled>
                                        </div>
                                    </div>
                                    <%}%>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Observação:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="nome" value="<%out.print(oficina.getObs());%>" disabled>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>    
                    </div>

                    <table class="table table-striped table-advance table-hover">
                        <hr>
                        <thead>
                            <tr>
                                <th><i class="fa fa-angle-double-right text-center"></i> DATA</th>
                                <th><i class="fa fa-angle-double-right text-center"></i> MOVIMENTAÇÃO</th>
                                <th><i class="fa fa-angle-double-right text-center"></i> IP</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Pedido_move satemove : list) {%>
                            <tr>
                                <td><%out.print(satemove.getDatacadmov());%>-<%out.print(satemove.getHoracadmov());%></td>
                                <td><%out.print(satemove.getDescricao_move());%></td>
                                <td><%out.print(satemove.getIp());%></td>
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