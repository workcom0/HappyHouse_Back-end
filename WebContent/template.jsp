<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>template</title>
<c:import url="/include/header.jsp"></c:import>


<!-- #main -->
<main id="main">

<!-- ======= Breadcrumbs ======= -->
<section class="breadcrumbs">
	<div class="container">

		<div class="d-flex justify-content-between align-items-center">
			<h2>Inner Page</h2>
			<ol>
				<li><a href="index.jsp">Home</a></li>
				<li>Inner Page</li>
			</ol>
		</div>

	</div>
</section>
<!-- End Breadcrumbs -->

<section class="inner-page">
	<div class="container">
		<p>Example inner page template</p>
	</div>
</section>

</main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>