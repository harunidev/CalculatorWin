import java.util.Scanner;

public class Calculator {

    // Ana giriş noktası
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayWelcomeMessage(); // Hoş geldiniz mesajı

        while (true) {
            displayMenu(); // Menü seçeneklerini göster

            int choice = getUserChoice(scanner); // Kullanıcının seçimini al

            if (isExitChoice(choice)) {
                displayExitMessage();
                break;
            }

            if (isInvalidChoice(choice)) {
                displayInvalidChoiceMessage();
                continue;
            }

            double num1 = getNumberFromUser(scanner, "Birinci sayıyı girin: ");
            double num2 = getNumberFromUser(scanner, "İkinci sayıyı girin: ");

            double result = performCalculation(choice, num1, num2);
            if (Double.isNaN(result)) {
                continue; // Geçersiz işlem durumunda döngüye devam et
            }

            displayResult(result);

            if (!askToContinue(scanner)) {
                displayExitMessage();
                break;
            }
        }

        scanner.close(); // Kaynakları serbest bırak
    }

    // Hoş geldiniz mesajı
    public static void displayWelcomeMessage() {
        System.out.println("Hesap Makinesi Uygulamasına Hoş Geldiniz!");
    }

    // Menü seçeneklerini göster
    public static void displayMenu() {
        System.out.println("\nYapmak istediğiniz işlemi seçin:");
        System.out.println("1. Toplama");
        System.out.println("2. Çıkarma");
        System.out.println("3. Çarpma");
        System.out.println("4. Bölme");
        System.out.println("5. Mod Alma");
        System.out.println("6. Üs Alma");
        System.out.println("7. Çıkış");
    }

    // Kullanıcının menü seçiminden sorumlu
    public static int getUserChoice(Scanner scanner) {
        System.out.print("Seçiminizi yapın (1-7): ");
        return scanner.nextInt();
    }

    // Çıkış seçimini kontrol et
    public static boolean isExitChoice(int choice) {
        return choice == 7;
    }

    // Geçersiz seçim kontrolü
    public static boolean isInvalidChoice(int choice) {
        return choice < 1 || choice > 7;
    }

    // Geçersiz seçim mesajı
    public static void displayInvalidChoiceMessage() {
        System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
    }

    // Kullanıcıdan sayı alma
    public static double getNumberFromUser(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    // İşlem yapma
    public static double performCalculation(int choice, double num1, double num2) {
        double result = 0;

        switch (choice) {
            case 1:
                result = add(num1, num2);
                break;
            case 2:
                result = subtract(num1, num2);
                break;
            case 3:
                result = multiply(num1, num2);
                break;
            case 4:
                result = divide(num1, num2);
                break;
            case 5:
                result = modulus(num1, num2);
                break;
            case 6:
                result = power(num1, num2);
                break;
            default:
                System.out.println("Hata: Geçersiz işlem!");
                return Double.NaN;
        }

        return result;
    }

    // Sonucu ekrana yazdır
    public static void displayResult(double result) {
        System.out.println("Sonuç: " + result);
    }

    // Kullanıcıya devam etmek isteyip istemediğini sor
    public static boolean askToContinue(Scanner scanner) {
        System.out.print("\nTekrar işlem yapmak ister misiniz? (Evet/Hayır): ");
        scanner.nextLine(); // Satır sonunu temizle
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("evet")) {
            return true;
        } else if (choice.equals("hayır")) {
            return false;
        } else {
            System.out.println("Geçersiz giriş! Program sonlandırılıyor.");
            return false;
        }
    }

    // Çıkış mesajı
    public static void displayExitMessage() {
        System.out.println("Hesap Makinesi Uygulamasından Çıkılıyor. Hoşçakalın!");
    }

    // Toplama
    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    // Çıkarma
    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Çarpma
    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Bölme
    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Hata: Bir sayı sıfıra bölünemez!");
            return Double.NaN;
        }
        return num1 / num2;
    }

    // Mod alma
    public static double modulus(double num1, double num2) {
        return num1 % num2;
    }

    // Üs alma
    public static double power(double num1, double num2) {
        return Math.pow(num1, num2);
    }
}
