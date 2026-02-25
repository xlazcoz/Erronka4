const contenedorTarjetas = document.getElementById("cart-container");

document.addEventListener("DOMContentLoaded", () => {
    crearTarjetasProductosCarrito();
    actualizarTotales();
    actualizarNumeroCarrito(); 
});

function crearTarjetasProductosCarrito() {
    contenedorTarjetas.innerHTML = "";

    const productos = JSON.parse(localStorage.getItem("saskia")) || [];

    if (productos && productos.length > 0) {
        productos.forEach((producto) => {
            const nuevoproducto = document.createElement("div");
            nuevoproducto.classList.add("tarjeta-producto");
            
            nuevoproducto.innerHTML = `
                <img src="${producto.image}" alt="${producto.title}">
                <div class="producto-info">
                    <span class="producto-titulo">${producto.title}</span>
                    <span class="producto-precio">${producto.price.toFixed(2)} €</span>
                </div>
                <div class="controles-cantidad">
                    <button class="btn-cantidad btn-menos">-</button>
                    <span class="cantidad">${producto.cantidad}</span>
                    <button class="btn-cantidad btn-mas">+</button>
                </div>
            `;
            contenedorTarjetas.appendChild(nuevoproducto);

            nuevoproducto.querySelector(".btn-menos").addEventListener("click", () => {
                restarAlCarrito(producto); 
                crearTarjetasProductosCarrito();
                actualizarTotales();
            });

            nuevoproducto.querySelector(".btn-mas").addEventListener("click", () => {
                agregarAlCarrito(producto); 
                crearTarjetasProductosCarrito();
                actualizarTotales();
            });
        });
    } else {
        contenedorTarjetas.innerHTML = "<p style='text-align:center; color:#777; padding: 40px 0;'>Zure saskia hutsik dago.</p>";
    }
}

function actualizarTotales() {
    const productos = JSON.parse(localStorage.getItem("saskia")) || [];
    let cantidad = 0;
    let precio = 0;

    if (productos && productos.length > 0) {
        productos.forEach((producto) => {
            cantidad += producto.cantidad;
            precio += producto.price * producto.cantidad;
        });
    }

    const totalesContainer = document.getElementById("totales");
    if (totalesContainer) {
        if (cantidad > 0) {
            totalesContainer.innerHTML = `
                <div class="totales-info">
                    <div>Unitateak: ${cantidad}</div>
                    <div>Guztira: ${precio.toFixed(2)} €</div>
                </div>
                <div class="botones-cart">
                    <button id="reiniciar" class="btn-vaciar">Saskia Hustu</button>
                    <button id="comprar" class="btn-comprar">Erosi</button>
                </div>
            `;

            const btnReiniciar = document.getElementById("reiniciar");
            if (btnReiniciar) {
                btnReiniciar.addEventListener("click", () => {
                    localStorage.removeItem("saskia");
                    crearTarjetasProductosCarrito();
                    actualizarTotales();
                    actualizarNumeroCarrito(); 
                });
            }

            const btnComprar = document.getElementById("comprar");
            if (btnComprar) {
                btnComprar.onclick = function(){
                    alert("Erosketa eginda! Eskerrik asko zure konfiantzagatik.");
                    localStorage.removeItem("saskia");
                    crearTarjetasProductosCarrito();
                    actualizarTotales();
                    actualizarNumeroCarrito(); 
                };
            }
        } else {
            totalesContainer.innerHTML = ""; 
        }
    }
}