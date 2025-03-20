import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ManajemenBuku {
    public static void main(String[] args) {
        Scanner hehe = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("=== Daftar Buku ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan: ");
            pilihan = hehe.nextInt();
            hehe.nextLine();

            switch (pilihan) {
                case 1:
                    tambahBuku(hehe);
                    break;
                case 2:
                    tampilDaftarBuku(hehe);
                    break;
                case 3:
                    System.out.println("Anda telah keluar dari program");
                    break;
                default:
                    System.out.println("Pilihan tidak valid (1-3)");
            }
        } while (pilihan != 3);
    }

    private static void tambahBuku(Scanner hehe) {
        System.out.println();
        System.out.print("Judul: ");
        String judul = hehe.nextLine();
        System.out.print("Penulis: ");
        String penulis = hehe.nextLine();
        System.out.print("Tahun Terbit: ");
        int tahunTerbit = hehe.nextInt();
        hehe.nextLine();

        Buku buku = new Buku(judul, penulis, tahunTerbit);
        simpanBukuFile(buku);
        System.out.println("Buku berhasil ditambahkan!");
        System.out.println();
    }
    

   private static void tampilDaftarBuku(Scanner hehe) {
    try (BufferedReader reader = new BufferedReader(new FileReader("DaftarBuku.txt"))) {
        System.out.print("Daftar buku ringkas? (iya/tidak): ");
        boolean ringkas = hehe.nextLine().equalsIgnoreCase("iya");

        while (true) {
            String judul = reader.readLine();
            String penulis = reader.readLine();
            String tahunString = reader.readLine();

            
            if (judul == null || penulis == null || tahunString == null) {
                break;
            }

            
            judul = judul.trim();
            penulis = penulis.trim();
            tahunString = tahunString.trim();

            
            int tahunTerbit;
            try {
                tahunTerbit = Integer.parseInt(tahunString);
            } catch (NumberFormatException e) {
                System.out.println("Error: Tahun terbit tidak valid untuk buku \"" + judul + "\"");
                continue; 
            }

            if (ringkas) {
                System.out.println();
                System.out.println("==================================");
                System.out.println("Judul Buku: " + judul);
                System.out.println("==================================");
                System.out.println();
            } else {
                System.out.println();
                System.out.println("==================================");
                System.out.println("Judul Buku: " + judul);
                System.out.println("Penulis: " + penulis);
                System.out.println("Tahun Terbit: " + tahunTerbit);
                System.out.println("==================================");
                System.out.println();
            }
        }

    } catch (IOException e) {
        System.out.println("Gagal membaca file!");
    }
}



    private static void simpanBukuFile(Buku buku) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DaftarBuku.txt", true))) {
            buku.saveFile(writer);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file!");
        }
    }
}
