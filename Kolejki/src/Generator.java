import java.util.Random;

public class Generator {
	Proces proces;
	Random losuj = new Random();
	static int num = 0;
	public Generator(){
		//proces.dlugosc_fazy = 5;
		proces = new Proces();
		//proces.numer = ++num;
		proces.dlugosc_fazy = losuj.nextInt(10)+1;
		proces.czas_oczekiwania = 0;
		proces.moment_zgloszenia = losuj.nextInt(10)+1;
	}
}
