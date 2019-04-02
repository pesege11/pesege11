<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2>Información sobre causa</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripción</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>Objetivo presupuestario</th>
            <td><c:out value="${cause.budgetTarget}"/></td>
        </tr>
        <tr>
            <th>Dinero restante</th>
            <td><c:out value="${cause.remainingMoney}"/><c:if test="${cause.remainingMoney eq 0.0}"><p style="display:inline-block;color:red;font-weight:bold">&nbsp;CAUSA CERRADA</p></c:if></td>
        </tr>
    </table>

     <spring:url value="{causeId}/delete" var="deleteUrl">
        <spring:param name="causeId" value="${cause.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Eliminar causa</a>
    


    <br/>
    <br/>
    <br/>
    <h2>Donaciones</h2>

   <table id="causeTable" class="table table-striped">
		<thead>
			<tr>
				<th>Cliente</th>
				<th>Cantidad</th>
				<th>Fecha</th>

			</tr>


		</thead>
		<tbody>
			<c:forEach items="${cause.donations}" var="donation">
				<tr>
					<td><c:out value="${donation.client}" /></td>
					
					<td><c:out value="${donation.amount}" /></td>

					<td><c:out value="${donation.donationDate}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	
	<c:if test="${cause.remainingMoney ne 0.0}">
<spring:url value="/causes/{causeId}/donations/new" var="newUrl">
        <spring:param name="causeId" value="${cause.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(newUrl)}" class="btn btn-default">Hacer una donación</a>
	</c:if>

</petclinic:layout>
