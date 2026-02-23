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
                <img src="${producto.image}" width="50">
                <span>${producto.title}</span>
                <span>${producto.price}€</span>
                <div>
                    <button class="btn-menos">-</button>
                    <span class="cantidad">${producto.cantidad}</span>
                    <button class="btn-mas">+</button>
                </div>
            `;
            contenedorTarjetas.appendChild(nuevoproducto);

            nuevoproducto.getElementsByClassName("btn-menos")[0].addEventListener("click", (e) => {
                restarAlCarrito(producto); 
                crearTarjetasProductosCarrito();
                actualizarTotales();
            });

            nuevoproducto.getElementsByClassName("btn-mas")[0].addEventListener("click", (e) => {
                agregarAlCarrito(producto); 
                crearTarjetasProductosCarrito();
                actualizarTotales();
            });
        });
    } else {
        contenedorTarjetas.innerHTML = "<p>Saskia hutsik dago (El carrito está vacío)</p>";
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
        totalesContainer.innerHTML = `
            <div class="totales-info">
                <div>Unitateak guztira: ${cantidad}</div>
                <div>Prezio totala: ${precio.toFixed(2)}€</div>
            </div>
            <button id="reiniciar">Saskia hustu</button>
            <button id="comprar">Erosi</button>
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
                alert("Erosketa eginda! (Compra realizada)");
                localStorage.removeItem("saskia");
                crearTarjetasProductosCarrito();
                actualizarTotales();
                actualizarNumeroCarrito(); 
            }
        }
    }
}

