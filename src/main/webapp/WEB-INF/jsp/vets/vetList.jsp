<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2><fmt:message key="vets"/></h2>

    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="specialities"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td>
                    <c:out value="${vet.firstName} ${vet.lastName}"/>
                </td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />"><fmt:message key="view.xml"/></a>
            </td>
            <td>
                <a href="<spring:url value="/vets.json" htmlEscape="true" />"><fmt:message key="view.json"/></a>
            </td>
        </tr>
    </table>
</petclinic:layout>
