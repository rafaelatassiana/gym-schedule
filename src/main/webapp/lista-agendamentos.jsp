<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container w-100">
    <div class="justify-content-center w-100">
        <h3 style="margin-bottom:70px">Gyms Shedule - Meus agendamentos</h3>
        <table class="table">
          <thead>
            <tr>
                <th scope="col">Data/Horário</th>
                <th scope="col">Ação</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="agendamento" items="${agendamentos}">
                <tr>
                    <td>${agendamento.getData()} ${agendamento.getHorario()}</td>
                    <td>
                        <button class="btn btn-danger" style="margin-top: 0" onclick="javascript:DeletarAgendamento('${agendamento.getId()}')">CANCELAR</button>                        
                    </td>
                </tr>
            </c:forEach>
          </tbody>
        </table>
        <button class="btn btn-primary" onclick="window.location='criar-cliente.jsp'">Novo agendamento</button>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript">
        DeletarAgendamento = function(agendamento){
            console.log(agendamento);
            if (window.confirm("Deseja cancelar o agendamento?")) {
                document.location.href="deletarAgendamento?id="+agendamento;
            }
        }
</script>
</body>
</html>
