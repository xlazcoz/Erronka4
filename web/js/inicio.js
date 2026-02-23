// js/inicio.js

let productosDestacados = [];

document.addEventListener('DOMContentLoaded', () => {
    cargarProductosInicio();
});

async function cargarProductosInicio() {
    try {
        const respuesta = await fetch('https://fakestoreapi.com/products?limit=3');
        productosDestacados = await respuesta.json();
        
        const contenedor = document.getElementById('contenedor-productos');
        if (!contenedor) return;

        let htmlProductos = '';
        
        productosDestacados.forEach(prod => {
            let tituloCorto = prod.title.length > 30 ? prod.title.substring(0, 30) + '...' : prod.title;
            
            htmlProductos += `
                <div class="tarjeta-producto">
                    <img class="imagen-producto" src="${prod.image}" alt="${tituloCorto}">
                    <h3 class="nombre-producto">${tituloCorto}</h3>
                    <p class="precio-producto">${prod.price.toFixed(2)} €</p>
                    <button class="boton-comprar" onclick="agregarAlCarritoDesdeInicio(${prod.id})">Saskira Gehitu</button>
                </div>
            `;
        });
        
        contenedor.innerHTML = htmlProductos;
        
    } catch (error) {
        console.error("Error al cargar los productos de inicio:", error);
    }
}

function agregarAlCarritoDesdeInicio(idProducto) {
    const productoEncontrado = productosDestacados.find(prod => prod.id === idProducto);
    
    if (productoEncontrado) {
        if (typeof agregarAlCarrito === 'function') {
            agregarAlCarrito(productoEncontrado); 
            mostrarNotificacion('Saskira gehituta!'); 
        } else {
            console.error("No se encuentra la función agregarAlCarrito");
        }
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
    }, 2500);
}