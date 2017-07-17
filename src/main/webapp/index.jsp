<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<!-- Bootstrap -->

		<link type="text/css" rel="stylesheet" href="css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->


	</head>
	<body>
		<div class="container theme-showcase" role="main">
			</br>
			<a href="event.xhtml"><button type="button" class="btn btn-lg btn-default">Events page based on jsf</button></a>
			</br>
			<h2> Rest requests addresses</h2>
			<p>Add event: localhost/rest/event/add</p>
			</br>
			<p>Load event by id: localhost/rest/event/yourEventId</p>
			</br>
			<p>Load events by state: localhost/rest/event/eventStateList/yourEventState</p>
			</br>
			<p>Load event list localhost/rest/event/list</p>

			<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script>window.jQuery || document.write('<script src="/webjars/jquery/1.11.1/jquery.min.js"><\/script>')</script>
			<!-- Include all compiled plugins (below), or include individual files as needed -->
			<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		</div>
	</body>
</html>
