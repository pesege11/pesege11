<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Bookings">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#start").datepicker({dateFormat: 'yy/mm/dd'});
                $("#finish").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>New Booking</h2>               

        <form:form modelAttribute="booking" class="form-horizontal">
            <div>for <c:out value="${booking.pet.name}"/> ( of <c:out value="${booking.pet.owner.firstName} ${booking.pet.owner.lastName}"/>)</div>
            <div class="form-group has-feedback">
                <petclinic:inputField label="Start Date" name="start"/>
                <petclinic:inputField label="End Date" name="finish"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${booking.pet.id}"/>
                    <button class="btn btn-default" type="submit">Add Booking</button>
                </div>
            </div>
        </form:form>

        <br/>
        <b>Other booking for the pet</b>
        
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Owner</th>
                <th>Start Date</th>
                <th>End Date</th>                
            </tr>
            </thead>
            <c:forEach var="mybooking" items="${booking.pet.bookings}">
            <tr>
                
                <td><c:out value="${mybooking.pet.name}"/></td>
                <td><c:out value="${mybooking.pet.owner.firstName} ${mybooking.pet.owner.lastName}"/></td>
                <td><petclinic:localDate date="${mybooking.start}" pattern="yyyy/MM/dd"/></td>
                <td><petclinic:localDate date="${mybooking.finish}" pattern="yyyy/MM/dd"/></td>                                
            </tr>
            </c:forEach>
        </table>
        
    </jsp:body>

</petclinic:layout>
