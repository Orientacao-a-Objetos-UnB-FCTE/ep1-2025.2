import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner painel = new Scanner(System.in);

        List<Pacientes> pacientes = new ArrayList<>();
        Pacientes p0 = new Pacientes("Leon Scott Kennedy","12344233125", (byte) 27, "RE007");
        Pacientes p1 = new Pacientes("Claire Redfield","32719827121", (byte) 24, "RE010");
        Pacientes p2 = new Pacientes("Ellie Williams","84938173956", (byte) 14, "TLOU1");
        Pacientes p3 = new Pacientes("Victor Sullivan","06183162862", (byte) 66, "UNC02");
        Pacientes p4 = new Pacientes("Johnny Cage","64837162964", (byte) 34, "MK042");
        Pacientes p5 = new Pacientes("John Marston","76481736121", (byte) 31, "RDR01");
        Pacientes p6 = new Pacientes("Alan Wake","84917365174", (byte) 42, "AW322");
        Pacientes p7 = new Pacientes("Arthur Morgan","98572764817", (byte) 36, "RDR02");
        Pacientes p8 = new Pacientes("Ezio Auditore","26381749826", (byte) 29, "ASC01");
        Pacientes p9 = new Pacientes("Elena Fisher","02985716234", (byte) 30, "UNC86");
        Pacientes p10 = new Pacientes("Triss Merigold","44728163455", (byte) 35, "TW333");
        Pacientes p11 = new Pacientes("Cranky Kong","54327714072", (byte) 81, "DK001");

         pacientes.add(p0); pacientes.add(p1); pacientes.add(p2); pacientes.add(p3); pacientes.add(p4); pacientes.add(p5);
         pacientes.add(p6); pacientes.add(p7); pacientes.add(p8); pacientes.add(p9); pacientes.add(p10); pacientes.add(p11);

        List<Medicos> medicos = new ArrayList<>();
        Medicos m0 = new Medicos("Dra. Jill Valentine", "DF001", "Clínico-Geral", "R$250,00", "Segunda");
        Medicos m1 = new Medicos("Dr. Joel Miller", "DF002", "Pediatra", "R$150,00", "Segunda");
        Medicos m2 = new Medicos("Dr. Nathan Drake", "DF003", "Cardiologista", "R$80,00", "Segunda");
        Medicos m3 = new Medicos("Dra. Lara Croft", "DF004", "Pneumologista", "R$125,00", "Segunda");
        Medicos m4 = new Medicos("Dr. Geralt de Rívia", "DF005", "Infectologista", "R$110,00", "Segunda");
        Medicos m5 = new Medicos("Dr. Gordon Freeman", "DF006", "Geriatra", "R", "Segunda");
        Medicos m6 = new Medicos("Dr. James Sunderland", "DF007", "Psiquiatra", "R$100,00", "Segunda");

         medicos.add(m0); medicos.add(m1); medicos.add(m2); medicos.add(m3); medicos.add(m4); medicos.add(m5); medicos.add(m6);


    }

}
