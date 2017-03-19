public class Proces implements Comparable<Proces> {
	int numer = 0;
	int dlugosc_fazy = 0;
	int moment_zgloszenia = 0;
	int czas_oczekiwania = 0;

	
	public Proces(){
		
	}
	public Proces(int dlugosc_fazy, int moment_zgloszenia, int czas_oczekiwania){
		numer++;
		this.dlugosc_fazy = dlugosc_fazy;
		this.moment_zgloszenia = moment_zgloszenia;
		this.czas_oczekiwania = czas_oczekiwania;
	}
	@Override
	public int compareTo(Proces o) {
		int porownanie = ((Proces)o).moment_zgloszenia;
		
		return this.moment_zgloszenia - porownanie;
	}
	
	// Gettery i settery
	public int getNumer() {
		return numer;
	}
	
	public void setNumer(int numer) {
		this.numer = numer;
	}
	
	public int getDlugosc_fazy() {
		return dlugosc_fazy;
	}
	
	public void setDlugosc_fazy(int dlugosc_fazy) {
		this.dlugosc_fazy = dlugosc_fazy;
	}
	public int getMoment_zgloszenia() {
		return moment_zgloszenia;
	}
	public void setMoment_zgloszenia(int moment_zgloszenia) {
		this.moment_zgloszenia = moment_zgloszenia;
	}
	public int getCzas_oczekiwania() {
		return czas_oczekiwania;
	}
	public void setCzas_oczekiwania(int czas_oczekiwania) {
		this.czas_oczekiwania = czas_oczekiwania;
	}
	
	
	
}
