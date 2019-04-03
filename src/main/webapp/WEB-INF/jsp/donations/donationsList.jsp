<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">
	<h2>
		Donaciones
	</h2>
	<table id="causeTable" class="table table-striped">
		<thead>
			<tr>
				<th>Cliente</th>
				<th>Cantidad</th>
				<th>Fecha</th>
				<th>Acciones</th>
				

			</tr>


		</thead>
		<tbody>
			<c:forEach items="${resultados}" var="donation">
				<c:set var ="causeId" value="${donation.cause.id }"/>
				<tr>
					<td><c:out value="${donation.client}" /></td>
					
					<td><c:out value="${donation.amount}" /></td>

					<td><c:out value="${donation.donationDate}" /></td>

					<td>
					<spring:url value="/causes/{causeId}/donations/"
							var="donationsCauseUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url> <a href="${fn:escapeXml(donationsCauseUrl)}">Ver donaciones</a>
						
					
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<spring:url value="/causes/{causeId}/donations/new" var="newUrl">
        <spring:param name="causeId" value="${causeId}"/>
    </spring:url>
    <a href="${fn:escapeXml(newUrl)}" class="btn btn-default">Hacer una donación</a>
	
</petclinic:layout>
