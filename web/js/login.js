document.addEventListener("DOMContentLoaded", () => {
    const formLogin = document.getElementById("login-form");
    const mensajeError = document.getElementById("mensaje-error");

    formLogin.addEventListener("submit", async (evento) => {
        evento.preventDefault(); 

        const emailIngresado = document.getElementById("email").value;
        const passwordIngresada = document.getElementById("password").value;

        try {
            const respuesta = await fetch("../bezeroak.json");
            const bezeroak = await respuesta.json();

            const usuarioValido = bezeroak.find(bezero => 
                bezero.emaila === emailIngresado && bezero.pasaitza === passwordIngresada
            );

            if (usuarioValido) {
                mensajeError.style.display = "none"; 
                
                localStorage.setItem("usuarioLogueado", JSON.stringify(usuarioValido));

                alert(`Ongi etorri, ${usuarioValido.izena}!`); 
                
                window.location.href = "index.html"; 
            } else {
                
                mensajeError.style.display = "block"; 
            }

        } catch (error) {
            console.error("Error al cargar bezeroak.json:", error);
            alert("Errore bat egon da zerbitzarian.");
        }
    });
});