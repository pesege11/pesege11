<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <c:if test="${pet['new']}"><fmt:message key="new"/> </c:if> <fmt:message key="pet"/>
        </h2>
        <form:form modelAttribute="pet"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${pet.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label"><fmt:message key="owner"/> </label>
                    <div class="col-sm-10">
                        <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
                    </div>
                </div>
                <petclinic:inputField label="Nombre" name="name"/>
                <petclinic:inputField label="Fecha de nacimiento" name="birthDate"/>
                <div class="control-group">
                    <petclinic:selectField name="type" label="Type " names="${types}" size="5"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${pet['new']}">
                            <button class="btn btn-default" type="submit"><fmt:message key="add.pet"/> </button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit"><fmt:message key="update.pet"/> </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!pet['new']}">
        	<a href="/owners/${owner.id}/pets/${pet.id}/delete">Delete</a>
        </c:if>
    </jsp:body>
</petclinic:layout>
