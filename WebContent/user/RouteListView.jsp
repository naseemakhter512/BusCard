<%@page import="in.co.buscard.common.model.RouteModel"%>
<%@page import="in.co.buscard.wsx.controller.ORSView"%>
<%@page import="in.co.buscard.common.model.RoleModel"%>
<%@page import="in.co.buscard.common.controller.RoleListCtl"%>
<%@page import="in.co.buscard.common.controller.BaseCtl"%>
<%@page import="in.co.buscard.util.HTMLUtility"%>
<%@page import="in.co.buscard.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.buscard.util.AccessUtility"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script src="js/Chart.min.js"></script>
<!--//charts-->
<!-- geo chart -->
    <script src="../../../../../cdn.jsdelivr.net/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <script>window.modernizr || document.write('<script src="lib/modernizr/modernizr-custom.html"><\/script>')</script>
    <!--<script src="lib/html5shiv/html5shiv.js"></script>-->
     <!-- Chartinator  -->
    <script src="js/chartinator.js" ></script>
    <script type="text/javascript">
        jQuery(function ($) {

            var chart3 = $('#geoChart').chartinator({
                tableSel: '.geoChart',

                columns: [{role: 'tooltip', type: 'string'}],
         
                colIndexes: [2],
             
                rows: [
                    ['China - 2015'],
                    ['Colombia - 2015'],
                    ['France - 2015'],
                    ['Italy - 2015'],
                    ['Japan - 2015'],
                    ['Kazakhstan - 2015'],
                    ['Mexico - 2015'],
                    ['Poland - 2015'],
                    ['Russia - 2015'],
                    ['Spain - 2015'],
                    ['Tanzania - 2015'],
                    ['Turkey - 2015']],
              
                ignoreCol: [2],
              
                chartType: 'GeoChart',
              
                chartAspectRatio: 1.5,
             
                chartZoom: 1.75,
             
                chartOffset: [-12,0],
             
                chartOptions: {
                  
                    width: null,
                 
                    backgroundColor: '#fff',
                 
                    datalessRegionColor: '#F5F5F5',
               
                    region: 'world',
                  
                    resolution: 'countries',
                 
                    legend: 'none',

                    colorAxis: {
                       
                        colors: ['#679CCA', '#337AB7']
                    },
                    tooltip: {
                     
                        trigger: 'focus',

                        isHtml: true
                    }
                }

               
            });                       
        });
    </script>
<!--geo chart-->

<!--skycons-icons-->
<script src="js/skycons.js"></script>
<!--//skycons-icons-->
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

<meta name="robots" content="noindex">
<title>Route List</title>
<body><link rel="stylesheet" href="../../../../../images/demobar_w3_4thDec2019.css">
	<!-- Demo bar start -->
<div class="page-container">	
   <div class="left-content">
	   <div class="mother-grid-inner">
            <!--header start here-->
				<div class="header-main">
					<div class="header-left">
							<div class="logo-name" style="display: none;">
									 <a href="index-2.html"> <h1>MICARD</h1> 
									<!--<img id="logo" src="" alt="Logo"/>--> 
								  </a> 								
							</div>
							<!--search-box-->
								<div class="search-box" style="display: none;">
									<form>
										<input type="text" placeholder="Search..." required="">	
										<input type="submit" value="">					
									</form>
								</div><!--//end-search-box-->
							<div class="clearfix"> </div>
						 </div>
						 <div class="header-right">
							<div class="profile_details_left"><!--notifications of menu start -->
					
								<div class="clearfix"> </div>
							</div>
							<!--notification menu end -->
							<div class="profile_details">		
								<ul>
									<li class="dropdown profile_details_drop">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
											<div class="profile_img">	
											
												<div class="user-name">
													<span>admin</span><i class="fa fa-user-circle-o"></i>
												</div>
												<i class="fa fa-angle-down lnr"></i>
												<i class="fa fa-angle-up lnr"></i>
												<div class="clearfix"></div>	
											</div>	
										</a>
										<ul class="dropdown-menu drp-mnu">
											<li> <a href="#"><i class="fa fa-cog"></i> Settings</a> </li> 
											<li> <a href="#"><i class="fa fa-user"></i> Profile</a> </li> 
											<li> <a href="/BusCard/LoginCtl"><i class="fa fa-sign-out"></i> Logout</a> </li>
										</ul>
									</li>
								</ul>
							</div>
							<div class="clearfix"> </div>				
						</div>
				     <div class="clearfix"> </div>	
				</div>
<!--heder end here-->
<!-- script-for sticky-nav -->
		<script>
		$(document).ready(function() {
			 var navoffeset=$(".header-main").offset().top;
			 $(window).scroll(function(){
				var scrollpos=$(window).scrollTop(); 
				if(scrollpos >=navoffeset){
					$(".header-main").addClass("fixed");
				}else{
					$(".header-main").removeClass("fixed");
				}
			 });
			 
		});
		</script>
		<!-- /script-for sticky-nav -->
<!--inner block start here-->
<div class="inner-block" style="padding: 5em 10em 4em 10em;">
 <div class="login-page"  style="padding-top: 0rem">
  <nav aria-label="breadcrumb" style="padding-top: 0.75rem;padding-right: .02rem;">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="/BusCard/Welcome.jsp">DashBoard</a></li>
    <li class="breadcrumb-item active" aria-current="page">Route</li>
  </ol>
</nav>
<!--mainpage chit-chating-->
<div class="chit-chat-layer1" style="position: relative;flex-direction: column;display: flex;min-width: 0;word-wrap: break-word;background-color: #fff;background-clip: border-box;border: 1px solid rgba(0, 0, 0, 0.125);border-radius: 0.25rem;">
	<div style="padding: 0.75rem 1.25rem; margin-bottom: 0;background-color: rgba(0, 0, 0, 0.03);border-bottom: 1px solid rgba(0, 0, 0, 0.125);">
					<div class="row">
						<div class="col-md-7">
							<i class="fa fa-table"></i> Route
						</div>
						<div class="col-md-3">							
							<input type="text"id="demo" placeholder="Search"  class="form-control emailfind">
						</div>
						<div class="col-md-2">
							<a href="/BusCard/RouteCtl" class="btn btn-info btn-block" title="User">Add New</a>
						</div>
					</div>	
				</div>
	
	<div  style="padding: 1.25rem">
	<div class="table-responsive" style="overflow-x:auto;">		
	  <form action="<%=ORSView.ROUTE_LIST_CTL%>">				
						<table class="table mt-2"  width="100%" cellspacing="0">
							<thead>
								<tr>
									<th width="10%">#</th>
									<th>Route ID</th>
									<th>Route Name</th>
									<th>KM</th>
									<th width="10%">Action</th>
								</tr>
							</thead>
							<tbody id="test">
								                         <%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
			<tr>
				<td colspan="2"><%=HTMLUtility.getErrorMessage(request)%></td>
			</tr>
			<%
			}
		%>
			<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<RouteModel> it = list.iterator();
			while (it.hasNext()) {
				RouteModel model = it.next();
		%>
                                <tr>
                                  <td><%=model.getId()%></td>
                                   <td><%=model.getRouteId()%></td>
                                  <td><%=model.getRouteName()%></td>
                                 <td><%=model.getKm()%></td>
                                 	<td>
														<a href="<%=ORSView.ROUTE_CTL%>?id=<%=model.getId()%>">
															<i class="fa fa-edit text-info"></i>
														</a>
														&nbsp;														
													<a href="<%=ORSView.ROUTE_LIST_CTL%>?operation=Delete&id=<%=model.getId()%>">	<i class="fa fa-trash text-danger" style="cursor:pointer"></i>
													</a>
													</td>
                              </tr>
                            	<%
			}
		%>								
											
							</tbody>
						</table>
						</form>
					</div>
	
             </div>
      </div>

   
</div>


</div>
<!--inner block end here-->
<!---728x90--->

<!--copy rights start here-->
<div class="copyrights">
	 <p>© 2020 All Rights Reserved  </p>
</div>	
<!--COPY rights end here-->
</div>
</div>
<!--slider menu-->
    <div class="sidebar-menu">
		  	<div class="logo"> <a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> <a href="#"> <span id="logo" ></span> 
			      <!--<img id="logo" src="" alt="Logo"/>--> 
			  </a> </div>		  
		    <div class="menu">
		      <ul id="menu" >
		    <li id="menu-home" ><a href="/BusCard/Welcome.jsp"><i class="fa fa-tachometer"></i><span>Dashboard</span></a></li>
	
		     
		        <li id="menu-academico" ><a href="#"><i class="fa fa-file-text"></i><span>Route</span><span class="fa fa-angle-right" style="float: right"></span></a>
		          <ul id="menu-academico-sub" >
		            <li id="menu-academico-avaliacoes" ><a href="/BusCard/RouteListCtl">List of Route</a></li>			           
		          </ul>
		        </li>
		          <li id="menu-academico" ><a href="#"><i class="fa fa-file-text"></i><span>Fare</span><span class="fa fa-angle-right" style="float: right"></span></a>
		          <ul id="menu-academico-sub" >
		            <li id="menu-academico-avaliacoes" ><a href="/BusCard/FareListCtl">List of Fare</a></li>	           
		          </ul>
		        </li>
		          <li id="menu-academico" ><a href="#"><i class="fa fa-file-text"></i><span>MST</span><span class="fa fa-angle-right" style="float: right"></span></a>
		          <ul id="menu-academico-sub" >
		          	 <li id="menu-academico-boletim" ><a href="#">Add New MST</a></li>
		            <li id="menu-academico-avaliacoes" ><a href="#">List of MST</a></li>		           
		          </ul>
		        </li>
		        <li><a href="#"><i class="fa fa-file-text"></i><span>Report Download</span></a></li>
		        <li><a href="#"><i class="fa fa-file-text"></i><span>Terms and conditions</span></a></li>
		 
		      </ul>
		    </div>
	 </div>
	<div class="clearfix"> </div>
</div>
<!--slide bar menu end here-->
<script>
var toggle = true;
            
$(".sidebar-icon").click(function() {                
  if (toggle)
  {
    $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
    $("#menu span").css({"position":"absolute"});
  }
  else
  {
    $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
    setTimeout(function() {
      $("#menu span").css({"position":"relative"});
    }, 400);
  }               
                toggle = !toggle;
            });
</script>
<!--scrolling js-->
		<script src="js/jquery.nicescroll.js"></script>
		<script src="js/scripts.js"></script>
		<!--//scrolling js-->
<script src="js/bootstrap.js"> </script>
<script>
$(document).ready(function(){
    $("#demo").on("keyup", function() {
       var value = $(this).val().toLowerCase();
       $("#test tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
       });
    });
 });
</script>
<!-- mother grid end here-->
</body>

<!-- Mirrored from p.w3layouts.com/demos/28-03-2016/shoppy/web/ by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jan 2020 12:57:18 GMT -->
</html>                   


