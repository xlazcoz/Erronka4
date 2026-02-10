const url = "https://fakestoreapi.com/products";

let todosProductos = [];
let carrito = [];

async function cargarProductos() {
    try {
        const response = await fetch(url);
        const productos = await response.json();
        todosProductos = productos;
        
        let productosHTML = '';
        
        productos.forEach(producto => {
            productosHTML += `
                <div>
                    <img src="${producto.image}" width="100"><br>
                    <span>${producto.title}</span><br>
                    <span>${producto.price}€</span><br>
                    <button onclick="agregarAlCarrito(${producto.id})">Saskira gehitu</button>
                </div>
            `;
        });
        
        document.getElementById('products-container').innerHTML = productosHTML;
        
    } catch (error) {
        console.error("Error cargando productos:", error);
    }
}

function agregarAlCarrito(idProducto) {
    const producto = todosProductos.find(p => p.id === idProducto);
    
    if (producto) {
        const itemExistente = carrito.find(item => item.id === idProducto);
        
        if (itemExistente) {
            itemExistente.cantidad += 1;
        } else {
            carrito.push({
                id: producto.id,
                title: producto.title,
                price: producto.price,
                cantidad: 1
            });
        }
        localStorage.setItem("saskia", JSON.stringify(carrito));
        
        alert(`${producto.title} saskira gehitu da!`);
    }
}

cargarProductos();