<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %> 

<petclinic:layout pageName="vets">
  
    <form:form modelAttribute="vet" class="form-horizontal" id="add-vet-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="firstName"/>
            <petclinic:inputField label="Apellidos" name="lastName"/>
  
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                   
                        <button class="btn btn-default" type="submit"><fmt:message key="add.owner"/></button>
                        <button class="btn btn-default" type="submit"><fmt:message key="update.owner"/></button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
