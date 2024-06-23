package Classes_extras;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumerosAlearios {

    public static int GerarNumeroAleatorio(){
        Random random = new Random();
        Set<Integer> lista = new HashSet<>();

        int numeroAleatorio;

        // Gerar um número aleatório que não está na lista
        do {
            numeroAleatorio = random.nextInt(1000) + 1;
        } while (!lista.add(numeroAleatorio)); // Adiciona o número à lista e continua se já estiver presente

        return numeroAleatorio;


    }

}
