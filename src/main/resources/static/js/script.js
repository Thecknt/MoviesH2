function actorSelected(select){
let index = select.selectedIndex;
let option = select.options[index];
let id = option.value;
let nombre = option.text;
let urlImagen = option.dataset.url;

option.disabled= "disabled";
select.selectedIndex = 0; //comboBox lo vuelvo a cero

agregarActor(id, nombre, urlImagen);

let ids = $("#ids").val();

if(ids == ""){
$("#ids").val(id);
} else {
$("#ids").val(ids + "," + id);
   }
}

function agregarActor(id, nombre, urlImagen){
let htmlString = `<div class="card col-md-3 m-2" style="width: 10rem">
     <img src="{urlImagen}" class="card-img-top">
         <div class="card-body">
              <p class="card-text">${nombre}</p>
                 <button class="btn btn-danger" data-id="{id}" onclick="eliminarActor(this); return false;">Eliminar</button>
         </div>
</div>`;

htmlString = htmlString.replace("{urlImagen}",urlImagen);
htmlString = htmlString.replace("{NOMBRE}",nombre);
htmlString = htmlString.replace("{ID}",id);
$("#protagonistas_container").append(htmlString);
}

function eliminarActor(btn){
let id = btn.dataset.id;
let node = btn.parentElement.parentElement;
let arrayIds = $("#ids").val().split(",").filter(idActor => idActor != id);//si quiero eliminar el id=3 por ej: 1,2,3,10 => [1,2,3,10] => [1,2,10]
$("#ids").val(arrayIds.join(","));
$("#protagonistas option[value='"+ id + "']").prop("disabled", false);
$(node).remove();
}