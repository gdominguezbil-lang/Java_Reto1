package Reto1;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Reto1 {

	public static void main(String[] args) {

		// Creamos las variables que usaremos y comenzamos el try-catch, donde crearemos
		// el File, el scanner que usaremos de forma habitual, y,
		// el primer scanne para leer.

		String linea;
		int cantidadPaises = 0;
		int numeritos;
		try {

			File file = new File("./src/DatosIgualdad.csv");
			Scanner scReed1 = new Scanner(file);
			Scanner sc = new Scanner(System.in);

			// Aqui leeremos una primera fila antes de entrar en el bucle, esto es porque en
			// el archivo .csv tiene un título, donde pone
			// en la primera fila el nombre de los datos y no los datos

			scReed1.nextLine();

			// Ahora entramos en el bucle, donde contaremos cuántas líneas hay, como vemos
			// en el archivo de DatosIgualdad.csv, hay 28 paises,
			// habremos de verificar si sale 28, por eso además, haremos un syso, dónde
			// indicaremos la cantidad de paises tras terminal el bucle

			while (scReed1.hasNextLine()) {
				scReed1.nextLine();
				cantidadPaises++;
			}
			System.out.println("La cantidad de paises es: " + cantidadPaises);

			// confirmamos y cerramos el scanner para volver a abirlo dentro de poco

			scReed1.close();

			String[] aPais = new String[cantidadPaises];
			String[] aUbicacion = new String[cantidadPaises];
			double[] aTrabajo = new double[cantidadPaises];
			double[] aDinero = new double[cantidadPaises];
			double[] aConocimiento = new double[cantidadPaises];
			double[] aTiempo = new double[cantidadPaises];
			double[] aPoder = new double[cantidadPaises];
			double[] aSalud = new double[cantidadPaises];

			Scanner scReed2 = new Scanner(file);

			// Creamos el bucle para cargar los arrays, en este punto ya es una cosa
			// habitual; debemos cargar los arrays, en un bucle leyendo cada linea,
			// y en cada línea, lo separamos con el .split("x") y lo metemos en un string de
			// valor, y por cada espacio de la linea se la indicamos al array
			// correspondiente

			scReed2.nextLine();

			for (int i = 0; i < cantidadPaises; i++) {
				linea = scReed2.nextLine();
				String[] valor = linea.split(";");

				aPais[i] = valor[0];
				aUbicacion[i] = valor[1];
				aTrabajo[i] = Double.valueOf(valor[2]);
				aDinero[i] = Double.valueOf(valor[3]);
				aConocimiento[i] = Double.valueOf(valor[4]);
				aTiempo[i] = Double.valueOf(valor[5]);
				aPoder[i] = Double.valueOf(valor[6]);
				aSalud[i] = Double.valueOf(valor[7]);
			}

			scReed2.close();

			// Aquí empezaremos con el menú, donde atenderemos a las necesidades del
			// ejercicio

			do {
				System.out.println(
						"============¡Bienvenido a Calculador de Desigualdad!============\n[1] : Visualizar datos por ubicación\n[2] : "
								+ "Media de datos por parámetro 'DINERO'\n[3] : Paises con la media superior en el parametro 'TIEMPO'\n[4] : Modificar valor de los "
								+ "paises\n[5] : Guardar los cambios\n[6] : Paises mas desiguales\n[7] : Salir");
				numeritos = Integer.parseInt(sc.nextLine());

				switch (numeritos) {
				case 1:
					verDatosUbicacion(sc, cantidadPaises, aPais, aUbicacion, aTrabajo, aDinero, aConocimiento, aTiempo,
							aPoder, aSalud);
					break;
				case 2:
					mediaDinero(sc, cantidadPaises, aPais, aUbicacion, aTrabajo, aDinero, aConocimiento, aTiempo,
							aPoder, aSalud);
					break;
				case 3:
					paisesSuperiorMediaTiempo(sc, cantidadPaises, aPais, aUbicacion, aTrabajo, aDinero, aConocimiento,
							aTiempo, aPoder, aSalud);
					break;
				case 4:
					modifyPais(sc, cantidadPaises, aPais, aUbicacion, aTrabajo, aDinero, aConocimiento,
							aTiempo, aPoder, aSalud);
					break;
				case 5:
					guardarCambios(sc, cantidadPaises, aPais, aUbicacion, aTrabajo, aDinero, aConocimiento,
							aTiempo, aPoder, aSalud, file);
					break;
				case 6:
					menorIgualdad(sc, cantidadPaises, aPais, aUbicacion, aTrabajo, aDinero, aConocimiento,
							aTiempo, aPoder, aSalud);
					break;
				case 7:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Número no válido, introduzca otro porfavor...");
				}

			} while (numeritos != 7);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 1. opción del menú "1. Visualización de datos por ubicación". Será necesario
	 * crear un PROCEDIMIENTO para visualizar el contenido de todos los arrays por
	 * pantalla (0,5p.) con el título (0,25p.) de los países cuya ubicación coincide
	 * con la ubicación que el usuario ha introducido. Será necesario solicitar la
	 * ubicación y mostrar un mensaje de error en caso de que la ubicación
	 * introducida no exista (0,25p.).
	 */

	public static void verDatosUbicacion(Scanner sc, int cantidad, String[] pais, String[] ubi, double[] trabajo,
			double[] dinero, double[] conoci, double[] tiempo, double[] poder, double[] salud) {
		String datIntrod;
		System.out.println("De que país deseas ver la información? : ");
		datIntrod = sc.nextLine();
		
		System.out.println(
				"\nPaís		|	Ubicación	|	Empleo	|	Dinero	|	Conocimiento	|	Tiempo	|	Poder	|	Salud\n");

		for (int i = 0; i < cantidad; i++) {
			if (datIntrod.equalsIgnoreCase(ubi[i])) {
				System.out.println(pais[i] + "	|	" + ubi[i] + "		|	"
						+ trabajo[i] + "	|	" + dinero[i] + "	|	" + conoci[i]
						+ "		|	" + tiempo[i] + "	|	" + poder[i] + "	|	"
						+ salud[i]);
			}
		}
	}

	/*
	 * 2. opción del menú "2. Media de datos parámetro DINERO". Será necesario crear
	 * un MÉTODO para calcular la media de dicho parámetro (0,75p.) y posteriormente
	 * mostrar el siguiente mensaje:
	 * "La media del parámetro DINERO para los X países es Y" (0,25p., siendo X el
	 * número total de países).
	 */

	public static void mediaDinero(Scanner sc, int cantidad, String[] pais, String[] ubi, double[] trabajo,
			double[] dinero, double[] conoci, double[] tiempo, double[] poder, double[] salud) {

		double dineroTot = 0;
		double dineroMedia = 0;

		for (int i = 0; i < cantidad; i++) {
			dineroTot = dineroTot + dinero[i];
		}

		dineroMedia = dineroTot / cantidad;
		System.out.println("\nLa media del parámetro 'DINERO' para los 28 países es " + dineroMedia + "\n\n");
	}

	/*
	 * 3. opción del menú
	 * "3. Países con valor superior a la media en parámetro TIEMPO". Será necesario
	 * crear un PROCEDIMIENTO para mostrar la media del parámetro TIEMPO y mostrar
	 * una lista con los países cuyo valor del parámetro TIEMPO es superior a dicha
	 * media (0,5p. + 0,25p. si se utiliza el método desarrollado en la opción 3.).
	 * Mensajes a mostrar:
	 * "La media del parámetro TIEMPO para los X países es Z"(0,25p).
	 * "Los países cuyo valor del parámetro TIEMPO es superior a dicha media son: JJJJ T1, KKK T2"
	 * (0,5p.).
	 */

	public static void paisesSuperiorMediaTiempo(Scanner sc, int cantidad, String[] pais, String[] ubi,
			double[] trabajo, double[] dinero, double[] conoci, double[] tiempo, double[] poder, double[] salud) {

		double tiempoTot = 0;
		double tiempoMedia = 0;
		int contad = 0;
		int contad2 = 0;
		String cadenaPaises = "";

		for (int i = 0; i < cantidad; i++) {
			tiempoTot = tiempoTot + tiempo[i];
		}
		tiempoMedia = tiempoTot / cantidad;

		for (int i = 0; i < cantidad; i++) {
			if (tiempo[i] > tiempoMedia) {
				contad++;
			}
		}

		int[] posiciones = new int[contad];

		for (int i = 0; i < cantidad; i++) {
			if (tiempo[i] > tiempoMedia) {
				posiciones[contad2] = i;
				contad2++;
			}
		}

		System.out.println("\nLa media del parámetro 'Tiempo' para los 28 países es " + String.format(Locale.US, "%.2f", tiempoMedia) + "\n");
		System.out.println("Los países cuyo valor parametro 'TIEMPO' supera dicha media son: \n");
		for (int i = 0; i < contad; i++) {
			cadenaPaises = pais[posiciones[i]] + " " + tiempo[posiciones[i]] + " 		" + cadenaPaises;
		}
		System.out.println(cadenaPaises);

	}
	/*
	 * 4. opción del menú "4. Modificar valores país". Será necesario crear un PROCEDIMIENTO para solicitar el país y 
	 * cada uno de los valores por parámetro (0,5p.). Validando que el país exista (0,25p.) 
	 * y los valores introducidos por el usuario sean mayores o iguales a 0 y menores o iguales a 100 y 
	 * mostrando un mensaje de error en caso de que no sea así ("Error: El valor introducido no es válido.") (0,25p.).
	 */
	
	public static void modifyPais(Scanner sc, int cantidad, String[] pais, String[] ubi,
			double[] trabajo, double[] dinero, double[] conoci, double[] tiempo, double[] poder, double[] salud) {
		String introd = "";
		int posi = -1;
		
		System.out.println("Introduzca el país del que desea modificar sus parámetros: ");
		introd = sc.nextLine();
		for(int i = 0; i < cantidad; i++) {
			if(introd.equalsIgnoreCase(pais[i])) {
				posi = i;
				break;
			}
		}
		if(posi == -1) {
			System.out.println("Este país no existe...\n");
		} else {
			System.out.println("El país que desea modificae es " + pais[posi] + ".");
			System.out.println("Empleo base: " + trabajo[posi]);
			trabajo[posi] = modificarCualquiera(sc, trabajo[posi]);
			System.out.println("Dinero base: " + dinero[posi]);
			dinero[posi] = modificarCualquiera(sc, dinero[posi]);
			System.out.println("Conocimiento base: " + conoci[posi]);
			conoci[posi] = modificarCualquiera(sc, conoci[posi]);
			System.out.println("Tiempo base: " + tiempo[posi]);
			tiempo[posi] = modificarCualquiera(sc, tiempo[posi]);
			System.out.println("Poder base: " + poder[posi]);
			poder[posi] = modificarCualquiera(sc, poder[posi]);
			System.out.println("Salud base: " + salud[posi]);
			salud[posi] = modificarCualquiera(sc, salud[posi]);
			
			System.out.println("Los nuevos parametros para " + pais[posi] + " son:\nEmpleo: " + trabajo[posi] + "\nDinero: " + dinero[posi] 
					+ "\nConocimiento: " + conoci[posi] + "\nTiempo: " + tiempo[posi] + "\nPoder: " + poder[posi] + "\nSalud: " + salud[posi]);
		}
		
	}
	
	//Un pequeño comentario, esta función ha sido creada de manera en la que nos facilite 
	//el modificar cualquier parametro dentro del procedimento de modificar datos
	public static double modificarCualquiera(Scanner sc, double dato) { 
		double introdu;
		do {
			System.out.println("A cuánto desearía modificar el dato? (Introducir número válido entre 0-100): ");
			introdu = Double.parseDouble(sc.nextLine());
			if(introdu < 0 || introdu > 100) {
				System.out.println("Error: el valor introducido no es válido.");
			}
		} while(introdu < 0 || introdu > 100);
		return introdu;
	}
	/*
	 *5. opción del menú "5. Guardar datos en fichero". Será necesario crear un PROCEDIMIENTO para grabar los datos contenidos en el array
	 *en el fichero (1,25p.).  Será necesario grabar el registro de cabecera al inicio del fichero (0,25p.)
	 *y mostrar mensaje de OK o Error en caso del resultado de la ejecución (caso OK: "Los datos se han guardado correctamente en el fichero."
	 *(0,25p.), caso NO OK: "Error: Los datos no se han guardado en el fichero." (0,25p.).
	 */
	
	public static void guardarCambios(Scanner sc, int cantidad, String[] pais, String[] ubi,
			double[] trabajo, double[] dinero, double[] conoci, double[] tiempo, double[] poder, double[] salud, File file) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			String titulo = "Pais;Ubicacion;Trabajo;Dinero;Conocimiento;Tiempo;Poder;Salud";
			pw.println(titulo);
			
			for(int i = 0; i < cantidad; i++) {
				pw.println(pais[i] + ";" + ubi[i] + ";" + String.format(Locale.US, "%.2f", trabajo[i]) + ";" + String.format(Locale.US, "%.2f", dinero[i]) +
						";" + String.format(Locale.US, "%.2f", conoci[i]) + ";" + String.format(Locale.US, "%.2f", tiempo[i]) + ";" + 
						String.format(Locale.US, "%.2f", poder[i]) + ";" + String.format(Locale.US, "%.2f", salud[i]));
			}
			pw.close();
			System.out.println("Guardando los datos...");
			System.out.println("OK: Los datos se han guardado correctamente en el fichero...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error: Los datos no se han guardado en el fichero.");
		}
	}
	/*
	 * 6. opción del menú "6. Países con menor igualdad". Será necesario crear un PROCEDIMIENTO que calcule 
	 * los tres países con menor igualdad (0,75p.) y mostrar el siguiente mensaje "Los tres países con menor igualdad son: X, Y y Z"(0,25p.). 
	 */
	public static void menorIgualdad(Scanner sc, int cantidad, String[] pais, String[] ubi,
			double[] trabajo, double[] dinero, double[] conoci, double[] tiempo, double[] poder, double[] salud) {
		double[] aMedias = new double[cantidad];
		int pais1 = -1;
		int pais2 = -1;
		int pais3 = -1;
		for(int i = 0; i < cantidad; i++) {
			aMedias[i] = (trabajo[i] + dinero[i] + conoci[i] + tiempo[i] + poder[i] + salud[i])/6;
		}
		Arrays.sort(aMedias); //esto se usa para ordenar
		for(int i = 0; i < cantidad; i++) {
			if(aMedias[i] == (trabajo[0] + dinero[0] + conoci[0] + tiempo[0] + poder[0] + salud[0])/6) {
				pais1 = i;
			} else if(aMedias[i] == (trabajo[1] + dinero[1] + conoci[1] + tiempo[1] + poder[1] + salud[1])/6) {
				pais2 = i;
			} else if(aMedias[i] == (trabajo[2] + dinero[2] + conoci[2] + tiempo[2] + poder[2] + salud[2])/6) {
				pais3 = i;
			}
		}
		if(pais1 == -1 || pais2 == -1 || pais3 == -1) {
			System.out.println("Ha ocurrido algun problema [!]");
		}
		System.out.println("Los paises con mayor desigualdad son: \n" + pais[pais1] + ": " + String.format(Locale.US, "%.2f", aMedias[0]) + "\n" + pais[pais2] + ": " + 
		String.format(Locale.US, "%.2f", aMedias[1]) + "\n" + pais[pais3] + ": " + String.format(Locale.US, "%.2f", aMedias[2]));
	}
	/*
	 * Esto es para probar a aver si hace el commit y eso
	 */
}