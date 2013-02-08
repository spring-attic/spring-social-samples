<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
top.location.href="https://www.facebook.com/dialog/oauth?client_id=<c:out value="${clientId}"/>&redirect_uri=<c:out value="${canvasPage}"/>";
</script>