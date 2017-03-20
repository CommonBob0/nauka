import java.util.Collections;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Kontener kontener = null;
		int liczba_procesow = 6;
		int liczba_testow = 3;
		Kontener kon2 = null;

		for (int j = 0; j < liczba_testow; j++) // liczba prób
		{
			kontener = new Kontener();
			kontener.lista_procesow.clear();
			kontener.stworz(liczba_procesow);

			// sortowanie wedlug momentu_zgloszenia
			Collections.sort(Kontener.lista_procesow);

			// wygenerowane procesy po sortowaniu
			//kontener.wyswietl();
			System.out.println();
			System.out.println();
			// FCFS
			kontener.wybierz(Fcfs.class);
			//kontener.wyswietl();
			//System.out.println("Œrednia FCFS: " + Kontener.sredniaFcfs);
			//System.out.println();
			//System.out.println();

			// ROT
			kontener.wybierz(Rot.class);
			//kontener.wyswietl();
			//System.out.println();
			//System.out.println("Œrednia ROT: " + Kontener.srednia);
			//System.out.println();
			//System.out.println();
			
			// SJFb
			kontener.wybierz(Sjfb.class);
			//kontener.wyswietl();
			//System.out.println();
			//System.out.println("Œrednia SJFb: " + Kontener.srednia);
			//System.out.println();
			//System.out.println();

			// SJFw
			kontener.wybierz(Sjfw.class);
			//kontener.wyswietl();
			//System.out.println();
			//System.out.println("Œrednia SJFw: " + Kontener.srednia);
			//System.out.println();
			//System.out.println();

		}

		System.out.println();
		System.out.println();
		System.out.println("Œrednia g³owna FCFS:" + Kontener.suma_Fcfs / liczba_testow);
		System.out.println("Œrednia g³owna SJFb:" + Kontener.suma_Sjfb / liczba_testow);
		System.out.println("Œrednia g³owna SJFw:" + Kontener.suma_Sjfw / liczba_testow);
		System.out.println("Œrednia g³owna ROT:" + Kontener.suma_Rot / liczba_testow);
	}
}
