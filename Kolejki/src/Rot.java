import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rot extends Kontener {
	
	int licznik_czasu = 0;
	int mozliwosc_wykonywania = 0;
	int kwant = 3;
	
	Proces[] temp = new Proces[lista_procesow.size()];;
	
	
	
	public Rot() {
		wykonuj();
		srednia();
	}


	private void wykonuj() {
		
		
		for(int i = 0; i < lista_procesow.size(); i++){
			temp[i] = new Proces(1, 1, 1);
			temp[i].moment_zgloszenia = lista_procesow.get(i).moment_zgloszenia;
			temp[i].dlugosc_fazy = lista_procesow.get(i).dlugosc_fazy;
			temp[i].czas_oczekiwania = 0;
			temp[i].numer = i+1;
		}
		
		
		
		List<Proces> kolejka = new ArrayList<Proces>();
		kolejka.add(temp[0]);
		
		// Wstepne ustawienia
		int k = 0;
		int id = 0;
		int ilosc_kwantow = 0;
		int l = 0;
		boolean czyDoszedl = true;
		
		licznik_czasu = kolejka.get(0).moment_zgloszenia;
		
		while(!kolejka.isEmpty()){
			// kolejka.remove(k);
			System.out.println("licznik : " +licznik_czasu);
			if(kolejka.get(k).dlugosc_fazy > kwant){
				
				kolejka.get(k).dlugosc_fazy -= kwant;
				licznik_czasu += kwant;
				ilosc_kwantow++;
				if(czyDoszedl){
					for(int i = l+1; i < temp.length; i++){
						if(licznik_czasu >= temp[i].moment_zgloszenia){
							kolejka.add(temp[i]);
							l++;
						}
							temp[i].czas_oczekiwania += licznik_czasu - temp[i].moment_zgloszenia;
						
						
					}
				}
				else{
					for(int i = 1; i < kolejka.size(); i ++){
						kolejka.get(i).czas_oczekiwania += kwant;
					}
					kolejka.get(0).czas_oczekiwania -= kwant;
				}
				
				Collections.rotate(kolejka, -1);
			
				//System.out.println();System.out.println();
				
				// tester
				for(int i = 0; i< kolejka.size(); i++){
					
					//System.out.print(kolejka.get(i).dlugosc_fazy + "   ");
					
				}
				
			}
			else{
				
				//System.out.println(lista_procesow.get(id).czas_oczekiwania);
				temp[id].czas_oczekiwania += licznik_czasu - kolejka.get(k).moment_zgloszenia + kolejka.get(0).czas_oczekiwania;
				licznik_czasu += kolejka.get(k).dlugosc_fazy;
				System.out.println("Jestem tu : " + l);
				System.out.println("licznik : " +licznik_czasu);
				kolejka.get(k).dlugosc_fazy = 0;
				System.out.println("Kolejka wielkosc :" + kolejka.size());
				kolejka.remove(k);
				System.out.println("Kolejka wielkosc :" + kolejka.size());
				
				if(czyDoszedl){
					for(int i = l+1; i < temp.length; i++){
						if(licznik_czasu >= temp[i].moment_zgloszenia){
							kolejka.add(temp[i]);
							l++;
						}
						
						if(kolejka.isEmpty()){
							kolejka.add(temp[i]);
							licznik_czasu = kolejka.get(k).moment_zgloszenia;
							i = temp.length+1;
						}
					}
					System.out.println("Kolejka wielkosc :" + kolejka.size());
				}
			}
			System.out.println(id);
			++id;
			
			
			if(id >= temp.length){
				id = 0;
				//System.out.println(numer_petli);
				czyDoszedl = false;
			}
				
			
			
		}
		System.out.println();System.out.println();
		for(int i = 0; i< temp.length; i++){
			
			lista_procesow.get(i).czas_oczekiwania = temp[i].czas_oczekiwania; 
			//System.out.println(temp[i].czas_oczekiwania);
		}
	}
	
	private void srednia() {
		
		double suma = 0;
		//Kontener.srednia = 0;
		//System.out.println("  " + suma);
		for(Proces temp: lista_procesow){
			suma += temp.czas_oczekiwania;
		}
		Kontener.srednia = suma / lista_procesow.size();
		Kontener.suma_Rot += srednia;
	}


}
