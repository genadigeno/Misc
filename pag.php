<?php

	$sql = "SELECT COUNT(id) FROM table";
	$sql = $pdo->prepare($sql);
	$sql->execute();
	
	$rows = $sql->fetch(PDO::FETCH_COLUMN);
	
	$pages = 10;
	
	$last = ceil($rows/$pages);
	
	$current = isset($_GET['pn']) ? $_GET['pn'] : 1;
	
	$limit = "LIMIT ".($current - 1) * $pages. ", ".$pages;
	
	$sql = "SELECT * FROM table $limit";
	$sql = $pdo->prepare($sql);
	$sql->execute();
	
	$datas = $sql->fetchAll(PDO::FETCH_ASSOC);
	
	$pagination = '';
	
	if($last > 1){
		if($current > 1){
			$prev = $current - 1;
			$pagination .= '<li><a href="?pn='.$prev.'">Prev</a></li>';
			for($i = $current - 4; $i < $current; $i++){
				if($i > 0){
					$pagination .= '<li><a href="?pn='.$i.'"></a></li>';
				}
			}
		}
		$pagination .= '<li><a>'.$current.'</a></li>';
		for($i = $current + 1; $i <= $last; $i++){
			$pagination .= '<li><a href="?pn='.$i.'">'.$i.'</a></li>';
			if($i >= $current + 4){
				break;
			}
		}
		if($current != $last){
			$next = $current + 1;
			$pagination .= '<li><a href="?pn='.$next.'">Next</a></li>';
		}
	}

?>