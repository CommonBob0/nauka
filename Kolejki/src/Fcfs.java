

public class Fcfs extends Kontener {
	
	int licznik_czasu = 0;
	int mozliwosc_wykonywania = 0;
	
	public Fcfs() {
		wykonuj();
		srednia();
	}


	private void wykonuj() {
		for (int i = 0; i < lista_procesow.size(); i++) {
			//System.out.println("  " + licznik_czasu);
			
			if(i>0){
				 // odleglosc pomiedzy dwoma zgloszeniami procesow
				int odleglosc = lista_procesow.get(i).moment_zgloszenia - lista_procesow.get(i-1).moment_zgloszenia;
				
				// w ktorej jednostce czasu procesor jest wolny
				mozliwosc_wykonywania = lista_procesow.get(i).moment_zgloszenia + lista_procesow.get(i-1).czas_oczekiwania + lista_procesow.get(i-1).dlugosc_fazy - odleglosc;
				//System.out.println("  " + mozliwosc_wykonywania);
				
				if(lista_procesow.get(i).moment_zgloszenia < mozliwosc_wykonywania){
					lista_procesow.get(i).czas_oczekiwania = mozliwosc_wykonywania - lista_procesow.get(i).moment_zgloszenia;
				}	
				else{
					// nowy ciag
					lista_procesow.get(i).czas_oczekiwania = 0;
				}
					
			}
			else{
				mozliwosc_wykonywania = lista_procesow.get(i).dlugosc_fazy + lista_procesow.get(i).moment_zgloszenia;
				lista_procesow.get(i).czas_oczekiwania = 0;
			}
			
			//System.out.println("  " + mozliwosc_wykonywania);
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
		Kontener.suma_Fcfs += srednia;
	}


}
