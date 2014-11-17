package domApli;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import java.util.Scanner;


public class Aplicacion {
	
	
	private static Scanner in;

	public static char leerOpcion() {
		
		in = new Scanner(System.in);
		String opc = in.next();
		opc = opc.toLowerCase();
		if(opc.length() == 1){
			return opc.charAt(0);
		}else{
			return 0;
		}
	}

	public static void main(String[] args) {
		char o = ' ';
		int contador = 0;
		int cantidadPomodoros;
		boolean continuarPomodoro;

		System.out.println("Contador pomodoro v0.2");
		System.out.println();
		System.out.println("La tecnica pododoro se define asi:");

		System.out.println("1. Decidir la tarea a realizar");
		System.out
				.println("2. Poner el pomodoro (el reloj o cronómetro) a 25 minutos");
		System.out
				.println("3. Trabajar en la tarea hasta que el reloj suene y anotar una X");
		System.out.println("4. Tomar un pausa breve (5 minutos)");
		System.out
				.println("5. Cada cuatro \"pomodoros\" tomar una pausa más larga (15-20 minutos)");

		System.out
				.println("Esta herramienta automatiza las alertas de pomodoro y descanzos");
		System.out.println();

		do {
			System.out.print("Iniciar contador pomodoro? [S/n]: ");
			o = leerOpcion();
			if (o == 0) {
				o = 's';
			}

			if (o != 's' && o != 'n') {
				System.out.println("Debes ingresar una opcion valida!");
			}
		} while (o != 's' && o != 'n');
		if (o == 's') {
			Date tstamp = new Date();
			DateFormat formatoTstamp = new SimpleDateFormat(
					"HH:mm:ss - dd/MM/yyyy || ");
			cantidadPomodoros = 0;
			
			System.out.println(formatoTstamp.format(tstamp)
					+ "Iniciada la cuenta pomodoro");
			boolean esDescanzo = false;
			int tiempo;
			String mensaje;

			boolean tiempoCompleto = false;
			continuarPomodoro = true;

			do {
				if (esDescanzo) {
					if(cantidadPomodoros == 3){
						cantidadPomodoros=0;
						tiempo = 900000;
					}else{
						tiempo = 300000;
					}
					mensaje = "Acabo el descanzo!!\nPasar a fase de estudio?";
				} else {
					tiempo = 1500000;
					mensaje = "A descanzar!!\nPasando a fase de descanzo\n(Cancelar para terminar con la cuenta pomodoro)";
				}
				
				do {
					try {
						Thread.sleep(1000);
						contador++;
					} catch (InterruptedException e) {
						System.err
								.println("Error en la cuenta!\nSaliendo de emergencia ...");
						System.exit(0);
					}
					if (contador == tiempo) {
						contador = 0;
						tiempoCompleto = true;
					}

				} while (!tiempoCompleto);

				int continuar = JOptionPane.showConfirmDialog(null, mensaje,
						"Hey!", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				
				if (continuar == 2) {
					continuarPomodoro = false;
					System.out.println("Hasta luego!");
				} else {
					String mensajeLog;
					
					tiempoCompleto=false;
					esDescanzo = !esDescanzo;
					
					if (esDescanzo) {
						mensajeLog = "Paso a fase de descanzo de "+((cantidadPomodoros == 3) ? "15" : "5")+" minutos";
					} else {
						mensajeLog = "Descanzo terminado, inicio fase de estudio";
						cantidadPomodoros++;
					}
					tstamp = new Date();
					System.out.println(formatoTstamp.format(tstamp)
							+ mensajeLog);
				}
			} while (continuarPomodoro);
		} else {
			System.out.println("Hasta luego");
		}
	}
}