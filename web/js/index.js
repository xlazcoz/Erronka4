const url = "../produktuak.json";
let todosProductos = [];

async function cargarProductos() {
    try {
        const response = await fetch(url);
        const productos = await response.json();
        todosProductos = productos;
        
        let productosHTML = '';
        
        productos.forEach(producto => {
            let imagen = producto.irudia || "img/logo.png";

            productosHTML += `
                <div class="tarjeta-producto">
                    <img src="${imagen}" width="100" alt="${producto.izena}"><br>
                    <span>${producto.izena}</span><br>
                    <span>${producto.prezioa}€</span><br>
                    <button onclick="procesarCompra(${producto.id})">Saskira gehitu</button>
                </div>
            `;
        });
        
        const container = document.getElementById('products-container');
        if (container) {
            container.innerHTML = productosHTML;
        }
        
    } catch (error) {
        console.error("Error cargando productos:", error);
    }
}

function procesarCompra(idProducto) {
    const productoEncontrado = todosProductos.find(prod => prod.id === idProducto);
    
    if (productoEncontrado) {
        agregarAlCarrito(productoEncontrado);
        mostrarNotificacion(`${productoEncontrado.izena} saskira gehitu da!`);
    } else {
        console.error("Producto no encontrado");
    }
}

function mostrarNotificacion(mensaje) {
    const notificacion = document.createElement('div');
    notificacion.textContent = mensaje;
    notificacion.classList.add('notificacion');
    document.body.appendChild(notificacion);

    setTimeout(() => {
        notificacion.classList.add('mostrar');
    }, 10);

    setTimeout(() => {
        notificacion.classList.remove('mostrar'); 
        setTimeout(() => {
            notificacion.remove();
        }, 500);
    }, 3000);
}

if(document.getElementById('products-container')) {
    cargarProductos();
}