<?php

$data_ = $conn->query('SELECT * FROM `main_categories`');
$data = $data_->fetchAll(PDO::FETCH_ASSOC);
$data_array = [];

foreach ($data as $item){
    $data_array[$item['id']] = $item['title'];
}

$total = '';

function get_recursion($parent_id, $level=null){
    global $conn;
    global $total;

    $rec = $conn->query('SELECT * FROM `main_categories` WHERE `parent_id`='.$parent_id.'');
    $get_result = $rec->fetchAll(PDO::FETCH_ASSOC);

    foreach ($get_result as $value){
        $delimiter = '';
        echo "<br>";
        for($i = 0; $i < $value['level']; $i++){
            $delimiter .= '-';
        }
        echo $delimiter.$value['title'];
        get_recursion($value['id'], $value['level']);
    }
}

function print_array($val){
    if(is_array($val)){
        foreach ($val as $key => $value){
            echo $value[$key];
        }
    }
    else{
        echo $val;
    }
}