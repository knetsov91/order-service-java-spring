<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="x-ua-compatible" content="IE=edge"/>
<title>Test results - OrderControllerApiTest</title>
<link href="../css/base-style.css" rel="stylesheet" type="text/css"/>
<link href="../css/style.css" rel="stylesheet" type="text/css"/>
<script src="../js/report.js" type="text/javascript"></script>
</head>
<body>
<div id="content">
<h1>OrderControllerApiTest</h1>
<div class="breadcrumbs">
<a href="../index.md">all</a> &gt; 
<a href="../packages/restaurant.com.orderservice.web.md">restaurant.com.orderservice.web</a> &gt; OrderControllerApiTest</div>
<div id="summary">
<table>
<tr>
<td>
<div class="summaryGroup">
<table>
<tr>
<td>
<div class="infoBox" id="tests">
<div class="counter">8</div>
<p>tests</p>
</div>
</td>
<td>
<div class="infoBox" id="failures">
<div class="counter">0</div>
<p>failures</p>
</div>
</td>
<td>
<div class="infoBox" id="ignored">
<div class="counter">0</div>
<p>ignored</p>
</div>
</td>
<td>
<div class="infoBox" id="duration">
<div class="counter">0.688s</div>
<p>duration</p>
</div>
</td>
</tr>
</table>
</div>
</td>
<td>
<div class="infoBox success" id="successRate">
<div class="percent">100%</div>
<p>successful</p>
</div>
</td>
</tr>
</table>
</div>
<div id="tabs">
<ul class="tabLinks">
<li>
<a href="#tab0">Tests</a>
</li>
<li>
<a href="#tab1">Standard output</a>
</li>
</ul>
<div id="tab0" class="tab">
<h2>Tests</h2>
<table>
<thead>
<tr>
<th>Test</th>
<th>Duration</th>
<th>Result</th>
</tr>
</thead>
<tr>
<td class="success">getRequestForWaiterOrders_returns200WithBodyContainingDto()</td>
<td class="success">0.007s</td>
<td class="success">passed</td>
</tr>
<tr>
<td class="success">getRequestOrders_returnAllOrders()</td>
<td class="success">0.013s</td>
<td class="success">passed</td>
</tr>
<tr>
<td class="success">getRequestToFetchOrdersForRestaurant_returns200StatusAndBodyWithDto()</td>
<td class="success">0.452s</td>
<td class="success">passed</td>
</tr>
<tr>
<td class="success">getRequestWithExistingOrderId_returnOrder()</td>
<td class="success">0.014s</td>
<td class="success">passed</td>
</tr>
<tr>
<td class="success">getRequestWithNonExistingOrderId_throwsExceptionAndReturnStatusCode500()</td>
<td class="success">0.021s</td>
<td class="success">passed</td>
</tr>
<tr>
<td class="success">postRequestToCreateOrderWithValidBody_returns201Status()</td>
<td class="success">0.068s</td>
<td class="success">passed</td>
</tr>
<tr>
<td class="success">postRequestWithMissingRequiredBodyField_throwsExceptionAndReturnStatusCode400()</td>
<td class="success">0.020s</td>
<td class="success">passed</td>
</tr>
<tr>
<td class="success">putRequestToUpdateOrderStatus_returns200Status()</td>
<td class="success">0.093s</td>
<td class="success">passed</td>
</tr>
</table>
</div>
<div id="tab1" class="tab">
<h2>Standard output</h2>
<span class="code">
<pre>2025-04-28T17:54:53.878+03:00  INFO 1219049 --- [    Test worker] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [restaurant.com.orderservice.web.OrderControllerApiTest]: OrderControllerApiTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2025-04-28T17:54:53.915+03:00  INFO 1219049 --- [    Test worker] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration restaurant.com.orderservice.OrderServiceApplication for test class restaurant.com.orderservice.web.OrderControllerApiTest

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.3)

2025-04-28T17:54:53.990+03:00  INFO 1219049 --- [    Test worker] r.c.o.web.OrderControllerApiTest         : Starting OrderControllerApiTest using Java 17.0.11 with PID 1219049 (started by kosio in /home/kosio/Documents/Projects/order-service-java-spring-boot)
2025-04-28T17:54:53.990+03:00  INFO 1219049 --- [    Test worker] r.c.o.web.OrderControllerApiTest         : No active profile set, falling back to 1 default profile: &quot;default&quot;
2025-04-28T17:54:54.531+03:00  INFO 1219049 --- [    Test worker] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2025-04-28T17:54:54.532+03:00  INFO 1219049 --- [    Test worker] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2025-04-28T17:54:54.535+03:00  INFO 1219049 --- [    Test worker] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2025-04-28T17:54:54.559+03:00  INFO 1219049 --- [    Test worker] r.c.o.web.OrderControllerApiTest         : Started OrderControllerApiTest in 0.639 seconds (process running for 16.631)
2025-04-28T17:54:55.037+03:00 ERROR 1219049 --- [    Test worker] r.c.o.exception.ControllerException      : Exception in /api/v1/orders/2
2025-04-28T17:54:55.037+03:00 ERROR 1219049 --- [    Test worker] r.c.o.exception.ControllerException      : null
2025-04-28T17:54:55.256+03:00 ERROR 1219049 --- [    Test worker] r.c.o.exception.ControllerException      : Exception in /api/v1/orders
2025-04-28T17:54:55.256+03:00 ERROR 1219049 --- [    Test worker] r.c.o.exception.ControllerException      : Restaurant Id is required.
</pre>
</span>
</div>
</div>
<div id="footer">
<p>
<div>
<label class="hidden" id="label-for-line-wrapping-toggle" for="line-wrapping-toggle">Wrap lines
<input id="line-wrapping-toggle" type="checkbox" autocomplete="off"/>
</label>
</div>Generated by 
<a href="http://www.gradle.org">Gradle 8.12.1</a> at Apr 28, 2025, 5:54:57 PM</p>
</div>
</div>
</body>
</html>
