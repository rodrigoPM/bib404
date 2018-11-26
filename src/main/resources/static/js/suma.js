function sumar() {
	document.getElementById("btnGuardar").disabled= false;
 a= document.getElementById("fisico_recurso_bib").value;
 b=document.getElementById("digital_recurso_bib").value;
 c=parseInt(a)+parseInt(b);
 document.getElementById("fisico_recurso_bib").value=c;
}