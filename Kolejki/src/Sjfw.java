import java.util.Collections;
import java.util.Comparator;

public class Sjfw extends Kontener{

	int licznik_czasu = 0;
	int mozliwosc_wykonywania = 0;
	
	public Sjfw() {
		wykonuj();
		srednia();
	}


	private void wykonuj() {
		
		sortuj();
		for (int i = 0; i < lista_procesow.size(); i++) {
			//System.out.println("  " + licznik_czasu);	
			
			if(i>0){
				 // odleglosc pomiedzy dwoma zgloszeniami procesow
				int odleglosc = lista_procesow.get(i).moment_zgloszenia - lista_procesow.get(i-1).moment_zgloszenia;
				
				// w ktorej jednostce czasu procesor jest wolny
				mozliwosc_wykonywania = lista_procesow.get(i).moment_zgloszenia + lista_procesow.get(i-1).czas_oczekiwania + lista_procesow.get(i-1).dlugosc_fazy - odleglosc;
				//System.out.println("  " + mozliwosc_wykonywania);
				
				
				if(lista_procesow.get(i).moment_zgloszenia < mozliwosc_wykonywania){
					
					//Zmiany procesow
					if(lista_procesow.get(i).dlugosc_fazy < lista_procesow.get(i-1).dlugosc_fazy - odleglosc){
						lista_procesow.get(i-1).dlugosc_fazy -= odleglosc;
						lista_procesow.get(i).czas_oczekiwania += odleglosc;
						Collections.swap(lista_procesow, i-1, i);
						sortuj();
						i--;
					}
					else {
						// jak w SJFb
						lista_procesow.get(i).czas_oczekiwania = mozliwosc_wykonywania - lista_procesow.get(i).moment_zgloszenia;
					}
					
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
	

	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	
	private void sortuj() {
		Collections.sort(lista_procesow, new Comparator() {

	        public int compare(Object p1, Object p2) {

	            Integer x1 = ((Proces) p1).moment_zgloszenia;
	            Integer x2 = ((Proces) p2).moment_zgloszenia;
	            int sComp = x1 - x2;

	            if (sComp != 0) {
	               return sComp;
	            } else {
	               Integer y1 = ((Proces) p1).dlugosc_fazy;
	               Integer y2 = ((Proces) p2).dlugosc_fazy;
	               return y1.compareTo(y2);
	            }
	    }});
		
	}


	private void srednia() {
		
		double suma = 0;
		//Kontener.srednia = 0;
		//System.out.println("  " + suma);
		for(Proces temp: lista_procesow){
			suma += temp.czas_oczekiwania;
		}
		Kontener.srednia = suma / lista_procesow.size();
		Kontener.suma_Sjfw += srednia;
	}
		
}
