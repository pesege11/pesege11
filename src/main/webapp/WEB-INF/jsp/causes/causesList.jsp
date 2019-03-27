<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">
	<h2>
		Causas
	</h2>
	<table id="causeTable" class="table table-striped">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Objetivo presupuestario</th>
				<th>Dinero restante</th>
				<th>Organización</th>
				<th>Acciones</th>

			</tr>


		</thead>
		<tbody>
			<c:forEach items="${resultados}" var="cause">
				<tr>
					<td><c:out value="${cause.name}" /></td>

					<td><c:out value="${cause.description}" /></td>

					<td><c:out value="${cause.budgetTarget}" /></td>
					
					<td><c:out value="${cause.remainingMoney}" /><c:if test="${cause.remainingMoney eq 0.0}"><p style="display:inline-block;color:red;font-weight:bold">&nbsp;CAUSA CERRADA</p></c:if></td>
					
					<td><c:out value="${cause.organization}" /></td>

					<td>
					<spring:url value="/causes/{causeId}"
							var="donationsCauseUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url> <a href="${fn:escapeXml(donationsCauseUrl)}">Detalles</a>
						
					
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<a class="btn btn-default" href='<spring:url value="/causes/new" htmlEscape="true"/>'>Añadir causa</a>
	
</petclinic:layout>
