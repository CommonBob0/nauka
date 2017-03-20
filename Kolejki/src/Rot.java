import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rot extends Kontener {

	int licznik_czasu = 0;
	int mozliwosc_wykonywania = 0;
	int kwant = 1;

	Proces[] temp = new Proces[lista_procesow.size()];;

	public Rot() {
		Kontener.sredniaRot = 0;
		wykonuj();
		srednia();
	}

	private void wykonuj() {

		for (int i = 0; i < lista_procesow.size(); i++) {
			temp[i] = new Proces(1, 1, 1);
			temp[i].moment_zgloszenia = lista_procesow.get(i).moment_zgloszenia;
			temp[i].dlugosc_fazy = lista_procesow.get(i).dlugosc_fazy;
			temp[i].czas_oczekiwania = 0;
			temp[i].numer = i + 1;
		}

		List<Proces> kolejka = new ArrayList<Proces>();
		kolejka.add(temp[0]);

		// Wstepne ustawienia
		int id = 0;
		int ilosc_kwantow = 0;
		int[] czas_wykonywania = new int[temp.length];
		int k = 0;
		boolean czyNowe = true;
		boolean czyJestProces = true;
		licznik_czasu = kolejka.get(0).moment_zgloszenia;
		for(int i = 1; i < temp.length; i++){
			kolejka.add(temp[i]);
		}
		
		while (czyJestProces) {
			// kolejka.remove(k);
			
		
			if (kolejka.get(id).dlugosc_fazy > kwant) {

				kolejka.get(id).dlugosc_fazy -= kwant;
				licznik_czasu += kwant;
				czas_wykonywania[id] += kwant;
				if (czyNowe) {
					
						if (licznik_czasu < kolejka.get(id+1).moment_zgloszenia) {
							id--;
						}

					}
				
				

			} else if (kolejka.get(id).dlugosc_fazy != 0) {

				kolejka.get(id).czas_oczekiwania = licznik_czasu - kolejka.get(id).moment_zgloszenia
						- czas_wykonywania[id];

				licznik_czasu += kolejka.get(id).dlugosc_fazy;
				
				kolejka.get(id).dlugosc_fazy = 0;
				

				if (czyNowe) {
					
						if (licznik_czasu < kolejka.get(id+1).moment_zgloszenia) {
							//licznik_czasu = kolejka.get(id+1).moment_zgloszenia;
							id--;
						}
					}

			}
			
			++id;
			
			
			if (id >= temp.length-1){
				czyNowe = false;
			}
			
			if (id >= temp.length) {
				id = 0;
				
			}
			

			// Wychodzenie z petli
			for (int i = 0; i < temp.length; i++) {
				if (kolejka.get(i).dlugosc_fazy > 0) {
					czyJestProces = true;
					i = kolejka.size();
				} else
					czyJestProces = false;
			}
		}
		
		for (int i = 0; i < temp.length; i++) {

			lista_procesow.get(i).czas_oczekiwania = kolejka.get(i).czas_oczekiwania;
			// System.out.println(temp[i].czas_oczekiwania);
		}
	}

	private void srednia() {

		double suma = 0;
		// Kontener.srednia = 0;
		// System.out.println(" " + suma);
		for (Proces temp : lista_procesow) {
			suma += temp.czas_oczekiwania;
		}
		Kontener.sredniaRot = suma / lista_procesow.size();
		Kontener.suma_Rot += sredniaRot;
	}

}
