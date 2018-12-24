<?php
include_once "config.php";

$query = 'SELECT * FROM `main_categories` WHERE `level`=1';

$statement = $conn->query($query);

$result = $statement->fetchAll(PDO::FETCH_ASSOC);

foreach ($result as $item){
    echo $item['title']; // მთავარი კატეგორია

//    print_r(get_recursion($item['id'], $item['level']));
    get_recursion($item['id'], $item['level']);

    echo "<br>";
}

?>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/bootstrap-4.2.1/css/bootstrap.css">
    <link rel="stylesheet" href="/bootstrap-4.2.1/css/bootstrap-grid.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
<!--                <ul class="list-group">-->
<!--                    <li class="list-group-item">-->
<!--                        Cras justo odio-->
<!--                        <ul class="list-group">-->
<!--                            <li class="list-group-item">Cras justo odio</li>-->
<!--                            <li class="list-group-item">Dapibus ac facilisis in</li>-->
<!--                        </ul>-->
<!--                    </li>-->
<!--                    <li class="list-group-item">Dapibus ac facilisis in</li>-->
<!--                    <li class="list-group-item">Morbi leo risus</li>-->
<!--                    <li class="list-group-item">Porta ac consectetur ac</li>-->
<!--                    <li class="list-group-item">Vestibulum at eros</li>-->
<!--                </ul>-->
            </div>
        </div>
    </div>
    <script src="/bootstrap-4.2.1/js/bootstrap.js"></script>
</body>
</html>