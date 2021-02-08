import java.util.Scanner;

public class StaticStack {

	private int capacity; //capacidad total(maxima)
	private int size; //numero de elementos
	private Object[] data; //los datos son de tipo object

	//CONSTRUCTOR CAPACIDAD
	public StaticStack (int capacity) {
		this.capacity = capacity;
		data = new Object [capacity]; //como mis datos son de tipo object lo inicializo
		size=0;
	}

	//comprueba si esta vacia
	boolean empty() {
		return size ==0;
	}
	/**
	 * apilar
	 * @param obj
	 * @return falso si esta llena
	 */
	//apilar
	boolean push(Object obj) {
		boolean bRet= true; //bRet es boolean return
		if(size < capacity) {
			data[size] = obj;
			size++;
		}else {
			System.out.println("llena");
			bRet = false;
		}
		return bRet;
	}

	//desapilar te dice el ultimo valor y te lo elimina
	Object pop() {
		Object oRet = ""; //para que cuando desapile la pila completa no me salga null en aquellos espacios donde la pila no este rellena pongo ""
		if(!empty()) {           //comprueba si esta vacia
			oRet = data [size-1];
			data[size-1]=null;
			size--;
		}
		//Comentamos el else porque en este caso no quiero que me devuelva el texto cuando la pila este vacia
		//		else {
		//			System.out.println("no puede desapilar, la pila esta vacia");
		//		}
		return oRet;
	}

	Object[] getData() {         
		return data;		
	}

	//visualizar ultimo elemento NO LO ELIMINA, SOLO LO VISUALIZA
	Object peek(char exp) {
		Object oRet = null;
		if(!empty())
			oRet = data [size-1];

		return oRet;
	}

	//obtener el numero real de elementos
	int size() {
		return size;
	}

	//obtener capacidad maxima
	int capacity() {
		return capacity;
	}

	//busca primer elemento
	public int search(Object obj) {
		int pos = -1;
		for (int i=0; (i<this.size) && pos ==-1; i++ )
			//if(data[i] == obj)
			if(data[i].equals(obj))
				pos=i;
		return pos;
	}

	public static void main (String[] args) {

		System.out.println("Este programa consiste en introducir por consola una frase/documento\n"
				+ "y que según le vayas pidiendo por teclado las diferenctes opciones que hay (borrar/agregar),\n"
				+ "te indique la letra que borra o agrega. Por último, existe la opcion de que te muestre el resultado\n"
				+ "final del texto con dichas modificaciones");
		
		System.out.println();

		Scanner sc = new Scanner(System.in); 
		System.out.println("Dime una frase");
		String input = sc.nextLine();

		//creamos las tres pilas necesarias para nuestro programa
		StaticStack ss = new StaticStack(input.length());
		StaticStack ss1 = new StaticStack(input.length());
		StaticStack ss2 = new StaticStack(input.length());

		//introduce en cada posicion del array la letra correspondiente
		for(int i = 0; i<input.length(); i++) {
			ss.push(input.charAt(i));
		}

		boolean cerrado = false;
		while (cerrado == false) {
			System.out.println("[1[borrar  [2] Agregar [3] salir del programa y ver texto final" );
			System.out.println("Dime un numero");
			int pila = sc.nextInt();

			switch (pila){
			case 1 :
				//lo que hace esta opcion es desapilar el ultimo elemento de nuestra pila e introducirlo en una nueva pila
				//la nueva pila en la que lo introduzcamos sera aquella que guarde por orden todas las letras borradas
				if(ss.size >0) {
					ss1.push(ss.pop());
					char exp = 0;
					System.out.println("La letra que has eliminado es: " + ss1.peek(exp));
				}else {
					System.out.println("no puedes borrar mas elementos");
				}
				cerrado = false;
				break;
			case 2 : 
				//lo que hace esta opcion es devolver la ultima letra borrada, para ello tenemos que desapilarlo de la pila donde 
				//introduciamos todos los valores que habiamos borrado y volviendolos a agregar en la pila del texto
				if(ss.size != ss.capacity) {
					ss.push(ss1.pop());
					char exp1 = 0;
					System.out.println("La letra que vas a añadir es: " + ss.peek(exp1));
				}else
					System.out.println("para poder introducir valores tienes que haberlos borrado antes");
				cerrado = false;
				break;
			case 3:
				//lo que hace esta opcion es imprimirme el resultado final del texto,para que el texto se imprima correctamente tenemos
				//que cambiar el orden de las letras de la pila, es decir, introducir toda la pila en una nueva pila e imprimir dicha pila nueva
				if(ss.size > 0) {
					System.out.println("El texto final es: ");
					for(int i = 0 ; i<input.length();i++) {
						ss2.push(ss.pop());
					}
					for(int i = 0 ; i<input.length();i++) {
						System.out.print(ss2.pop());
					}
				}else {
					System.out.println("El texto se ha borrado al completo");
				}

				cerrado = true;

			}


		}
	}


}





