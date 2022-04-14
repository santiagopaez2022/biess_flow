function fechahoy() {
  var diasemana = new Array('Domingo', 'Lunes', 'Martes', 'Mi&eacute;rcoles', 'Jueves', 'Viernes', 'S&aacute;bado');
  var nombremes = new Array('enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre');
  var ahora;
  var fecha = new Date();
  var anio = fecha.getYear();
  var mes = fecha.getMonth();
  var dia = fecha.getDay();
  var num = fecha.getDate();
  ahora = diasemana[dia] + ", " + num + " " + nombremes[mes] + " " + anio;
  return ahora;
} 