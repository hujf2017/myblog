<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>框架实现背景音乐不切换</title>
</head>
</frameset>
	<body >

		<div class="container">
		    <ol>
		        <audio id="music" autoplay="autoplay" loop="loop">       
   	 				<source src="/music/1.mp3" type="audio/mpeg" />
				</audio>
		    </ol>
		 
		    <iframe src="/" name="mainFrame"
		            frameborder="1" width="100%" height="600px"
		            scrolling="yes" noresize="noresize">
		    </iframe>
		</div>

	</body>
</html>