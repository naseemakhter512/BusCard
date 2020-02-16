
<%@page import="in.co.buscard.common.controller.BaseCtl"%>
<%@page import="in.co.buscard.wsx.controller.ORSView"%>
<%@page import="in.co.buscard.common.controller.ForgetPasswordCtl"%>
<%@page import="in.co.buscard.util.HTMLUtility"%>
<%@page import="in.co.buscard.util.DataUtility"%>
<%@page import="in.co.buscard.util.ServletUtility"%>

<jsp:useBean id="model" class="in.co.buscard.common.model.UserModel"
	scope="request"></jsp:useBean>
<head>
   <meta http-equiv="content-type" content="text/html; charset=UTF-8">
 <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!--js-->
<script src="js/jquery-2.1.1.min.js"></script> 
<!--icons-css-->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!--Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
<!--static chart-->
</head>
<body>
<script src='../../../../../ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script><script src="../../../../../m.servedby-buysellads.com/monetization.js" type="text/javascript"></script>
<script>
(function(){
	if(typeof _bsa !== 'undefined' && _bsa) {
  		// format, zoneKey, segment:value, options
  		_bsa.init('flexbar', 'CKYI627U', 'placement:w3layoutscom');
  	}
})();
</script>
<script>
(function(){
if(typeof _bsa !== 'undefined' && _bsa) {
	// format, zoneKey, segment:value, options
	_bsa.init('fancybar', 'CKYDL2JN', 'placement:demo');
}
})();
</script>
<script>
(function(){
	if(typeof _bsa !== 'undefined' && _bsa) {
  		// format, zoneKey, segment:value, options
  		_bsa.init('stickybox', 'CKYI653J', 'placement:w3layoutscom');
  	}
})();
</script>
<!--<script>(function(v,d,o,ai){ai=d.createElement("script");ai.defer=true;ai.async=true;ai.src=v.location.protocol+o;d.head.appendChild(ai);})(window, document, "//a.vdo.ai/core/w3layouts_V2/vdo.ai.js?vdo=34");</script>-->
<div id="codefund"><!-- fallback content --></div>
<script src="../../../../../codefund.io/properties/441/funder.js" async="async"></script>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src='https://www.googletagmanager.com/gtag/js?id=UA-149859901-1'></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-149859901-1');
</script>

<script>
     window.ga=window.ga||function(){(ga.q=ga.q||[]).push(arguments)};ga.l=+new Date;
     ga('create', 'UA-149859901-1', 'demo.w3layouts.com');
     ga('require', 'eventTracker');
     ga('require', 'outboundLinkTracker');
     ga('require', 'urlChangeTracker');
     ga('send', 'pageview');
   </script>
<script async src='../../../../js/autotrack.js'></script>
</head>


 <body style="background-color: #343a40">
 <div class="login-page">
<!---728x90--->

   <%=HTMLUtility.getSuccessMessage(request)%>
                      <%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
  <div class="alert alert-danger" style="width: 25rem;margin: 0 auto;margin-bottom:5px; background-color: #a94442;border-color: #a94442;color:#fff">
   <%=HTMLUtility.getErrorMessage(request)%>
  </div>
  	<%
			}
		%>
    <div class="login-main">  	
    	 <div class="login-head">
			Forgot Password
			</div>			
			<div class="login-block">
				<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="Post">
					<input type="text" name="login" placeholder="Email" required=""
					value=<%=DataUtility.getStringData(request.getParameter("login"))%>>
				<br>
				<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%>
			</font>
				
				
					<input type="submit" name="operation" value="<%=BaseCtl.OP_GO%>">	
					<div class="forgot-top-grids">
						
						<div class="forgot">
						<a href="<%=ORSView.LOGIN_CTL%>">Back Home</a>
						</div>
						<div class="clearfix"> </div>
					</div>
				</form>
			</div>
      </div>
</div>
<!--COPY rights end here-->
<!---728x90--->

<!--scrolling js-->
		<script src="js/jquery.nicescroll.js"></script>
		<script src="js/scripts.js"></script>
		<!--//scrolling js-->
<script src="js/bootstrap.js"> </script>
<!-- mother grid end here-->
</body>

<!-- Mirrored from p.w3layouts.com/demos/28-03-2016/shoppy/web/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jan 2020 12:57:35 GMT -->
</html>
		</body>

