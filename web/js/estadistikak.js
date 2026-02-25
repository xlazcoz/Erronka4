document.addEventListener('DOMContentLoaded', () => {
    cargarDatosEstadisticas();
});

async function cargarDatosEstadisticas() {
    try {
        const [respuestaProductos, respuestaBezeroak] = await Promise.all([
            fetch('../produktuak.json'),
            fetch('../bezeroak.json')
        ]);

        if (!respuestaProductos.ok || !respuestaBezeroak.ok) {
            throw new Error("Ezin izan dira JSON fitxategiak kargatu");
        }
        
        const productos = await respuestaProductos.json();
        const bezeroak = await respuestaBezeroak.json();

        renderizarDashboard(productos, bezeroak);
        
    } catch (error) {
        console.error("Error al cargar estadísticas:", error);
        document.querySelector('.dashboard-main').innerHTML = `
            <div style="text-align:center; padding: 50px;">
                <h2>Errorea</h2>
                <p>Ezin izan dira datuak kargatu. Mesedez, ziurtatu 'produktuak.json' eta 'bezeroak.json' existitzen direla.</p>
            </div>
        `;
    }
}

function renderizarDashboard(productos, bezeroak) {

    let totalGanado = 0;
    let ventasPorMes = {};
    let clientesFidelidad = [];

+
    bezeroak.forEach(cliente => {
        let numPedidos = 0;
        if (cliente.eskaerak && cliente.eskaerak.length > 0) {
            cliente.eskaerak.forEach(eskaera => {
                totalGanado += eskaera.guztira;
                numPedidos++;

                const mes = eskaera.data.substring(0, 7); 
                if (!ventasPorMes[mes]) ventasPorMes[mes] = 0;
                ventasPorMes[mes] += eskaera.guztira;
            });
        }

        if (numPedidos > 0) {
            clientesFidelidad.push({
                usuario: (cliente.izena + cliente.abizena).toLowerCase(),
                total_pedidos: numPedidos
            });
        }
    });

    const topClientes = clientesFidelidad.sort((a, b) => b.total_pedidos - a.total_pedidos).slice(0, 5);


    const prodBajoStock = productos.filter(p => p.stocka < 5);


    const tarjetasContainer = document.getElementById('resumen-tarjetas');
    if (tarjetasContainer) {
        tarjetasContainer.innerHTML = `
            <div class="card">
                <div class="card-title">Irabaziak Guztira</div>
                <div class="card-value">${totalGanado.toLocaleString('es-ES')} €</div>
            </div>
            <div class="card">
                <div class="card-title">Produktu Izarrak</div>
                <div class="card-value">0</div> </div>
            <div class="card">
                <div class="card-title">Stock Alerta</div>
                <div class="card-value text-danger">${prodBajoStock.length}</div>
            </div>
        `;
    }





    let htmlStock = `<tr><th>Produktua</th><th class="text-right">Stock</th></tr>`;
    if (prodBajoStock.length > 0) {
        prodBajoStock.forEach(p => {
            htmlStock += `<tr><td>${p.izena}</td><td class="text-right text-danger">${p.stocka}</td></tr>`;
        });
    } else {
        htmlStock += `<tr><td colspan="2" style="text-align:center; color:#777;">Stock ondo dago!</td></tr>`;
    }
    document.getElementById('tabla-stock').innerHTML = htmlStock;


    let htmlEstrellas = `<tr><th>Produktua</th><th class="text-right">Sarrerak</th></tr>`;
    htmlEstrellas += `<tr><td colspan="2" style="text-align:center; color:#777;">Datu falta eskaeretan</td></tr>`;
    document.getElementById('tabla-estrellas').innerHTML = htmlEstrellas;

 
    let htmlVendidos = `<tr><th>Produktua</th><th class="text-right">Unitateak</th></tr>`;
    htmlVendidos += `<tr><td colspan="2" style="text-align:center; color:#777;">Datu falta eskaeretan</td></tr>`;
    document.getElementById('tabla-mas-vendidos').innerHTML = htmlVendidos;


    let htmlClientes = `<tr><th>Bezeroa</th><th class="text-right">Eskaerak</th></tr>`;
    if (topClientes.length > 0) {
        topClientes.forEach(c => {
            htmlClientes += `<tr><td>@${c.usuario}</td><td class="text-right">${c.total_pedidos}</td></tr>`;
        });
    } else {
        htmlClientes += `<tr><td colspan="2" style="text-align:center; color:#777;">Oraindik ez dago bezerorik</td></tr>`;
    }
    document.getElementById('tabla-clientes').innerHTML = htmlClientes;


    let htmlMensuales = `<tr><th>Urtea - Hila</th><th class="text-right">Guztira</th></tr>`;
    const mesesOrdenados = Object.keys(ventasPorMes).sort().reverse();
    mesesOrdenados.forEach(mes => {
        htmlMensuales += `<tr><td>${mes}</td><td class="text-right text-bold">${ventasPorMes[mes].toLocaleString('es-ES')} €</td></tr>`;
    });
    document.getElementById('tabla-mensuales').innerHTML = htmlMensuales;


    let htmlNoVendidos = `<tr><th>Produktua</th><th class="text-right">Stock</th></tr>`;
    htmlNoVendidos += `<tr><td colspan="2" style="text-align:center; color:#777;">Datu falta eskaeretan</td></tr>`;
    document.getElementById('tabla-no-vendidos').innerHTML = htmlNoVendidos;
}