<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>= Bigu PetShop =</title>
        <link rel="shortcut icon" href="assets/images/gt_favicon.png">

        <!-- Bootstrap Core CSS -->
        <link href="css/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/css/business-casual.css" rel="stylesheet">

        <!-- Fonts -->
        <link href="css/fonts/font.css" rel="stylesheet" type="text/css">
        <link href="css/fonts/font2.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <!--[endif]-->

    </head>

    <body>

        <br><br><br>
        <div class="container">
            <div class="row text-center" style="margin-right: 300px; margin-left: 300px" >
                <div class="box">
                    <div class="col-lg-12">
                        <hr>
                        <h2 class="intro-text text-center">
                            <a class="" href="index.html"><img src="css/img/ASA.fw.png" width="200"></a>
                        </h2>
                        <hr>
                        <form  class="form login text-center" name="sigbase" action="Login.jsp" method="POST">
                            <div class="row">
                                <div class="form-group col-lg-12" >
                                    <label>Login:</label>
                                    <input class="form-control text-center" name="nip" id="nip" type="text" required="" placeholder="" autofocus>
                                    <font color="red">${mensagem}</font>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-lg-12" >
                                    <label>Senha:</label>
                                    <input class="form-control text-center" name="senha" id="senha" type="password" required placeholder="" autofocus>
                                </div>
                            </div>
                                <br>
                            <div class="row">
                                <div class="form-group col-lg-12">
                                    <button type="submit" class="btn btn-default">Entrar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container -->
        <br><br>
        <br><br>
        <br><br>
        <br><br>
        <br><br>
        <br><br>
        <footer>
            <div class="container">
                <div class="row">
                    <br>
                    <div style="font-size:11px; padding-bottom: 30px" class="col-lg-12 text-center">
                        <p>Todos os Direitos Reservados © 2017 - PetShop Bigu</p>
                        <p>Av. Brasil nº: xxxxxxx - CEP: xxxxx-xxx - Bairro: Irajá - Rio de Janeiro - RJ</p>
                        <p>Tel: (21) xxxx-xxxx - Cel: (21) xxxxx-xxxx</p>
                    </div>
                    <br>
                    <br>
                </div>
            </div>
        </footer>

        <!-- jQuery -->
        <script src="css/js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="css/js/bootstrap.min.js"></script>
    </body>

</html>
