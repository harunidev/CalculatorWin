import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hesap Makinesi Uygulamasına Hoş Geldiniz!");

        while (true) {
            System.out.println("\nYapmak istediğiniz işlemi seçin:");
            System.out.println("1. Toplama");
            System.out.println("2. Çıkarma");
            System.out.println("3. Çarpma");
            System.out.println("4. Bölme");
            System.out.println("5. Mod Alma");
            System.out.println("6. Üs Alma");
            System.out.println("7. Çıkış");

            System.out.print("Seçiminizi yapın (1-7): ");
            int choice = scanner.nextInt();

            if (choice == 7) {
                System.out.println("Hesap Makinesi Uygulamasından Çıkılıyor. Hoşçakalın!");
                break;
            }

            if (choice < 1 || choice > 7) {
                System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                continue;
            }

            System.out.print("Birinci sayıyı girin: ");
            double num1 = scanner.nextDouble();

            System.out.print("İkinci sayıyı girin: ");
            double num2 = scanner.nextDouble();

            double result = 0;

            switch (choice) {
                case 1:
                    result = num1 + num2;
                    System.out.println("Sonuç: " + result);
                    break;
                case 2:
                    result = num1 - num2;
                    System.out.println("Sonuç: " + result);
                    break;
                case 3:
                    result = num1 * num2;
                    System.out.println("Sonuç: " + result);
                    break;
                case 4:
                    if (num2 == 0) {
                        System.out.println("Hata: Bir sayı sıfıra bölünemez!");
                    } else {
                        result = num1 / num2;
                        System.out.println("Sonuç: " + result);
                    }
                    break;
                case 5:
                    result = num1 % num2;
                    System.out.println("Sonuç: " + result);
                    break;
                case 6:
                    result = Math.pow(num1, num2);
                    System.out.println("Sonuç: " + result);
                    break;
            }

            System.out.print("\nTekrar işlem yapmak ister misiniz? (Evet/Hayır): ");
            scanner.nextLine(); // Boş satırı temizle
            String continueChoice = scanner.nextLine().trim().toLowerCase();

            if (continueChoice.equals("hayır")) {
                System.out.println("Hesap Makinesi Uygulamasından Çıkılıyor. Hoşçakalın!");
                break;
            } else if (!continueChoice.equals("evet")) {
                System.out.println("Geçersiz giriş! Program sonlandırılıyor.");
                break;
            }
        }

        scanner.close();
    }
}