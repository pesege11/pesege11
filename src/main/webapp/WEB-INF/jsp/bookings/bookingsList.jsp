<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <h2>Bookings</h2>

    <table id="bookingsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Pet</th>
            <th style="width: 200px;">Owner</th>
            <th>Start Date</th>
            <th>End Date</th>            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookings}" var="booking">
            <tr>
                <td>                    
                    <c:out value="${booking.pet.name}"/>
                </td>
                <td>
                    <spring:url value="/owners/{ownerId}.html" var="ownerUrl">
                        <spring:param name="ownerId" value="${booking.pet.owner.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${booking.pet.owner.firstName} ${booking.pet.owner.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${booking.start}"/>
                </td>
                <td>
                    <c:out value="${booking.finish}"/>
                </td>                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
