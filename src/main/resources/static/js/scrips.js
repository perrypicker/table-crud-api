// definición de variables
const baseUrl = "http://localhost:8080/api/items";
const contenedor = document.querySelector("tbody");
let resultados = "";

// referencia a elementos del modal
const modalArticulo = new bootstrap.Modal(
  document.getElementById("modalArticulo")
);
const formulario = document.querySelector("form");
const descripcion = document.getElementById("detail");
const precio = document.getElementById("price");
const stock = document.getElementById("stock");
let opcion = "";

// función para mostrar el modal al dar clic en botón "Crear"
btnCrear.addEventListener("click", () => {
  descripcion.value = "";
  precio.value = "";
  stock.value = "";
  modalArticulo.show();
  opcion = "crear";
});

// función para mostrar los resultados
function mostrar(articulos) {
  articulos.forEach((articulo) => {
    resultados += `
      <tr data-id="${articulo.id}">
        <td>${articulo.id}</td>
        <td>${articulo.detail}</td>
        <td>${articulo.price}</td>
        <td>${articulo.stock}</td>
        <td class="text-center">
          <a class="btnEditar btn btn-primary">Editar</a>
          <a class="btnBorrar btn btn-danger"">Borrar</a>
        </td>
      </tr>
    `;
  });
  contenedor.innerHTML = resultados;
}

// procedimiento para obtener los artículos.
fetch(baseUrl)
  .then((res) => {
    if (!res.ok) throw new Error("Error al obtener artículos.");
    return res.json();
  })
  .then((data) => {
    mostrar(data);
  })
  .catch((err) => {
    console.error("Hubo un error: " + err);
    alertify.alert(
      "Hubo un error al intentar obtener los artículos: " + err,
      function () {
        alertify.message("OK");
      }
    );
  });

// función asíncrona para crear
async function crearArticulo(theBaseUrl) {
  try {
    const res = await fetch(theBaseUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        detail: descripcion.value,
        price: precio.value,
        stock: stock.value,
      }),
    });
    if (!res.ok) {
      throw new Error("Error al crear el artículo.");
    }
    const resData = await res.json();

    // Agregar una nueva fila al final de la tabla
    const nuevaFila = `
      <tr data-id="${resData.id}">
        <td>${resData.id}</td>
        <td>${resData.detail}</td>
        <td>${resData.price}</td>
        <td>${resData.stock}</td>
        <td class="text-center">
          <a class="btnEditar btn btn-primary">Editar</a>
          <a class="btnBorrar btn btn-danger">Borrar</a>
        </td>
      </tr>
    `;
    contenedor.insertAdjacentHTML("beforeend", nuevaFila);

    // mensaje de éxito
    alertify.success("Artículo creado exitosamente.");
  } catch (err) {
    console.error("Hubo un error al crear el usuario: " + err);
    alertify.error("Hubo un error al intentar crear el artículo.");
  }
}

// función asincrona para editar
async function editarArticulo(theBaseUrl, theId) {
  try {
    const res = await fetch(`${theBaseUrl}/${theId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        detail: descripcion.value,
        price: precio.value,
        stock: stock.value,
      }),
    });
    const resData = await res.json();

    // Actualizar la fila directamente
    const fila = document.querySelector(`tr[data-id='${theId}']`); // selector para elementos <tr> con atributo personalizado "data-id"
    fila.children[1].textContent = resData.detail;
    fila.children[2].textContent = resData.price;
    fila.children[3].textContent = resData.stock;

    // mensaje de éxito
    alertify.success("Artículo editado exitosamente.");
  } catch (err) {
    console.error("Hubo un error al editar artículo: " + err);
    alertify.error("Hubo un error al editar artículo.");
  }
}

// función asíncrona para borrar
async function borrarArticulo(theBaseUrl, theId) {
  try {
    const res = await fetch(`${theBaseUrl}/${theId}`, {
      method: "DELETE",
    });
    if (!res.ok) {
      throw new Error("Error al intentar borrar artículo.");
    }
    // const resData = await res.json();
    // console.log(resData);
    // mostrar();
    const fila = document.querySelector(`tr[data-id='${theId}']`);
    fila.remove(); // Eliminar la fila del DOM

    alertify.success("Artículo borrado exitosamente.");
  } catch (err) {
    console.error("Hubo un error al borrar artículo: " + err);
    alertify.error("Hubo un error al intentar borrar el artículo.");
  }
}

// Método on (simulación de $on de JQuery)
const on = (element, event, selector, handler) => {
  element.addEventListener(event, (e) => {
    if (e.target.closest(selector)) {
      handler(e);
    }
  });
};

// Evento para Borrar
on(document, "click", ".btnBorrar", (e) => {
  const fila = e.target.parentNode.parentNode;
  const id = fila.firstElementChild.innerHTML;
  alertify.confirm(
    `¿Seguro que quieres borrar <b>${fila.children[1].innerHTML}</b>?<br>
    Esta acción no se puede deshacer.`,
    function () {
      borrarArticulo(baseUrl, id);
    },
    function () {
      alertify.error("Cancel");
    }
  );
});

// evento para editar
let idForm = 0;
on(document, "click", ".btnEditar", (e) => {
  const fila = e.target.parentNode.parentNode;

  idForm = fila.children[0].innerHTML;
  const descripcionForm = fila.children[1].innerHTML;
  const precioForm = fila.children[2].innerHTML;
  const stockForm = fila.children[3].innerHTML;

  descripcion.value = descripcionForm;
  precio.value = precioForm;
  stock.value = stockForm;

  opcion = "editar";
  modalArticulo.show();
});

// procedimientopara crear y editar
formulario.addEventListener("submit", (e) => {
  e.preventDefault();
  if (opcion === "crear") {
    crearArticulo(baseUrl);
  }
  if (opcion === "editar") {
    editarArticulo(baseUrl, idForm);
  }
  modalArticulo.hide();
});
