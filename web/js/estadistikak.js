    cargarDatosEstadisticas();


async function cargarDatosEstadisticas() {
    try {
        const respuesta = await fetch('datos_estadisticas.json');
        if (!respuesta.ok) throw new Error("No se pudo cargar el JSON");
        
        const datos = await respuesta.json();
        renderizarDashboard(datos);
        
    } catch (error) {
        console.error("Error al cargar estadísticas:", error);
        document.querySelector('.dashboard-main').innerHTML = `
            <div style="text-align:center; padding: 50px;">
                <h2>Errorea</h2>
                <p>Ezin izan dira datuak kargatu. Mesedez, ziurtatu 'datos_estadisticas.json' fitxategia existitzen dela.</p>
            </div>
        `;
    }
}

function renderizarDashboard(datos) {
    const { resumen_general, productos, clientes, ventas_mensuales } = datos;

    // 1. Cálculos iniciales
    const totalGanado = resumen_general.ganancias_totales;
    const prodBajoStock = productos.filter(p => p.stock < 5);
    const prodEstrella = productos.filter(p => p.ventas_total_euros > 500);
    const prodNoVendidos = productos.filter(p => p.ventas_cantidad === 0);
    
    // Ordenar para Rankings
    const topVendidos = [...productos].sort((a, b) => b.ventas_cantidad - a.ventas_cantidad).slice(0, 5);
    
    const topClientes = clientes
        .filter(c => c.total_pedidos >= 5)
        .sort((a, b) => b.total_pedidos - a.total_pedidos)
        .slice(0, 5);

    // 2. Tarjetas Superiores
    const tarjetasContainer = document.getElementById('resumen-tarjetas');
    tarjetasContainer.innerHTML = `
        <div class="card">
            <div class="card-title">Irabaziak Guztira</div>
            <div class="card-value">${totalGanado.toLocaleString('es-ES')} €</div>
        </div>
        <div class="card">
            <div class="card-title">Produktu Izarrak</div>
            <div class="card-value">${prodEstrella.length}</div>
        </div>
        <div class="card">
            <div class="card-title">Stock Alerta</div>
            <div class="card-value text-danger">${prodBajoStock.length}</div>
        </div>
    `;

    
    // Tabla: Stock < 5
    let htmlStock = `<tr><th>Produktua</th><th class="text-right">Stock</th></tr>`;
    prodBajoStock.forEach(p => {
        htmlStock += `<tr><td>${p.nombre}</td><td class="text-right text-danger">${p.stock}</td></tr>`;
    });
    document.getElementById('tabla-stock').innerHTML = htmlStock;

    // Tabla: Estrellas (> 500€)
    let htmlEstrellas = `<tr><th>Produktua</th><th class="text-right">Sarrerak</th></tr>`;
    prodEstrella.forEach(p => {
        htmlEstrellas += `<tr><td>${p.nombre}</td><td class="text-right text-bold">${p.ventas_total_euros.toLocaleString('es-ES')} €</td></tr>`;
    });
    document.getElementById('tabla-estrellas').innerHTML = htmlEstrellas;

    // Tabla: Más vendidos (Por cantidad)
    let htmlVendidos = `<tr><th>Produktua</th><th class="text-right">Unitateak</th></tr>`;
    topVendidos.forEach(p => {
        if(p.ventas_cantidad > 0) {
            htmlVendidos += `<tr><td>${p.nombre}</td><td class="text-right">${p.ventas_cantidad}</td></tr>`;
        }
    });
    document.getElementById('tabla-mas-vendidos').innerHTML = htmlVendidos;

    // Tabla: Mejores Clientes (Solo los de 5 o más pedidos)
    let htmlClientes = `<tr><th>Bezeroa</th><th class="text-right">Eskaerak</th></tr>`;
    if (topClientes.length > 0) {
        topClientes.forEach(c => {
            htmlClientes += `<tr><td>@${c.usuario}</td><td class="text-right">${c.total_pedidos}</td></tr>`;
        });
    } else {
        htmlClientes += `<tr><td colspan="2" style="text-align:center; color:#777;">Oraindik ez dago bezero fidelik (5+)</td></tr>`;
    }
    document.getElementById('tabla-clientes').innerHTML = htmlClientes;

    // Tabla: Ventas Mensuales
    let htmlMensuales = `<tr><th>Urtea - Hila</th><th class="text-right">Guztira</th></tr>`;
    ventas_mensuales.forEach(v => {
        htmlMensuales += `<tr><td>${v.año} - ${v.mes}</td><td class="text-right text-bold">${v.total.toLocaleString('es-ES')} €</td></tr>`;
    });
    document.getElementById('tabla-mensuales').innerHTML = htmlMensuales;

    // Tabla: Nunca vendidos
    let htmlNoVendidos = `<tr><th>Produktua</th><th class="text-right">Stock</th></tr>`;
    if(prodNoVendidos.length > 0) {
        prodNoVendidos.forEach(p => {
            htmlNoVendidos += `<tr><td>${p.nombre}</td><td class="text-right">${p.stock}</td></tr>`;
        });
    } else {
        htmlNoVendidos += `<tr><td colspan="2" style="text-align:center; color:#777;">Produktu guztiak saldu dira gutxienez behin!</td></tr>`;
    }
    document.getElementById('tabla-no-vendidos').innerHTML = htmlNoVendidos;
}